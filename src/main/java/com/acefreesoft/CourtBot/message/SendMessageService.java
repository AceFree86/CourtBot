package com.acefreesoft.CourtBot.message;


import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import java.io.File;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;


public class SendMessageService {

    private final MessageSender messageSender;

    public SendMessageService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void botEditMessage(String chatId, Message message) {
        EditMessageReplyMarkup editMessageRequest = EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(message.getMessageId())
                .replyMarkup(null)
                .build();
        messageSender.editMessage(editMessageRequest);
    }

    public void botSendPhotoText(String chatId, String photo, String caption) {
        SendPhoto msg = SendPhoto.builder()
                .chatId(chatId)
                .photo(new InputFile(new File(photo)))
                .caption(caption)
                .parseMode(ParseMode.HTML)
                .build();
        messageSender.sendPhoto(msg);
    }

    public void botSendPhotoTextReplyButtons(String chatId, String photo, String caption, ReplyKeyboardMarkup keyboardMarkup) {
        SendPhoto msg = SendPhoto.builder()
                .chatId(chatId)
                .photo(new InputFile(new File(photo)))
                .caption(caption)
                .parseMode(ParseMode.HTML)
                .replyMarkup(keyboardMarkup)
                .build();
        messageSender.sendPhoto(msg);
    }

    public void botSendPhotoReplyButtons(String chatId, String photo, InlineKeyboardMarkup markupInline) {
        SendPhoto msg = SendPhoto.builder()
                .chatId(chatId)
                .photo(new InputFile(new File(photo)))
                .replyMarkup(markupInline)
                .build();
        messageSender.sendPhoto(msg);
    }

    public void botSendMessage(String chatId, String text) {
        SendMessage msg = SendMessage.builder()
                .text(text)
                .parseMode(ParseMode.HTML)
                .chatId(chatId)
                .build();
        messageSender.sendMessage(msg);
    }

    public void botSendMessageReplyButtons(String chatId, String text, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage msg = SendMessage.builder()
                .text(text)
                .parseMode(ParseMode.HTML)
                .chatId(chatId)
                .replyMarkup(keyboardMarkup)
                .build();
        messageSender.sendMessage(msg);
    }

    public void botSendMessageInlineButtons(String chatId, String text, InlineKeyboardMarkup markupInline) {
        SendMessage msg  = SendMessage.builder()
                .text(text)
                .parseMode(ParseMode.HTML)
                .chatId(chatId)
                .replyMarkup(markupInline)
                .build();
        messageSender.sendMessage(msg);
    }
}
