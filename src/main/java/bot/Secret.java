package bot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Secret {
    public Secret() {
    }

    public static String getUniwaveAnonBotToken() {
        Properties properties;
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/secret.properties"));
        } catch (IOException var2) {
            throw new RuntimeException(var2);
        }

        return properties.getProperty("BOT_TOKEN");
    }

    public static String getUniwaveAnonBotUsername() {
        return "uniwave_anon_bot";
    }
}