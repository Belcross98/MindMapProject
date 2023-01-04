package dsw.gerumap.app.maprepository.composite;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dsw.gerumap.app.maprepository.observer.IPublisher;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public abstract class MapNode implements IPublisher {
    private String name;


    private transient MapNode parent;

    private boolean changed;

    private transient List<ISubscriber> subscribers;

    public void setName(String helloWorld) {
        this.name = helloWorld;
    }

    public void setParent(MapNode parent) {
        this.parent = parent;
    }

    public MapNode(){

    }

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
        this.changed = true;

    }

    @Override
    public boolean equals(Object obj){
//        if(obj != null && obj instanceof MapNode){
//            MapNode otherobj = (MapNode) obj;
//            return this.getName().equals(otherobj.getName());
//        }
//
        return super.equals(obj);
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
