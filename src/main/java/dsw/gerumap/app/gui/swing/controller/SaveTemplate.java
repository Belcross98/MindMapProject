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

public class SaveTemplate extends AbstractGerumapAction{

    public SaveTemplate(){


        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Saves selected MindMap as Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap)) {
            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_HAVE_TO_SELECT_MINDMAP);
            return;
        }

        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap)) {
            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_HAVE_TO_SELECT_MINDMAP);
            return;
        }

        MindMap project = (MindMap) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        File projectFile = null;

        if (!project.isChanged()) {
            return;
        }

        if (project.getFilePath() == null || project.getFilePath().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            } else {
                return;
            }

        }

        ApplicationFramework.getInstance().getSerializer().saveTemplate(project);

        project.setChanged(false);
    }


}
