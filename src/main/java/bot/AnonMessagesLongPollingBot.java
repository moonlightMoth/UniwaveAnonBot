package bot;

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
    private ReplyHandler commandReplyHandler = new CommandReplyHandler(this);
    private PersistenceHandler persistenceHandler = new StubDatabase();
    private HashMap<User, Chat> anonMessagesDestinations;

    public AnonMessagesLongPollingBot(String token) {
        super(token);
        this.anonMessagesDestinations = this.persistenceHandler.getAnonMessagesDestinations();
    }

    public void onUpdateReceived(Update update) {
        System.out.println();
        System.out.println(update.getMessage());
        System.out.println(update);
        System.out.println();
        if (update.hasMessage() && update.getMessage().isCommand()) {
            try {
                this.commandReplyHandler.handleReply(update.getMessage());
            } catch (TelegramApiException var3) {
                throw new RuntimeException(var3);
            }
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