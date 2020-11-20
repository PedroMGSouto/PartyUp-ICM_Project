package com.example.partyup;

public class Request {
    public String message;
    public String game;
    public String partySize;
    public String messageDate;
    public String messageTime;
    public String sentBy;

    public Request(){

    }

    public Request(String message, String messageDate, String messageTime, String sentBy){
        this.message=message;
        this.game=game;
        this.partySize=partySize;
        this.messageDate=messageDate;
        this.messageTime=messageTime;
        this.sentBy=sentBy;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getSentBy() {
        return sentBy;
    }

    public String getGame() {
        return game;
    }

    public String getPartySize() {
        return partySize;
    }
}
