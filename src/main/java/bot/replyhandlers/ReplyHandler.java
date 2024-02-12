package bot.replyhandlers;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface ReplyHandler {
    void handleReply(Message var1) throws TelegramApiException;
}