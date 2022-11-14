package dsw.gerumap.app;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.maprepository.MapRepositoryImplemetation;


public class AppCore extends ApplicationFramework {

    private static AppCore instance;
    private AppCore(){super();}


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
        Gui gui = new SwingGui();
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        MapRepository mapRepository = new MapRepositoryImplemetation();
        appCore.initialise(gui,mapRepository);
        appCore.run();
    }
}