class Contact:
    def __init__(self, id, full_name, email, cell_phone_number,
                 instagram, facebook, is_known, type, gender,
                 hobbies, birth_date, comments):
        self.id = id
        self.full_name = full_name
        self.email = email
        self.cell_phone_number = cell_phone_number
        self.instagram = instagram
        self.facebook = facebook
        self.is_known = is_known
        self.type = type
        self.gender = gender
        self.hobbies = hobbies
        self.birth_date = birth_date
        self.comments = comments

    def __str__(self):
        return (f"ID: {self.id}\n"
                f"Full Name: {self.full_name}\n"
                f"Email: {self.email}\n"
                f"Cell Phone Number: {self.cell_phone_number}\n"
                f"Instagram: {self.instagram}\n"
                f"Facebook: {self.facebook}\n"
                f"Known: {self.is_known}\n"
                f"Type: {self.type}\n"
                f"Gender: {self.gender}\n"
                f"Hobbies: {', '.join(self.hobbies)}\n"
                f"Birth Date: {self.birth_date}\n"
                f"Comments: {self.comments}")
