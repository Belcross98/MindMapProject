package dsw.gerumap.app.core.messagegen;

import dsw.gerumap.app.maprepository.observer.IPublisher;
import dsw.gerumap.app.maprepository.observer.ISubscriber;
import dsw.gerumap.app.core.MessageGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements MessageGenerator, IPublisher {

    private EventType type;

    private List<ISubscriber> listOfSubscribers = new ArrayList<>();

    @Override
    public Message messageGenerate(EventType type) {

        Message msg = null;
        if (type == EventType.TRY_TO_DELETE_PROJECTEXPLORER) {
            msg = new Message("You can not delete project explorer!", MessageType.ERROR, LocalDateTime.now());
        }

        if(type == EventType.MUST_HAVE_NAME){
            msg = new Message("You need to enter name!", MessageType.WARNING, LocalDateTime.now());
        }
        if(type == EventType.NODE_NOT_SELECTED){
            msg = new Message("Nothing is selected!", MessageType.WARNING, LocalDateTime.now());
        }

        if (type == EventType.NODE_CANNOT_HAVE_CHILDREN) {
            msg = new Message("You can not add node!", MessageType.ERROR, LocalDateTime.now());
        }
        if(type == EventType.NO_ELEMENT_FOUND){
            msg = new Message("No element found", MessageType.WARNING, LocalDateTime.now());
        }
        if(type == EventType.YOU_DONT_HAVE_INITIAL_POINT){
            msg = new Message("You dont have start piont!", MessageType.WARNING, LocalDateTime.now());
        }
        notifySubscribers(msg);
        return msg;
    }



    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscriber != null && !listOfSubscribers.contains(subscriber)){
            listOfSubscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        if(subscriber != null && listOfSubscribers.contains(subscriber)){
            listOfSubscribers.remove(subscriber);
        }

    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber subs : listOfSubscribers){
            subs.update(notification);
        }
    }
}
