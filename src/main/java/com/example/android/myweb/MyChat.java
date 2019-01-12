package com.example.android.myweb;

/**
 * Created by raj garg on 24-12-2018.
 */

public class MyChat {
    String myChat;
    String sender;
    String reciever;

    public String getMyChat() {
        return myChat;
    }

    public void setMyChat(String myChat) {
        this.myChat = myChat;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public MyChat() {
    }

    public MyChat(String myChat, String sender, String reciever) {
        this.myChat = myChat;
        this.sender = sender;
        this.reciever = reciever;
    }
}
