package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.CommandManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SwingGui implements Gui {

    private MainFrame instance;

    private CommandManager commandManager;

    public void  initialise(MessageGenerator messageGenerator){
        instance.setMessageGenerator(messageGenerator);
        this.commandManager = new CommandManager();
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }

    @Override
    public void disableUndoAction() {

    }

    @Override
    public void disableRedoAction() {

    }

    @Override
    public void enableUndoAction() {

    }

    @Override
    public void enableRedoAction() {

    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }
}
