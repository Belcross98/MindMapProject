package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.maprepository.observer.IPublisher;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Project extends MapNodeComposite {

    protected String filePath;


    private String author;


    public void setAuthor(String name){
        author = name;
        notifySubscribers(this);
    }
    public void setHelloWorld(String helloWorld){
        super.setName(helloWorld);

        notifySubscribers(this);
    }

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.author = author;
    }
    public Project(){

    }
    @Override
    public void addChild(MapNode child) {
        System.out.println("1");
        if (child != null &&  child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            System.out.println("2");
            if(!this.getListOfChildren().contains(mindMap)){
                System.out.println("3");
                this.getListOfChildren().add(mindMap);
                System.out.println("4");
            }
            }
    }

    @Override
    public String getChildrenClassName() {
        return "MindMap";
    }

    @Override
    public void removeChild(MapNode child) {
        if(child != null && listOfChildren != null)
        listOfChildren.remove(child);
    }


}
