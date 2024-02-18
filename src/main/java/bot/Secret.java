package bot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Secret {

    private static String BOT_TOKEN = "";
    private static String BOT_USERNAME = "";

    public Secret() {
    }

    public static String getBotToken() {
        if (BOT_TOKEN.isEmpty())
        {
            Properties properties;
            try {
                properties = new Properties();
                properties.load(new FileInputStream("SECRET"));
                BOT_TOKEN = properties.getProperty("BOT_TOKEN");
            } catch (IOException var2) {
                throw new RuntimeException(var2);
            }
        }

        return BOT_TOKEN;
    }

    public static String getBotUsername() {
        if (BOT_USERNAME.isEmpty())
        {
            Properties properties;
            try {
                properties = new Properties();
                properties.load(new FileInputStream("SECRET"));
                BOT_USERNAME = properties.getProperty("BOT_USERNAME");
            } catch (IOException var2) {
                throw new RuntimeException(var2);
            }
        }

        return BOT_USERNAME;
    }
}
