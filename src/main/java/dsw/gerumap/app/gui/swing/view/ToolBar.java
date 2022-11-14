package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionMenager().getExtitAction());
        add(MainFrame.getInstance().getActionMenager().getNewProjectAction());
        add(MainFrame.getInstance().getActionMenager().getInfoAction());
        add(MainFrame.getInstance().getActionMenager().getRemoveAction());
    }
}
