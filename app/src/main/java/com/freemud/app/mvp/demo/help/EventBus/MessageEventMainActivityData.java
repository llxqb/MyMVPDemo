package com.freemud.app.mvp.demo.help.EventBus;

/**
 * Created by li.liu on 2018/1/29.
 */

public class MessageEventMainActivityData {
    private String message;

    public MessageEventMainActivityData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
