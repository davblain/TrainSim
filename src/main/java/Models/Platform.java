package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Platform  implements Observer {
    private ExecutorService executor;
    private static int capacity;
    private ConcurrentLinkedQueue<Person> personsOnPlatform;
    private int length;
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
