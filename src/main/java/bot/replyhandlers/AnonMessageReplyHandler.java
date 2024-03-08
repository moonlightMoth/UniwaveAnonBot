package bot.replyhandlers;

import java.util.HashMap;

import database.PersistenceHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AnonMessageReplyHandler extends ReplyHandler {


    public AnonMessageReplyHandler(AbsSender sender, PersistenceHandler db) {
        super(sender, db);
    }

    public void handleReply(Update update) throws TelegramApiException {

        if (update.hasMessage() && update.getMessage().getChat().getType().equals("private"))
        {
            if (db.getAnonMessageDirection(update.getMessage().getFrom()) == null)
            {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("You have not selected any group to send messages to\n" +
                        "please refer to /help command");

                sender.execute(sendMessage);
                return;
            }

            if (update.getMessage().hasText())
            {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(db.getAnonMessageDirection(update.getMessage().getFrom()).getId());
                sendMessage.setText(update.getMessage().getText());

                sender.execute(sendMessage);
            }
            if (update.getMessage().hasSticker())
            {
                SendSticker sendSticker = new SendSticker();
                sendSticker.setChatId(db.getAnonMessageDirection(update.getMessage().getFrom()).getId());
                sendSticker.setSticker(new InputFile(update.getMessage().getSticker().getFileId()));

                sender.execute(sendSticker);
            }

        }
    }
}