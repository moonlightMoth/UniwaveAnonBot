package bot.replyhandlers;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CommandReplyHandler implements ReplyHandler {
    private AbsSender sender;

    public CommandReplyHandler(AbsSender sender) {
        this.sender = sender;
    }

    public void handleReply(Message message) throws TelegramApiException {
        System.out.println("CommandReplyHandler.handleReply: chatId " + message.getChatId());
        if (!message.getChat().getType().equals("private")) {
            this.sendNoReply(message);
        }

        if (message.getText().equals("/chat")) {
            this.initChatScript(message);
        } else if (message.getText().equals("/clear_chat")) {
            this.stub(message);
        }

    }

    private void initChatScript(Message message) throws TelegramApiException {
        System.out.println("CommandReplyHandler.initChatScript: chatId " + message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Select chat to send messages to\nOnly chats that you and bot both share are selectable.");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow row = new KeyboardRow();
        row.add("Row 1 Button 1");
        row.add("Row 1 Button 2");
        row.add("Row 1 Button 3");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Row 2 Button 1");
        row.add("Row 2 Button 2");
        row.add("Row 2 Button 3");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        try {
            this.sender.execute(sendMessage);
        } catch (TelegramApiException var7) {
            var7.printStackTrace();
        }

    }

    private void sendNoReply(Message message) {
        System.out.println("CommandReplyHandler.sendNoReply: chatId " + message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
    }

    private void stub(Message message) {
        System.out.println("CommandReplyHandler.sendNoReply: stub " + message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("STUB! Not implemented!");
    }
}
