package dsw.gerumap.app.maprepository.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class MindMap extends MapNodeComposite {


    public MindMap(String name) {
        super(name, null);
    }

    public MindMap(){

    }


    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof Element){
            Element element = (Element) child;
            if (!this.getListOfChildren().contains(element)){
                this.getListOfChildren().add(element);
                notifySubscribers(this);
            }
        }
    }

    private String filePath;

    @Override
    public String getChildrenClassName() {
        return "Element";
    }

    @Override
    public void removeChild(MapNode child) {
        if(child != null && listOfChildren != null)
        listOfChildren.remove(child);
        notifySubscribers(this);

    }


    }
