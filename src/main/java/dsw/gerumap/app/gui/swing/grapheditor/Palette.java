package dsw.gerumap.app.gui.swing.grapheditor;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Palette extends JToolBar {

    public Palette(){
        super(JToolBar.VERTICAL);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(MainFrame.getInstance().getActionMenager().getAddTitleAction());
        addSeparator();
        add(MainFrame.getInstance().getActionMenager().getAddLinkAction());
        addSeparator();
        add(MainFrame.getInstance().getActionMenager().getSelectAction());
        addSeparator();
        add(MainFrame.getInstance().getActionMenager().getDeleteItemAction());
        addSeparator();
        add(MainFrame.getInstance().getActionMenager().getSettingsAction());
        addSeparator();
        add(MainFrame.getInstance().getActionMenager().getMoveAction());

    }
}
