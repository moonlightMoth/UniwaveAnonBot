package database;

import java.util.HashMap;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class StubDatabase implements PersistenceHandler {
    private HashMap<User, Chat> anonMessagesDestinations;

    public StubDatabase() {
    }

    public HashMap<User, Chat> getAnonMessagesDestinations() {
        if (this.anonMessagesDestinations == null) {
            this.anonMessagesDestinations = new HashMap();
        }

        return this.anonMessagesDestinations;
    }
}