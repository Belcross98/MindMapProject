package dsw.gerumap.app.errorlogger;

import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.maprepository.observer.Subscriber;
import dsw.gerumap.app.core.ErrorLogger;

public class ConsoleLogger implements ErrorLogger, Subscriber {

    private EventType type;



    @Override
    public void log() {
        System.out.println("[" + type + "]");
    }

    @Override
    public void update() {
        log();
    }
}
