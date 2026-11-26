import { useState } from "react";
import type { ChangeEvent } from "react";
import { type Contact, contactToString } from "../model/Contact";
import "./FrmContacts.css";

const TYPES = ["Friend", "Gym", "High School"];
const GENDERS = ["female", "masculine"];
const HOBBIES = ["Play the Guitar", "Watch movies", "Play soccer", "Reading", "Swimming"];

interface FormState {
  id: string;
  fullName: string;
  email: string;
  cellPhoneNumber: string;
  instagram: string;
  facebook: string;
  isKnown: boolean;
  type: string;
  gender: string;
  hobbies: string[];
  birthDate: string; // dd/mm/yyyy
  comments: string;
}

const emptyForm: FormState = {
  id: "",
  fullName: "",
  email: "",
  cellPhoneNumber: "",
  instagram: "",
  facebook: "",
  isKnown: false,
  type: TYPES[0],
  gender: GENDERS[0],
  hobbies: [],
  birthDate: "",
  comments: "",
};

type Field = HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement;

// Convierte "dd/mm/yyyy" a Date (o null si no es válida)
function parseDate(text: string): Date | null {
  const m = /^(\d{2})\/(\d{2})\/(\d{4})$/.exec(text.trim());
  if (!m) return null;
  const [, d, mo, y] = m.map(Number);
  const date = new Date(y, mo - 1, d);
  return date.getDate() === d && date.getMonth() === mo - 1 ? date : null;
}

// Equivalente a la clase FrmContacts (JFrame)
export default function FrmContacts() {
  const [form, setForm] = useState<FormState>(emptyForm);
  const [error, setError] = useState("");

  // Un solo manejador para todos los campos de texto / combos
  const onChange = (key: keyof FormState) => (e: ChangeEvent<Field>) =>
    setForm({ ...form, [key]: e.target.value });

  // Equivalente a btnSaveActionPerformed
  const btnSaveClick = () => {
    const birthDate = parseDate(form.birthDate);
    if (!/^\d+$/.test(form.id) || !birthDate) {
      setError("El id debe ser un número y la fecha debe tener el formato dd/mm/yyyy.");
      return;
    }
    const contact: Contact = {
      id: Number(form.id),
      fullName: form.fullName,
      email: form.email,
      cellPhoneNumber: form.cellPhoneNumber,
      instagram: form.instagram,
      facebook: form.facebook,
      isKnown: form.isKnown,
      type: form.type,
      gender: form.gender === "female" ? "F" : "M",
      hobbies: form.hobbies,
      birthDate,
      comments: form.comments.trim(),
    };
    console.log(contactToString(contact));
    setError("");
    alert(`Contacto '${contact.fullName}' guardado.`);
  };

  return (
    <div className="frm">
      {/* pnlTitle */}
      <div className="pnl-title">CONTACT</div>

      {/* pnlData */}
      <div className="pnl-data">
        <div className="col">
          <label>id:</label>
          <input value={form.id} onChange={onChange("id")} />
          <label>Full Name:</label>
          <input value={form.fullName} onChange={onChange("fullName")} />
          <label>Email:</label>
          <input value={form.email} onChange={onChange("email")} />
          <label>Cell Phone Number:</label>
          <input value={form.cellPhoneNumber} onChange={onChange("cellPhoneNumber")} />
          <label>Instagram:</label>
          <input value={form.instagram} onChange={onChange("instagram")} />
          <label>Facebook:</label>
          <input value={form.facebook} onChange={onChange("facebook")} />
          <label>Known:</label>
          <label className="check">
            <input
              type="checkbox"
              checked={form.isKnown}
              onChange={(e) => setForm({ ...form, isKnown: e.target.checked })}
            />
            Yes
          </label>
        </div>

        <div className="col">
          <label>Type:</label>
          <select value={form.type} onChange={onChange("type")}>
            {TYPES.map((t) => (
              <option key={t}>{t}</option>
            ))}
          </select>
          <label>Gender:</label>
          <select value={form.gender} onChange={onChange("gender")}>
            {GENDERS.map((g) => (
              <option key={g}>{g}</option>
            ))}
          </select>
          <label>Hobbies:</label>
          <select
            multiple
            size={4}
            value={form.hobbies}
            onChange={(e) =>
              setForm({
                ...form,
                hobbies: Array.from(e.target.selectedOptions).map((o) => o.value),
              })
            }
          >
            {HOBBIES.map((h) => (
              <option key={h}>{h}</option>
            ))}
          </select>
          <label>Birth Date:</label>
          <input
            placeholder="dd/mm/yyyy"
            value={form.birthDate}
            onChange={onChange("birthDate")}
          />
          <label>Comments:</label>
          <textarea rows={3} value={form.comments} onChange={onChange("comments")} />
        </div>
      </div>

      {error && <p className="error">{error}</p>}

      {/* pnlBottons */}
      <div className="pnl-buttons">
        <button onClick={btnSaveClick}>Save</button>
        <button onClick={() => setForm(emptyForm)}>Clear</button>
      </div>
    </div>
  );
}
