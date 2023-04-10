package com.acefreesoft.CourtBot.handlers;

import com.acefreesoft.CourtBot.MsgArrayList;
import com.acefreesoft.CourtBot.db.DBHelper;
import com.acefreesoft.CourtBot.db.DataModel;
import com.acefreesoft.CourtBot.message.SendMessageService;
import com.acefreesoft.CourtBot.service.KeyboardHelper;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class CallbackQueryHandler implements Handler<CallbackQuery> {

    private final MsgArrayList msgList;
    private final SendMessageService messageService;
    private final KeyboardHelper keyboardHelper;
    private final DBHelper db;

    public CallbackQueryHandler(SendMessageService messageService, KeyboardHelper keyboardHelper, MsgArrayList msgList, DBHelper db) {
        this.messageService = messageService;
        this.keyboardHelper = keyboardHelper;
        this.msgList = msgList;
        this.db = db;
    }

    @Override
    public void choose(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatId = callbackQuery.getFrom().getId().toString();
        String userName = callbackQuery.getFrom().getFirstName();
        String callbackData = callbackQuery.getData();

        if (callbackData.equals("callback")) {
            final String msg = "Головне меню";
            messageService.botEditMessage(chatId, message);
            messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenuKeyboard());

        } else if (callbackData.equals("callbackPr")) {
            messageService.botEditMessage(chatId, message);
            final String msg = userName + msgList.getMessageList().get(7);
            final String[] strPr = msgList.getCourtList().get(0);
            final String nameBtn1 = strPr[0];
            final String nameBtn2 = strPr[1];
            final String url = strPr[2];
            final String nameBtn3 = strPr[3];
            messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(
                    nameBtn1, nameBtn2, url, nameBtn3
            ));
        }  else if (callbackData.equals("callbackAppeal")) {
            messageService.botEditMessage(chatId, message);
            final String msg = userName + msgList.getMessageList().get(7);
            final String[] strAppeal = msgList.getCourtList().get(1);
            final String nameBtn1 = strAppeal[0];
            final String nameBtn2 = strAppeal[1];
            final String url = strAppeal[2];
            final String nameBtn3 = strAppeal[3];
            messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(
                    nameBtn1, nameBtn2, url, nameBtn3
            ));
        } else if (callbackData.equals("callbackUz")) {
            messageService.botEditMessage(chatId, message);
            final String msg = userName + msgList.getMessageList().get(7);
            final String[] strUz = msgList.getCourtList().get(2);
            final String nameBtn1 = strUz[0];
            final String nameBtn2 = strUz[1];
            final String url = strUz[2];
            final String nameBtn3 = strUz[3];
            messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(
                    nameBtn1, nameBtn2, url, nameBtn3
            ));
        } else if (callbackData.equals("callbackVb")) {
            messageService.botEditMessage(chatId, message);
            final String msg = userName + msgList.getMessageList().get(7);
            final String[] strVb = msgList.getCourtList().get(3);
            final String nameBtn1 = strVb[0];
            final String nameBtn2 = strVb[1];
            final String url = strVb[2];
            final String nameBtn3 = strVb[3];
            messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(
                    nameBtn1, nameBtn2, url, nameBtn3
            ));
        } else if (callbackData.contains("list/")) {
            messageService.botEditMessage(chatId, message);
            String[] str = callbackData.split("list/");
            db.deleteOneRow("list_user_input", "USER_INPUT", str[1]);
            ArrayList<DataModel> inputList = db.getUserInput(Integer.parseInt(chatId));
            if (!inputList.isEmpty()) {
                String msg = "Зі списка видалено " + str[1] + " залишилося :";
                messageService.botSendMessageInlineButtons(chatId, msg, keyboardHelper.getListInlineKeyboard(inputList));
            }

        } else if (callbackData.equals("callbackDelete")) {
            messageService.botEditMessage(chatId, message);
            db.deleteAllData("list_user_input", "USER_ID", Integer.parseInt(chatId));
            final String msg = "Все видалено👍.";
            messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenuKeyboard());
        }
    }
}
