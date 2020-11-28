package com.example.partyup.Requests;

public class Request {
    public String game;
    public String partySize;
    public String partyCurr;
    public String messageDate;
    public String messageTime;
    public String sentBy;
    public String time;
    public String id;

    public Request(){

    }

    public Request(String game, String id, String time, String partySize, String partyCurr, String messageDate, String messageTime, String sentBy){
        this.game=game;
        this.time=time;
        this.id=id;
        this.partySize=partySize;
        this.partyCurr=partyCurr;
        this.messageDate=messageDate;
        this.messageTime=messageTime;
        this.sentBy=sentBy;
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

    public String getPartyCurr() {
        return partyCurr;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }
}
