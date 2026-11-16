class Location:
    def __init__(self, description):
        self._description = description

    def __str__(self):
        return f"Location{{description={self._description}}}"

    def getDescription(self):
        return self._description

    def setDescription(self, description):
        self._description = description
