FROM azul/zulu-openjdk:11
EXPOSE 8080 7070

COPY launch.sh /service/launch.sh
RUN chmod 755 /service/launch.sh
COPY @project.build.finalName@.jar /service/app.jar
RUN chmod 755 /service/app.jar

ENTRYPOINT ["/service/launch.sh"]