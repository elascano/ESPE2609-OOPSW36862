class SoccerPlayer:

    def __init__(self, id, name, position, shirt_number):
        self.__id = id
        self.__name = name
        self.__position = position
        self.__shirt_number = shirt_number

    def get_id(self):
        return self.__id

    def get_name(self):
        return self.__name

    def get_position(self):
        return self.__position

    def get_shirt_number(self):
        return self.__shirt_number

    def set_id(self, id):
        self.__id = id

    def set_name(self, name):
        self.__name = name

    def set_position(self, position):
        self.__position = position

    def set_shirt_number(self, shirt_number):
        self.__shirt_number = shirt_number

    def to_dictionary(self):
        return {
            "id": self.__id,
            "name": self.__name,
            "position": self.__position,
            "shirtNumber": self.__shirt_number
        }

    @staticmethod
    def from_dictionary(data):
        return SoccerPlayer(data["id"], data["name"], data["position"], data["shirtNumber"])

    def __str__(self):
        return ("Id: " + str(self.__id)
                + " | Name: " + self.__name
                + " | Position: " + self.__position
                + " | Shirt number: " + str(self.__shirt_number))
