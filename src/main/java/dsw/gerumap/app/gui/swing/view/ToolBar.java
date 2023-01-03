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
        add(MainFrame.getInstance().getActionMenager().getAddAuthorAction());
        add(MainFrame.getInstance().getActionMenager().getSaveAsPictureAction());
        add(MainFrame.getInstance().getActionMenager().getSaveAction());
        add(MainFrame.getInstance().getActionMenager().getOpenAction());
    }
}
