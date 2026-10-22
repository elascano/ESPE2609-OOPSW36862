"""
@author Andres Pachacama, Byte Work, @ESPE
"""

CAPACITY = 10    # max chickens per coop


class ChickenCoop:

    # ===================== CONSTRUCTOR =====================

    def __init__(self, chicken_coop_id, chickens=None):
        self.chicken_coop_id = chicken_coop_id
        self._chickens = [None] * CAPACITY    
        self._count = 0                       
        self._index = 0                       

        if chickens is not None:
            for chicken in chickens:
                if chicken is not None:
                    self.add(chicken)

    def add(self, chicken):
        if self._count < len(self._chickens):
            self._chickens[self._count] = chicken
            self._count = self._count + 1


    def remove(self, chicken_id):
        for i in range(self._count):
            if self._chickens[i].id == chicken_id:

                for j in range(i, self._count - 1):
                    self._chickens[j] = self._chickens[j + 1]
                self._chickens[self._count - 1] = None
                self._count = self._count - 1
                return

    def reset_iteration(self):
        self._index = 0

    def next(self):
        if self._index < self._count:
            chicken = self._chickens[self._index]
            self._index = self._index + 1
            return chicken
        return None    

    # ===================== HELPERS =====================

    def exists(self, chicken_id):
        for i in range(self._count):
            if self._chickens[i].id == chicken_id:
                return True
        return False

    def is_full(self):
        return self._count == len(self._chickens)

    def is_empty(self):
        return self._count == 0

    def get_count(self):
        return self._count

    def get_capacity(self):
        return len(self._chickens)

    # ===================== toString (V2) =====================

    def __str__(self):
        local_chickens = ""

        for chicken in self._chickens:
            if chicken is not None:          # the empty nests are skipped
                local_chickens += str(chicken)
                local_chickens += "\t"

        return ("ChickenCoop{chickenCoopId=" + str(self.chicken_coop_id)
                + ", chickens=\n" + local_chickens + "}")
