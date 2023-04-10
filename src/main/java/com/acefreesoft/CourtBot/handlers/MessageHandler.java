package com.acefreesoft.CourtBot.handlers;

import com.acefreesoft.CourtBot.MsgArrayList;
import com.acefreesoft.CourtBot.db.DBHelper;
import com.acefreesoft.CourtBot.db.DataModel;
import com.acefreesoft.CourtBot.message.SendMessageService;
import com.acefreesoft.CourtBot.service.KeyboardHelper;
import com.acefreesoft.CourtBot.state.BotState;
import com.acefreesoft.CourtBot.state.TelegramUser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MessageHandler implements Handler<Message> {
    private final MsgArrayList msgList;
    private final SendMessageService messageService;
    private final KeyboardHelper keyboardHelper;
    private final AnswerHandler answerHandler;
    private int index;
    private final List<TelegramUser> users = new ArrayList<>();
    private final DBHelper db;


    public MessageHandler(SendMessageService messageService, KeyboardHelper keyboardHelper, MsgArrayList msgList, DBHelper db) {
        this.messageService = messageService;
        this.keyboardHelper = keyboardHelper;
        this.msgList = msgList;
        this.db = db;
        answerHandler = new AnswerHandler(messageService, keyboardHelper, msgList);
    }

    @Override
    public void choose(Message message) throws ParseException, java.text.ParseException, FileNotFoundException {
        String chatId = message.getChatId().toString();
        TelegramUser user = saveUser(chatId);
        String userName = message.getChat().getFirstName();
        if (message.hasText()) {
            String text = message.getText();
            if (text.equals(msgList.START_COMMAND)) {
                if (!db.checkUser(Integer.parseInt(chatId))) {
                    db.insertUser(new DataModel(Integer.parseInt(chatId), userName, 1));
                    System.out.println("yes");
                } else {
                    System.out.println("duplicate");
                }
                final String msgHello = "👋Доброго дня " + userName;
                messageService.botSendMessage(chatId, msgHello);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                final String photo = msgList.getUrlPhotoPate().get(0);
                messageService.botSendPhotoText(chatId, photo, msgList.getMessageList().get(0));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                messageService.botSendMessageReplyButtons(chatId, msgList.getMessageList().get(1), keyboardHelper.getMainMenuKeyboard());

            } else if (text.equals("☎️Контактні данні")) {
                final String msg = userName + msgList.getMessageList().get(2);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getCustomMenuKeyboard("🗺Карти проїзду"));

            } else if (text.equals("🗺Карти проїзду")) {
                final String msg = userName + msgList.getMessageList().get(3);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getCustomMenuKeyboard("🗺Карти проїзду"));
                final String photo_1 = msgList.getUrlPhotoPate().get(1);
                final String nameBTN_1 = "Google Maps";
                final String url_1 = "https://goo.gl/maps/sNThx2MEs5VCuy2z9";
                messageService.botSendPhotoReplyButtons(chatId, photo_1, keyboardHelper.getMapsInlineKeyboard(url_1, nameBTN_1));
                final String photo_2 = msgList.getUrlPhotoPate().get(2);
                final String nameBTN_2 = "Apple Карти";
                final String url_2 = "https://maps.apple.com/place?address=48.735389,22.476694&q";
                messageService.botSendPhotoReplyButtons(chatId, photo_2, keyboardHelper.getMapsInlineKeyboard(url_2, nameBTN_2));

            } else if (text.equals("📢Оголошення про виклик")) {
                final String msg = userName + msgList.getMessageList().get(4);
                final String nameBTN = "Переміститися в Оголошення";
                final String url = "https://pr.zk.court.gov.ua/sud0708/gromadyanam/";
                messageService.botSendMessageInlineButtons(chatId, msg, keyboardHelper.getMapsInlineKeyboard(url, nameBTN));

            } else if (text.equals("📃Електронний Суд")) {
                final String photo = msgList.getUrlPhotoPate().get(3);
                final String msgCaption = userName + msgList.getMessageList().get(5);
                final String nameBTN = "📲Завантажити офіційний мобільний додаток єСуд";
                messageService.botSendPhotoTextReplyButtons(chatId, photo, msgCaption, keyboardHelper.getCustomMenuKeyboard(nameBTN));

            } else if (text.equals("📲Завантажити офіційний мобільний додаток єСуд")) {
                final String msg = userName + msgList.getMessageList().get(6);
                final String nameBTN1 = "Апп Стор";
                final String url1 = "https://apps.apple.com/ua/app/%D1%94%D1%81%D1%83%D0%B4/id1578245779?l=uk";
                final String nameBTN2 = "Гугле Плей";
                final String url2 = "https://play.google.com/store/apps/details?id=com.floor12apps.ecourt&gl=UA";
                messageService.botSendMessageInlineButtons(chatId, msg, keyboardHelper.getTwoInlineKeyboard(nameBTN1, url1, nameBTN2, url2));

            } else if (text.equals("📅Дата засідання") || text.equals(msgList.PR_COMMAND)) {
                final String msg = userName + msgList.getMessageList().get(7);
                final String[] strPerechyn = msgList.getCourtList().get(0);
                final String nameBtn1 = strPerechyn[0];
                final String nameBtn2 = strPerechyn[1];
                final String url = strPerechyn[2];
                final String nameBtn3 = strPerechyn[3];
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(nameBtn1, nameBtn2, url, nameBtn3));

            } else if (text.equals("⚖️Закарпатський апеляційний суд") || text.equals(msgList.ZKA_COMMAND)) {
                final String msg = userName + msgList.getMessageList().get(7);
                final String[] strCourtAppeal = msgList.getCourtList().get(1);
                final String nameBtn1 = strCourtAppeal[0];
                final String nameBtn2 = strCourtAppeal[1];
                final String url = strCourtAppeal[2];
                final String nameBtn3 = strCourtAppeal[3];
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(nameBtn1, nameBtn2, url, nameBtn3));

            } else if (text.equals("⚖️Ужгородський міськрайонний суд") || text.equals(msgList.UG_COMMAND)) {
                final String msg = userName + msgList.getMessageList().get(7);
                final String[] strUzhhorodCourt = msgList.getCourtList().get(2);
                final String nameBtn1 = strUzhhorodCourt[0];
                final String nameBtn2 = strUzhhorodCourt[1];
                final String url = strUzhhorodCourt[2];
                final String nameBtn3 = strUzhhorodCourt[3];
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(nameBtn1, nameBtn2, url, nameBtn3));

            } else if (text.equals("⚖️Великоберезнянський районний суд") || text.equals(msgList.VB_COMMAND)) {
                final String msg = userName + msgList.getMessageList().get(7);
                final String[] strVelikobereznyanskyCourt = msgList.getCourtList().get(3);
                final String nameBtn1 = strVelikobereznyanskyCourt[0];
                final String nameBtn2 = strVelikobereznyanskyCourt[1];
                final String url = strVelikobereznyanskyCourt[2];
                final String nameBtn3 = strVelikobereznyanskyCourt[3];
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(nameBtn1, nameBtn2, url, nameBtn3));

            } else if (text.equals("⚖️Інші суди")) {
                final String msg = userName + msgList.getMessageList().get(8);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getListCourtsKeyboard());

            } else if (text.equals("🔙Назат в меню")) {
                final String msg = "Головне меню.";
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenuKeyboard());

            } else if (text.equals(msgList.LIST_COMMAND)) {
                ArrayList<DataModel> userList = db.getUserList();
                StringBuilder messageBuilder = new StringBuilder();
                for (DataModel users : userList) {
                    messageBuilder.append(users.getText()).append("\n");
                }
                String msg = messageBuilder.toString().trim();
                String msgText = "Список користувачів :\n\n";
                messageService.botSendMessage(chatId, msgText + msg);

            } else if (text.equals(msgList.MAILING_COMMAND)) {
                final String msg = userName + " пропишіть що хочете розіслати";
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenu());
                user.setState(BotState.GET_USER_MAILING);

            } else if (text.equals(msgList.ADMIN_COMMAND)) {
                final String msg = userName + " напишіть Ваш відгук або рекомендації адміністратору. Щоб вийти натисніть на👉 🔙_Назат_";
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenu());
                user.setState(BotState.GET_USER_INPUT_ADMIN);

            } else if (text.equals("📩Сповіщення")) {
                final String msg = userName + msgList.getMessageList().get(10);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getBackMainKeyboard());
                user.setState(BotState.GET_USER_INPUT_PUSH);

            } else if (text.equals("📋Список Ваших запис")) {
                user.setState(BotState.START_STAGE);
                ArrayList<DataModel> inputList = db.getUserInput(Integer.parseInt(chatId));
                if (!inputList.isEmpty()) {
                    String msg = "Щоб видалити Ваш запис виберіть зі списку та натисніть його. Список записів :";
                    messageService.botSendMessageInlineButtons(chatId, msg, keyboardHelper.getListInlineKeyboard(inputList));
                } else {
                    String msg = "Список записів пустий!";
                    messageService.botSendMessage(chatId, msg);
                }

            } else if (text.equals("🎫Пошук за П.І.П. або номером справи")) {
                final String msg = userName + msgList.getMessageList().get(9);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getBackKeyboard());
                user.setState(BotState.GET_USER_INPUT);
                setIndex(0);

            } else if (text.equals("🎫Пошук за П.І.П. або номером справи.")) {
                final String msg = userName + msgList.getMessageList().get(9);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getBackKeyboard());
                user.setState(BotState.GET_USER_INPUT_APPEAL);
                setIndex(1);

            } else if (text.equals("🎫Пошук за П.І.П. або номером справи..")) {
                final String msg = userName + msgList.getMessageList().get(9);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getBackKeyboard());
                user.setState(BotState.GET_USER_INPUT_UZHHOROD);
                setIndex(2);

            } else if (text.equals("🎫Пошук за П.І.П. або номером справи...")) {
                final String msg = userName + msgList.getMessageList().get(9);
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getBackKeyboard());
                user.setState(BotState.GET_USER_INPUT_VB);
                setIndex(3);

            } else if (text.equals("🔙Назат")) {
                user.setState(BotState.START_STAGE);
                final String msg = userName + msgList.getMessageList().get(7);
                final String[] strArray = msgList.getCourtList().get(getIndex());
                final String nameBtn1 = strArray[0];
                final String nameBtn2 = strArray[1];
                final String url = strArray[2];
                final String nameBtn3 = strArray[3];
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getDateMenuKeyboard(nameBtn1, nameBtn2, url, nameBtn3));

            } else if (text.equals("🔙_Назат_")) {
                user.setState(BotState.START_STAGE);
                final String msg = "Головне меню";
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenuKeyboard());

            } else if (user.getState().equals(BotState.GET_USER_INPUT)) {
                user.setMsg(text);
                final String[] strData = new String[]{user.getMsg(), msgList.empty_str, msgList.empty_str, msgList.name_pr};
                answerHandler.massage(chatId, strData, 0);
                user.setState(BotState.START_STAGE);

            } else if (user.getState().equals(BotState.GET_USER_INPUT_PUSH)) {
                user.setMsg(text);
                String msg;
                if (!db.checkRow("list_user_input", "USER_INPUT", user.getMsg())) {
                    db.insertUserInput(new DataModel(Integer.parseInt(chatId), user.getMsg(), 1));
                    msg = userName + " Ви підписалися👍, очікуйте на сповіщення.";
                } else {
                    msg = userName + " 🤚даний запис Ви вже вносили 😎.";
                }
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenuKeyboard());
                user.setState(BotState.START_STAGE);

            } else if (user.getState().equals(BotState.GET_USER_INPUT_APPEAL)) {
                user.setMsg(text);
                final String[] strData = new String[]{user.getMsg(), msgList.empty_str, msgList.empty_str, msgList.name_zka};
                answerHandler.massage(chatId, strData, 1);
                user.setState(BotState.START_STAGE);

            } else if (user.getState().equals(BotState.GET_USER_INPUT_UZHHOROD)) {
                user.setMsg(text);
                final String[] strData = new String[]{user.getMsg(), msgList.empty_str, msgList.empty_str, msgList.name_ug};
                answerHandler.massage(chatId, strData, 2);
                user.setState(BotState.START_STAGE);

            } else if (user.getState().equals(BotState.GET_USER_INPUT_VB)) {
                user.setMsg(text);
                final String[] strData = new String[]{user.getMsg(), msgList.empty_str, msgList.empty_str, msgList.name_vb};
                answerHandler.massage(chatId, strData, 3);
                user.setState(BotState.START_STAGE);

            } else if (user.getState().equals(BotState.GET_USER_INPUT_ADMIN)) {
                user.setMsg(text);
                String channelId  = "-1001978805431";
                messageService.botSendMessage(channelId, userName + " " + user.getMsg());
                String msg = "Адміністратору відправлено 😎.";
                messageService.botSendMessageReplyButtons(chatId, msg, keyboardHelper.getMainMenuKeyboard());
                user.setState(BotState.START_STAGE);

            } else if (user.getState().equals(BotState.GET_USER_MAILING)) {
                user.setMsg(text);
                ArrayList<DataModel> userList = db.getUserList();
                for (DataModel users : userList) {
                    try {
                        Thread.sleep(30000);
                        messageService.botSendMessageReplyButtons(String.valueOf(users.getUserId()), user.getMsg(), keyboardHelper.getMainMenuKeyboard());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                user.setState(BotState.START_STAGE);
            }

        } else if (message.getWebAppData().getButtonText().equals("🗓️Пошук за датою")) {
            final String webAppData = message.getWebAppData().getData();
            final String[] strData = getWebAppData(webAppData, msgList.name_pr);
            answerHandler.massage(chatId, strData, 0);

        } else if (message.getWebAppData().getButtonText().equals("🗓️Пошук за датою.")) {
            final String webAppData = message.getWebAppData().getData();
            final String[] strData = getWebAppData(webAppData, msgList.name_zka);
            answerHandler.massage(chatId, strData, 1);

        } else if (message.getWebAppData().getButtonText().equals("🗓️Пошук за датою..")) {
            final String webAppData = message.getWebAppData().getData();
            final String[] strData = getWebAppData(webAppData, msgList.name_ug);
            answerHandler.massage(chatId, strData, 2);

        } else if (message.getWebAppData().getButtonText().equals("🗓️Пошук за датою...")) {
            final String webAppData = message.getWebAppData().getData();
            final String[] strData = getWebAppData(webAppData, msgList.name_vb);
            answerHandler.massage(chatId, strData, 3);
        }
    }

    private String[] getWebAppData(String webAppData, String strRoot) {
        final String[] strData;
        try {
            final JSONParser parser = new JSONParser();
            final JSONObject obj = (JSONObject) parser.parse(webAppData);
            final DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy", new Locale("uk", "UA"));
            final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("uk", "UA"));
            final Date date = inputFormat.parse(obj.get("dob").toString());
            final String outputText = outputFormat.format(date);
            strData = new String[]{outputText, obj.get("court").toString(), obj.get("formset").toString(), strRoot};
        } catch (java.text.ParseException | ParseException e) {
            throw new RuntimeException(e);
        }
        return strData;
    }

    private int getIndex() {
        return index;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    private TelegramUser saveUser(String chatId) {
        for (TelegramUser user : users) {
            if (user.getChatId().equals(chatId)) {
                return user;
            }
        }
        final TelegramUser user = new TelegramUser();
        user.setChatId(chatId);
        users.add(user);
        return user;
    }
}
