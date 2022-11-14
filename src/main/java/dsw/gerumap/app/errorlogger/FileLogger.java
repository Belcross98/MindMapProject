package dsw.gerumap.app.errorlogger;

import dsw.gerumap.app.maprepository.observer.Subscriber;
import dsw.gerumap.app.core.ErrorLogger;

import java.io.File;

public class FileLogger implements ErrorLogger, Subscriber {

    private File file;

    @Override
    public void log() {

    }

    @Override
    public void update() {
        log();
    }
}
