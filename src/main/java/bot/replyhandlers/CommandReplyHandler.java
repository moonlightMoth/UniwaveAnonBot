package bot.replyhandlers;

import database.PersistenceHandler;
import bot.Secret;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class CommandReplyHandler extends ReplyHandler {

    public CommandReplyHandler(AbsSender sender, PersistenceHandler db) {
        super(sender, db);
    }

    public void handleReply(Update update) throws TelegramApiException {
        Message message = update.getMessage();

        System.out.println("CommandReplyHandler.handleReply: chatId " + message.getChatId());

        if (!message.getChat().getType().equals("private")) {

            if (message.getText().equals("/register@" + Secret.getBotUsername()))
                processGroupRegistration(message);

            if (message.getText().equals("/enlist@" + Secret.getBotUsername())) {
                processGroupUserEnlistment(message);
            }

            if (message.getText().equals("/chat@" + Secret.getBotUsername()))
                stub(message);

            if (message.getText().equals("/clear_chat@" + Secret.getBotUsername()))
                stub(message);
        }
        else
        {
            if (message.getText().equals("/register"))
                processPrivateRegistration(message);

            if (message.getText().equals("/chat"))
                processPrivateChatCmd(message);

            if (message.getText().equals("/enlist"))
                processPrivateEnlistment(message);

            if (message.getText().equals("/start"))
                processPrivateStart(message);
        }

    }

    private void processPrivateStart(Message message) throws TelegramApiException {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Hi!\n" +
                "This bot can send private messages to any chat you like" +
                "For more information refer to /help" +
                "If you got here by enlistment link type /chat to select chat");

        sender.execute(sendMessage);
    }

    private void processGroupUserEnlistment(Message message) throws TelegramApiException {
        int callback = db.addUserMembership(message.getFrom(), message.getChat());

        if (callback == PersistenceHandler.USER_ALREADY_HAS_CHAT_MEMBERSHIP)
            return;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if (callback == PersistenceHandler.NO_SUCH_KNOWN_CHAT)
        {
            sendMessage.setText("This chat is not known by this bot yet.\n" +
                    "Try /register@" + Secret.getBotUsername());
        }
        else
        {
            sendMessage.setText("Congrats!\n" +
                    "From now on @" + message.getFrom().getUserName() + " " +
                    "can set up bot to send their anon messages here\n" +
                    "Proceed to private chat with this bot for further details\n" +
                    "https://t.me/" + Secret.getBotUsername());
        }

        sender.execute(sendMessage);
    }

    private void processPrivateEnlistment(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("You should type this command in chat you want messages to be sent to after bot registration.\n" +
                "Refer /help for more information.");

        sender.execute(sendMessage);
    }

    private void processGroupRegistration(Message message) throws TelegramApiException {
        int callback = db.addKnownChat(message.getChat());

        if (callback == PersistenceHandler.ALREADY_KNOWN_CHAT)
            return;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Now bot is acknowledged of this chat\n" +
                "Each of chat members should type /enlist@" + Secret.getBotUsername() +
                        " to activate bot for themselves\n");

        sender.execute(sendMessage);
    }

    private void processPrivateRegistration(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Unsupported operation here\n" +
                "You need to call it inside of group chat in order to register bot there");

        sender.execute(sendMessage);
    }

    private void processPrivateChatCmd(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Select a group to send message to");


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> Buttons = new ArrayList<>();

        db.getUserMemberships(message.getFrom()).forEach(chat -> {
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
