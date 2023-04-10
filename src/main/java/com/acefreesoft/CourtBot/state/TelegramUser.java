package com.acefreesoft.CourtBot.state;

public class TelegramUser {

    public String chatId;

    public String state;

    public String msg;

    public int msgId;


    public TelegramUser() {
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        if (msg != null) {
            return msg;
        }
        return "";
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

}
