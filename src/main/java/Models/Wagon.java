package Models;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by gemini on 02.07.17.
 */

public class Wagon {
    private int capacity;
    private int length;
    private int numberOfOutputs;
    private Boolean status = false;
    private LinkedBlockingQueue<Person> personsInWagon;
    private ExecutorService  executorService = Executors.newFixedThreadPool(numberOfOutputs);


    public Wagon(int capacity, int numberOfOutputs) {
        this.capacity = capacity;
        this.numberOfOutputs = numberOfOutputs;
        personsInWagon = new LinkedBlockingQueue<>(capacity);
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
    public  int getCapacity() {
        return capacity;
    }
    boolean getCapability(Person person)
    {
        return true;
    }
    public  int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNumberOfOutputs() {
        return numberOfOutputs;
    }

    public void setNumberOfOutputs(int numberOfOutputs) {
        this.numberOfOutputs = numberOfOutputs;
    }

    public LinkedBlockingQueue<Person> getPersonsInWagon() {
        return personsInWagon;
    }

    public void setPersonsInWagon(LinkedBlockingQueue<Person> personsInWagon) {
        this.personsInWagon = personsInWagon;
    }

}
