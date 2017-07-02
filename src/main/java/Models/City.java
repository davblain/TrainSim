package Models;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gemini on 01.07.2017.
 */
public class City implements Runnable {
    private Platform platform;
    private ConcurrentLinkedQueue<Person> personsInCity;

    public void run() {
        while (true) {

            personsInCity.stream().filter(Person::getWant).forEach(p -> {
                platform.getPersonsOnPlatform().add(p);
                personsInCity.remove(p);
            }
            );
            if(condition()) break;
        }
    }

    public ConcurrentLinkedQueue<Person> getPersonsInCity() {
        return personsInCity;
    }

    public void setPersonsInCity(ConcurrentLinkedQueue<Person> personsInCity) {
        this.personsInCity = personsInCity;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    private boolean condition()
    {
        return true;
    }
}