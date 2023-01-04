package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.gui.swing.command.AbstractCommand;
import dsw.gerumap.app.gui.swing.command.implementation.AddChildCommand;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.grapheditor.workspace.MapView;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.factory.*;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;


public class MapTreeImplementation implements MapTree{

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    public MapTreeImplementation() {
        MapRepository mapRepository = ApplicationFramework.getInstance().getMapRepository();
        ProjectExplorer projectExplorer = mapRepository.getProjectExplorer();
        MapTreeItem mapTreeItem = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(mapTreeItem);
        this.treeView = new MapTreeView(treeModel);

    }

    @Override
    public MapTreeView getTree() {
        return treeView;
    }

    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void addElement(MapTreeItem parent, MapNode child) {

        if(!(parent.getMapNode() instanceof MapNodeComposite)) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NODE_CANNOT_HAVE_CHILDREN);
            return;
        }


        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        child.setParent(((MapNodeComposite) parent.getMapNode()));
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public MapTreeItem getNode(MapNode child) {

        MapTreeItem root = this.getRoot();
        Enumeration en = root.depthFirstEnumeration();
        while (en.hasMoreElements()) {

            MapTreeItem node = (MapTreeItem) en.nextElement();
            System.out.println(node.toString() + "IM THIS GUY ");
            if(node.getMapNode() == child){
                System.out.println("I FOUND U ");
                return node;
            }

        }

        return null;
    }

    @Override
    public MapTreeItem getRoot() {

        return (MapTreeItem) this.treeModel.getRoot();
    }

    @Override
    public void loadProject(MapNodeComposite node) {


        MapTreeItem loadedProject = new MapTreeItem(node);
        getRoot().add(loadedProject);
        MainFrame.getInstance().getProjectView().refreshWorkspace(node);

        MapNodeComposite mapNode = (MapNodeComposite) getRoot().getMapNode();
        mapNode.addChild(node);
        node.setParent(getRoot().getMapNode());
        node.addSubscriber(MainFrame.getInstance().getProjectView().getMapView());

        for(MapNode nodic: node.getListOfChildren()){

            nodic.setParent(node);
            nodic.addSubscriber(MainFrame.getInstance().getProjectView().getMapView());
            MapTreeItem reborNodic = new MapTreeItem(nodic);
            loadedProject.add(reborNodic);

            for(MapNode childNodic: ((MapNodeComposite) nodic).getListOfChildren()){



                reborNodic.add(new MapTreeItem(childNodic));
                childNodic.addSubscriber(MainFrame.getInstance().getProjectView().getMapView());
                childNodic.setParent(nodic);

            }


            for(MapNode childNodic: ((MapNodeComposite) nodic).getListOfChildren()){

                if(childNodic instanceof Title)
                    ((Title) childNodic).setLinks(new ArrayList<>());
            }

            for(MapNode childNodic: ((MapNodeComposite)nodic).getListOfChildren()){


                if(childNodic instanceof Link){


                    ElementPainter fromElement = MainFrame.getInstance().getProjectView().getMapView().elementPainter(((Link) childNodic).getFromPoint());
                    Title from = (Title) fromElement.getElement();
                    from.addLink((Link) childNodic);
                    ElementPainter toElement = MainFrame.getInstance().getProjectView().getMapView().elementPainter(((Link) childNodic).getToPoint());
                    Title to = (Title) toElement.getElement();
                    to.addLink((Link) childNodic);


                    ((Link) childNodic).setFrom(from);
                    ((Link) childNodic).setTo(to);


                }

            }

        }

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadTemplate(MindMap node) {

        MapTreeItem loadedProject = new MapTreeItem(node);
        MapTreeItem project = MainFrame.getInstance().getMapTree().getSelectedNode();
        project.add(loadedProject);

        MapNodeComposite projectNode = (MapNodeComposite) project.getMapNode();
        projectNode.addChild(node);
        node.setParent(project.getMapNode());
        node.addSubscriber(MainFrame.getInstance().getProjectView().getMapView());

        MainFrame.getInstance().getProjectView().refreshWorkspace(project.getMapNode());

        for(MapNode childNodic: node.getListOfChildren()){

            if(childNodic instanceof Title)
                ((Title) childNodic).setLinks(new ArrayList<>());
        }

        for(MapNode nodic: node.getListOfChildren()){

            nodic.setParent(node);
            nodic.addSubscriber(MainFrame.getInstance().getProjectView().getMapView());
            MapTreeItem reborNodic = new MapTreeItem(nodic);
            loadedProject.add(reborNodic);

             if(nodic instanceof Link){
                 ElementPainter fromElement = MainFrame.getInstance().getProjectView().getMapView().elementPainter(((Link) nodic).getFromPoint());
                 Title from = (Title) fromElement.getElement();
                 from.addLink((Link) nodic);
                 ElementPainter toElement = MainFrame.getInstance().getProjectView().getMapView().elementPainter(((Link) nodic).getToPoint());
                 Title to = (Title) toElement.getElement();
                 to.addLink((Link) nodic);


                 ((Link) nodic).setFrom(from);
                 ((Link) nodic).setTo(to);


                }

            }



        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(MapTreeItem child) {
        System.out.println(child);
        System.out.println(child.getMapNode());
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        System.out.println(parent);
        if(child.getMapNode() instanceof ProjectExplorer) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.TRY_TO_DELETE_PROJECTEXPLORER);
            return;
        }

        parent.removeChild(child.getMapNode());
        treeModel.removeNodeFromParent(child);
        SwingUtilities.updateComponentTreeUI(treeView);
    }


    private MapNode createChild(MapNodeComposite parent){

        NodeFactory nodeFactory = new FactoryUtils().getNodeFactory(parent);
        return nodeFactory.getNode(parent.getChildrenClassName() + " " + (parent.getListOfChildren().size() + 1));


    }
    @Override
    public void addChild(MapTreeItem parent) {

        if(!(parent.getMapNode() instanceof MapNodeComposite)) {
            AppCore.getInstance().getMessageGenerator().messageGenerate(EventType.NODE_CANNOT_HAVE_CHILDREN);
            return;
        }




        MapNode child = createChild((MapNodeComposite)parent.getMapNode());
        AbstractCommand command = new AddChildCommand(parent, new MapTreeItem(child));
        child.setParent(((MapNodeComposite) parent.getMapNode()));
        System.out.println(((MapNodeComposite) parent.getMapNode()).getListOfChildren());
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        System.out.println(((MapNodeComposite) parent.getMapNode()).getListOfChildren());
    }




}
