![GitHub Release](https://img.shields.io/github/v/release/moonlightmoth/UniwaveAnonBot?include_prereleases)
![GitHub License](https://img.shields.io/github/license/moonlightmoth/UniwaveAnonBot)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/moonlightmoth/UniwaveAnonBot/docker-image.yml)




# UniwaveAnonBot
This bot allows sending private messages to any group it is registered in.

## Disclaimer
Currently bot has security and persistence issues. Do not use for production.
Persistence of user data and other improvements will be added in future updates.


# How To


## Prerequisites

* Java 11+ (developed on 11 [temurin](https://adoptium.net/temurin/releases/?version=11))
* [Maven](https://maven.apache.org/)
* [Telegram bot token](https://core.telegram.org/bots)

<b>OR </b>

* [Docker](https://www.docker.com/)
* [Telegram bot token](https://core.telegram.org/bots)

## Build and run

### Build on host

```
git clone https://github.com/moonlightmoth/UniwaveAnonBot
cd UniwaveAnonBot
mvn clean compile assembly:single
```
Executable jar will be inside of `target` directory

<b>---------------------!!!IMPORTANT!!!--------------------- <br> <br>
You need to create SECRET file and place it inside 
directory containing built jar. 
It must contain bot token and bot username</b>
<br><br>
For example: 
```
BOT_TOKEN=8273647236892734:ex_amplesjinvsjidnvjsd
BOT_USERNAME=your_bot_username
```


### Run on host

Run jar with `java -jar UniwaveAnonBot-{version}.jar`


### Build with docker

Bot can be built and executed inside docker container. To make it work follow these steps:
```
git clone https://github.com/moonlightmoth/UniwaveAnonBot
cd UniwaveAnonBot
```

Create`SECRET` file in project root and place your bot token and username there.

```
docker build . --tag {your_tag}
```

### Run with docker

```
docker run {your_tag}
```

## Registration and usage

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

# 

## Contributions

Contributions are welcome, but open a issue with your idea first. We will discuss it.

## License
Source code is licensed under MIT license. For more details click [here](https://github.com/moonlightMoth/UniwaveAnonBot/blob/main/LICENSE)

## Contact
* Gmail: jyhgftm@gmail.com
* [Telegram](https://t.me/moonlightmoth)

