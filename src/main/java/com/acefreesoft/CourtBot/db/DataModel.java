package com.acefreesoft.CourtBot.db;

public class DataModel {

    private final int userId;
    private final String text;
    private final int state;

    public DataModel(int userId, String text, int state) {
        this.userId = userId;
        this.text = text;
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public int getState() {
        return state;
    }
}
