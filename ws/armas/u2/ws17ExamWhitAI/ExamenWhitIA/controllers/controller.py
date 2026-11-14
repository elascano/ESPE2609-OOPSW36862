from models.model import SoundMixer, MixerModel
from views.view import MixerView

class MixerController:
    def __init__(self):
        self.model = MixerModel()
        self.view = MixerView()

    def run(self):
        while True:
            choice = self.view.show_menu()
            
            if choice == '1':
                # Read initial list from JSON if exists, then append new item
                self.model.load_from_json()
                mixer_id, brand, channels, has_effects = self.view.input_sound_mixer()
                new_mixer = SoundMixer(mixer_id, brand, channels, has_effects)
                self.model.mixers_list.append(new_mixer)
                self.model.save_to_json()
                self.view.display_message("\n[System]: SoundMixer successfully created and saved to JSON file.")
                
            elif choice == '2':
                # Read and display items from JSON
                self.model.load_from_json()
                self.view.display_mixers(self.model.mixers_list, "SoundMixers Read from JSON File")
                
            elif choice == '3':
                self.view.display_message("\nExiting program...")
                break
            else:
                self.view.display_message("\nInvalid option. Please enter 1, 2, or 3.")