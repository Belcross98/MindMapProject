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

        Message msg;
        if (type == EventType.TRY_TO_DELETE_PROJECTEXPLORER) {
            msg = new Message("", type, LocalDateTime.now());
        }

        return null;
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
