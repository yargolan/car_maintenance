#!/usr/bin/env python3

import os
import sys
import json

app_config = {}


def parse_arguments(arguments):
    if len(arguments) > 1:
        action_name = sys.argv[1]
        if action_name == "add_garage":
            garage_add(arguments[2:])
    else:
        show_usage(sys.argv[0].split(os.sep)[-1])



def show_usage(script_name):
    print(f"\nUsage:\n{script_name}\n")
    print(f"*  add_garage")
    print(f"  name=<Garage name> location=<Location> contact=<Contact person> phone=<Phone number>")
    sys.exit(len(sys.argv))



def garage_add(parameters):

    data_file = "/opt/git_ws/YG/repos/car_maint/main/Data/1.json"

    garages_list = []

    # Read existing garages, if any.
    if os.path.exists(data_file):
        with open(data_file) as d:
            garages_list = json.load(d)



    # Parse the new garage details.
    data = {}
    for p in parameters:
        if p.__contains__("="):
            k, v = p.split("=")
            data[k] = v
        else:
            print(f"Invalid argument '{p}', ignoring.")


    # Verify that the garage does not exist yet.
    for garage in garages_list:
        if data['name'] == garage['name']:
            create_user_message({"status": "failed", "message": f"The garage '{data['name']}' already exist in the list."})
            return

    garages_list.append(data)

    with open(data_file, "w+") as j:
        json.dump(garages_list, j, indent = 2)

    create_user_message({"status": "success", "message": ""})



def create_user_message(message_data):
    messages_file = os.sep.join([
        app_config['directories']['user_messages'],
        app_config['files']['user_messages'],
    ])
    with open(messages_file, "w") as mf:
        mf.write(str(message_data))



if __name__ == '__main__':
    with open("../common/app_config.json") as c:
        app_config = json.load(c)

    parse_arguments(sys.argv)
