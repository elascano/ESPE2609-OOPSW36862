from src.model.video_projector import VideoProjector
from src.data.video_projector_json_file import VideoProjectorJsonFile


class ConsoleMenu:
    """
    View class: shows the menu, asks the user for data
    and uses the JSON file class to create and read video projectors.
    """

    def __init__(self, json_file: VideoProjectorJsonFile):
        self.__json_file = json_file
        self.__video_projector_list = json_file.read()

    def __ask_positive_number(self, question: str) -> int:
        """Asks until the user types a whole number greater than 0"""
        while True:
            answer = input(question).strip()
            if answer.isdigit() and int(answer) > 0:
                return int(answer)
            print("  Invalid value. Enter a whole number greater than 0.")

    def __ask_text(self, question: str) -> str:
        """Asks until the user types some text"""
        while True:
            answer = input(question).strip()
            if answer != "":
                return answer
            print("  This field cannot be empty.")

    def __id_exists(self, id: int) -> bool:
        """Checks if an id is already in the list"""
        for video_projector in self.__video_projector_list:
            if video_projector.get_id() == id:
                return True
        return False

    def __create_video_projector(self) -> None:
        """Option 1: data input and saving into the JSON file"""
        print("\n--- CREATE VIDEO PROJECTOR ---")

        id = self.__ask_positive_number("Id: ")
        while self.__id_exists(id):
            print("  That id already exists. Try another one.")
            id = self.__ask_positive_number("Id: ")

        brand = self.__ask_text("Brand: ")
        lumens = self.__ask_positive_number("Lumens: ")
        resolution = self.__ask_text("Resolution (e.g. 1920x1080): ")

        video_projector = VideoProjector(id, brand, lumens, resolution)
        self.__video_projector_list.append(video_projector)
        self.__json_file.save(self.__video_projector_list)

        print("Video projector saved in " + self.__json_file.get_file_path())

    def __show_video_projectors(self) -> None:
        """Option 2: reading from the JSON file"""
        print("\n--- VIDEO PROJECTORS IN THE JSON FILE ---")

        self.__video_projector_list = self.__json_file.read()

        if len(self.__video_projector_list) == 0:
            print("There are no video projectors registered.")
            return

        for video_projector in self.__video_projector_list:
            print(video_projector)
        print("Total: " + str(len(self.__video_projector_list)))

    def start(self) -> None:
        """Main loop of the program"""
        option = ""

        while option != "0":
            print("\n===== VIDEO PROJECTOR MANAGER =====")
            print("1. Create video projector")
            print("2. Show video projectors (read JSON)")
            print("0. Exit")
            option = input("Choose an option: ").strip()

            if option == "1":
                self.__create_video_projector()
            elif option == "2":
                self.__show_video_projectors()
            elif option == "0":
                print("Goodbye!")
            else:
                print("Invalid option.")
