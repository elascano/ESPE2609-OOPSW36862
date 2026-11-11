
from datetime import date

from model.vinyl_record import VinylRecord


class VinylView:

    def show_menu(self):
        print("\n========== VINYL RECORD MANAGER ==========")
        print("1. Add vinyl record")
        print("2. Show all vinyl records")
        print("3. Search vinyl record by ID")
        print("4. Read records from JSON")
        print("0. Exit")
        print("==========================================")

    def get_option(self):
        return input("Select an option: ").strip()

    def get_record_data(self):
        print("\n---------- NEW VINYL RECORD ----------")

        record_id = self.get_positive_integer("Enter ID: ")
        title = self.get_required_text("Enter title: ")
        artist = self.get_required_text("Enter artist: ")
        year = self.get_positive_integer(
            "Enter release year: ",
            date.today().year
        )

        return VinylRecord(record_id, title, artist, year)

    def get_positive_integer(self, message, maximum=None):
        while True:
            try:
                value = int(input(message))

                if value > 0 and (maximum is None or value <= maximum):
                    return value

                print("Invalid number. Please try again.")

            except ValueError:
                print("Please enter a valid integer.")

    def get_required_text(self, message):
        while True:
            value = input(message).strip()

            if value:
                return value

            print("This field cannot be empty.")

    def show_record(self, record):
        print("------------------------------------")
        print(f"ID: {record.id}")
        print(f"Title: {record.title}")
        print(f"Artist: {record.artist}")
        print(f"Release year: {record.year}")

    def show_records(self, records):
        if not records:
            self.show_message("No vinyl records found.")
            return

        print("\n========== VINYL RECORD LIST ==========")

        for record in records:
            self.show_record(record)

        print("------------------------------------")

    def show_message(self, message):
        print(message)
