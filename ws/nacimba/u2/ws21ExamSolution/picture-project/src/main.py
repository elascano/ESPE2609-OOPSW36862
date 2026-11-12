import os

from src.models.picture import Picture
from src.services.picture_repository import PictureRepository

FILE = os.path.join(os.path.dirname(__file__), "..", "data", "pictures.json")


def main() -> None:
    repo = PictureRepository(os.path.normpath(FILE))

    while True:
        print("\n=== PICTURE MANAGER ===")
        print("1. Add picture\n2. List pictures in memory\n3. Save to JSON\n4. Read from JSON\n5. Exit")
        option = input("Choose an option: ").strip()

        try:
            if option == "1":
                id_ = int(input("Id (number): "))
                title = input("Title: ")
                author = input("Author: ")
                year = int(input("Year: "))
                repo.add(Picture(id_, title, author, year))
                print("Picture added.")
            elif option == "2":
                pictures = repo.get_all()
                if pictures:
                    for p in pictures:
                        print(p)
                else:
                    print("(empty)")
            elif option == "3":
                repo.save()
                print("Saved to JSON.")
            elif option == "4":
                pictures = repo.load()
                print(f"Read {len(pictures)} picture(s) from JSON:")
                for p in pictures:
                    print(p)
            elif option == "5":
                break
            else:
                print("Invalid option.")
        except ValueError as e:
            print("Error:", e)
        except FileNotFoundError as e:
            print("Error:", e)


if __name__ == "__main__":
    main()
