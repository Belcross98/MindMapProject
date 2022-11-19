package dsw.gerumap.app;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.core.messagegen.Message;
import dsw.gerumap.app.core.messagegen.MessageGeneratorImplementation;
import dsw.gerumap.app.errorlogger.ConsoleLogger;
import dsw.gerumap.app.errorlogger.FileLogger;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.maprepository.MapRepositoryImplemetation;
import dsw.gerumap.app.maprepository.observer.ISubscriber;


public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private MessageGenerator messageGenerator;

    private AppCore(){super();}

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public static AppCore getInstance(){
        if(instance == null){
            instance = new AppCore();
        }
        return instance;
    }

    public void run(){
        this.gui.start();
    }

    public static void main(String[] args){
        getInstance().messageGenerator = new MessageGeneratorImplementation();
        ConsoleLogger consoleLogger = new ConsoleLogger();
        FileLogger fileLogger = new FileLogger();
        Gui gui = new SwingGui();
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        MapRepository mapRepository = new MapRepositoryImplemetation();
        appCore.initialise(gui,mapRepository);
        appCore.run();
        ((SwingGui)gui).initialise(getInstance().messageGenerator);
        consoleLogger.initialise(instance.messageGenerator);
        fileLogger.initialise(instance.messageGenerator);
    }
}