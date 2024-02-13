package bot.replyhandlers;

import database.PersistenceHandler;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class ReplyHandler {
    protected AbsSender sender;
    protected PersistenceHandler db;

    public ReplyHandler(AbsSender sender, PersistenceHandler db) {
        this.db = db;
        this.sender = sender;
    }

    public abstract void handleReply(Update update) throws TelegramApiException;
}