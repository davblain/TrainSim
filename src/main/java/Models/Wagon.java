package Models;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gemini on 02.07.17.
 */

public class Wagon {
    static private int capacity;
    static private int length;
    static private int numberOfOutputs;
    private Boolean status = false;
    private ConcurrentLinkedQueue<Person> personsInWagon;
    private ExecutorService  executorService = Executors.newFixedThreadPool(numberOfOutputs);


    public Wagon(ConcurrentLinkedQueue<Person> personsInWagon) {
        this.personsInWagon = personsInWagon;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void AdmitAndRealisePersons(City city) {
        Platform platform = city.getPlatform();
        for (int i = 0; i < numberOfOutputs ; i++) {
            executorService.submit(() -> personsInWagon.forEach(person -> {
                if (person.inThisCity(city)) {
                    city.getPersonsInCity().add(person);
                    person.setCurrCity(city);
                    personsInWagon.remove(person);
                    try {
                        Thread.sleep(person.getLoadingDellay());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } ) );
        }
        executorService.shutdown();
        for (int i = 0; i < numberOfOutputs ; i++) {
            executorService.submit(() -> platform.getPersonsOnPlatform().forEach(person -> {
                if (personsInWagon.size()< capacity) {
                    personsInWagon.add(person);
                    platform.getPersonsOnPlatform().remove(person);
                }

            }));
        }
        executorService.shutdown();

    }
    public static int getCapacity() {
        return capacity;
    }
    boolean getCapability(Person person)
    {
        return true;
    }
    public static void setCapacity(int capacity) {
        Wagon.capacity = capacity;
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        Wagon.length = length;
    }

    public static int getNumberOfOutputs() {
        return numberOfOutputs;
    }

    public static void setNumberOfOutputs(int numberOfOutputs) {
        Wagon.numberOfOutputs = numberOfOutputs;
    }

    public ConcurrentLinkedQueue<Person> getPersonsInWagon() {
        return personsInWagon;
    }

    public void setPersonsInWagon(ConcurrentLinkedQueue<Person> personsInWagon) {
        this.personsInWagon = personsInWagon;
    }

}
