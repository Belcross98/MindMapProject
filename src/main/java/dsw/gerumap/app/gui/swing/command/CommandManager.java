package dsw.gerumap.app.gui.swing.command;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;

@Getter
@Setter

public class CommandManager  {

    private ArrayList<AbstractCommand> commands = new ArrayList<>();
    private int currCommand = 0;


    public void addCommand(AbstractCommand command){
        while(currCommand < commands.size())
            commands.remove(currCommand);
        commands.add(command);
        doCommand();
    }


    public void doCommand(){
        if(currCommand < commands.size()){
            commands.get(currCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTree());
            MainFrame.getInstance().getActionMenager().getUndoAction().setEnabled(true);
        }
        if(currCommand==commands.size()){
            MainFrame.getInstance().getActionMenager().getRedoAction().setEnabled(false);
        }
    }

    public void undoCommand(){
        if(currCommand > 0){
            MainFrame.getInstance().getActionMenager().getRedoAction().setEnabled(true);
            commands.get(--currCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTree());
        }
        if(currCommand==0){
            MainFrame.getInstance().getActionMenager().getUndoAction().setEnabled(false);
        }
    }
}
