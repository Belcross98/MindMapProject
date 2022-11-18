package dsw.gerumap.app.maprepository.observer;

public interface IPublisher {


    void addSubscriber(ISubscriber subscriber);

    void removeSubscriber(ISubscriber subscriber);

    void notifySubscribers(Object notification);

}
