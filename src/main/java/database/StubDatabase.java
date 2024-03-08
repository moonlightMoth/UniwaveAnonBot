package database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public class StubDatabase implements PersistenceHandler {
    private HashMap<User, Chat> anonMessagesDestinations = new HashMap<>();
    private HashMap<Long, Chat> knownChats = new HashMap<>();
    private HashMap<User, Set<Chat>> userMembershipInChats = new HashMap<>();

    public StubDatabase() {
    }

    @Override
    public int addKnownChat(Chat chat) {
        if (knownChats.containsKey(chat.getId()))
            return ALREADY_KNOWN_CHAT;

        knownChats.put(chat.getId(), chat);
        return OK;
    }

    @Override
    public int changeAnonMessagesDirection(User user, Chat chat) {
        if (!knownChats.containsKey(chat.getId()))
            return NO_SUCH_KNOWN_CHAT;

        if (!userMembershipInChats.containsKey(user))
            return NO_SUCH_USER;

        if (!userMembershipInChats.get(user).contains(chat))
            return USER_NOT_ENLISTED_IN_CHAT;

        anonMessagesDestinations.put(user, chat);

        return OK;
    }

    @Override
    public Set<Chat> getUserMemberships(User user) {
        if (!userMembershipInChats.containsKey(user))
            return null;

        return userMembershipInChats.get(user);
    }

    @Override
    public int addUserMembership(User user, Chat chat) {

        if (!knownChats.containsKey(chat.getId()))
            return NO_SUCH_KNOWN_CHAT;

        if (!userMembershipInChats.containsKey(user))
            userMembershipInChats.put(user, new HashSet<>());
        else if (userMembershipInChats.get(user).contains(chat))
            return USER_ALREADY_HAS_CHAT_MEMBERSHIP;

        userMembershipInChats.get(user).add(chat);

        return OK;
    }

    @Override
    public Chat getAnonMessageDirection(User user) {

        if (!anonMessagesDestinations.containsKey(user))
            return null;

        return anonMessagesDestinations.get(user);
    }

    @Override
    public Chat getChatById(Long chatId) {
        if (!knownChats.containsKey(chatId))
            return null;

        return knownChats.get(chatId);
    }

    @Override
    public int deleteUserData(User user) {
        userMembershipInChats.remove(user);
        anonMessagesDestinations.remove(user);
        return OK;
    }
}