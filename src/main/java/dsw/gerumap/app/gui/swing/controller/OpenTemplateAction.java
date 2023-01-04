package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenTemplateAction extends AbstractGerumapAction{


    public OpenTemplateAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Open Template");
        putValue(SHORT_DESCRIPTION, "Opens existing mind map template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)) {
            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_HAVE_TO_SELECT_PROJECT);
            return;
        }

        {
            JFileChooser jfc = new JFileChooser("src/main/resources/templates");

            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = jfc.getSelectedFile();
                    MindMap p = ApplicationFramework.getInstance().getSerializer().loadTemplate(file);
                    MainFrame.getInstance().getMapTree().loadTemplate(p);


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }


    }
}
