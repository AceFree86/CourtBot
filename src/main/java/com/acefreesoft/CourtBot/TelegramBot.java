package com.acefreesoft.CourtBot;

import com.acefreesoft.CourtBot.processors.DefaultProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    String botToken;
    @Value("${bot.username}")
    String botUsername;
    @Override
    public String getBotToken() {
        return botToken;
    }
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    private final DefaultProcessor processor = new DefaultProcessor(this);

    @Override
    public void onUpdateReceived(Update update) {
        processor.process(update);
    }
}
