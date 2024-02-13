package database;

import java.util.HashMap;
import java.util.HashSet;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class StubDatabase implements PersistenceHandler {
    private HashMap<User, Chat> anonMessagesDestinations = new HashMap<>();
    private HashSet<Chat> knownChats = new HashSet<>();

    public StubDatabase() {
    }

    public HashMap<User, Chat> getAnonMessagesDestinations() {
        return this.anonMessagesDestinations;
    }

    @Override
    public HashSet<Chat> getKnownChats() {
        return knownChats;
    }
}