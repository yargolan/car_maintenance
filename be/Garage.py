
class Garage:
    """
    A class for handling the garages details.
    """
    def __init__(self, name, location, contact, phone):
        self.n = name
        self.p = phone
        self.c = contact
        self.ll = location


    def get_name(self):
        return self.n

    def get_phone(self):
        return self.p

    def get_contact(self):
        return self.c

    def get_location(self):
        return self.ll

    def __str__(self):
        return "\n".join([
            "+---------------------------",
            f"| Garage name     : {self.n}",
            f"| Garage phone    : {self.p}",
            f"| Garage contact  : {self.c}",
            f"| Garage location : {self.ll}",
            "+---------------------------"
        ])

    def __eq__(self, other):
        if isinstance(other, Garage):
            return self.n == other.n
        else:
            return False
