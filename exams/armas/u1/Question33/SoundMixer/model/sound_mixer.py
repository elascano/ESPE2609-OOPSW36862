class SoundMixer:
    def __init__(self, mixer_id, brand, model, channels):
        self.id = mixer_id
        self.brand = brand
        self.model = model
        self.channels = channels

    def to_dict(self):
        return {
            "id": self.id,
            "brand": self.brand,
            "model": self.model,
            "channels": self.channels,
        }

    @classmethod
    def from_dict(cls, data):
        return cls(data["id"], data["brand"], data["model"], data["channels"])
