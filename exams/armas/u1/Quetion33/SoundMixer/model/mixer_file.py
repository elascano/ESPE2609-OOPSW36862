import json

from sound_mixer import SoundMixer


class MixerFile:
    def __init__(self, file_path):
        self.file_path = file_path

    def load(self):
        try:
            with open(self.file_path, "r", encoding="utf-8") as file:
                data = json.load(file)
        except FileNotFoundError:
            return []

        if not isinstance(data, list):
            raise ValueError("The JSON file must contain a list.")

        mixers = []
        for item in data:
            mixers.append(SoundMixer.from_dict(item))
        return mixers

    def save(self, mixers):
        data = [mixer.to_dict() for mixer in mixers]
        self.file_path.parent.mkdir(parents=True, exist_ok=True)
        with open(self.file_path, "w", encoding="utf-8") as file:
            json.dump(data, file, indent=4)
            file.write("\n")
