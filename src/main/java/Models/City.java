package Models;

import java.util.LinkedList;

/**
 * Created by Gemini on 01.07.2017.
 */
public class City implements Runnable {
    private Platform platform;
    private LinkedList<Person> personsInCity;

    public void run() {
        while (true) {
            personsInCity.stream().filter(Person::getWant).forEach(p -> platform.getPersonsOnPlatform().add(p));
            if(condition()) break;
        }
    }
    private boolean condition()
    {
        return true;
    }
}