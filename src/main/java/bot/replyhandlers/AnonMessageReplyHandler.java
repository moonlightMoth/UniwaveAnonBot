package bot.replyhandlers;

import java.util.HashMap;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AnonMessageReplyHandler implements ReplyHandler {
    private AbsSender sender;
    private HashMap<Chat, AnonMessageReplyHandler> map;

    public AnonMessageReplyHandler(Chat chat, AbsSender sender) {
    }

    public void handleReply(Message message) throws TelegramApiException {
    }
}