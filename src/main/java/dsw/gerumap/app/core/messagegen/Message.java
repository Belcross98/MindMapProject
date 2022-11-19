package dsw.gerumap.app.core.messagegen;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class Message {

    private String text;
    private MessageType type;

    private LocalDateTime dateAndTime;

    public Message(String text, MessageType type,  LocalDateTime dateAndTime ) {
        this.text = text;
        this.type = type;
        this.dateAndTime = dateAndTime;
    }

    public String toString(){
        return "[" + type + "] +" + "[" + dateAndTime + "]" + " " + text;
    }

}
