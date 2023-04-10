package com.acefreesoft.CourtBot.message;

import com.acefreesoft.CourtBot.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class MessageSenderImpl implements MessageSender {
    private final TelegramBot bot;
    public MessageSenderImpl(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
           bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sendPhoto(SendPhoto sendPhoto) {
        try {
            bot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void editMessage(EditMessageReplyMarkup editMessageRequest) {
        try {
            bot.execute(editMessageRequest);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
