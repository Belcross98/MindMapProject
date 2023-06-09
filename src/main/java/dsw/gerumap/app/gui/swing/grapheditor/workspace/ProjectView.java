package dsw.gerumap.app.gui.swing.grapheditor.workspace;

import dsw.gerumap.app.gui.swing.grapheditor.model.DiagramElement;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.gui.swing.grapheditor.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.LinkPainter;
import dsw.gerumap.app.gui.swing.grapheditor.painters.TitlePainter;
import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ProjectView extends JPanel implements ISubscriber {

    private Project p;

    private StateManager stateManager;

    private  MindMap mindMap;

    private String projectName;
    private String authorName;

    private JLabel projectName1;

    private  JLabel authorName1;

    private MapNode project;

    private JTabbedPane tabbedPane;
    private List<MapView> tabs;

    private Settings settings;

    private  MapTreeItem selectedMapTreItem;

   public ProjectView(){

       this.projectName1 = new JLabel();
       this.authorName1 = new JLabel();
       this.tabbedPane = new JTabbedPane();
       this.tabs = new ArrayList<>();
       this.mindMap = new MindMap();
       this.settings = new Settings(new Frame());
       add(projectName1);
       add(authorName1);
       add(tabbedPane);
       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

       stateManager = new StateManager();

       authorName1.setVisible(true);
       projectName1.setVisible(true);

       authorName1.setText("Autor");
       projectName1.setText("Projekat");

       authorName1.setMinimumSize(new Dimension(150,20));
       projectName1.setMinimumSize(new Dimension(150,20));

   }

   public void startAddTittleState(){
       this.stateManager.setAddTittleState();
   }
   public void startAddLinkState(){
       this.stateManager.setAddLinkState();
   }
   public void startDeleteState(){
       this.stateManager.setDeleteState();
   }
   public void startMoveState(){this.stateManager.setMoveState();}

   public MapView getMapView(){
      return  ((MapView)tabbedPane.getSelectedComponent());
   }

   public void startSelectState(){
       this.stateManager.setSelectState();
   }

    public void refreshWorkspace(MapNode selectedProject){


        tabs.clear();
        tabbedPane.removeAll();

        if(project != null){
            ((Project)project).removeSubscriber(this);
        }


        this.project = selectedProject;

        ((Project)project).addSubscriber(this);


        for(MapNode child: ((Project) selectedProject).getListOfChildren()) {
            MapView tab = new MapView((MindMap) child);
            tabs.add(tab);
            for(MapNode p: ((MindMap) child).getListOfChildren()){

                if(p instanceof Title) {
                    p.addSubscriber(tab);
                    TitlePainter titlePainter = new TitlePainter((Title) p);
                    titlePainter.setShape(((Title) p).getShape());
                    tab.addPainter(titlePainter);
                }
                if(p instanceof Link){
                    p.addSubscriber(tab);
                    LinkPainter linkPainter = new LinkPainter((Link) p);
                    linkPainter.setShape(((Link) p).getShape());
                    tab.addPainter(linkPainter);

                }

            }

        }

        for(MapView tab : tabs)
          tabbedPane.addTab(tab.getMindMap().getName(), tab);

        refreshLabele();

    }

        @Override
        public void update(Object notification) {

            System.out.println("IM IN HERE AAAAAAAAAAAAAAA");
            project.setChanged(true);
            if(project == null){
                return;
            }
            refreshLabele();


        }

        private void refreshLabele(){

            authorName = ((Project)project).getAuthor();
            projectName = ((Project)project).getName();

            projectName1.setText(projectName);
            authorName1.setText(authorName);


        }

}

