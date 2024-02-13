package bot.replyhandlers;

import database.PersistenceHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CallbackReplyHandler extends ReplyHandler {

    public CallbackReplyHandler(AbsSender sender, PersistenceHandler db)
    {
        super(sender, db);

    }

    @Override
    public void handleReply(Update update) throws TelegramApiException {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        String[] data = callbackQuery.getData().split("///");
        Chat incomingChat = new Chat();
        incomingChat.setId(Long.parseLong(data[0]));
        incomingChat.setTitle(data[1]);

        db.getAnonMessagesDestinations().put(callbackQuery.getFrom(), incomingChat);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(data[2]);
        sendMessage.setText("Now your messages will be delivered to preferred chat\n" +
                "Try to type something here\n" +
                "If you want to change chat use /chat command\n" +
                "If you want to stop sending messages to any chat use /clear_chat");

        sender.execute(sendMessage);
    }



}
