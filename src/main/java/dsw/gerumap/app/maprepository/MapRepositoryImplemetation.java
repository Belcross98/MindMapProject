package dsw.gerumap.app.maprepository;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MapRepositoryImplemetation implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImplemetation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {

        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {
        if (child != null) {
            parent.addChild(child);
        }
    }
}