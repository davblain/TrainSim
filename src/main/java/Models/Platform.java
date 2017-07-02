package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Platform  implements Observer {
    private ConcurrentLinkedQueue<Person> personsOnPlatform;
    private Integer length;
    private Train currTrain;
    private City city;
    public ConcurrentLinkedQueue<Person> getPersonsOnPlatform() {
        return personsOnPlatform;
    }
    public void setPersonsOnPlatform(ConcurrentLinkedQueue<Person> personsOnPlatform) {
        this.personsOnPlatform = personsOnPlatform;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
