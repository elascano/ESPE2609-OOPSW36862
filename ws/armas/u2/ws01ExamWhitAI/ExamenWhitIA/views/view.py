class MixerView:
    @staticmethod
    def show_menu():
        print("\n===============================")
        print("    SOUND MIXER MANAGEMENT     ")
        print("===============================")
        print("1. Add SoundMixer to JSON")
        print("2. Read & Display SoundMixers from JSON")
        print("3. Exit")
        return input("Select an option (1-3): ")

    @staticmethod
    def input_sound_mixer():
        print("\n--- Enter SoundMixer Details ---")
        mixer_id = int(input("Enter ID: "))
        brand = input("Enter Brand: ")
        channels = int(input("Enter Channels count: "))
        effects_input = input("Has Effects? (y/n): ").strip().lower()
        has_effects = effects_input == 'y'
        return mixer_id, brand, channels, has_effects

    @staticmethod
    def display_mixers(mixers, section_title):
        print(f"\n--- {section_title} ---")
        if not mixers:
            print("No SoundMixers available to display.")
            return
        for mixer in mixers:
            print(f"ID: {mixer.id}, Brand: {mixer.brand}, Channels: {mixer.channels}, Effects: {mixer.has_effects}")

    @staticmethod
    def display_message(message):
        print(message)