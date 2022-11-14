package dsw.gerumap.app.core;

import dsw.gerumap.app.core.messagegen.EventType;
import dsw.gerumap.app.core.messagegen.Message;

public interface MessageGenerator {

    Message messageGenerate(EventType type);
}
