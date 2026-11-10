"""
@author Andres Pachacama, Byte Work, @ESPE
"""


class Doll:

    # ===================== CONSTRUCTOR =====================

    def __init__(self, id, name, brand, price):
        self.id = id
        self.name = name
        self.brand = brand
        self.price = price

    # ===================== JSON =====================

    def to_dictionary(self):
        return {
            "id": self.id,
            "name": self.name,
            "brand": self.brand,
            "price": self.price
        }

    @staticmethod
    def from_dictionary(dictionary):
        return Doll(int(dictionary["id"]),
                    str(dictionary["name"]),
                    str(dictionary["brand"]),
                    float(dictionary["price"]))

    # ===================== toString =====================

    def __str__(self):
        return ("Doll{id=" + str(self.id) + ", name=" + self.name
                + ", brand=" + self.brand + ", price=" + str(self.price) + "}")
