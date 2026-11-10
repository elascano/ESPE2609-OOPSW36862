from pathlib import Path

from mixer_file import MixerFile
from sound_mixer import SoundMixer


def read_positive_integer(message):
    while True:
        try:
            value = int(input(message))
            if value > 0:
                return value
        except ValueError:
            pass
        print("Please enter a positive integer.")


def read_text(message):
    while True:
        value = input(message).strip()
        if value:
            return value
        print("This field cannot be empty.")


def create_mixer(mixer_file):
    mixers = mixer_file.load()
    mixer_id = read_positive_integer("ID: ")

    for mixer in mixers:
        if mixer.id == mixer_id:
            print("That ID already exists.")
            return

    brand = read_text("Brand: ")
    model = read_text("Model: ")
    channels = read_positive_integer("Channels: ")

    mixer = SoundMixer(mixer_id, brand, model, channels)
    mixers.append(mixer)
    mixer_file.save(mixers)
    print("Sound mixer saved successfully.")


def read_mixers(mixer_file):
    mixers = mixer_file.load()
    if not mixers:
        print("No sound mixers found.")
        return

    print("\nSAVED SOUND MIXERS")
    for mixer in mixers:
        print(f"ID: {mixer.id}")
        print(f"Brand: {mixer.brand}")
        print(f"Model: {mixer.model}")
        print(f"Channels: {mixer.channels}\n")


def main():
    file_path = Path(__file__).resolve().parent / "data" / "sound_mixers.json"
    mixer_file = MixerFile(file_path)

    while True:
        print("\nSOUND MIXER")
        print("1. Create sound mixer")
        print("2. Read from JSON")
        print("3. Exit")

        try:
            option = input("Choose an option: ").strip()
            if option == "1":
                create_mixer(mixer_file)
            elif option == "2":
                read_mixers(mixer_file)
            elif option == "3":
                print("Goodbye!")
                break
            else:
                print("Invalid option.")
        except (ValueError, KeyError, TypeError, OSError) as error:
            print(f"Error: {error}")
        except (EOFError, KeyboardInterrupt):
            print("\nGoodbye!")
            break


if __name__ == "__main__":
    main()
