



class Action:
    """Get an action to perform and run it"""

    def __init__(self):
        data = {}



    def set_details(self, name, phone, contact, location):
        self.data['name']     = name
        self.data['phone']    = phone
        self.data['contact']  = contact
        self.data['location'] = location


    def get_location(self):
        return self.data['location']


    def get_name(self):
        return self.data['name']


    def get_phone(self):
        return self.data['phone']


    def get_contact(self):
        return self.data['contact']


    def __str__(self):
        return "+-----------------------------\n" +
        "| Garage name: ", data['name']



if __name__ == '__main__':
    g = Action