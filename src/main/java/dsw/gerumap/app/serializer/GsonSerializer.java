package dsw.gerumap.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer {

    private final Gson gson;

    public GsonSerializer(){
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(MapNode.class, new MapNodeAdapter());
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        builder.registerTypeAdapter(Shape.class, new ShapeAdapter());
        builder.registerTypeAdapter(Point2D.class,new Point2DAdapter());
        builder.setPrettyPrinting();

        gson = builder.create();
    }

    @Override
    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return gson.fromJson(fileReader, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTemplate(MindMap template){

        try (FileWriter writer = new FileWriter(template.getFilePath())) {
            gson.toJson(template, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public MindMap loadTemplate(File file) {
        try(FileReader fileReader = new FileReader(file)){
            return gson.fromJson(fileReader,MindMap.class);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }
}
