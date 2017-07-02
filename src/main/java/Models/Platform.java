package Models;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Platform implements Observer{
    private LinkedList<Person> personsOnPlatform;
    private Integer length;
    private Train currTrain;

    public LinkedList<Person> getPersonsOnPlatform() {
        return personsOnPlatform;
    }
    public void setPersonsOnPlatform(LinkedList<Person> personsOnPlatform) {
        this.personsOnPlatform = personsOnPlatform;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
