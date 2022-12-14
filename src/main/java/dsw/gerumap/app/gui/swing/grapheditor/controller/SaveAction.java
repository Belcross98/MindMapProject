package dsw.gerumap.app.gui.swing.grapheditor.controller;

import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.Settings;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaveAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();
        Color color = MainFrame.getInstance().getProjectView().getSettings().getJColorChooser().getColor();
        String name = MainFrame.getInstance().getProjectView().getSettings().getJTextField().getText();
        List<ElementPainter> selectedPainters = mapView.getSelectedPainters();
        String stroke1 = MainFrame.getInstance().getProjectView().getSettings().getJTextField2().getText();
        int stroke = Integer.parseInt(stroke1);

        for(ElementPainter painter : selectedPainters){
            painter.getElement().setColor(color);
            painter.getElement().setCurrentColor(color);
            painter.getElement().setName(name);
            painter.getElement().setWidth(stroke);


        }
        MainFrame.getInstance().getProjectView().getMapView().repaint();
        MainFrame.getInstance().getProjectView().getSettings().getJTextField().setText("");
        MainFrame.getInstance().getProjectView().getSettings().getJTextField2().setText("");
        MainFrame.getInstance().getProjectView().getSettings().setVisible(false);

    }

}
