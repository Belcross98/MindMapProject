package dsw.gerumap.app.core;

import dsw.gerumap.app.maprepository.observer.ISubscriber;

public interface ErrorLogger extends ISubscriber {

   void  log();

   void initialise(MessageGenerator messageGenerator);
}
