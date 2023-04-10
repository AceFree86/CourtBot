package com.acefreesoft.CourtBot.handlers;

import com.acefreesoft.CourtBot.MsgArrayList;
import com.acefreesoft.CourtBot.message.SendMessageService;
import com.acefreesoft.CourtBot.service.KeyboardHelper;
import com.acefreesoft.CourtBot.service.RaedJsonData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AnswerHandler {

    private final SendMessageService sendMessageService;
    private final KeyboardHelper keyboardHelper;
    private final RaedJsonData raedJson;

    private final MsgArrayList stringList;

    public AnswerHandler(SendMessageService sendMessageService, KeyboardHelper keyboardHelper, MsgArrayList stringList) {
        this.sendMessageService = sendMessageService;
        this.keyboardHelper = keyboardHelper;
        this.stringList = stringList;
        raedJson = new RaedJsonData();
    }

    public void massage(String chatId, String[] userInput, int index) {
        boolean flag = true;
        try {
            JSONArray jsons = raedJson.readJson(userInput[3]);
            for (Object list : jsons) {
                JSONObject jsonObject = (JSONObject) list;
                if (jsonObject.get("involved").toString().toLowerCase().replace("'", " ").replace("\"", " ")
                        .contains(userInput[0].toLowerCase().replace("'", " ").replace("\"", " "))
                        || jsonObject.get("number").toString().contains(userInput[0])
                        || jsonObject.get("date").toString().contains(userInput[0])
                        && jsonObject.get("judge").toString().contains(userInput[1])
                        && jsonObject.get("forma").toString().contains(userInput[2])) {
                    String date = (String) jsonObject.get("date");
                    String number = (String) jsonObject.get("number");
                    String judge = (String) jsonObject.get("judge");
                    String involved = (String) jsonObject.get("involved");
                    String description = (String) jsonObject.get("description");
                    String forma = (String) jsonObject.get("forma");
                    String text = "<a href='http://www.example.com'>Дата</a> : <b>"+ date +"</b> год.\n" +
                                    "<a href='http://www.example.com'>№ Справи</a> : "+ number +"\n" +
                                    "<a href='http://www.example.com'>Суддя</a> : "+ judge +"\n__________\n" +
                                    "<a href='http://www.example.com'>Сторони по справі</a> : <b>"+ involved +".</b>\n" +
                                    "<a href='http://www.example.com'>Суть</a> : <b>"+ description +".</b>\n" +
                                    "<a href='http://www.example.com'>"+ forma +"</a>.\n__________";
                    final String[] strArray = stringList.getCourtList().get(index);
                    final String nameBtn1 = strArray[0];
                    final String nameBtn2 = strArray[1];
                    final String url = strArray[2];
                    final String nameBtn3 = strArray[3];
                    sendMessageService.botSendMessageReplyButtons(chatId, text, keyboardHelper.getDateMenuKeyboard(
                            nameBtn1, nameBtn2, url, nameBtn3
                    ));
                    flag = false;
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        if (flag) {
            final String text = "Ваш запит: <b>" + userInput[0] + "</b>." + """
                     Якщо нічого не з'явилося, можливо неправильно введене слово чи № справа або ще не призначене дане засідання.
                                            
                    Ви також можете відвідати Наш вебпортал.""";
            final String[] urlArray = stringList.getUrlList().get(index);
            final String nameBTN = urlArray[0];
            final String url = urlArray[1];
            final String callback = urlArray[2];
            sendMessageService.botSendMessageInlineButtons(chatId, text, keyboardHelper.getURLInlineKeyboard(
                    url, nameBTN, callback
            ));
        }
    }
}
