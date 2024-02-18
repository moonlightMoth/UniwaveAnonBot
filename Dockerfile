FROM alpine AS build
RUN apk add maven
WORKDIR /root/
COPY . /root/
RUN mvn clean compile assembly:single

FROM alpine

RUN apk add openjdk11-jre
WORKDIR /root/bot
COPY --from=build /root/target/UniwaveAnonBot*.jar /root/bot/UniwaveAnonBot.jar
COPY SECRET /root/bot/SECRET
WORKDIR /root/db
RUN apk add sqlite

WORKDIR /root/bot
CMD ["java", "-jar", "UniwaveAnonBot.jar"]
