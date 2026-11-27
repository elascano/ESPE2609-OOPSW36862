import tkinter as tk
from tkinter import ttk, messagebox
from view.frm_contacts_book import FrmContactsBook

class FrmLogin(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Login")
        self.geometry("600x360")
        self.resizable(False, False)
        self._init_components()

    def _init_components(self):
        pnl_title = tk.Frame(self)
        pnl_title.pack(pady=(35, 25))
        tk.Label(pnl_title, text="LOGIN TO CONTACTS", font=("Arial", 11, "bold")).pack()

        pnl_form = tk.Frame(self)
        pnl_form.pack(pady=10)

        tk.Label(pnl_form, text="User Name:").grid(row=0, column=0, sticky="e", padx=15, pady=8)
        self.txt_user_name = tk.Entry(pnl_form, width=30)
        self.txt_user_name.grid(row=0, column=1, pady=8)

        tk.Label(pnl_form, text="Password:").grid(row=1, column=0, sticky="e", padx=15, pady=8)
        self.pw_password = tk.Entry(pnl_form, show="*", width=30)
        self.pw_password.grid(row=1, column=1, pady=8)
        self.pw_password.bind("<Return>", lambda event: self._validate_user_and_password())

        tk.Label(pnl_form, text="Type:").grid(row=2, column=0, sticky="e", padx=15, pady=8)
        self.cmb_user_type = ttk.Combobox(pnl_form, values=["Manager", "Friend"], state="readonly", width=27)
        self.cmb_user_type.current(0)
        self.cmb_user_type.grid(row=2, column=1, pady=8)

        pnl_buttons = tk.Frame(self)
        pnl_buttons.pack(pady=30)
        tk.Button(pnl_buttons, text="Login", width=10, command=self._validate_user_and_password).pack(side=tk.LEFT, padx=35)
        tk.Button(pnl_buttons, text="Cancel", width=10, command=self.destroy).pack(side=tk.LEFT, padx=35)

    def _validate_user_and_password(self):
        expected_user = "David"
        expected_password = "Clavon"

        if self.txt_user_name.get() == expected_user and self.pw_password.get() == expected_password:
            self.destroy()
            frm_contacts_book = FrmContactsBook()
            frm_contacts_book.mainloop()
        else:
            messagebox.showerror("Login Error", "User or password are wrong")