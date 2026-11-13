import os

from src.data.video_projector_json_file import VideoProjectorJsonFile
from src.view.console_menu import ConsoleMenu


def main() -> None:
    """Entry point of the program. The JSON file is stored in the "data" folder."""
    project_folder = os.path.dirname(os.path.abspath(__file__))
    file_path = os.path.join(project_folder, "data", "videoProjectors.json")

    json_file = VideoProjectorJsonFile(file_path)
    menu = ConsoleMenu(json_file)
    menu.start()


if __name__ == "__main__":
    main()
