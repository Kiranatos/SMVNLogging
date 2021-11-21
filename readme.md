Summary of logging via Maven

DEFAULT: C:\Program Files\Java\jdk-13.0.1\conf\logging.properties

JUL:    |log4j:
ALL     |ALL - Уровень, при котором будут записаны все логи из системы;
FINEST  |TRACE - Менее приоритетные логи для отладки, с наименьшим уровнем логирования;
FINER   |
FINE    |DEBUG - Логи, необходимые для отладки приложения. Для уверенности в том, что система делает именно то, что от нее ожидают, или описания действия системы: “method1 начал работу”; Fine-grained informational events that are most useful to debug an application; Detailed information, typically of interest only when diagnosing problems.
CONFIG  |
INFO    |INFO - Лог, который записывает важные действия в приложении. Это не ошибки, это не предостережение, это ожидаемые действия системы; Informational messages that highlight the progress of the application at coarse-grained level; Confirmation that things are working as expected.
WARNING |WARN - Обозначаются логи, которые содержат предостережение. Произошло неожиданное действие, несмотря на это система устояла и выполнила запрос; Potentially harmful situations; An indication that something unexpected happened, or indicative of some problem in the near future (e.g. 'disk space low')
SEVERE  |ERROR - Уровень ошибок, когда есть проблемы, которые нужно решить. Ошибка не останавливает работу приложения в целом. Остальные запросы могут работать корректно; Error events that might still allow the application to continue running; Due to a more serious problem, the software has not been able to perform some function.
        |FATAL - Ошибка, после которой приложение уже не сможет работать и будет остановлено, например, JVM out of memory error; Very severe error events that will presumably lead the application to abort; A serious error, indicating that the program itself may be unable to continue running
OFF     |OFF - Никакие логи не записываются, все будут проигнорированы;

java.util.logging
Handlers by default: ConsoleHandler, FileHandler(turned off in logging.properties)
Formatters:
    SimpleFormatter - Generates text with basic information, ConsoleHandler uses this formatter to print log messages to console.
    XMLFormatter - Generates XML message for the log, FileHandler uses XMLFormatter as a default formatter.

1) https://stackoverflow.com/questions/53211694/change-color-and-format-of-java-util-logging-logger-output-in-eclipse
    - сделано: законспектированно в Demo03Formatter
    - не сделано: просмотреть ссылки на странице

/*******************************************************************************/

log4j 
Configuration files: log4j2-test.xml log4j2.xml
Структура логирования:
- Appenders (куда пишется)https://logging.apache.org/log4j/2.x/manual/appenders.html  :
    + console: ConsoleAppender;
    + files: FileAppender, RollingFileAppender, MemoryMappedFileAppender, DailyRollingFileAppender & etc;
    + db: JdbcAppender, JpaAppender, NoSqlAppender;
    + TCP/IP: TelnetAppender;
    + topics JMS: JmsAppender;
    + SMTP: SmtpAppender;
    + socket: SocketAppender;
    + Syslog: SyslogAppender;
    + asynchronous(для быстродействия): AsyncAppender;
    + implement Appender: написать свой аппендер
    + any: Writer, OutputStream(WriterAppender, OutputStreamAppender);
- Layout Patterns (в каком виде): HTML, JSON, RFC-5424, Serialized, Syslog, XML, YAML, CSV, GELF;
- Logger (сам логгер и его сообщения):

/*******************************************************************************/

Logback:
    улучшена производительность;
    добавлена нативная поддержка slf4j;
    расширена опция фильтрации.
Bсе логи от DEBUG и выше by default. Hастройка в xml:
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>app.log</file>
        <encoder>
            <pattern>%d{HH:mm:ss,SSS} %-5p [%c] - %m%n</pattern>
        </encoder>
    </appender>
    <logger name="org.hibernate.SQL" level="DEBUG" />
    <logger name="org.hibernate.type.descriptor.sql" level="TRACE" />
    <root level="info">
        <appender-ref ref="FILE" />
    </root>
</configuration>

/*******************************************************************************/

slf4j (Simple Logging Facade for Java)
Обертка вокруг log4j, JUL, common-loggins и logback
Пример вывода: log.debug("User {} connected from {}", user, request.getRemoteAddr()); */

/*******************************************************************************/

JCL (jakarta commons logging)



