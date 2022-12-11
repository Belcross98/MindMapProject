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

public class MindMap extends MapNodeComposite implements IPublisher {

    private List<ISubscriber> subscribers;


    public MindMap(String name) {
        super(name, null);
        subscribers = new ArrayList<>();
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
