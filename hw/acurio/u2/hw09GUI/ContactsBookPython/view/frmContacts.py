import tkinter as tk
from tkinter import ttk

window = tk.Tk()
window.title("Contacts Book")
window.geometry("730x520")

label_title = tk.Label(window, text="CONTACT", font=("Arial", 16, "bold"))
label_title.pack(pady=15)

frame = tk.Frame(window)
frame.pack(padx=20, pady=10)

left = tk.Frame(frame)
left.grid(row=0, column=0, padx=25, sticky="n")

right = tk.Frame(frame)
right.grid(row=0, column=1, padx=25, sticky="n")

labels = ["ID:", "Full Name:", "E-mail:", "Cell Phone Number:",
          "Instagram:", "Facebook:"]
entries = []


for i, name in enumerate(labels):
    tk.Label(left, text=name).grid(row=i, column=0, sticky="e", padx=10, pady=7)
    entry = tk.Entry(left, width=27)
    entry.grid(row=i, column=1, pady=7)
    entries.append(entry)


known = tk.BooleanVar()
tk.Label(left, text="Known:").grid(row=6, column=0, sticky="e", padx=10, pady=7)
tk.Checkbutton(left, text="Yes", variable=known).grid(row=6, column=1, sticky="w")

tk.Label(right, text="Type:").grid(row=0, column=0, sticky="w", pady=7)
contact_type = ttk.Combobox(right, state="readonly", width=20,
    values=["Friend", "Family", "Job Mate", "Team Mate", "High School", "Gym"])
contact_type.grid(row=0, column=1, pady=7)
contact_type.current(0)

tk.Label(right, text="Gender:").grid(row=1, column=0, sticky="w", pady=7)
gender = ttk.Combobox(right, state="readonly", values=["Male", "Female"], width=20)
gender.grid(row=1, column=1, pady=7)
gender.current(0)

tk.Label(right, text="Hobbies:").grid(row=2, column=0, sticky="nw", pady=7)
hobbies = tk.Listbox(right, height=8, width=22, selectmode=tk.MULTIPLE,
                     exportselection=False)
hobbies.grid(row=2, column=1, pady=7)

for hobby in ["Play the Guitar", "Watch movies", "Play Soccer",
              "Play Basketball", "Cooking", "Reading", "Running", "Swimming"]:
    hobbies.insert(tk.END, hobby)

tk.Label(right, text="BirthDate:").grid(row=3, column=0, sticky="w", pady=7)
birth_date = tk.Entry(right, width=23)
birth_date.grid(row=3, column=1, pady=7)

tk.Label(right, text="Comments:").grid(row=4, column=0, sticky="nw", pady=7)
comments = tk.Text(right, width=23, height=5)
comments.grid(row=4, column=1, pady=7)

window.mainloop()
