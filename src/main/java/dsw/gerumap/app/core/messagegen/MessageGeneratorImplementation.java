package dsw.gerumap.app.core.messagegen;

import dsw.gerumap.app.maprepository.observer.Publisher;
import dsw.gerumap.app.maprepository.observer.Subscriber;
import dsw.gerumap.app.core.MessageGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements MessageGenerator, Publisher {

    private EventType type;

    private List<Subscriber> listOfSubscribers = new ArrayList<>();

    @Override
    public Message messageGenerate(EventType type) {

        Message msg;
        if (type == EventType.TRY_TO_DELETE_PROJECTEXPLORER) {
            msg = new Message("", type, LocalDateTime.now());
        }
        return null;
    }

    @Override
    public void update() {

        for(Subscriber subs : listOfSubscribers){
            subs.update();
        }

    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber != null && !listOfSubscribers.contains(subscriber)){
            listOfSubscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber != null && listOfSubscribers.contains(subscriber)){
            listOfSubscribers.remove(subscriber);
        }

    }
}
