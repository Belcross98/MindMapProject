package dsw.gerumap.app.core;

import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import java.io.File;

public interface Serializer {

    Project loadProject(File file);
    void saveProject(Project node);
    void saveTemplate(MindMap template);
    MindMap loadTemplate(File file);

}
