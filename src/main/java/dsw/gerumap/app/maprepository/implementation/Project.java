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

public class Project extends MapNodeComposite implements IPublisher {

    private String author;

    private List<ISubscriber> subscribers;

    public void setAuthor(String name){
        author = name;
        notifySubscribers(this);
    }
    @Override
    public void setName(String name){
        super.setName(name);
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


    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscriber == null){
            return;
        }
        if(this.subscribers== null){
            subscribers = new ArrayList<>();
        }
        if(this.subscribers.contains(subscriber)){
            return;
        }

        this.subscribers.add(subscriber);

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        if(subscriber == null || this.subscribers.contains(subscriber)){
            return;
        }
        this.subscribers.remove(subscriber);

    }

    @Override
    public void notifySubscribers(Object notification) {
        if (notification == null || this.subscribers == null || this.subscribers.isEmpty()){
            return;
        }
        for(ISubscriber subscriber: subscribers){
            subscriber.update(notification);
        }

    }
}
