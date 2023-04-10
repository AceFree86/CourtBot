package com.acefreesoft.CourtBot.processors;

import com.acefreesoft.CourtBot.MsgArrayList;
import com.acefreesoft.CourtBot.db.DBHelper;
import com.acefreesoft.CourtBot.handlers.CallbackQueryHandler;
import com.acefreesoft.CourtBot.handlers.MessageHandler;
import com.acefreesoft.CourtBot.message.MessageSenderImpl;
import com.acefreesoft.CourtBot.message.SendMessageService;
import com.acefreesoft.CourtBot.service.KeyboardHelper;
import com.acefreesoft.CourtBot.TelegramBot;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.FileNotFoundException;

public class DefaultProcessor implements Processor {

    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final MsgArrayList msgList = new MsgArrayList();
    private final KeyboardHelper keyboard = new KeyboardHelper();
    private final DBHelper db = new DBHelper();

    public DefaultProcessor(TelegramBot bot) {
        final MessageSenderImpl messageSenderImpl = new MessageSenderImpl(bot);
        final SendMessageService sendMessageService = new SendMessageService(messageSenderImpl);
        messageHandler = new MessageHandler(sendMessageService, keyboard, msgList, db);
        callbackQueryHandler = new CallbackQueryHandler(sendMessageService, keyboard, msgList, db);
    }

    @Override
    public void executeMessage(Message message) {
        try {
            messageHandler.choose(message);
        } catch (ParseException | java.text.ParseException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void executeCallBackQuery(CallbackQuery callbackQuery) {
        callbackQueryHandler.choose(callbackQuery);
    }
}
