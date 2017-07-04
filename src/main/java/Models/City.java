package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Gemini on 01.07.2017.
 */
public class City implements Observer {
    private Platform platform;
    private ConcurrentLinkedQueue<Person> personsInCity;
    private static long currTimeOfDay;
    private static long dayDuration;

    public City(int numberOfPersons, int platformCapacity, ArrayList<City> cities,long loadingDellay, long unloadingDellay) {
        personsInCity = new ConcurrentLinkedQueue<>();
        cities.add(this);
        for (int i = 0; i < numberOfPersons ; i++) {
            personsInCity.add(new Person(this,cities,loadingDellay,unloadingDellay));
        }
        platform = new Platform(platformCapacity,this);

    }

    @Override
    public void update(Observable o, Object arg) {
        personsInCity.forEach(Person::setRandom);
        personsInCity.stream().filter(Person::getWant).forEach(p -> {
            if (platform.getPersonsOnPlatform().size()< platform.getCapacity()) {
                platform.getPersonsOnPlatform().add(p);
                personsInCity.remove(p);
            }


        });

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