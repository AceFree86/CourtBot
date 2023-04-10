package com.acefreesoft.CourtBot.service;

import com.acefreesoft.CourtBot.db.DataModel;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

public class KeyboardHelper {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton("📅Дата засідання"));
        row1.add(createKeyboardButton("📩Сповіщення"));

        KeyboardRow row2 = createKeyboardRow();
        row2.add(createKeyboardButton("⚖️Інші суди"));
        row2.add(createKeyboardButton("☎️Контактні данні"));

        KeyboardRow row3 = createKeyboardRow();
        row3.add(createKeyboardButton("📃Електронний Суд"));
        row3.add(createKeyboardButton("📢Оголошення про виклик"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        return keyboardSetup(keyboard);
    }

    public ReplyKeyboardMarkup getListCourtsKeyboard() {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton("⚖️Закарпатський апеляційний суд"));

        KeyboardRow row2 = createKeyboardRow();
        row2.add(createKeyboardButton("⚖️Ужгородський міськрайонний суд"));

        KeyboardRow row3 = createKeyboardRow();
        row3.add(createKeyboardButton("⚖️Великоберезнянський районний суд"));

        KeyboardRow row4 = createKeyboardRow();
        row4.add(createKeyboardButton("🔙Назат в меню"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        return keyboardSetup(keyboard);
    }

    public ReplyKeyboardMarkup getDateMenuKeyboard(String nameBtn1, String nameBtn2, String url, String nameBtn3) {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton(nameBtn1));

        KeyboardRow row2 = createKeyboardRow();
        KeyboardButton keyboardButton = createKeyboardButton(nameBtn2);
        WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl(url);
        keyboardButton.setWebApp(webAppInfo);
        row2.add(keyboardButton);

        KeyboardRow row3 = createKeyboardRow();
        row3.add(createKeyboardButton(nameBtn3));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        return keyboardSetup(keyboard);
    }

    public ReplyKeyboardMarkup getCustomMenuKeyboard(String name) {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton(name));
        KeyboardRow row2 = createKeyboardRow();
        row2.add(createKeyboardButton("🔙Назат в меню"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        return keyboardSetup(keyboard);
    }

    public ReplyKeyboardMarkup getMainMenu() {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton("🔙_Назат_"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        return keyboardSetup(keyboard);
    }

    public ReplyKeyboardMarkup getBackMainKeyboard() {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton("📋Список Ваших запис"));
        KeyboardRow row2 = createKeyboardRow();
        row2.add(createKeyboardButton("🔙_Назат_"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getBackKeyboard() {
        KeyboardRow row1 = createKeyboardRow();
        row1.add(createKeyboardButton("🔙Назат"));
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        return keyboardSetup(keyboard);
    }

    public InlineKeyboardMarkup getMapsInlineKeyboard(String URL, String nameBTN) {
        InlineKeyboardButton inlineButton1 = createInlineKeyboardButton();
        inlineButton1.setText(nameBTN);
        inlineButton1.setUrl(URL);
        List<InlineKeyboardButton> inlineKeyboard1 = new ArrayList<>();
        inlineKeyboard1.add(inlineButton1);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(inlineKeyboard1);
        return setInlineKeyboardSetup(keyboard);
    }

    public InlineKeyboardMarkup getTwoInlineKeyboard(String nameBTN1, String URL1, String nameBTN2, String URL2) {
        InlineKeyboardButton inlineButton1 = createInlineKeyboardButton();
        inlineButton1.setText(nameBTN1);
        inlineButton1.setUrl(URL1);
        List<InlineKeyboardButton> inlineKeyboard1 = new ArrayList<>();
        inlineKeyboard1.add(inlineButton1);

        InlineKeyboardButton inlineButton2 = createInlineKeyboardButton();
        inlineButton2.setText(nameBTN2);
        inlineButton2.setUrl(URL2);
        List<InlineKeyboardButton> inlineKeyboard2 = new ArrayList<>();
        inlineKeyboard1.add(inlineButton2);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(inlineKeyboard1);
        keyboard.add(inlineKeyboard2);
        return setInlineKeyboardSetup(keyboard);
    }

    public InlineKeyboardMarkup getURLInlineKeyboard(String URL, String nameBTN, String callback) {
        InlineKeyboardButton inlineButton1 = createInlineKeyboardButton();
        inlineButton1.setText(nameBTN);
        inlineButton1.setUrl(URL);
        List<InlineKeyboardButton> inlineKeyboard1 = new ArrayList<>();
        inlineKeyboard1.add(inlineButton1);

        InlineKeyboardButton inlineButton2 = createInlineKeyboardButton();
        inlineButton2.setText("🔙Назат");
        inlineButton2.setCallbackData(callback);
        List<InlineKeyboardButton> inlineKeyboard2 = new ArrayList<>();
        inlineKeyboard2.add(inlineButton2);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(inlineKeyboard1);
        keyboard.add(inlineKeyboard2);
        return setInlineKeyboardSetup(keyboard);
    }

    public InlineKeyboardMarkup getListInlineKeyboard(ArrayList<DataModel> inputList) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (DataModel input : inputList) {
            InlineKeyboardButton inlineButton = createInlineButton(input.getText(), input.getText());
            List<InlineKeyboardButton> inlineKeyboard = new ArrayList<>();
            inlineKeyboard.add(inlineButton);
            keyboard.add(inlineKeyboard);
        }
        InlineKeyboardButton inlineButton2 = createInlineKeyboardButton();
        inlineButton2.setText("Видалити все");
        inlineButton2.setCallbackData("callbackDelete");
        List<InlineKeyboardButton> inlineKeyboard2 = new ArrayList<>();
        inlineKeyboard2.add(inlineButton2);
        keyboard.add(inlineKeyboard2);
        InlineKeyboardButton inlineButton3 = createInlineKeyboardButton();
        inlineButton3.setText("🔙Назат");
        inlineButton3.setCallbackData("callback");
        List<InlineKeyboardButton> inlineKeyboard3 = new ArrayList<>();
        inlineKeyboard3.add(inlineButton3);
        keyboard.add(inlineKeyboard3);
        return setInlineKeyboardSetup(keyboard);
    }

    private InlineKeyboardButton createInlineButton(String text, String callback) {
        InlineKeyboardButton inlineButton = new InlineKeyboardButton();
        inlineButton.setText(text);
        inlineButton.setCallbackData("list/" + callback);
        return inlineButton;
    }

    private ReplyKeyboardMarkup keyboardSetup(List<KeyboardRow> keyboard) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    private InlineKeyboardMarkup setInlineKeyboardSetup(List<List<InlineKeyboardButton>> keyboard) {
        final InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(keyboard);
        return markupInline;
    }

    private KeyboardButton createKeyboardButton(String name) {
        return new KeyboardButton(name);
    }

    private KeyboardRow createKeyboardRow() {
        return new KeyboardRow();
    }

    private InlineKeyboardButton createInlineKeyboardButton() {
        return new InlineKeyboardButton();
    }
}
