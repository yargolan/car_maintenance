package com.ygsoft.apps.cars;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ygsoft.apps.Messages;



public class UserMessages {

    private final AppData appData = AppData.getInstance();



    public static void main(String[] args) {
        UserMessages userMessages = new UserMessages();
        userMessages.convertBeMessage();
    }



    public UserMessages(){}



    public void convertBeMessage() {

        String status  = "";
        String message = "";


        Gson gson = new Gson();

        try {

            // create a reader
            Path path = Paths.get(appData.getUserMessagesFileFullPath());
            File f = path.toFile();
            Reader reader = Files.newBufferedReader(path);

            JsonObject jo = gson.fromJson(reader, JsonObject.class);
            status  = jo.get("status").getAsString();
            message = jo.get("message").getAsString();

            // Delete the file.
            if (! f.delete()) {
                throw new CmInternalException("Cannot delete the message file");
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


        int messageType = status.equals("failed") ?
                Messages.MESSAGE_ERR :
                Messages.MESSAGE_INF
        ;

        Messages.showMessage(messageType, message);
    }
}
