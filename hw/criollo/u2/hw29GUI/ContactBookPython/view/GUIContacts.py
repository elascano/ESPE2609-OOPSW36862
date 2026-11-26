import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from datetime import date
import tkinter as tk
from tkinter import ttk, messagebox
from model.Contact import Contacts

CONTACT_TYPES = ["Friend", "Family", "Job Mate", "Team Mate", "High School", "Gym"]
GENDER_OPTIONS = ["Male", "Female"]
HOBBY_OPTIONS = [
    "Play the Guitar",
    "Watch movies",
    "Play Soccer",
    "Play Basketball",
    "Cooking",
    "Reading",
    "Running",
    "Swimming",
]


class ContactBookUI:
    def __init__(self, root: tk.Tk) -> None:
        self.root = root
        self.root.title("Contacts Book")
        self.root.geometry("540x680")
        self.root.resizable(False, False)

        self.contactsList: list[Contacts] = []
        self.setupWidgets()

    def setupWidgets(self) -> None:
        mainFrame = ttk.Frame(self.root, padding=15)
        mainFrame.pack(fill=tk.BOTH, expand=True)

        titleLabel = ttk.Label(mainFrame, text="CONTACTS", font=("Segoe UI", 14, "bold"))
        titleLabel.grid(row=0, column=0, columnspan=2, pady=(0, 12))

        ttk.Label(mainFrame, text="ID:").grid(row=1, column=0, sticky=tk.W, pady=4)
        self.idEntry = ttk.Entry(mainFrame, width=35)
        self.idEntry.grid(row=1, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Full Name:").grid(row=2, column=0, sticky=tk.W, pady=4)
        self.fullNameEntry = ttk.Entry(mainFrame, width=35)
        self.fullNameEntry.grid(row=2, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="E-mail:").grid(row=3, column=0, sticky=tk.W, pady=4)
        self.emailEntry = ttk.Entry(mainFrame, width=35)
        self.emailEntry.grid(row=3, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Cell Phone Number:").grid(row=4, column=0, sticky=tk.W, pady=4)
        self.phoneEntry = ttk.Entry(mainFrame, width=35)
        self.phoneEntry.grid(row=4, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Instagram:").grid(row=5, column=0, sticky=tk.W, pady=4)
        self.instagramEntry = ttk.Entry(mainFrame, width=35)
        self.instagramEntry.grid(row=5, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Facebook:").grid(row=6, column=0, sticky=tk.W, pady=4)
        self.facebookEntry = ttk.Entry(mainFrame, width=35)
        self.facebookEntry.grid(row=6, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Is this person known?:").grid(row=7, column=0, sticky=tk.W, pady=4)
        self.isKnownVar = tk.BooleanVar(value=False)
        self.isKnownCheck = ttk.Checkbutton(mainFrame, text="Yes", variable=self.isKnownVar)
        self.isKnownCheck.grid(row=7, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Type:").grid(row=8, column=0, sticky=tk.W, pady=4)
        self.typeCombo = ttk.Combobox(mainFrame, values=CONTACT_TYPES, state="readonly", width=32)
        self.typeCombo.current(0)
        self.typeCombo.grid(row=8, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Gender:").grid(row=9, column=0, sticky=tk.W, pady=4)
        genderFrame = ttk.Frame(mainFrame)
        genderFrame.grid(row=9, column=1, sticky=tk.W, pady=4)
        self.genderVar = tk.StringVar(value=GENDER_OPTIONS[0])
        for option in GENDER_OPTIONS:
            ttk.Radiobutton(
                genderFrame, text=option, value=option, variable=self.genderVar
            ).pack(side=tk.LEFT, padx=(0, 12))

        ttk.Label(mainFrame, text="Hobbies:").grid(row=10, column=0, sticky=tk.NW, pady=4)
        self.hobbiesListbox = tk.Listbox(mainFrame, selectmode=tk.MULTIPLE, height=5, width=35, exportselection=False)
        for hobby in HOBBY_OPTIONS:
            self.hobbiesListbox.insert(tk.END, hobby)
        self.hobbiesListbox.grid(row=10, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Birth Date (YYYY-MM-DD):").grid(row=11, column=0, sticky=tk.W, pady=4)
        self.birthDateEntry = ttk.Entry(mainFrame, width=35)
        self.birthDateEntry.grid(row=11, column=1, sticky=tk.W, pady=4)

        ttk.Label(mainFrame, text="Comments:").grid(row=12, column=0, sticky=tk.NW, pady=4)
        self.commentsText = tk.Text(mainFrame, width=35, height=4)
        self.commentsText.grid(row=12, column=1, sticky=tk.W, pady=4)

        buttonFrame = ttk.Frame(mainFrame)
        buttonFrame.grid(row=13, column=0, columnspan=2, pady=15)

        saveButton = ttk.Button(buttonFrame, text="Save", command=self.saveContact)
        saveButton.pack(side=tk.LEFT, padx=8)

        clearButton = ttk.Button(buttonFrame, text="Clear", command=self.clearFields)
        clearButton.pack(side=tk.LEFT, padx=8)

    def saveContact(self) -> None:
        rawId = self.idEntry.get().strip()
        try:
            contactId = int(rawId)
            if contactId <= 0:
                raise ValueError
        except ValueError:
            messagebox.showerror("Validation Error", "ID must be a positive integer.")
            return

        fullName = self.fullNameEntry.get().strip()
        if not fullName:
            messagebox.showerror("Validation Error", "Full name is required.")
            return

        birthDate = self.birthDateEntry.get().strip()
        try:
            date.fromisoformat(birthDate)
        except ValueError:
            messagebox.showerror("Validation Error", "Enter a valid birth date using YYYY-MM-DD.")
            return

        email = self.emailEntry.get().strip()
        cellPhoneNumber = self.phoneEntry.get().strip()
        instagram = self.instagramEntry.get().strip()
        facebook = self.facebookEntry.get().strip()
        isKnown = self.isKnownVar.get()
        contactType = self.typeCombo.get()
        gender = self.genderVar.get()
        selectedIndices = self.hobbiesListbox.curselection()
        hobbies = [HOBBY_OPTIONS[i] for i in selectedIndices]
        comments = self.commentsText.get("1.0", tk.END).strip()

        contact = Contacts(
            contactId,
            fullName,
            email,
            cellPhoneNumber,
            instagram,
            facebook,
            isKnown,
            contactType,
            gender,
            hobbies,
            birthDate,
            comments,
        )

        self.contactsList.append(contact)
        messagebox.showinfo("Contact Saved", f"Contact created successfully:\n\n{contact}")
        self.clearFields()

    def clearFields(self) -> None:
        self.idEntry.delete(0, tk.END)
        self.fullNameEntry.delete(0, tk.END)
        self.emailEntry.delete(0, tk.END)
        self.phoneEntry.delete(0, tk.END)
        self.instagramEntry.delete(0, tk.END)
        self.facebookEntry.delete(0, tk.END)
        self.isKnownVar.set(False)
        self.typeCombo.current(0)
        self.genderVar.set(GENDER_OPTIONS[0])
        self.hobbiesListbox.selection_clear(0, tk.END)
        self.birthDateEntry.delete(0, tk.END)
        self.commentsText.delete("1.0", tk.END)


def main() -> None:
    root = tk.Tk()
    ContactBookUI(root)
    root.mainloop()


if __name__ == "__main__":
    main()