package dsw.gerumap.app.errorlogger;

import dsw.gerumap.app.core.messagegen.Message;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import dsw.gerumap.app.core.ErrorLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ErrorLogger, ISubscriber {

    private File file;
    private Message message;

    @Override
    public void log() {
        try {
            file = new File("log.txt");
            FileWriter fw = new FileWriter(file);
            fw.write(message.toString());
            fw.close();
            System.out.println("Uspesno je ubaceno u fajl");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    @Override
    public void update(Object notification) {
        log();
    }
}
