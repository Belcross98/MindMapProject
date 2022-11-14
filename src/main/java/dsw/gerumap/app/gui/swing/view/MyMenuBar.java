package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File") ;
        JMenu fileMenu1 = new JMenu("Help");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionMenager().getExtitAction());
        fileMenu.add(MainFrame.getInstance().getActionMenager().getNewProjectAction());
        fileMenu1.add(MainFrame.getInstance().getActionMenager().getInfoAction());


        this.add(fileMenu);
        this.add(fileMenu1);
    }
}
