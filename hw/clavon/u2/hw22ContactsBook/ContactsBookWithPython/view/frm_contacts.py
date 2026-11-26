import tkinter as tk
from tkinter import ttk


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("ContactsBook")
        self.geometry("760x530")
        self.resizable(False, False)
        self._init_components()

    def _init_components(self):
        self.pnl_title = tk.Frame(self, relief="groove", bd=1)
        self.pnl_title.pack(fill="x", padx=48, pady=(17, 12), ipady=15)
        self.j_label_1 = tk.Label(self.pnl_title, text="CONTACT")
        self.j_label_1.pack()

        self.pnl_form = tk.Frame(self)
        self.pnl_form.pack(fill="x", padx=22, pady=12)

        left_col = tk.Frame(self.pnl_form)
        left_col.grid(row=0, column=0, sticky="n", padx=(31, 18))

        self.j_label_2 = tk.Label(left_col, text="id:")
        self.j_label_2.grid(row=0, column=0, sticky="e", pady=6, padx=(0, 6))
        self.txt_id = tk.Entry(left_col, width=18)
        self.txt_id.grid(row=0, column=1, sticky="w", pady=6)

        self.j_label_3 = tk.Label(left_col, text="Full Name:")
        self.j_label_3.grid(row=1, column=0, sticky="e", pady=6, padx=(0, 6))
        self.txt_full_name = tk.Entry(left_col, width=18)
        self.txt_full_name.grid(row=1, column=1, sticky="w", pady=6)

        self.j_label_4 = tk.Label(left_col, text="Email:")
        self.j_label_4.grid(row=2, column=0, sticky="e", pady=6, padx=(0, 6))
        self.txt_email = tk.Entry(left_col, width=18)
        self.txt_email.grid(row=2, column=1, sticky="w", pady=6)

        self.j_label_5 = tk.Label(left_col, text="Cell Phone Number:")
        self.j_label_5.grid(row=3, column=0, sticky="e", pady=6, padx=(0, 6))
        self.txt_cell_phone_number = tk.Entry(left_col, width=18)
        self.txt_cell_phone_number.grid(row=3, column=1, sticky="w", pady=6)

        self.j_label_6 = tk.Label(left_col, text="Instagram:")
        self.j_label_6.grid(row=4, column=0, sticky="e", pady=6, padx=(0, 6))
        self.txt_instagram = tk.Entry(left_col, width=18)
        self.txt_instagram.grid(row=4, column=1, sticky="w", pady=6)

        self.j_label_7 = tk.Label(left_col, text="Facebook:")
        self.j_label_7.grid(row=5, column=0, sticky="e", pady=6, padx=(0, 6))
        self.txt_facebook = tk.Entry(left_col, width=18)
        self.txt_facebook.grid(row=5, column=1, sticky="w", pady=6)

        self.j_label_8 = tk.Label(left_col, text="Know:")
        self.j_label_8.grid(row=6, column=0, sticky="e", pady=6, padx=(0, 6))
        self.var_is_known = tk.BooleanVar(value=False)
        self.chk_is_known = tk.Checkbutton(left_col, text="Yes", variable=self.var_is_known)
        self.chk_is_known.grid(row=6, column=1, sticky="w", pady=6)

        right_col = tk.Frame(self.pnl_form)
        right_col.grid(row=0, column=1, sticky="n")

        self.j_label_9 = tk.Label(right_col, text="Type:")
        self.j_label_9.grid(row=0, column=0, sticky="e", pady=6, padx=(0, 18))
        self.cmb_type = ttk.Combobox(
            right_col,
            values=["Friend", "Family", "Job Mate", "Team Mate", "High School"],
            state="readonly",
            width=15,
        )
        self.cmb_type.current(0)
        self.cmb_type.grid(row=0, column=1, sticky="w", pady=6)

        self.j_label_10 = tk.Label(right_col, text="Gender:")
        self.j_label_10.grid(row=1, column=0, sticky="e", pady=6, padx=(0, 18))
        self.cmb_gender = ttk.Combobox(
            right_col,
            values=["Mate", "Formate"],
            state="readonly",
            width=15,
        )
        self.cmb_gender.current(0)
        self.cmb_gender.grid(row=1, column=1, sticky="w", pady=6)

        self.j_label_11 = tk.Label(right_col, text="Hobbies:")
        self.j_label_11.grid(row=2, column=0, sticky="ne", pady=6, padx=(0, 18))

        hobbies_container = tk.Frame(right_col)
        hobbies_container.grid(row=2, column=1, sticky="w", pady=6)
        hobbies_scroll = tk.Scrollbar(hobbies_container, orient="vertical")
        self.list_hobbies = tk.Listbox(
            hobbies_container,
            selectmode="multiple",
            height=7,
            width=18,
            yscrollcommand=hobbies_scroll.set,
            exportselection=False,
        )
        hobbies_scroll.config(command=self.list_hobbies.yview)
        for hobby in [
            "Play the guitar",
            "Watch movies",
            "Play soccer",
            "Play Basketball",
            "Reading",
            "Cooking",
            "Swiming",
        ]:
            self.list_hobbies.insert(tk.END, hobby)
        self.list_hobbies.pack(side="left")
        hobbies_scroll.pack(side="right", fill="y")

        self.j_label_12 = tk.Label(right_col, text="Birth Date:")
        self.j_label_12.grid(row=3, column=0, sticky="e", pady=6, padx=(0, 25))
        self.txt_birth_date = tk.Entry(right_col, width=22)
        self.txt_birth_date.grid(row=3, column=1, sticky="w", pady=6)

        self.j_label_13 = tk.Label(right_col, text="Comments:")
        self.j_label_13.grid(row=4, column=0, sticky="ne", pady=6, padx=(0, 25))

        comments_container = tk.Frame(right_col)
        comments_container.grid(row=4, column=1, sticky="w", pady=6)
        comments_scroll = tk.Scrollbar(comments_container, orient="vertical")
        self.txt_comments = tk.Text(
            comments_container,
            width=17,
            height=3,
            yscrollcommand=comments_scroll.set,
        )
        comments_scroll.config(command=self.txt_comments.yview)
        self.txt_comments.pack(side="left")
        comments_scroll.pack(side="right", fill="y")

        self.pnl_buttons = tk.Frame(self, height=80)
        self.pnl_buttons.pack(fill="x", padx=22, pady=(12, 20))