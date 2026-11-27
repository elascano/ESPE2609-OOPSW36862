import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime, date
import re
from model.contact import Contact


def save_contact():
    id_text = fields["ID:"].get().strip()
    full_name = fields["Full Name:"].get().strip()
    email = fields["E-mail:"].get().strip()
    phone = fields["Cell Phone Number:"].get().strip()
    birth_date_text = birth_date_entry.get().strip()

    if not id_text.isascii() or not id_text.isdigit():
        messagebox.showerror("Invalid ID", "ID must contain only numbers.")
        fields["ID:"].focus_set()
        return

    id = int(id_text)
    if id <= 0:
        messagebox.showerror("Invalid ID", "ID must be greater than zero.")
        fields["ID:"].focus_set()
        return

    if len(full_name) < 2 or not all(c.isalpha() or c == " " for c in full_name):
        messagebox.showerror("Invalid name", "Enter a valid full name without numbers.")
        fields["Full Name:"].focus_set()
        return

    if not re.fullmatch(r"[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}", email):
        messagebox.showerror("Invalid email", "Enter a valid email, for example: ana@gmail.com")
        fields["E-mail:"].focus_set()
        return

    if not re.fullmatch(r"09[0-9]{8}", phone):
        messagebox.showerror("Invalid phone", "Phone number must have 10 digits and start with 09.")
        fields["Cell Phone Number:"].focus_set()
        return

    try:
        if not re.fullmatch(r"[0-9]{2}/[0-9]{2}/[0-9]{4}", birth_date_text):
            raise ValueError
        birth_date = datetime.strptime(birth_date_text, "%d/%m/%Y").date()
    except ValueError:
        messagebox.showerror("Invalid date", "Invalid birth date. Use dd/MM/yyyy.")
        birth_date_entry.focus_set()
        return

    if birth_date > date.today():
        messagebox.showerror("Invalid date", "Birth date cannot be in the future.")
        birth_date_entry.focus_set()
        return

    selected_hobbies = [hobbies_list.get(i) for i in hobbies_list.curselection()]

    contact = Contact(
        id, full_name, email, phone,
        fields["Instagram:"].get().strip(),
        fields["Facebook:"].get().strip(),
        known.get(), contact_type.get(), gender.get(), selected_hobbies,
        birth_date, comments.get("1.0", tk.END).strip()
    )

    messagebox.showinfo("Contact details", str(contact))


def clear_form():
    for entry in fields.values():
        entry.delete(0, tk.END)
    known.set(False)
    contact_type.current(0)
    gender.current(0)
    hobbies_list.selection_clear(0, tk.END)
    birth_date_entry.delete(0, tk.END)
    comments.delete("1.0", tk.END)


window = tk.Tk()
window.title("Contacts Book")
window.geometry("760x580")

heading = tk.Label(window, text="ADD CONTACT", font=("Arial", 16, "bold"))
heading.pack(pady=20)

form = tk.Frame(window)
form.pack(padx=15, pady=10)

left = tk.Frame(form)
left.grid(row=0, column=0, padx=15, sticky="n")
right = tk.Frame(form)
right.grid(row=0, column=1, padx=15, sticky="n")

fields = {}
field_names = ["ID:", "Full Name:", "E-mail:", "Cell Phone Number:",
               "Instagram:", "Facebook:"]

for i, name in enumerate(field_names):
    tk.Label(left, text=name).grid(row=i, column=0, sticky="e", padx=8, pady=8)
    entry = tk.Entry(left, width=25)
    entry.grid(row=i, column=1, pady=8)
    fields[name] = entry

known = tk.BooleanVar()
tk.Label(left, text="Known:").grid(row=6, column=0, sticky="e", padx=8, pady=8)
tk.Checkbutton(left, text="Yes", variable=known).grid(row=6, column=1, sticky="w")

tk.Label(right, text="Type:").grid(row=0, column=0, sticky="e", padx=8, pady=8)
contact_type = ttk.Combobox(right, width=19, state="readonly",
                            values=["Friend", "Family", "Job Mate", "Team Mate", "High School", "Gym"])
contact_type.grid(row=0, column=1, pady=8)
contact_type.current(0)

tk.Label(right, text="Gender:").grid(row=1, column=0, sticky="e", padx=8, pady=8)
gender = ttk.Combobox(right, width=19, state="readonly", values=["M", "F"])
gender.grid(row=1, column=1, pady=8)
gender.current(0)

tk.Label(right, text="Hobbies:").grid(row=2, column=0, sticky="ne", padx=8, pady=8)
hobbies_list = tk.Listbox(right, width=22, height=8, selectmode=tk.MULTIPLE, exportselection=False)
hobbies_list.grid(row=2, column=1, pady=8)

for hobby in ["Play the Guitar", "Watch movies", "Play Soccer", "Play Basketball",
              "Cooking", "Reading", "Running", "Swimming"]:
    hobbies_list.insert(tk.END, hobby)

tk.Label(right, text="Birth Date:").grid(row=3, column=0, sticky="e", padx=8, pady=8)
birth_date_entry = tk.Entry(right, width=23)
birth_date_entry.grid(row=3, column=1, pady=8)

tk.Label(right, text="Comments:").grid(row=4, column=0, sticky="ne", padx=8, pady=8)
comments = tk.Text(right, width=22, height=5)
comments.grid(row=4, column=1, pady=8)

buttons = tk.Frame(window)
buttons.pack(pady=15)

tk.Button(buttons, text="Save", width=12, command=save_contact).grid(row=0, column=0, padx=35)
tk.Button(buttons, text="Cancel", width=12, command=clear_form).grid(row=0, column=1, padx=35)

window.mainloop()
