package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveAsPictureAction extends  AbstractGerumapAction{


    public SaveAsPictureAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/export.png")); ///relativna putanja u okviru projekta!
        putValue(NAME, "Save as picture");
        putValue(SHORT_DESCRIPTION, "Save as picture");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MapView mapView = MainFrame.getInstance().getProjectView().getMapView();

        JFileChooser jFileChooser = new JFileChooser();
        File file = null;

        if(jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile();
        }

        BufferedImage buffImage = new BufferedImage(mapView.getWidth(), mapView.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffImage.createGraphics();
        mapView.printAll(g2);
        g2.dispose();

        try {
            ImageIO.write(buffImage, "png", new File(file + ".png"));
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}
