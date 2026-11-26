class Contact:
    def __init__(self, id_contact, full_name, email, cell_phone, instagram, facebook, is_known, type_contact, gender, hobbies, birth_date, comments):
        self.id = id_contact
        self.full_name = full_name
        self.email = email
        self.cell_phone = cell_phone
        self.instagram = instagram
        self.facebook = facebook
        self.is_known = is_known
        self.type = type_contact
        self.gender = gender
        self.hobbies = hobbies
        self.birth_date = birth_date
        self.comments = comments

    def __str__(self):
        return (f"ID: {self.id}\n"
                f"Nombre: {self.full_name}\n"
                f"Email: {self.email}\n"
                f"Celular: {self.cell_phone}\n"
                f"Instagram: {self.instagram}\n"
                f"Facebook: {self.facebook}\n"
                f"Conocido: {self.is_known}\n"
                f"Tipo: {self.type}\n"
                f"Género: {self.gender}\n"
                f"Hobbies: {', '.join(self.hobbies)}\n"
                f"Fecha Nacimiento: {self.birth_date.strftime('%d/%m/%Y')}\n"
                f"Comentarios: {self.comments}")