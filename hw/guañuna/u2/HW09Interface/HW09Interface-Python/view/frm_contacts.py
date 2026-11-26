import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime

from model.contact import Contact


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("CONTACT")
        self.geometry("850x600")
        self.configure(padx=10, pady=10)

        self.build_title_panel()
        self.build_data_panel()
        self.build_buttons_panel()

    def build_title_panel(self):
        pnl_title = tk.Frame(self, bg="#e0e0e0", bd=1, relief="solid")
        pnl_title.pack(fill="x", pady=(0, 8))
        tk.Label(pnl_title, text="CONTACT", font=("Arial", 14, "bold"),
                 bg="#e0e0e0").pack(pady=8)

    def build_data_panel(self):
        pnl_data = tk.Frame(self, bd=1, relief="solid")
        pnl_data.pack(fill="both", expand=True, pady=(0, 8))

        left = tk.Frame(pnl_data)
        left.pack(side="left", fill="y", padx=10, pady=10)

        self.txt_id = self._add_field(left, "id:")
        self.txt_full_name = self._add_field(left, "Full Name:")
        self.txt_email = self._add_field(left, "Email:")
        self.txt_cellphone = self._add_field(left, "Cell Phone Number:")
        self.txt_instagram = self._add_field(left, "Instagram:")
        self.txt_facebook = self._add_field(left, "Facebook:")

        frame_known = tk.Frame(left)
        frame_known.pack(fill="x", pady=4)
        tk.Label(frame_known, text="Know:", width=18, anchor="e").pack(side="left")
        self.chk_is_known = tk.BooleanVar()
        tk.Checkbutton(frame_known, text="Yes",
                       variable=self.chk_is_known).pack(side="left", padx=(10, 0))

        center = tk.Frame(pnl_data)
        center.pack(side="left", fill="both", expand=True, padx=10, pady=10)

        tk.Label(center, text="Type:").grid(row=0, column=0, sticky="e", pady=4)
        self.cmb_type = ttk.Combobox(center, state="readonly",
                                     values=["Friend", "Family", "Job Mate",
                                             "Gym Mate", "Highschool"])
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=1, sticky="w", pady=4)

        tk.Label(center, text="Gender:").grid(row=1, column=0, sticky="e", pady=4)
        self.cmb_gender = ttk.Combobox(center, state="readonly",
                                       values=["Male", "Female"])
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=1, sticky="w", pady=4)

        tk.Label(center, text="Hobbies:").grid(row=2, column=0, sticky="ne", pady=4)
        self.lst_hobbies = tk.Listbox(center, height=6, exportselection=False,
                                      selectmode="multiple")
        for h in ["Play the guitar", "Watch movies", "Play soccer",
                  "Play Basketball", "Reading", "Cooking", "Swimming", "Running"]:
            self.lst_hobbies.insert("end", h)
        self.lst_hobbies.grid(row=2, column=1, sticky="w", pady=4)

        tk.Label(center, text="Birth Date:").grid(row=3, column=0, sticky="e", pady=4)
        self.txt_birth_date = tk.Entry(center, width=30)
        self.txt_birth_date.grid(row=3, column=1, sticky="w", pady=4)

        tk.Label(center, text="Comments:").grid(row=4, column=0, sticky="ne", pady=4)
        self.txt_comments = tk.Text(center, width=30, height=5)
        self.txt_comments.grid(row=4, column=1, sticky="w", pady=4)

    def _add_field(self, parent, label_text):
        frame = tk.Frame(parent)
        frame.pack(fill="x", pady=4)
        tk.Label(frame, text=label_text, width=18, anchor="e").pack(side="left")
        entry = tk.Entry(frame, width=25)
        entry.pack(side="left", padx=(10, 0))
        return entry

    def build_buttons_panel(self):
        pnl_buttons = tk.Frame(self, bd=1, relief="solid", height=60)
        pnl_buttons.pack(fill="x")
        tk.Button(pnl_buttons, text="Guardar", width=12,
                  command=self.save_contact).pack(side="left", padx=10, pady=10)
        tk.Button(pnl_buttons, text="Limpiar", width=12,
                  command=self.clear_form).pack(side="left", padx=10, pady=10)
        tk.Button(pnl_buttons, text="Salir", width=12,
                  command=self.destroy).pack(side="right", padx=10, pady=10)

    def save_contact(self):
        try:
            hobbies = [self.lst_hobbies.get(i)
                       for i in self.lst_hobbies.curselection()]
            birth = None
            if self.txt_birth_date.get().strip():
                birth = datetime.strptime(self.txt_birth_date.get().strip(),
                                          "%Y-%m-%d").date()
            contact = Contact(
                id=int(self.txt_id.get()),
                full_name=self.txt_full_name.get(),
                email=self.txt_email.get(),
                cell_phone_number=self.txt_cellphone.get(),
                instagram=self.txt_instagram.get(),
                facebook=self.txt_facebook.get(),
                is_known=self.chk_is_known.get(),
                type=self.cmb_type.get(),
                gender=self.cmb_gender.get(),
                hobbies=hobbies,
                birth_date=birth,
                comments=self.txt_comments.get("1.0", "end").strip()
            )
            messagebox.showinfo("Contacto guardado", str(contact))
        except ValueError:
            messagebox.showerror("Error", "El id debe ser numérico y la fecha "
                                          "debe tener formato YYYY-MM-DD.")

    def clear_form(self):
        for entry in (self.txt_id, self.txt_full_name, self.txt_email,
                      self.txt_cellphone, self.txt_instagram, self.txt_facebook,
                      self.txt_birth_date):
            entry.delete(0, "end")
        self.txt_comments.delete("1.0", "end")
        self.chk_is_known.set(False)
        self.cmb_type.current(0)
        self.cmb_gender.current(0)
        self.lst_hobbies.selection_clear(0, "end")