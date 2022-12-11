package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.observer.IPublisher;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Element extends MapNode implements IPublisher {

    private boolean selected;
    private List<ISubscriber> subscribers;

    public Element(String name, MapNode parent) {
        super(name, parent);
        selected = false;
    }

    public  Element(){

    }

   public void setSelected(boolean b){
        selected = b;
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
        {
            if (notification == null || this.subscribers == null || this.subscribers.isEmpty()){
                return;
            }
            for(ISubscriber subscriber: subscribers){
                subscriber.update(notification);
            }
        }

    }
}
