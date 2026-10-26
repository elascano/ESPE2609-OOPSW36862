class ChickenFarmer:
    def __init__(self, name):
        self.name = name
        self.coops = []

    def add_coop(self, coop):
        self.coops.append(coop)

    def set_coops(self, coops):
        self.coops = coops

    def get_coops(self):
        return self.coops