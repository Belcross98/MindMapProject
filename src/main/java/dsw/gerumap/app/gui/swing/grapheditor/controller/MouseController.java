package dsw.gerumap.app.gui.swing.grapheditor.controller;

import dsw.gerumap.app.gui.swing.grapheditor.workspace.ProjectView;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        //Nista
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if(projectView.getMapView() == null){
            return;
        }
        projectView.getStateManager().getCurrentState().mousePressed(e.getPoint(), projectView.getMapView());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if(projectView.getMapView() == null){
            return;
        }
        projectView.getStateManager().getCurrentState().mouseReleased(e.getPoint(), projectView.getMapView());

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Nista
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Nista
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        if(projectView.getMapView() == null){
            return;
        }
        projectView.getStateManager().getCurrentState().mouseDragged(e.getPoint(), projectView.getMapView());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Nista
    }
}
