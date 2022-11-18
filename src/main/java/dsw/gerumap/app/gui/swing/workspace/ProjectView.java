package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JPanel implements ISubscriber {

    private String projectName;
    private String authorName;

    private JLabel projectName1;

    private  JLabel authorName1;

    private MapNode project;

    private JTabbedPane tabbedPane;
    private List<MapView> tabs;

   public ProjectView(){
       this.projectName1 = new JLabel();
       this.authorName1 = new JLabel();
       this.tabbedPane = new JTabbedPane();
       this.tabs = new ArrayList<>();
       add(projectName1);
       add(authorName1);
       add(tabbedPane);
       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

       authorName1.setVisible(true);
       projectName1.setVisible(true);

       authorName1.setText("Autor");
       projectName1.setText("Projekat");

       authorName1.setMinimumSize(new Dimension(150,20));
       projectName1.setMinimumSize(new Dimension(150,20));

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
        }

        for(MapView tab : tabs)
          tabbedPane.addTab(tab.getMindMap().getName(), tab);

        refreshLabele();

    }

        @Override
        public void update(Object notification) {
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

