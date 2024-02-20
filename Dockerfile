FROM alpine AS build
RUN apk add maven
WORKDIR /root/
COPY . /root/
RUN mvn clean compile assembly:single

FROM alpine

RUN apk add openjdk11-jre
RUN apk add sqlite
WORKDIR /root/bot
COPY --from=build /root/target/UniwaveAnonBot*.jar /root/bot/UniwaveAnonBot.jar
COPY secret/SECRET /root/bot/secret/SECRET
VOLUME /root/bot/db

CMD ["java", "-jar", "UniwaveAnonBot.jar"]
