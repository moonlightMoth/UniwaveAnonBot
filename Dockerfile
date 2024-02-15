FROM maven AS build
WORKDIR /root/
COPY . /root/
RUN mvn clean compile assembly:single

FROM eclipse-temurin

WORKDIR /root/bot
COPY --from=build /root/target/UniwaveAnonBot*.jar /root/bot/UniwaveAnonBot.jar
COPY SECRET /root/bot/SECRET

CMD ["java", "-jar", "/root/bot/UniwaveAnonBot.jar"]
