package database;

import java.util.HashMap;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public interface PersistenceHandler {
    HashMap<User, Chat> getAnonMessagesDestinations();
}