package bl.helper.observerPattern;

import java.util.ArrayList;

public abstract class Broadcaster <T> {

    private ArrayList<T> subscribers = new ArrayList<>();

    public void subscribe(T subscriber){
        subscribers.add(subscriber);
    }

    public void unsubscribe(T subscriber){
        subscribers.remove(subscriber);
    }

    public ArrayList<T> getSubscribers(){
        return subscribers;
    }

    public abstract <U> void broadcast();

}
