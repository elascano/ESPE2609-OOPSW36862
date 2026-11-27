import sys
import tkinter as tk
from view.frm_contacts import FrmContacts

class FrmContactsBook(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts Book")
        self.geometry("530x380")
        self._init_components()

    def _init_components(self):
        menu_bar = tk.Menu(self)

        mnu_contacts_book = tk.Menu(menu_bar, tearoff=0)
        mnu_contacts_book.add_command(label="About")
        mnu_contacts_book.add_command(label="Exit", command=self._itm_exit_action)
        menu_bar.add_cascade(label="Contacts Book", menu=mnu_contacts_book)

        mnu_contact = tk.Menu(menu_bar, tearoff=0)
        mnu_contact.add_command(label="Add", command=self._itm_add_contact_action)
        mnu_contact.add_command(label="Search")

        mnu_reports = tk.Menu(mnu_contact, tearoff=0)
        mnu_reports.add_command(label="Print out")
        mnu_reports.add_command(label="Save as PDF")
        mnu_contact.add_cascade(label="Reports", menu=mnu_reports)
        menu_bar.add_cascade(label="Contact", menu=mnu_contact)

        mnu_help = tk.Menu(menu_bar, tearoff=0)
        mnu_use_guide = tk.Menu(mnu_help, tearoff=0)
        mnu_use_guide.add_command(label="On Line")
        mnu_use_guide.add_command(label="Print out")
        mnu_help.add_cascade(label="Use Guide", menu=mnu_use_guide)
        menu_bar.add_cascade(label="Help", menu=mnu_help)

        self.config(menu=menu_bar)

    def _itm_add_contact_action(self):
        self.destroy()
        frm_contacts = FrmContacts()
        frm_contacts.mainloop()

    def _itm_exit_action(self):
        self.destroy()
        sys.exit(0)