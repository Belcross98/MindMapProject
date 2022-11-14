package dsw.gerumap.app.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;


    public MapRepository getMapRepository() {
        return mapRepository;
    }

    public void run() {
        this.gui.start();
    }


    public void initialise(Gui gui, MapRepository mapRepository){
        this.gui = gui;
        this.mapRepository=mapRepository;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    private static ApplicationFramework instance;

    protected ApplicationFramework(){}

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}

