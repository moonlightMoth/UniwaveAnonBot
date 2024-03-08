package database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public interface PersistenceHandler {

    int NO_SUCH_KNOWN_CHAT = -12489123;
    int NO_SUCH_USER = 293857;
    int USER_NOT_ENLISTED_IN_CHAT = -278356;
    int USER_ALREADY_HAS_CHAT_MEMBERSHIP = 982356;
    int ALREADY_KNOWN_CHAT = 328975;
    int OK = 0;

    int addKnownChat(Chat chat);
    int changeAnonMessagesDirection(User user, Chat chat);
    Set<Chat> getUserMemberships(User user);
    int addUserMembership(User user, Chat chat);
    Chat getAnonMessageDirection(User user);
    Chat getChatById(Long chatId);
    int deleteUserData(User user);
}