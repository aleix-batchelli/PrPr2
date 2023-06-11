package com.example.practica_final;

public class Message {
    private int messageID;
    private String content;
    private int user_id_send;
    private int user_id_received;
    private String timeStamp;

    public int getMessageID() {
        return messageID;
    }

    public String getContent() {
        return content;
    }

    public int getUser_id_send() {
        return user_id_send;
    }

    public int getUser_id_received() {
        return user_id_received;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Message(int messageID, String content, int user_id_send, int user_id_received, String timeStamp) {
        this.messageID = messageID;
        this.content = content;
        this.user_id_send = user_id_send;
        this.user_id_received = user_id_received;
        this.timeStamp = timeStamp;
    }
}
