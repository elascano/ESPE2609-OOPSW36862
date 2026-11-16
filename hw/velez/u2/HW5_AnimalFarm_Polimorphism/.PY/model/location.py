class Location:
    def __init__(self, description):
        self._description = description

    def get_description(self):
        return self._description

    def set_description(self, description):
        self._description = description

    def __str__(self):
        return f"Location{{description={self._description}}}"