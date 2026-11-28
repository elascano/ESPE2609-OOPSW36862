import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime
from model.contact import Contact

class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Add Contact")
        self.geometry("760x480")
        self.resizable(False, False)
        self._init_components()

    def _init_components(self):

        pnl_title = tk.Frame(self)
        pnl_title.pack(pady=(20, 15))
        tk.Label(pnl_title, text="ADD CONTACT", font=("Arial", 11, "bold")).pack()


        pnl_form = tk.Frame(self)
        pnl_form.pack(padx=25, pady=10, fill=tk.BOTH, expand=True)


        tk.Label(pnl_form, text="id:").grid(row=0, column=0, sticky="e", padx=8, pady=5)
        self.txt_id = tk.Entry(pnl_form, width=18)
        self.txt_id.grid(row=0, column=1, sticky="w", pady=5)

        tk.Label(pnl_form, text="Full Name:").grid(row=1, column=0, sticky="e", padx=8, pady=5)
        self.txt_full_name = tk.Entry(pnl_form, width=18)
        self.txt_full_name.grid(row=1, column=1, sticky="w", pady=5)

        tk.Label(pnl_form, text="Email:").grid(row=2, column=0, sticky="e", padx=8, pady=5)
        self.txt_email = tk.Entry(pnl_form, width=18)
        self.txt_email.grid(row=2, column=1, sticky="w", pady=5)

        tk.Label(pnl_form, text="Cell Phone Number:").grid(row=3, column=0, sticky="e", padx=8, pady=5)
        self.txt_cell_phone_number = tk.Entry(pnl_form, width=18)
        self.txt_cell_phone_number.grid(row=3, column=1, sticky="w", pady=5)

        tk.Label(pnl_form, text="Instagram:").grid(row=4, column=0, sticky="e", padx=8, pady=5)
        self.txt_instagram = tk.Entry(pnl_form, width=18)
        self.txt_instagram.grid(row=4, column=1, sticky="w", pady=5)

        tk.Label(pnl_form, text="Facebook:").grid(row=5, column=0, sticky="e", padx=8, pady=5)
        self.txt_facebook = tk.Entry(pnl_form, width=18)
        self.txt_facebook.grid(row=5, column=1, sticky="w", pady=5)

        tk.Label(pnl_form, text="Know:").grid(row=6, column=0, sticky="e", padx=8, pady=5)
        self.is_known_var = tk.BooleanVar()
        self.chk_is_known = tk.Checkbutton(pnl_form, text="Yes", variable=self.is_known_var)
        self.chk_is_known.grid(row=6, column=1, sticky="w", pady=5)

      
        tk.Label(pnl_form, text="Type:").grid(row=0, column=2, sticky="e", padx=(40, 8), pady=5)
        self.cmb_type = ttk.Combobox(
            pnl_form,
            values=["Friend", "Family", "Job Mate", "Team Mate", "High School", " "],
            state="readonly",
            width=18
        )
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=3, sticky="w", pady=5)

        tk.Label(pnl_form, text="Gender:").grid(row=1, column=2, sticky="e", padx=(40, 8), pady=5)
        self.cmb_gender = ttk.Combobox(pnl_form, values=["M", "F"], state="readonly", width=8)
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=3, sticky="w", pady=5)

        tk.Label(pnl_form, text="Hobbies:").grid(row=2, column=2, sticky="ne", padx=(40, 8), pady=5)
        hobbies_frame = tk.Frame(pnl_form)
        hobbies_frame.grid(row=2, column=3, rowspan=3, sticky="w", pady=5)

        scrollbar = tk.Scrollbar(hobbies_frame, orient=tk.VERTICAL)
        self.list_hobbies = tk.Listbox(
            hobbies_frame,
            selectmode=tk.MULTIPLE,
            height=6,
            width=20,
            yscrollcommand=scrollbar.set,
            exportselection=False
        )
        scrollbar.config(command=self.list_hobbies.yview)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.list_hobbies.pack(side=tk.LEFT)

        hobbies_items = [
            "Play the guitar", "Watch movies", "Play soccer",
            "Play Basketball", "Reading", "Cooking", "Swiming", " "
        ]
        for item in hobbies_items:
            self.list_hobbies.insert(tk.END, item)

        tk.Label(pnl_form, text="Birth Date:").grid(row=5, column=2, sticky="e", padx=(40, 8), pady=5)
        self.txt_birth_date = tk.Entry(pnl_form, width=21)
        self.txt_birth_date.grid(row=5, column=3, sticky="w", pady=5)

        tk.Label(pnl_form, text="Comments:").grid(row=6, column=2, sticky="ne", padx=(40, 8), pady=5)
        self.txt_comments = tk.Text(pnl_form, width=20, height=3)
        self.txt_comments.grid(row=6, column=3, sticky="w", pady=5)

        # Panel Botones
        pnl_buttons = tk.Frame(self)
        pnl_buttons.pack(pady=20)
        tk.Button(pnl_buttons, text="Save", width=10, command=self._btn_save_action).pack(side=tk.LEFT, padx=15)
        tk.Button(pnl_buttons, text="Cancel", width=10).pack(side=tk.LEFT, padx=15)
        tk.Button(pnl_buttons, text="System", width=10, command=self._btm_system_action).pack(side=tk.LEFT, padx=15)

    def _btn_save_action(self):
        try:
            contact_id = int(self.txt_id.get())
        except ValueError:
            messagebox.showerror("Error", "El campo 'id' debe ser un número entero.")
            return

        full_name = self.txt_full_name.get()
        email = self.txt_email.get()
        cell_phone_number = self.txt_cell_phone_number.get()
        instagram = self.txt_instagram.get()
        facebook = self.txt_facebook.get()
        is_known = self.is_known_var.get()
        contact_type = self.cmb_type.get()
        gender = self.cmb_gender.get()[0]
        hobbies = [self.list_hobbies.get(i) for i in self.list_hobbies.curselection()]
        birth_date = datetime.now()
        comments = self.txt_comments.get("1.0", tk.END).strip()

        contact = Contact(
            contact_id, full_name, email, cell_phone_number,
            instagram, facebook, is_known, contact_type,
            gender, hobbies, birth_date, comments
        )

        messagebox.showinfo("Contact Saved", str(contact))

    def _btm_system_action(self):
        from view.frm_contacts_book import FrmContactsBook
        self.destroy()
        frm_contacts_book = FrmContactsBook()
        frm_contacts_book.mainloop()