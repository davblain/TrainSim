package Models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Person {

    private Boolean Want;
    private City currCity;
    private City nextCity;
    private long loadingDellay;
    private long unloadingDellay;
    private ArrayList<City> cities;

    public Person(City currCity,ArrayList<City> cities, long loadingDellay,long unloadingDellay) {
        this.currCity = currCity;
        this.cities = cities;
        this.loadingDellay = loadingDellay;
        this.unloadingDellay = unloadingDellay;

    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public long getLoadingDellay() {
        return loadingDellay;
    }
    public void setRandom() {
        final Random random = new Random();
        if (random.nextFloat()<0.5){
            Want = true;
            City randomCity = cities.get(random.nextInt(cities.size()));
            while (randomCity == currCity) {
                randomCity = cities.get(random.nextInt(cities.size()));
            }
            nextCity = cities.get(random.nextInt(cities.size()));

        } else {
            Want = false;
            nextCity = null;
        }


    }

    public void setLoadingDellay(Integer loadingDellay) {
        this.loadingDellay = loadingDellay;
    }

    public long getUnloadingDellay() {
        return unloadingDellay;
    }

    public void setUnloadingDellay(Integer unloadingDellay) {
        this.unloadingDellay = unloadingDellay;
    }

    public Boolean getWant() {
        return Want;
    }

    public void setWant(Boolean want) {
        Want = want;
    }
    public Boolean inThisCity(City city) {
        return nextCity == city;
    }
    public City getCurrCity() {
        return currCity;
    }

    public void setCurrCity(City currCity) {
        this.currCity = currCity;
    }

    public City getNextCity() {
        return nextCity;
    }

    public void setNextCity(City nextCity) {
        this.nextCity = nextCity;
    }
}
