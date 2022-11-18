package dsw.gerumap.app.errorlogger;

import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.core.messagegen.Message;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import dsw.gerumap.app.core.ErrorLogger;

public class ConsoleLogger implements ErrorLogger, ISubscriber {

    private EventType type;

    private Message message;



    @Override
    public void log() {
        System.out.println(message.toString());
    }

    @Override
    public void update(Object notification) {
        log();
    }


}
