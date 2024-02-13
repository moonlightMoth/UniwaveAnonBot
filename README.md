
# UniwaveAnonBot
This bot allows sending private messages to any group it is registered in.

## Disclaimer
Currently bot has security and persistence issues. Do not use for production.
Persistence of user data and other improvements will be added in future updates.

## How To 

### Prerequisites

* Java 11+ (developed on 11 [temurin](https://adoptium.net/temurin/releases/?version=11))
* [Maven](https://maven.apache.org/)
* [Telegram bot token](https://core.telegram.org/bots)

### Build

```
git clone https://github.com/moonlightmoth/UniwaveAnonBot
cd UniwaveAnonBot
```
Next you need to edit `{project_dir}/src/main/java/bot/Secret.java`. Method `getUniwaveAnonBotToken()` should return token in plain text. This is temporary decision and you are free to provide token any way you like.
An example of `Secret.java` with token in plain text:
```
public class Secret {
    public Secret() {
    }

    public static String getUniwaveAnonBotToken() {
        return "12124:asdsdlkvneijfneuirnvoiuer"; //Your bot token given by BotFather
    }

    public static String getUniwaveAnonBotUsername() {
        return "bot_username"; //Your bot username
    }
}
```

Next you compile and build it.
```
mvn clean compile assembly:single
```
Executable jar will be inside of `target` directory

### Run

Run jar with `java -jar UniwaveAnonBot-{version}.jar`

### Register and usage

To register this bot you need:
* Add you bot to group
* Give it admin rights
* Type `/register@{your_bot_username}`

Then every group member should:
* Proceed to private chat with bot
* Type `/chat`
* Select chat to send anon messages to

Now any text message or sticker sent to bot by such user is copied and sent to selected group.

Anyway, bot gives tips on the way you go, so you don't get lost.

## Contributions

Contributions are welcome, but open a issue with your idea first. We will discuss it.

## License
Source code is licensed under MIT license. For more details click [here](https://github.com/moonlightMoth/UniwaveAnonBot/blob/main/LICENSE)

## Contact
* Gmail: jyhgftm@gmail.com
* [Telegram](https://t.me/moonlightmoth)

