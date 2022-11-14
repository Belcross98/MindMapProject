package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProjectExplorer extends MapNodeComposite {


    public ProjectExplorer(String name) {
        super(name, null);
    }
    @Override
    public void addChild(MapNode child) {
        if (child != null && child instanceof Project) {
            Project project = (Project) child;
            if (!this.getListOfChildren().contains(project)) {
                this.getListOfChildren().add(project);
            }
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if(child != null && listOfChildren != null)
        listOfChildren.remove(child);
    }

}
