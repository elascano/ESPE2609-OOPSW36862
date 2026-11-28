import os
import sys

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))

import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime
from model.contact import Contact


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("ContactsBook")
        self.geometry("680x480")
        self.resizable(False, False)
        self.init_components()

    def init_components(self):
        self.pnl_title = tk.Frame(self)
        self.pnl_title.pack(pady=(20, 10))
        tk.Label(self.pnl_title, text="ADD CONTACT", font=("Segoe UI", 11)).pack()

        self.pnl_fields = tk.Frame(self)
        self.pnl_fields.pack(padx=30, pady=10, fill="x")


        tk.Label(self.pnl_fields, text="id:").grid(row=0, column=0, sticky="e", padx=5, pady=4)
        self.txt_id = tk.Entry(self.pnl_fields, width=22)
        self.txt_id.grid(row=0, column=1, padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Full Name:").grid(row=1, column=0, sticky="e", padx=5, pady=4)
        self.txt_full_name = tk.Entry(self.pnl_fields, width=22)
        self.txt_full_name.grid(row=1, column=1, padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Email:").grid(row=2, column=0, sticky="e", padx=5, pady=4)
        self.txt_email = tk.Entry(self.pnl_fields, width=22)
        self.txt_email.grid(row=2, column=1, padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Cell Phone Number:").grid(row=3, column=0, sticky="e", padx=5, pady=4)
        self.txt_cell_phone_number = tk.Entry(self.pnl_fields, width=22)
        self.txt_cell_phone_number.grid(row=3, column=1, padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Instagram:").grid(row=4, column=0, sticky="e", padx=5, pady=4)
        self.txt_instagram = tk.Entry(self.pnl_fields, width=22)
        self.txt_instagram.grid(row=4, column=1, padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Facebook:").grid(row=5, column=0, sticky="e", padx=5, pady=4)
        self.txt_facebook = tk.Entry(self.pnl_fields, width=22)
        self.txt_facebook.grid(row=5, column=1, padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Know:").grid(row=6, column=0, sticky="e", padx=5, pady=4)
        self.is_known_var = tk.BooleanVar(value=False)
        self.chk_is_known = tk.CheckBox = tk.Checkbutton(
            self.pnl_fields, text="Yes", variable=self.is_known_var
        )
        self.chk_is_known.grid(row=6, column=1, sticky="w", padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Type:").grid(row=0, column=2, sticky="e", padx=(30, 5), pady=4)
        self.cmb_type = ttk.Combobox(
            self.pnl_fields,
            values=["Friend", "Family", "Job Mate", "Team Mate", "High School", " "],
            state="readonly",
            width=18,
        )
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=3, sticky="w", padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Gender:").grid(row=1, column=2, sticky="e", padx=(30, 5), pady=4)
        self.cmb_gender = ttk.Combobox(
            self.pnl_fields,
            values=["M", "F"],
            state="readonly",
            width=8,
        )
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=3, sticky="w", padx=5, pady=4)

        tk.Label(self.pnl_fields, text="Hobbies:").grid(row=2, column=2, sticky="ne", padx=(30, 5), pady=4)
        hobbies_frame = tk.Frame(self.pnl_fields)
        hobbies_frame.grid(row=2, column=3, rowspan=5, sticky="w", padx=5, pady=4)

        scrollbar = tk.Scrollbar(hobbies_frame, orient="vertical")
        self.list_hobbies = tk.Listbox(
            hobbies_frame,
            selectmode=tk.MULTIPLE,
            height=8,
            width=18,
            yscrollcommand=scrollbar.set,
            exportselection=False,
        )
        scrollbar.config(command=self.list_hobbies.yview)
        scrollbar.pack(side="right", fill="y")
        self.list_hobbies.pack(side="left")

        hobbies_items = [
            "Play the guitar",
            "Watch movies",
            "Play soccer",
            "Play Basketball",
            "Reading",
            "Cooking",
            "Swiming",
            " ",
        ]
        for item in hobbies_items:
            self.list_hobbies.insert(tk.END, item)

        tk.Label(self.pnl_fields, text="Birth Date:").grid(row=7, column=2, sticky="e", padx=(30, 5), pady=(12, 4))
        self.txt_birth_date = tk.Entry(self.pnl_fields, width=22)
        self.txt_birth_date.grid(row=7, column=3, sticky="w", padx=5, pady=(12, 4))

        tk.Label(self.pnl_fields, text="Comments:").grid(row=8, column=2, sticky="ne", padx=(30, 5), pady=4)
        self.txt_comments = tk.Text(self.pnl_fields, width=17, height=2)
        self.txt_comments.grid(row=8, column=3, sticky="w", padx=5, pady=4)

        self.pnl_buttons = tk.Frame(self)
        self.pnl_buttons.pack(pady=25)

        self.btn_save = tk.Button(self.pnl_buttons, text="Save", width=10, command=self.btn_save_action_performed)
        self.btn_save.pack(side="left", padx=15)

        self.btn_cancel = tk.Button(self.pnl_buttons, text="Cancel", width=10)
        self.btn_cancel.pack(side="left", padx=15)

    def btn_save_action_performed(self):
        id_val = int(self.txt_id.get())
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
        comments = self.txt_comments.get("1.0", "end-1c")

        contact = Contact(
            id_val,
            full_name,
            email,
            cell_phone_number,
            instagram,
            facebook,
            is_known,
            contact_type,
            gender,
            hobbies,
            birth_date,
            comments,
        )

        messagebox.showinfo("Message", str(contact))