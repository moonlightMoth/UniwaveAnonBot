package bot.replyhandlers;

import database.PersistenceHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CommandReplyHandler extends ReplyHandler {

    public CommandReplyHandler(AbsSender sender, PersistenceHandler db) {
        super(sender, db);
    }

    public void handleReply(Update update) throws TelegramApiException {
        Message message = update.getMessage();

        System.out.println("CommandReplyHandler.handleReply: chatId " + message.getChatId());

        if (!message.getChat().getType().equals("private")) {
            if (message.getText().equals("/register@uniwave_anon_bot")) {
                processGroupRegisterReply(message);

                db.getKnownChats().add(message.getChat());
            }
            if (message.getText().equals("/chat@uniwave_anon_bot")) {
                stub(message);
            }
            if (message.getText().equals("/clear_chat@uniwave_anon_bot")) {
                stub(message);
            }
        }
        else
        {
            if (message.getText().equals("/register")) {
                processPrivateRegisterReply(message);
            }
            if (message.getText().equals("/chat"))
            {
                processPrivateChatReply(message);
            }
        }

    }

    private void processGroupRegisterReply(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Now bot is acknowledged of this chat\n" +
                "Chat members can set up bot to send their anon messages here\n" +
                "Proceed to private chat with this bot for further details\n" +
                "https://t.me/uniwave_anon_bot");

        sender.execute(sendMessage);
    }

    private void processPrivateRegisterReply(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Unsupported operation here\n" +
                "You need to call it inside of group chat in order to register bot there");

        sender.execute(sendMessage);
    }

    private void processPrivateChatReply(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Select a group to send message to");


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> Buttons = new ArrayList<>();

        db.getKnownChats().forEach(chat -> {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(chat.getTitle());
            inlineKeyboardButton.setCallbackData(chat.getId() + "///" + chat.getType() + "///" + message.getChatId());
            Buttons.add(inlineKeyboardButton);
        });

        keyboard.add(Buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        sender.execute(sendMessage);

    }

    private void sendNoReply(Message message) throws TelegramApiException{
        System.out.println("CommandReplyHandler.sendNoReply: chatId " + message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
    }

    private void stub(Message message) throws TelegramApiException{
        System.out.println("CommandReplyHandler.sendNoReply: stub " + message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("STUB! Not implemented!");

        sender.execute(sendMessage);
    }
}
