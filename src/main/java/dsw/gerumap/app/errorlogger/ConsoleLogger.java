package dsw.gerumap.app.errorlogger;

import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.core.messagegen.Message;
import dsw.gerumap.app.core.messagegen.MessageType;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import dsw.gerumap.app.core.ErrorLogger;

import java.time.LocalDateTime;

public class ConsoleLogger implements ErrorLogger, ISubscriber {

    private EventType type;

    private Message message;

    @Override
    public void log() {
        System.out.println(message.toString());
    }

    @Override
    public void initialise(MessageGenerator messageGenerator) {
        messageGenerator.addSubscriber(this);
    }

    @Override
    public void update(Object notification) {
        message = (Message) notification;
        log();
    }


}
