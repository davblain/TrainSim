package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.*;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Platform  implements Observer {
    private ExecutorService executor;
    private int capacity;
    private LinkedBlockingQueue<Person> personsOnPlatform;
    private int length;
    private Train currTrain;
    private City city;
    public LinkedBlockingQueue<Person> getPersonsOnPlatform() {
        return personsOnPlatform;
    }
    public void setPersonsOnPlatform(LinkedBlockingQueue<Person> personsOnPlatform) {
        this.personsOnPlatform = personsOnPlatform;
    }

    public Platform(int capacity, City city) {
        this.capacity = capacity;
        this.city = city;
        personsOnPlatform = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public void update(Observable o, Object arg) {
        currTrain = (Train) o;
        currTrain.setOnStation(true);
        executor = Executors.newFixedThreadPool(currTrain.getWagons().size());
        for (Wagon wagon:currTrain.getWagons()) {
            executor.submit(() -> wagon.AdmitAndRealisePersons(city));
        }
        executor.shutdown();
        currTrain.setOnStation(false);
    }



}
