import re
import tkinter as tk
from tkinter import ttk, messagebox
from datetime import date

from model.contact import Contact
from controller.contact_controller import ContactController


class ContactsBookApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts Book")
        self.geometry("680x440")
        self.resizable(False, False)

        self._create_menu_bar()

        self.container = tk.Frame(self)
        self.container.pack(fill="both", expand=True)

        self.show_main_menu()

    def _create_menu_bar(self):
        menu_bar = tk.Menu(self)

        mnu_system = tk.Menu(menu_bar, tearoff=0)
        mnu_system.add_command(label="Main Menu", command=self.show_main_menu)
        mnu_system.add_command(label="About", command=self.show_about)
        mnu_system.add_separator()
        mnu_system.add_command(label="Quit", command=self.quit)
        menu_bar.add_cascade(label="ContactsBook", menu=mnu_system)

        mnu_contacts = tk.Menu(menu_bar, tearoff=0)
        mnu_contacts.add_command(label="Add Contacts", command=self.show_add_contact)
        mnu_contacts.add_command(label="View Contacts", command=self.show_view_contacts)
        menu_bar.add_cascade(label="Contacts", menu=mnu_contacts)

        self.config(menu=menu_bar)

    def _clear_container(self):
        for widget in self.container.winfo_children():
            widget.destroy()

    # ---------------- MAIN MENU ----------------
    def show_main_menu(self):
        self._clear_container()
        self.title("Contacts Book - Menu")

        frame = tk.Frame(self.container, padx=20, pady=40)
        frame.pack(fill="both", expand=True)

        tk.Label(frame, text="CONTACTS BOOK SYSTEM", font=("Segoe UI", 16, "bold")).pack(pady=15)
        tk.Label(frame, text="Select an option from the menu or buttons below:").pack(pady=10)

        btn_frame = tk.Frame(frame)
        btn_frame.pack(pady=30)

        tk.Button(btn_frame, text="Add Contact", width=16, height=2, command=self.show_add_contact).grid(row=0, column=0, padx=12)
        tk.Button(btn_frame, text="View Contacts", width=16, height=2, command=self.show_view_contacts).grid(row=0, column=1, padx=12)

    # ---------------- ABOUT ----------------
    def show_about(self):
        self._clear_container()
        self.title("About ContactsBook")

        frame = tk.Frame(self.container, padx=20, pady=50)
        frame.pack(fill="both", expand=True)

        tk.Label(frame, text="ContactsBook V1.0", font=("Segoe UI", 16, "bold")).pack(pady=10)
        tk.Label(frame, text="Author: Yandry Velez - ESPE").pack(pady=5)
        tk.Label(frame, text="Object-Oriented Programming (WS29)").pack(pady=5)

        tk.Button(frame, text="Back to Menu", width=15, command=self.show_main_menu).pack(pady=25)

    # ---------------- ADD CONTACT ----------------
    def show_add_contact(self):
        self._clear_container()
        self.title("Add Contact")

        tk.Label(self.container, text="ADD CONTACT", font=("Segoe UI", 15, "bold")).pack(pady=10)

        form = tk.Frame(self.container, padx=20, pady=5)
        form.pack(fill="both", expand=True)

        vcmd_digits = (self.register(lambda val: val == "" or val.isdigit()), "%P")
        vcmd_phone = (self.register(lambda val: val == "" or (val.isdigit() and len(val) <= 10)), "%P")
        vcmd_name = (self.register(lambda val: val == "" or all(ch.isalpha() or ch == " " for ch in val)), "%P")

        # Left column
        tk.Label(form, text="id:").grid(row=0, column=0, sticky="w", pady=4)
        self.txt_id = tk.Entry(form, width=14, validate="key", validatecommand=vcmd_digits)
        self.txt_id.grid(row=0, column=1, sticky="w", padx=8, pady=4)

        tk.Label(form, text="Full Name:").grid(row=1, column=0, sticky="w", pady=4)
        self.txt_name = tk.Entry(form, width=24, validate="key", validatecommand=vcmd_name)
        self.txt_name.grid(row=1, column=1, sticky="w", padx=8, pady=4)

        tk.Label(form, text="Email:").grid(row=2, column=0, sticky="w", pady=4)
        self.txt_email = tk.Entry(form, width=24)
        self.txt_email.grid(row=2, column=1, sticky="w", padx=8, pady=4)

        tk.Label(form, text="Cell Phone Number:").grid(row=3, column=0, sticky="w", pady=4)
        self.txt_phone = tk.Entry(form, width=24, validate="key", validatecommand=vcmd_phone)
        self.txt_phone.grid(row=3, column=1, sticky="w", padx=8, pady=4)

        tk.Label(form, text="Instagram:").grid(row=4, column=0, sticky="w", pady=4)
        self.txt_instagram = tk.Entry(form, width=24)
        self.txt_instagram.grid(row=4, column=1, sticky="w", padx=8, pady=4)

        tk.Label(form, text="Facebook:").grid(row=5, column=0, sticky="w", pady=4)
        self.txt_facebook = tk.Entry(form, width=24)
        self.txt_facebook.grid(row=5, column=1, sticky="w", padx=8, pady=4)

        tk.Label(form, text="Known:").grid(row=6, column=0, sticky="w", pady=4)
        self.var_known = tk.BooleanVar(value=False)
        tk.Checkbutton(form, text="Yes", variable=self.var_known).grid(row=6, column=1, sticky="w", padx=8, pady=4)

        # Right column
        tk.Label(form, text="Type:").grid(row=0, column=2, sticky="w", padx=(24, 6), pady=4)
        self.cmb_type = ttk.Combobox(form, values=["Friend", "Family", "Job", "University"], state="readonly", width=16)
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=3, sticky="w", pady=4)

        tk.Label(form, text="Gender:").grid(row=1, column=2, sticky="w", padx=(24, 6), pady=4)
        self.cmb_gender = ttk.Combobox(form, values=["Male", "Female"], state="readonly", width=16)
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=3, sticky="w", pady=4)

        tk.Label(form, text="Birth Date (D/M/Y):").grid(row=2, column=2, sticky="w", padx=(24, 6), pady=4)
        date_frame = tk.Frame(form)
        date_frame.grid(row=2, column=3, sticky="w", pady=4)

        days = [str(d) for d in range(1, 32)]
        months = [str(m) for m in range(1, 13)]
        current_year = date.today().year
        years = [str(y) for y in range(current_year, 1939, -1)]

        self.cmb_day = ttk.Combobox(date_frame, values=days, state="readonly", width=4)
        self.cmb_day.current(0)
        self.cmb_day.pack(side="left", padx=(0, 4))

        self.cmb_month = ttk.Combobox(date_frame, values=months, state="readonly", width=4)
        self.cmb_month.current(0)
        self.cmb_month.pack(side="left", padx=(0, 4))

        self.cmb_year = ttk.Combobox(date_frame, values=years, state="readonly", width=6)
        self.cmb_year.set("2005")
        self.cmb_year.pack(side="left")

        tk.Label(form, text="Hobbies:").grid(row=3, column=2, sticky="nw", padx=(24, 6), pady=4)
        self.lst_hobbies = tk.Listbox(form, selectmode="multiple", height=5, width=22, exportselection=False)
        for h in ["Play the guitar", "Watch movies", "Play soccer", "Play videogames", "Reading"]:
            self.lst_hobbies.insert(tk.END, h)
        self.lst_hobbies.grid(row=3, column=3, rowspan=3, sticky="w", pady=4)

        tk.Label(form, text="Comments:").grid(row=6, column=2, sticky="nw", padx=(24, 6), pady=4)
        self.txa_comments = tk.Text(form, width=22, height=4)
        self.txa_comments.grid(row=6, column=3, rowspan=2, sticky="w", pady=4)

        # Bottom buttons
        btn_bar = tk.Frame(self.container, padx=20, pady=10)
        btn_bar.pack(fill="x")

        tk.Button(btn_bar, text="Back to Menu", command=self.show_main_menu).pack(side="left")
        tk.Button(btn_bar, text="Save", width=10, command=self._save_contact).pack(side="right", padx=(8, 0))
        tk.Button(btn_bar, text="Cancel / Clear", command=self.show_add_contact).pack(side="right")

    def _save_contact(self):
        id_text = self.txt_id.get().strip()
        full_name = self.txt_name.get().strip()
        email = self.txt_email.get().strip()
        phone = self.txt_phone.get().strip()
        instagram = self.txt_instagram.get().strip()
        facebook = self.txt_facebook.get().strip()

        if not id_text.isdigit() or int(id_text) <= 0:
            messagebox.showwarning("Validation", "Please enter a valid numeric ID greater than 0.")
            return

        if not full_name or not re.match(r"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", full_name):
            messagebox.showwarning("Validation", "Please enter a valid Full Name (letters only).")
            return

        if not email or not re.match(r"^[\w\.-]+@[\w\.-]+\.[a-zA-Z]{2,}$", email):
            messagebox.showwarning("Validation", "Please enter a valid Email (example: user@mail.com).")
            return

        if not re.match(r"^\d{10}$", phone):
            messagebox.showwarning("Validation", "Cell Phone Number must have 10 digits.")
            return

        day = int(self.cmb_day.get())
        month = int(self.cmb_month.get())
        year = int(self.cmb_year.get())

        try:
            selected_date = date(year, month, day)
        except ValueError:
            messagebox.showwarning("Validation", "Invalid Birth Date selected (check day and month).")
            return

        if selected_date > date.today():
            messagebox.showwarning("Validation", "Birth Date cannot be in the future.")
            return

        hobbies = [self.lst_hobbies.get(i) for i in self.lst_hobbies.curselection()]
        comments = self.txa_comments.get("1.0", tk.END).strip()

        contact = Contact(
            int(id_text),
            full_name,
            email,
            phone,
            instagram,
            facebook,
            self.var_known.get(),
            self.cmb_type.get(),
            self.cmb_gender.get()[0],
            hobbies,
            selected_date,
            comments,
        )
        ContactController.add_contact(contact)
        messagebox.showinfo("Saved", f"Contact saved!\n\n{contact}")
        self.show_add_contact()

    # ---------------- VIEW CONTACTS ----------------
    def show_view_contacts(self):
        self._clear_container()
        self.title("View Contacts")

        tk.Label(self.container, text="REGISTERED CONTACTS", font=("Segoe UI", 15, "bold")).pack(pady=12)

        cols = ("ID", "Full Name", "Email", "Cell Phone", "Type", "Gender", "Birth Date")
        tree = ttk.Treeview(self.container, columns=cols, show="headings", height=11)

        widths = [50, 140, 150, 100, 80, 60, 85]
        for col, w in zip(cols, widths):
            tree.heading(col, text=col)
            tree.column(col, width=w, anchor="center")

        for c in ContactController.get_contacts():
            bdate = c.birth_date.strftime("%d/%m/%Y") if c.birth_date else ""
            tree.insert("", tk.END, values=(c.id, c.full_name, c.email, c.cell_phone_number, c.type, c.gender, bdate))

        tree.pack(padx=16, pady=5, fill="both", expand=True)

        btn_bar = tk.Frame(self.container, padx=16, pady=10)
        btn_bar.pack(fill="x")
        tk.Button(btn_bar, text="Back to Menu", command=self.show_main_menu).pack(side="left")
        tk.Button(btn_bar, text="Add Contact", command=self.show_add_contact).pack(side="right")


if __name__ == "__main__":
    app = ContactsBookApp()
    app.mainloop()
