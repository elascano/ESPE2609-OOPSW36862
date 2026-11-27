import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime

from model.contact import Contact


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("FrmContacs")
        self.geometry("680x470")
        self.resizable(False, False)
        self._init_components()

    def _init_components(self):
        # Title Panel
        pnl_title = tk.Frame(self, pady=15)
        pnl_title.pack(fill="x")
        tk.Label(pnl_title, text="ADD CONTACT", font=("Segoe UI", 12, "bold")).pack()

        # Data Panel
        pnl_data = tk.Frame(self, padx=35, pady=5)
        pnl_data.pack(fill="both", expand=True)

        # Left column
        tk.Label(pnl_data, text="id:").grid(row=0, column=0, sticky="w", pady=4)
        self.txt_id = tk.Entry(pnl_data, width=12)
        self.txt_id.grid(row=0, column=1, sticky="w", padx=10, pady=4)

        tk.Label(pnl_data, text="Full Name ").grid(row=1, column=0, sticky="w", pady=4)
        self.txt_full_name = tk.Entry(pnl_data, width=20)
        self.txt_full_name.grid(row=1, column=1, sticky="w", padx=10, pady=4)

        tk.Label(pnl_data, text="Email:").grid(row=2, column=0, sticky="w", pady=4)
        self.txt_email = tk.Entry(pnl_data, width=20)
        self.txt_email.grid(row=2, column=1, sticky="w", padx=10, pady=4)

        tk.Label(pnl_data, text="Cell Phone Number:").grid(row=3, column=0, sticky="w", pady=4)
        self.txt_cellphone = tk.Entry(pnl_data, width=20)
        self.txt_cellphone.grid(row=3, column=1, sticky="w", padx=10, pady=4)

        tk.Label(pnl_data, text="Instagram:").grid(row=4, column=0, sticky="w", pady=4)
        self.txt_instagram = tk.Entry(pnl_data, width=20)
        self.txt_instagram.grid(row=4, column=1, sticky="w", padx=10, pady=4)

        tk.Label(pnl_data, text="Facebook").grid(row=5, column=0, sticky="w", pady=4)
        self.txt_facebook = tk.Entry(pnl_data, width=20)
        self.txt_facebook.grid(row=5, column=1, sticky="w", padx=10, pady=4)

        tk.Label(pnl_data, text="Known:").grid(row=6, column=0, sticky="w", pady=4)
        self.var_known = tk.BooleanVar(value=False)
        tk.Checkbutton(pnl_data, text="Yes", variable=self.var_known).grid(row=6, column=1, sticky="w", padx=10, pady=4)

        # Right column
        tk.Label(pnl_data, text="Type:").grid(row=0, column=2, sticky="w", padx=(30, 6), pady=4)
        self.cmb_type = ttk.Combobox(pnl_data, values=["Friend", "Family"], state="readonly", width=15)
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=3, sticky="w", pady=4)

        tk.Label(pnl_data, text="Gender:").grid(row=1, column=2, sticky="w", padx=(30, 6), pady=4)
        self.cmb_gender = ttk.Combobox(pnl_data, values=["Male ", "Female"], state="readonly", width=15)
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=3, sticky="w", pady=4)

        tk.Label(pnl_data, text="Hobbies:").grid(row=2, column=2, sticky="nw", padx=(30, 6), pady=4)
        self.lst_hobbies = tk.Listbox(pnl_data, selectmode="multiple", height=6, width=20, exportselection=False)
        for item in ["Play the gittar ", "watch movies", "Play Scoer", "Play videogames", "Reading", " "]:
            self.lst_hobbies.insert(tk.END, item)
        self.lst_hobbies.grid(row=2, column=3, rowspan=4, sticky="w", pady=4)

        tk.Label(pnl_data, text="Birth Date:").grid(row=6, column=2, sticky="w", padx=(30, 6), pady=4)
        self.txt_birth_date = tk.Entry(pnl_data, width=18)
        self.txt_birth_date.grid(row=6, column=3, sticky="w", pady=4)

        tk.Label(pnl_data, text="Comments:").grid(row=7, column=2, sticky="nw", padx=(30, 6), pady=4)
        self.txa_comments = tk.Text(pnl_data, width=22, height=4)
        self.txa_comments.grid(row=7, column=3, sticky="w", pady=4)

        # Cancel & Save buttons
        btn_frame = tk.Frame(pnl_data)
        btn_frame.grid(row=7, column=0, columnspan=2, sticky="sw", pady=10)
        tk.Button(btn_frame, text="Cancel", width=10, command=self.btn_cancel_action).pack(side="left", padx=(0, 35))
        tk.Button(btn_frame, text="Save", width=10, command=self.btn_save_action).pack(side="left")

    def btn_cancel_action(self):
        self.txt_id.delete(0, tk.END)
        self.txt_full_name.delete(0, tk.END)
        self.txt_email.delete(0, tk.END)
        self.txt_cellphone.delete(0, tk.END)
        self.txt_instagram.delete(0, tk.END)
        self.txt_facebook.delete(0, tk.END)
        self.var_known.set(False)
        self.cmb_type.current(0)
        self.cmb_gender.current(0)
        self.lst_hobbies.selection_clear(0, tk.END)
        self.txt_birth_date.delete(0, tk.END)
        self.txa_comments.delete("1.0", tk.END)

    def btn_save_action(self):
        try:
            contact_id = int(self.txt_id.get().strip())
        except ValueError:
            messagebox.showwarning("Message", "Please enter a valid numeric id.")
            return

        full_name = self.txt_full_name.get()
        email = self.txt_email.get()
        cell_phone = self.txt_cellphone.get()
        instagram = self.txt_instagram.get()
        facebook = self.txt_facebook.get()
        is_known = self.var_known.get()
        contact_type = self.cmb_type.get()
        gender = self.cmb_gender.get()[0]
        hobbies = [self.lst_hobbies.get(i) for i in self.lst_hobbies.curselection()]
        birth_date = datetime.now()
        comments = self.txa_comments.get("1.0", tk.END).rstrip("\n")

        contact = Contact(
            contact_id,
            full_name,
            email,
            cell_phone,
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


if __name__ == "__main__":
    app = FrmContacts()
    app.mainloop()
