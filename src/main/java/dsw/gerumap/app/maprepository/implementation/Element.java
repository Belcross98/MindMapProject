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

public abstract class Element extends MapNode {

    private boolean selected;

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



    }

