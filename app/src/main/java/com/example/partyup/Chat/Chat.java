package com.example.partyup.Chat;

import java.util.HashMap;

public class Chat {
    public String lastMessageSent;
    public HashMap<String,String> members;
    public String more_properties;

    public Chat(){}

    public Chat(String lastMessageSent, HashMap<String, String> members, String more_properties) {
        this.lastMessageSent = lastMessageSent;
        this.members = members;
        this.more_properties = more_properties;
    }

    public String getLastMessageSent() {
        return lastMessageSent;
    }

    public HashMap<String, String> getMembers() {
        return members;
    }

    public String getMore_properties() {
        return more_properties;
    }
}
