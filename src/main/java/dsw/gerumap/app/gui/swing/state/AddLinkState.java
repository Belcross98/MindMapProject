package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.commands.AddLinkCommand;
import dsw.gerumap.app.maprepository.commands.AddTitleCommand;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class AddLinkState extends  State{


    private Link link = null;
    private LinkPainter linkPainter = null;

    @Override
    public void mousePressed(Point pos, MapView mapView) {


        link = new Link(5,Color.BLACK,"description",null,null,pos,pos);

        for(ElementPainter painter:mapView.getPainters()){

            if(painter.elementAt(pos) && painter instanceof TitlePainter){
                link.setFrom(painter.getElement());
                break;
            }

        }
        if(link.getFrom() == null){
            MainFrame.getInstance().getMessageGenerator().messageGenerate(EventType.YOU_DONT_HAVE_INITIAL_POINT);
            return;
        }

        //hande this with message generator

        linkPainter = new LinkPainter(link);

    }

    @Override
    public void mouseDragged(Point pos, MapView mapView) {

        if(link.getFrom() == null)
            return;


        if(link.getSubscribers() != null)
            link.removeSubscriber(mapView);
        link.addSubscriber(mapView);
        mapView.removePainter(linkPainter);
        linkPainter.setElement(link);
        link.setToPoint(pos);
        mapView.addPainter(linkPainter);

    }

    @Override
    public void mouseReleased(Point pos, MapView mapView) {

        AbstractCommand abstractCommand = new AddLinkCommand(linkPainter,  pos, link, (Title) link.getFrom());
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(abstractCommand);


    }
}
