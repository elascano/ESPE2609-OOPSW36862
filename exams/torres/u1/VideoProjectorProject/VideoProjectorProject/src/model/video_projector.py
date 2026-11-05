class VideoProjector:
    """
    Model class: represents a Video Projector.
    Attributes: id (mandatory) + brand, lumens and resolution.
    """

    def __init__(self, id: int, brand: str, lumens: int, resolution: str):
        # Private attributes (encapsulation)
        self.__id = id
        self.__brand = brand
        self.__lumens = lumens
        self.__resolution = resolution

    # ---------- Getters ----------
    def get_id(self) -> int:
        return self.__id

    def get_brand(self) -> str:
        return self.__brand

    def get_lumens(self) -> int:
        return self.__lumens

    def get_resolution(self) -> str:
        return self.__resolution

    # ---------- Setters ----------
    def set_id(self, id: int) -> None:
        self.__id = id

    def set_brand(self, brand: str) -> None:
        self.__brand = brand

    def set_lumens(self, lumens: int) -> None:
        self.__lumens = lumens

    def set_resolution(self, resolution: str) -> None:
        self.__resolution = resolution

    def to_dictionary(self) -> dict:
        """Returns the object as a dictionary to save it in the JSON file"""
        return {
            "id": self.__id,
            "brand": self.__brand,
            "lumens": self.__lumens,
            "resolution": self.__resolution
        }

    def __str__(self) -> str:
        """Text representation of the object"""
        return ("VideoProjector{id=" + str(self.__id) +
                ", brand='" + self.__brand + "'" +
                ", lumens=" + str(self.__lumens) +
                ", resolution='" + self.__resolution + "'}")
