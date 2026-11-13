
import json
import os

from speaker import Speaker


class SpeakerManager:

    def __init__(self):
        self.speakers = []
        self.FILE = "speakers.json"

    # Register a speaker
    def add_speaker(self, speaker):

        for s in self.speakers:
            if s.id == speaker.id:
                print("Error: ID already exists!")
                return False

        self.speakers.append(speaker)
        print("Speaker registered successfully!")
        return True

    # Save speakers to JSON
    def save_to_json(self):

        try:
            data = [speaker.to_dict() for speaker in self.speakers]

            with open(self.FILE, "w", encoding="utf-8") as file:
                json.dump(data, file, indent=4)

            print("Speakers saved successfully!")

        except (OSError, TypeError) as error:
            print("Error saving JSON:", error)

    # Read speakers from JSON
    def read_from_json(self):

        try:
            with open(self.FILE, "r", encoding="utf-8") as file:
                data = json.load(file)

            self.speakers = [
                Speaker(
                    item["id"],
                    item["brand"],
                    item["power"],
                    item["price"]
                )
                for item in data
            ]

            print("\n===== SPEAKER LIST =====")

            for speaker in self.speakers:
                speaker.display_info()

            print("Total speakers:", len(self.speakers))

        except FileNotFoundError:
            print("JSON file does not exist yet!")

        except (json.JSONDecodeError, KeyError, TypeError) as error:
            print("Error reading JSON:", error)

        except OSError as error:
            print("File error:", error)

    # Load saved speakers when the program starts
    def load_from_json(self):

        if os.path.exists(self.FILE):
            try:
                with open(self.FILE, "r", encoding="utf-8") as file:
                    data = json.load(file)

                self.speakers = [
                    Speaker(
                        item["id"],
                        item["brand"],
                        item["power"],
                        item["price"]
                    )
                    for item in data
                ]

            except (json.JSONDecodeError, KeyError, TypeError, OSError) as error:
                print("Could not load saved speakers:", error)
