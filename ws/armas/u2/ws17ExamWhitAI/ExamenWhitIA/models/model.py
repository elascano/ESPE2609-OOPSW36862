import json

class SoundMixer:
    def __init__(self, id, brand, channels, has_effects):
        self.id = id
        self.brand = brand
        self.channels = channels
        self.has_effects = has_effects

    def to_dict(self):
        return {
            'id': self.id,
            'brand': self.brand,
            'channels': self.channels,
            'has_effects': self.has_effects
        }

    @classmethod
    def from_dict(cls, data):
        return cls(data['id'], data['brand'], data['channels'], data['has_effects'])


class MixerModel:
    def __init__(self, filename="sound_mixers.json"):
        self.filename = filename
        self.mixers_list = []

    def save_to_json(self):
        with open(self.filename, 'w') as file:
            json.dump([mixer.to_dict() for mixer in self.mixers_list], file, indent=4)

    def load_from_json(self):
        try:
            with open(self.filename, 'r') as file:
                data = json.load(file)
                self.mixers_list = [SoundMixer.from_dict(item) for item in data]
        except FileNotFoundError:
            self.mixers_list = []