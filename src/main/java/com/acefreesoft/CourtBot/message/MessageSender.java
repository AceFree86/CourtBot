package com.acefreesoft.CourtBot.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;


public interface MessageSender {

    void sendMessage(SendMessage sendMessage);
    void sendPhoto(SendPhoto sendPhoto);
    void editMessage(EditMessageReplyMarkup editMessageRequest);
}
