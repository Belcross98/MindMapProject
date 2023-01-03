package dsw.gerumap.app.core.messagegen;

import dsw.gerumap.app.maprepository.implementation.Project;

import java.io.File;

public interface Serializer {

    Project loadProject(File file);

    void saveProject(Project node);
}