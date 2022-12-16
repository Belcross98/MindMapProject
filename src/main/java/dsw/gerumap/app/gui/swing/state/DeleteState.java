package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;

import java.awt.*;
import java.util.ArrayList;

public class DeleteState extends State{

    @Override
    public void mousePressed(Point pos, MapView mapView) {

        ElementPainter painter = mapView.elementPainter(pos);
        if(painter == null){
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NO_ELEMENT_FOUND);
            return;
        }

        for(ElementPainter p : mapView.getSelectedPainters()){

            if(p instanceof TitlePainter) {

                Title title = (Title) p.getElement();
                mapView.removePainter(p);
                mapView.getMindMap().removeChild(p.getElement());

                if(!(title.getLinks().isEmpty())){

                    ArrayList<LinkPainter> painters = title.getLinks();

                    for(LinkPainter linkPainter:title.getLinks()) {

                        mapView.getPainters().remove(linkPainter);
//                      ((Title) ((Link)linkPainter.getElement()).getFrom()).getLinks().remove(linkPainter);
//                      ((Title) ((Link)linkPainter.getElement()).getTo()).getLinks().remove(linkPainter);

                    }
                    //title.getLinks().removeAll(title.getLinks());


                }

           }
        }

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {
        //Nista
    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {
        //Nista
    }
}
