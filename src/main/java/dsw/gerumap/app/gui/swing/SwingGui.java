package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.observer.ISubscriber;

public class SwingGui implements Gui {

    private MainFrame instance;

    public void  initialise(MessageGenerator messageGenerator){
        instance.setMessageGenerator(messageGenerator);
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }
}
