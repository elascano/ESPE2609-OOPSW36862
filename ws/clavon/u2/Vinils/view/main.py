
from pathlib import Path

from model.vinyl_catalog import VinylCatalog
from view.vinyl_view import VinylView


class VinylApplication:

    def __init__(self):
        self.view = VinylView()

        project_path = Path(__file__).resolve().parent.parent
        json_path = project_path / "vinyl_records.json"

        self.catalog = VinylCatalog(json_path)

    def add_vinyl_record(self):
        record = self.view.get_record_data()

        try:
            if self.catalog.add_record(record):
                self.view.show_message(
                    "Vinyl record saved successfully!"
                )
            else:
                self.view.show_message(
                    "A vinyl record with this ID already exists."
                )

        except OSError as error:
            self.view.show_message(
                f"Error saving vinyl record: {error}"
            )

    def show_all_records(self):
        records = self.catalog.get_all_records()
        self.view.show_records(records)

    def search_vinyl_record(self):
        record_id = self.view.get_positive_integer(
            "Enter record ID: "
        )

        record = self.catalog.find_by_id(record_id)

        if record is not None:
            self.view.show_record(record)
        else:
            self.view.show_message(
                "Vinyl record not found."
            )

    def read_json_records(self):
        try:
            total = self.catalog.load_from_json()

            self.view.show_message(
                f"{total} vinyl record(s) loaded from JSON."
            )

            self.show_all_records()

        except ValueError as error:
            self.view.show_message(str(error))

    def run(self):
        while True:
            self.view.show_menu()
            option = self.view.get_option()

            if option == "1":
                self.add_vinyl_record()

            elif option == "2":
                self.show_all_records()

            elif option == "3":
                self.search_vinyl_record()

            elif option == "4":
                self.read_json_records()

            elif option == "0":
                self.view.show_message(
                    "Thank you for using Vinyl Record Manager!"
                )
                break

            else:
                self.view.show_message(
                    "Invalid option. Please try again."
                )


def main():
    try:
        application = VinylApplication()
        application.run()

    except ValueError as error:
        print(f"Application error: {error}")

    except (KeyboardInterrupt, EOFError):
        print("\nApplication closed.")


if __name__ == "__main__":
    main()
