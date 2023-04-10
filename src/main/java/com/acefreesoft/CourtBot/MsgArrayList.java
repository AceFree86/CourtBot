package com.acefreesoft.CourtBot;

import java.util.ArrayList;
import java.util.Arrays;


public class MsgArrayList {
    private final MsgContainer msgContainer = new MsgContainer();

    public final String START_COMMAND = "/start";
    public final String LIST_COMMAND = "/list";
    public final String MAILING_COMMAND = "/mailing";
    public final String PR_COMMAND = "/pr";
    public final String ZKA_COMMAND = "/zka";
    public final String UG_COMMAND = "/ug";
    public final String VB_COMMAND = "/vb";
    public final String ADMIN_COMMAND = "/admin";
    public final String name_pr = "data_pr.json";
    public final String name_zka = "data_zka.json";
    public final String name_ug = "data_ug.json";
    public final String name_vb = "data_vb.json";
    public final String empty_str = "";

    public ArrayList<String[]> getCourtList() {
        final String[][] strCourts = {
                {msgContainer.nameBtn1, msgContainer.nameBtn2, msgContainer.url, msgContainer.nameBtn3},
                {msgContainer.courtAppealBtn1, msgContainer.courtAppealBtn2, msgContainer.courtAppealUrl, msgContainer.nameBtn3},
                {msgContainer.uzhhorodCourtBtn1, msgContainer.uzhhorodCourtBtn2, msgContainer.uzhhorodCourtUrl, msgContainer.nameBtn3},
                {msgContainer.velikobereznyanskyCourtBtn1, msgContainer.velikobereznyanskyCourtBtn2, msgContainer.velikobereznyanskyCourtUrl, msgContainer.nameBtn3}
        };
        return new ArrayList<>(Arrays.asList(strCourts));
    }
    public ArrayList<String[]> getUrlList() {
        final String[][] urlCourts = {
                {msgContainer.nameBTN, msgContainer.urlPr, msgContainer.callbackPr},
                {msgContainer.nameBTN, msgContainer.urlAppeal, msgContainer.callbackAppeal},
                {msgContainer.nameBTN, msgContainer.urlUz, msgContainer.callbackUz},
                {msgContainer.nameBTN, msgContainer.urlVb, msgContainer.callbackVb}
        };
        return new ArrayList<>(Arrays.asList(urlCourts));
    }
    public ArrayList<String> getUrlPhotoPate() {
        ArrayList<String> photoPate = new ArrayList<>();
        photoPate.add(msgContainer.photoLogo);
        photoPate.add(msgContainer.photoGoogleMaps);
        photoPate.add(msgContainer.photoAppleMaps);
        photoPate.add(msgContainer.photoElectronicCourt);
        return photoPate;
    }
    public ArrayList<String> getMessageList() {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(msgContainer.msgDescription);
        messageList.add(msgContainer.msgInstruction);
        messageList.add(msgContainer.msgContacts);
        messageList.add(msgContainer.msgTravelCards);
        messageList.add(msgContainer.msgGoingWeb);
        messageList.add(msgContainer.msgElectronicCourt);
        messageList.add(msgContainer.msgMobileApplication);
        messageList.add(msgContainer.msgMeetingDate);
        messageList.add(msgContainer.msgListCourt);
        messageList.add(msgContainer.msgInputInstruction);
        messageList.add(msgContainer.msgInputPush);
        return messageList;
    }
}
