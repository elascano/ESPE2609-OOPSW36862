import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime

from ec.edu.espe.contactsbook.model.contact import Contact


# Equivalente a ec.edu.espe.contactsbook.view.FrmContacts (JFrame)
class FrmContacts(tk.Tk):
    TYPES = ["Friend", "Gym", "High School"]
    GENDERS = ["female", "masculine"]
    HOBBIES = ["Play the Guitar", "Watch movies", "Play soccer", "Reading", "Swimming"]

    def __init__(self):
        super().__init__()
        self.title("Contacts Book")
        self.resizable(False, False)
        self.init_components()

    # Equivalente a initComponents()
    def init_components(self):
        # ----- pnlTitle -----
        pnl_title = ttk.Frame(self)
        pnl_title.pack(fill="x", pady=(20, 10))
        ttk.Label(pnl_title, text="CONTACT").pack()

        # ----- pnlData -----
        pnl_data = ttk.Frame(self)
        pnl_data.pack(padx=20, pady=10)

        left = ttk.Frame(pnl_data)
        left.grid(row=0, column=0, sticky="n", padx=(0, 30))
        right = ttk.Frame(pnl_data)
        right.grid(row=0, column=1, sticky="n")

        # Columna izquierda
        self.txt_id = self._row(left, 0, "id:")
        self.txt_full_name = self._row(left, 1, "Full Name:")
        self.txt_email = self._row(left, 2, "Email:")
        self.txt_cell_phone_number = self._row(left, 3, "Cell Phone Number:")
        self.txt_instagram = self._row(left, 4, "Instagram:")
        self.txt_facebook = self._row(left, 5, "Facebook:")

        ttk.Label(left, text="Known:").grid(row=6, column=0, sticky="e", padx=5, pady=6)
        self.var_known = tk.BooleanVar()
        self.chb_known = ttk.Checkbutton(left, text="Yes", variable=self.var_known)
        self.chb_known.grid(row=6, column=1, sticky="w", padx=5)

        # Columna derecha
        ttk.Label(right, text="Type:").grid(row=0, column=0, sticky="e", padx=5, pady=6)
        self.cmb_type = ttk.Combobox(right, values=self.TYPES, state="readonly", width=18)
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=1, sticky="w", padx=5)

        ttk.Label(right, text="Gender:").grid(row=1, column=0, sticky="e", padx=5, pady=6)
        self.cmb_gender = ttk.Combobox(right, values=self.GENDERS, state="readonly", width=18)
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=1, sticky="w", padx=5)

        ttk.Label(right, text="Hobbies:").grid(row=2, column=0, sticky="ne", padx=5, pady=6)
        frame_hobbies = ttk.Frame(right)  # equivalente a jScrollPane1
        frame_hobbies.grid(row=2, column=1, sticky="w", padx=5, pady=6)
        self.lst_hobbies = tk.Listbox(frame_hobbies, selectmode=tk.MULTIPLE, height=4,
                                      width=18, exportselection=False)
        for hobby in self.HOBBIES:
            self.lst_hobbies.insert(tk.END, hobby)
        scroll = ttk.Scrollbar(frame_hobbies, command=self.lst_hobbies.yview)
        self.lst_hobbies.config(yscrollcommand=scroll.set)
        self.lst_hobbies.pack(side="left")
        scroll.pack(side="left", fill="y")

        ttk.Label(right, text="Birth Date:").grid(row=3, column=0, sticky="e", padx=5, pady=6)
        self.txt_birth_date = ttk.Entry(right, width=20)
        self.txt_birth_date.grid(row=3, column=1, sticky="w", padx=5)
        ttk.Label(right, text="dd/mm/yyyy", foreground="gray").grid(row=4, column=1, sticky="w", padx=5)

        ttk.Label(right, text="Comments:").grid(row=5, column=0, sticky="ne", padx=5, pady=6)
        self.txt_comments = tk.Text(right, width=24, height=3)
        self.txt_comments.grid(row=5, column=1, sticky="w", padx=5, pady=6)

        # ----- pnlBottons -----
        pnl_buttons = ttk.Frame(self)
        pnl_buttons.pack(pady=(10, 20))
        ttk.Button(pnl_buttons, text="Save", command=self.btn_save_click).pack(side="left", padx=8)
        ttk.Button(pnl_buttons, text="Clear", command=self.btn_clear_click).pack(side="left", padx=8)

    def _row(self, parent, row, label):
        ttk.Label(parent, text=label).grid(row=row, column=0, sticky="e", padx=5, pady=6)
        entry = ttk.Entry(parent, width=20)
        entry.grid(row=row, column=1, sticky="w", padx=5)
        return entry

    # ----- Eventos (como los ActionListener de Java) -----
    def btn_save_click(self):
        try:
            contact = Contact(
                id=int(self.txt_id.get()),
                full_name=self.txt_full_name.get(),
                email=self.txt_email.get(),
                cell_phone_number=self.txt_cell_phone_number.get(),
                instagram=self.txt_instagram.get(),
                facebook=self.txt_facebook.get(),
                is_known=self.var_known.get(),
                type=self.cmb_type.get(),
                gender="F" if self.cmb_gender.get() == "female" else "M",
                hobbies=[self.lst_hobbies.get(i) for i in self.lst_hobbies.curselection()],
                birth_date=datetime.strptime(self.txt_birth_date.get().strip(), "%d/%m/%Y"),
                comments=self.txt_comments.get("1.0", tk.END).strip(),
            )
        except ValueError:
            messagebox.showerror("Error", "El id debe ser un número y la fecha debe tener el formato dd/mm/yyyy.")
            return
        print(contact)
        messagebox.showinfo("Saved", f"Contacto '{contact.full_name}' guardado.")

    def btn_clear_click(self):
        for txt in (self.txt_id, self.txt_full_name, self.txt_email, self.txt_cell_phone_number,
                    self.txt_instagram, self.txt_facebook, self.txt_birth_date):
            txt.delete(0, tk.END)
        self.var_known.set(False)
        self.cmb_type.current(0)
        self.cmb_gender.current(0)
        self.lst_hobbies.selection_clear(0, tk.END)
        self.txt_comments.delete("1.0", tk.END)
