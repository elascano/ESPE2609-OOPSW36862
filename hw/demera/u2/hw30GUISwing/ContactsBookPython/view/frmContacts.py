import re
from datetime import datetime, date
import tkinter as tk
from tkinter import ttk, messagebox
from model import Contact


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("ContactsBook - Add Contact")
        self.geometry("680x500")
        self.resizable(False, False)

        self.initComponents()

    def initComponents(self):
        lblTitle = tk.Label(self, text="ADD CONTACT", font=("Arial", 14, "bold"))
        lblTitle.pack(pady=15)

        pnlData = tk.Frame(self, padx=20)
        pnlData.pack(fill="both", expand=True)

        vcmdDigits = (self.register(self.onlyDigits), "%P")
        vcmdLetters = (self.register(self.onlyLetters), "%P")

        tk.Label(pnlData, text="id:").grid(row=0, column=0, sticky="e", pady=5)
        self.txtId = tk.Entry(
            pnlData, width=10, validate="key", validatecommand=vcmdDigits
        )
        self.txtId.grid(row=0, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="Full Name:").grid(row=1, column=0, sticky="e", pady=5)
        self.txtFullName = tk.Entry(
            pnlData, width=28, validate="key", validatecommand=vcmdLetters
        )
        self.txtFullName.grid(row=1, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="E-mail:").grid(row=2, column=0, sticky="e", pady=5)
        self.txtEmail = tk.Entry(pnlData, width=28)
        self.txtEmail.grid(row=2, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="Cell Phone Number:").grid(
            row=3, column=0, sticky="e", pady=5
        )
        self.txtCellPhoneNumber = tk.Entry(
            pnlData, width=28, validate="key", validatecommand=vcmdDigits
        )
        self.txtCellPhoneNumber.grid(row=3, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="Instagram:").grid(row=4, column=0, sticky="e", pady=5)
        self.txtInstagram = tk.Entry(pnlData, width=28)
        self.txtInstagram.grid(row=4, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="Facebook:").grid(row=5, column=0, sticky="e", pady=5)
        self.txtFacebook = tk.Entry(pnlData, width=28)
        self.txtFacebook.grid(row=5, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="Known:").grid(row=6, column=0, sticky="e", pady=5)
        self.knownVar = tk.BooleanVar()
        self.chkKnown = tk.Checkbutton(pnlData, text="Yes", variable=self.knownVar)
        self.chkKnown.grid(row=6, column=1, sticky="w", padx=5)

        tk.Label(pnlData, text="Type:").grid(row=0, column=2, sticky="e", padx=(20, 5))
        self.cmbTypeOfContact = ttk.Combobox(
            pnlData,
            values=["Friend", "Family", "Job Mate", "Team Mate", "High School", "Gym"],
            state="readonly",
            width=18,
        )
        self.cmbTypeOfContact.current(0)
        self.cmbTypeOfContact.grid(row=0, column=3, sticky="w")

        tk.Label(pnlData, text="Gender:").grid(
            row=1, column=2, sticky="e", padx=(20, 5)
        )
        self.cmbGender = ttk.Combobox(
            pnlData, values=["M", "F"], state="readonly", width=5
        )
        self.cmbGender.current(0)
        self.cmbGender.grid(row=1, column=3, sticky="w")

        tk.Label(pnlData, text="Hobbies:").grid(
            row=2, column=2, sticky="ne", padx=(20, 5)
        )
        self.lstHobbies = tk.Listbox(pnlData, selectmode=tk.MULTIPLE, height=5, width=22)
        hobbiesItems = [
            "Play the Guitar",
            "Watch movies",
            "Play Soccer",
            "Play Basketball",
            "Cooking",
            "Reading",
            "Running",
            "Swimming",
        ]
        for item in hobbiesItems:
            self.lstHobbies.insert(tk.END, item)
        self.lstHobbies.grid(row=2, column=3, rowspan=3, sticky="w")

        tk.Label(pnlData, text="BirthDate:").grid(
            row=6, column=2, sticky="e", padx=(20, 5)
        )
        self.txtBirthDate = tk.Entry(pnlData, width=20)
        self.txtBirthDate.grid(row=6, column=3, sticky="w")

        tk.Label(pnlData, text="Comments:").grid(
            row=7, column=2, sticky="ne", padx=(20, 5), pady=10
        )
        self.txaComments = tk.Text(pnlData, width=24, height=4)
        self.txaComments.grid(row=7, column=3, sticky="w", pady=10)

        pnlButtons = tk.Frame(self)
        pnlButtons.pack(pady=15)

        btnSave = tk.Button(
            pnlButtons, text="Save", width=10, command=self.btnSaveActionPerformed
        )
        btnSave.pack(side="left", padx=20)

        btnCancel = tk.Button(
            pnlButtons, text="Cancel", width=10, command=self.clearForm
        )
        btnCancel.pack(side="left", padx=20)

    @staticmethod
    def onlyDigits(newValue: str) -> bool:
        return newValue.isdigit() or newValue == ""

    @staticmethod
    def onlyLetters(newValue: str) -> bool:
        return all(c.isalpha() or c.isspace() for c in newValue)

    def clearForm(self):
        self.txtId.delete(0, tk.END)
        self.txtFullName.delete(0, tk.END)
        self.txtEmail.delete(0, tk.END)
        self.txtCellPhoneNumber.delete(0, tk.END)
        self.txtInstagram.delete(0, tk.END)
        self.txtFacebook.delete(0, tk.END)
        self.knownVar.set(False)
        self.cmbTypeOfContact.current(0)
        self.cmbGender.current(0)
        self.lstHobbies.selection_clear(0, tk.END)
        self.txtBirthDate.delete(0, tk.END)
        self.txaComments.delete("1.0", tk.END)
        self.txtId.focus()

    def btnSaveActionPerformed(self):
        idText = self.txtId.get().strip()
        if not idText or not idText.isdigit():
            messagebox.showwarning(
                "Validation Error", "ID is required and must contain only positive numbers."
            )
            self.txtId.focus()
            return
        id = int(idText)

        fullName = self.txtFullName.get().strip()
        if not re.match(r"^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,}$", fullName):
            messagebox.showwarning(
                "Validation Error",
                "Full Name is required and must contain only letters (at least 2 characters).",
            )
            self.txtFullName.focus()
            return

        email = self.txtEmail.get().strip()
        if not re.match(r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$", email):
            messagebox.showwarning(
                "Validation Error",
                "Please enter a valid email address (e.g., user@domain.com).",
            )
            self.txtEmail.focus()
            return

        cellPhoneNumber = self.txtCellPhoneNumber.get().strip()
        if not re.match(r"^\d{10}$", cellPhoneNumber):
            messagebox.showwarning(
                "Validation Error", "Cell Phone Number must be exactly 10 digits."
            )
            self.txtCellPhoneNumber.focus()
            return

        instagram = self.txtInstagram.get().strip()
        if instagram and " " in instagram:
            messagebox.showwarning(
                "Validation Error", "Instagram username cannot contain spaces."
            )
            self.txtInstagram.focus()
            return

        facebook = self.txtFacebook.get().strip()
        if facebook and " " in facebook:
            messagebox.showwarning(
                "Validation Error", "Facebook username cannot contain spaces."
            )
            self.txtFacebook.focus()
            return

        selectedIndices = self.lstHobbies.curselection()
        hobbies = [self.lstHobbies.get(i) for i in selectedIndices]
        if not hobbies:
            messagebox.showwarning(
                "Validation Error", "Please select at least one hobby."
            )
            self.lstHobbies.focus()
            return

        dateText = self.txtBirthDate.get().strip()
        if not dateText:
            messagebox.showwarning(
                "Validation Error", "Birth Date is required (dd/MM/yyyy)."
            )
            self.txtBirthDate.focus()
            return

        try:
            birthDate = datetime.strptime(dateText, "%d/%m/%Y").date()
            if birthDate > date.today():
                messagebox.showwarning(
                    "Validation Error", "Birth Date cannot be in the future."
                )
                self.txtBirthDate.focus()
                return
        except ValueError:
            messagebox.showwarning(
                "Validation Error",
                "Invalid date. Please use the exact format dd/MM/yyyy (e.g., 25/12/2000).",
            )
            self.txtBirthDate.focus()
            return

        contact = Contact(
            id=id,
            fullName=fullName,
            email=email,
            cellPhoneNumber=cellPhoneNumber,
            instagram=instagram,
            facebook=facebook,
            isKnown=self.knownVar.get(),
            type=self.cmbTypeOfContact.get(),
            gender=self.cmbGender.get(),
            hobbies=hobbies,
            birthDate=birthDate,
            comments=self.txaComments.get("1.0", tk.END).strip(),
        )

        messagebox.showinfo("Success", f"Contact Saved Successfully:\n\n{contact}")
        self.clearForm()