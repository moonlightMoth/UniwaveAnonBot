package bot;

import bot.replyhandlers.AnonMessageReplyHandler;
import bot.replyhandlers.CallbackReplyHandler;
import bot.replyhandlers.CommandReplyHandler;
import bot.replyhandlers.ReplyHandler;
import database.PersistenceHandler;
import database.StubDatabase;

import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AnonMessagesLongPollingBot extends TelegramLongPollingBot {
    private PersistenceHandler persistenceHandler = new StubDatabase();
    private ReplyHandler commandReplyHandler = new CommandReplyHandler(this, persistenceHandler);
    private ReplyHandler callbackReplyHandler = new CallbackReplyHandler(this, persistenceHandler);
    private ReplyHandler anonMessageReplyHandler = new AnonMessageReplyHandler(this, persistenceHandler);

    public AnonMessagesLongPollingBot(String token) {
        super(token);
    }

    public void onUpdateReceived(Update update) {
        System.out.println();
        System.out.println(update.getMessage());
        System.out.println(update);

        try {
            if (update.hasMessage() && update.getMessage().isCommand()) {
                System.out.println("AnonMessagesLongPollingBot.onUpdateReceived: isCommand");
                this.commandReplyHandler.handleReply(update);

            } else if (update.hasCallbackQuery()) {
                System.out.println("AnonMessagesLongPollingBot.onUpdateReceived: hasCallbackQuery");
                this.callbackReplyHandler.handleReply(update);
            }
            else if (update.hasMessage())
            {
                System.out.println("AnonMessagesLongPollingBot.onUpdateReceived: hasMessage, not cmd");
                anonMessageReplyHandler.handleReply(update);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public String getBotUsername() {
        return Secret.getUniwaveAnonBotUsername();
    }

    public void onRegister() {
        super.onRegister();
        System.out.println("registered");
    }
}