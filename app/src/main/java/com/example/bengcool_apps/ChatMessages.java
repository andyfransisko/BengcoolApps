package com.example.bengcool_apps;


public class ChatMessages {

    private String messageText;
    private String email;

    public ChatMessages(String email, String messageText)
    {
        this.email = email;
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
