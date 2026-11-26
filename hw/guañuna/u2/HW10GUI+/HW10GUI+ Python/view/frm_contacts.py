import tkinter as tk
from tkinter import messagebox, ttk
from datetime import datetime
import re

from model.contact import Contact

class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("ContactBook - Agregar Contacto")
        self.geometry("700x650")
        
        self.error_log = []
        self.crear_menu()
        self.crear_formulario()

    def crear_menu(self):
        menubar = tk.Menu(self)

        menu_archivo = tk.Menu(menubar, tearoff=0)
        menu_archivo.add_command(label="Nuevo Contacto", command=self.limpiar_formulario)
        menu_archivo.add_separator()
        menu_archivo.add_command(label="Salir", command=self.quit)
        menubar.add_cascade(label="Archivo", menu=menu_archivo)

        menu_ayuda = tk.Menu(menubar, tearoff=0)
        menu_ayuda.add_command(label="Errores (0)", command=self.mostrar_errores)
        menu_ayuda.add_separator()
        menu_ayuda.add_command(label="Acerca de...", command=self.acerca_de)
        menubar.add_cascade(label="Ayuda", menu=menu_ayuda)

        self.config(menu=menubar)
        self.menu_ayuda = menu_ayuda

    def crear_formulario(self):
        frame_datos = tk.LabelFrame(self, text="Datos del Contacto", padx=10, pady=10)
        frame_datos.pack(padx=20, pady=10, fill="both", expand=True)

        frame_datos.columnconfigure(1, weight=1)
        frame_datos.columnconfigure(3, weight=1)

        tk.Label(frame_datos, text="ID:").grid(row=0, column=0, sticky="e", pady=2)
        self.txt_id = tk.Entry(frame_datos)
        self.txt_id.grid(row=0, column=1, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Tipo:").grid(row=0, column=2, sticky="e", pady=2)
        self.cmb_tipo = ttk.Combobox(frame_datos, values=["Friend", "Family", "Job Mate", "Gym Mate", "Highschool"])
        self.cmb_tipo.current(0)
        self.cmb_tipo.grid(row=0, column=3, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Full Name:").grid(row=1, column=0, sticky="e", pady=2)
        self.txt_nombre = tk.Entry(frame_datos)
        self.txt_nombre.grid(row=1, column=1, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Email:").grid(row=2, column=0, sticky="e", pady=2)
        self.txt_email = tk.Entry(frame_datos)
        self.txt_email.grid(row=2, column=1, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Género:").grid(row=2, column=2, sticky="e", pady=2)
        self.cmb_genero = ttk.Combobox(frame_datos, values=["M", "F"], width=10)
        self.cmb_genero.current(0)
        self.cmb_genero.grid(row=2, column=3, sticky="w", pady=2, padx=5)

        tk.Label(frame_datos, text="Celular:").grid(row=3, column=0, sticky="e", pady=2)
        self.txt_celular = tk.Entry(frame_datos)
        self.txt_celular.grid(row=3, column=1, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Instagram:").grid(row=4, column=0, sticky="e", pady=2)
        self.txt_instagram = tk.Entry(frame_datos)
        self.txt_instagram.grid(row=4, column=1, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Hobbies:").grid(row=4, column=2, sticky="ne", pady=2)
        self.lst_hobbies = tk.Listbox(frame_datos, selectmode="multiple", height=5)
        hobbies = ["Pay the guitar", "Watch movies", "Play soccer", "Play Basketball", "Reading", "Cooking", "Swimming", "Running"]
        for h in hobbies:
            self.lst_hobbies.insert(tk.END, h)
        self.lst_hobbies.grid(row=4, column=3, sticky="w", pady=2, padx=5)

        tk.Label(frame_datos, text="Facebook:").grid(row=5, column=0, sticky="e", pady=2)
        self.txt_facebook = tk.Entry(frame_datos)
        self.txt_facebook.grid(row=5, column=1, sticky="we", pady=2, padx=5)

        tk.Label(frame_datos, text="Conocido:").grid(row=6, column=0, sticky="e", pady=2)
        self.chk_conocido = tk.BooleanVar()
        tk.Checkbutton(frame_datos, text="Sí", variable=self.chk_conocido).grid(row=6, column=1, sticky="w", pady=2, padx=5)

        tk.Label(frame_datos, text="Fecha Nac. (dd/mm/yyyy):").grid(row=6, column=2, sticky="e", pady=2)
        self.txt_fecha = tk.Entry(frame_datos)
        self.txt_fecha.grid(row=6, column=3, sticky="w", pady=2, padx=5)

        tk.Label(frame_datos, text="Comentarios:").grid(row=7, column=0, sticky="ne", pady=2)
        self.txt_comentarios = tk.Text(frame_datos, height=4, width=30)
        self.txt_comentarios.grid(row=7, column=1, columnspan=3, sticky="we", pady=2, padx=5)

        frame_botones = tk.Frame(self)
        frame_botones.pack(pady=10)

        btn_guardar = tk.Button(frame_botones, text="Save", command=self.guardar, width=10, bg="lightgreen")
        btn_guardar.pack(side=tk.LEFT, padx=10)

        btn_cancelar = tk.Button(frame_botones, text="Cancel", command=self.limpiar_formulario, width=10, bg="lightcoral")
        btn_cancelar.pack(side=tk.LEFT, padx=10)

    def limpiar_formulario(self):
        self.txt_id.delete(0, tk.END)
        self.txt_nombre.delete(0, tk.END)
        self.txt_email.delete(0, tk.END)
        self.txt_celular.delete(0, tk.END)
        self.txt_instagram.delete(0, tk.END)
        self.txt_facebook.delete(0, tk.END)
        self.txt_fecha.delete(0, tk.END)
        self.txt_comentarios.delete("1.0", tk.END)
        self.chk_conocido.set(False)
        self.lst_hobbies.selection_clear(0, tk.END)

    def registrar_error(self, mensaje):
        hora = datetime.now().strftime("%H:%M:%S")
        self.error_log.append(f"[{hora}] {mensaje}")
        self.menu_ayuda.entryconfig(0, label=f"Errores ({len(self.error_log)})")
        messagebox.showerror("Error de validación", mensaje)

    def mostrar_errores(self):
        if not self.error_log:
            messagebox.showinfo("Historial de errores", "No hay errores registrados.")
            return
        messagebox.showwarning("Historial de errores", "\n".join(self.error_log))

    def acerca_de(self):
        messagebox.showinfo("Acerca de", "ContactBook v1.0\nAutor: Cristopher Guañuna\nESPE - Runtime Dynamics")

    def guardar(self):
        try:
            id_contact = int(self.txt_id.get().strip())
        except ValueError:
            self.registrar_error("El ID debe ser un número entero.")
            return

        full_name = self.txt_nombre.get().strip()
        if not re.match(r"^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", full_name):
            self.registrar_error("El nombre solo debe contener letras y espacios.")
            return

        email = self.txt_email.get().strip()
        if not re.match(r"^[\w\-\.]+@([\w\-]+\.)+[\w\-]{2,4}$", email):
            self.registrar_error("Ingrese un correo electrónico válido (ejemplo@dominio.com).")
            return

        cell_phone = self.txt_celular.get().strip()
        if not re.match(r"^\d{10}$", cell_phone):
            self.registrar_error("El número de celular debe tener 10 dígitos numéricos.")
            return

        fecha_str = self.txt_fecha.get().strip()
        try:
            birth_date = datetime.strptime(fecha_str, "%d/%m/%Y")
        except ValueError:
            self.registrar_error("Ingrese la fecha en formato dd/mm/yyyy (ejemplo: 25/12/2000).")
            return

        instagram = self.txt_instagram.get().strip()
        facebook = self.txt_facebook.get().strip()
        is_known = self.chk_conocido.get()
        type_contact = self.cmb_tipo.get()
        gender = self.cmb_genero.get()
        
        seleccion = self.lst_hobbies.curselection()
        hobbies = [self.lst_hobbies.get(i) for i in seleccion]
        
        comments = self.txt_comentarios.get("1.0", tk.END).strip()

        contacto = Contact(id_contact, full_name, email, cell_phone, instagram, facebook, is_known, type_contact, gender, hobbies, birth_date, comments)
        messagebox.showinfo("Contacto Guardado", str(contacto))