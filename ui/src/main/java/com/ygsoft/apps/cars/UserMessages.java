package com.ygsoft.apps.cars;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ygsoft.apps.Messages;


public class UserMessages {

    private final AppData appData = AppData.getInstance();



    public UserMessages(){}



    private HashMap<Integer, String> generateMessagesData() {
        HashMap<Integer, String> data = new HashMap<>();
        data.put(0, "בדיקה בלבד");
        return data;
    }


    public void convertBeMessage() throws CMIException {

        HashMap<Integer, String> messagesData = generateMessagesData();

        String status          = "Unknown";
        String message         = "INTERNAL: Cannot read the message file.";


        Path path = Paths.get(appData.getUserMessagesFileFullPath());
        File f = path.toFile();

        if (f.exists()) {

            Gson gson = new Gson();

            try {

                // create a reader
                Reader reader = Files.newBufferedReader(path);

                JsonObject jo = gson.fromJson(reader, JsonObject.class);
                status = jo.get("status").getAsString();
                message = jo.get("message").getAsString();
                boolean needConversion = jo.get("need_conversion")
                        .getAsString()
                        .equalsIgnoreCase(Boolean.TRUE.toString())
                ;

                if (needConversion) {
                    message = messagesData.getOrDefault(Integer.parseInt(message), "xxx");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        int messageType = status.equals("failed") ?
                Messages.MESSAGE_ERR :
                Messages.MESSAGE_INF
        ;

        Messages.showMessage(messageType, message);


        // Delete the file.
        if (! f.delete()) {
            throw new CMIException("Cannot delete the message file");
        }
    }


    public static void main(String[] args) throws CMIException {
        new UserMessages().convertBeMessage();
    }
}
