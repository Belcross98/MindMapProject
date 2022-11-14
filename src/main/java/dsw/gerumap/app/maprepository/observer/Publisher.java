package dsw.gerumap.app.maprepository.observer;

public interface Publisher {

    void update();

    void addSubscriber(Subscriber subscriber);

    void removeSubscriber(Subscriber subscriber);

}
