package com.example.root.listexample.message_app;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by root on 1/3/17.
 */

public class ChatMessage {
    private String username, message;
    private String stimestamp;

    public ChatMessage(String username, String message) {
        this.username = username;
        this.message = message;
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        stimestamp = timestamp.toString();
    }

    public ChatMessage(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return stimestamp;
    }

    public void setTimestamp(String timestamp) {
        this.stimestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
