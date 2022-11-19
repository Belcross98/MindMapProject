package dsw.gerumap.app.core;

import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.core.messagegen.Message;
import dsw.gerumap.app.maprepository.observer.IPublisher;

public interface MessageGenerator extends IPublisher {

    Message messageGenerate(EventType type);
}
