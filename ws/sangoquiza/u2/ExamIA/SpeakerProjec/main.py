
from speaker import Speaker
from speaker_manager import SpeakerManager


def main():

    manager = SpeakerManager()

    # Load previously saved data
    manager.load_from_json()

    while True:

        print("\n===== SPEAKER MANAGEMENT =====")
        print("1. Register Speaker")
        print("2. Save Speakers to JSON")
        print("3. Read Speakers from JSON")
        print("4. Exit")

        option = input("Select an option: ")

        if option == "1":

            try:
                id = int(input("Enter ID: "))
                brand = input("Enter Brand: ").strip()
                power = int(input("Enter Power (Watts): "))
                price = float(input("Enter Price: "))

                if id <= 0 or power <= 0 or price < 0 or not brand:
                    print("Error: Enter valid speaker information!")
                    continue

                speaker = Speaker(id, brand, power, price)

                manager.add_speaker(speaker)

            except ValueError:
                print("Error: Please enter valid numeric values!")

        elif option == "2":

            manager.save_to_json()

        elif option == "3":

            manager.read_from_json()

        elif option == "4":

            print("Program finished!")
            break

        else:

            print("Invalid option! Try again.")


if __name__ == "__main__":
    main()
