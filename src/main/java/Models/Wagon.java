package Models;

import java.util.concurrent.*;

/**
 * Created by gemini on 02.07.17.
 */

public class Wagon {
    private int capacity;
    private int length;
    private int numberOfOutputs;
    private Boolean status = false;
    private LinkedBlockingQueue<Person> personsInWagon;
    private ExecutorService  executorService ;
    private TimeUnit unit;
    private long tic;

    public Wagon(int capacity, int numberOfOutputs, TimeUnit unit ,long tic ) {
        this.capacity = capacity;
        this.numberOfOutputs = numberOfOutputs;
        personsInWagon = new LinkedBlockingQueue<>(capacity);
        executorService =  Executors.newFixedThreadPool(numberOfOutputs);
        this.unit = unit;
        this.tic = tic;
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
                        unit.sleep(person.getUnloadingDellay()*tic);
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
                    try {
                        unit.sleep(person.getLoadingDellay()*tic);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
