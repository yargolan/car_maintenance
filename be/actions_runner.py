#!/usr/bin/env python3

import os
import sys
import json


app_config = {}



def parse_arguments(arguments):
    data = {}

    if len(arguments) < 3:
        show_usage(sys.argv[0].split(os.sep)[-1])

    for p in arguments:
        if p.__contains__("="):
            k, v = p.split("=")
            data[k.lower()] = v
        else:
            print(f"Invalid argument '{p}', ignoring.")

    return data



def show_usage(script_name):
    print(f"\nUsage:\n")
    print(f"*  {script_name} add_garage name=<Garage name> location=<Location> contact=<Contact person> phone=<Phone number>")
    print(f"*  {script_name} del_garage name=<Garage name>")
    sys.exit(len(sys.argv))



def action_garage_add():

    garages_file = app_config['garages_file']

    if os.path.exists(garages_file):
        with open(garages_file) as ac:
            garages_list = json.load(ac)
    else:
        garages_list = []


    # for garage in garages_list:
    new = {
        "name": parsed_arguments['name'],
        "phone": parsed_arguments['phone'],
        "contact": parsed_arguments['contact'],
        "location": parsed_arguments['location']
    }

    garages_list.append(json.dumps(new))

    with open(garages_file, "w") as j:
        json.dump(garages_list, j, indent = 2)

    create_user_message(status="success", message="")



def init_app():
    app_config['messages_file'] = os.sep.join([
        "..",
        app_config['directories']['user_messages'],
        app_config['files']['user_messages']
    ])
    app_config['garages_file'] = os.sep.join([
        "..",
        app_config['directories']['data_folder'],
        app_config['files']['db_garages']
    ])



def create_user_message(status, message):
    with open(app_config['messages_file'], "w") as mf:
        user_message = "{\"status\": \"" + status + "\", \"message\": \"" + message + "\"}"
        mf.write(user_message)


if __name__ == '__main__':

    with open("../common/app_config.json") as c:
        app_config = json.load(c)

    # Add variables to the application config.
    init_app()

    # Parse the script's arguments.
    parsed_arguments = parse_arguments(sys.argv[1:])

    # Run needed action
    if parsed_arguments['action'] == "test":
        create_user_message(status="success", message="Way to go !")
    elif parsed_arguments['action'] == "add_garage":
        action_garage_add()
    else:
        print("No action provided.")
        show_usage(sys.argv[0])
