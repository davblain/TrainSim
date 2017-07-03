import Models.City;
import Models.Platform;
import Models.Train;

import java.util.ArrayList;

/**
 * Created by gemini on 03.07.17.
 */
public class Configuration {

    private ArrayList<City> cities;
    private ArrayList<Train> trains;

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public Configuration(int numberOfCities, int  distBetweenCities, int numberOfPersons, int platformCapacity,
                         int numberOfTrains, int numberOfOutputs, int speed, int numberOfWagons,
                         int wagonsCapacity, int acceleration, int notificationDist, int minDist,  int dellay) {

        cities = new ArrayList<>(numberOfCities);
        for (int i = 0; i <numberOfCities ; i++) {
            City city = new City(numberOfPersons,platformCapacity,cities);
        }
        ArrayList<Platform> platforms = new ArrayList<>(numberOfCities);
        cities.forEach(city -> platforms.add(city.getPlatform()));
        trains = new ArrayList<>(numberOfTrains);
        //конфигурация поездов, установление связей между поездами.
        for (int i = 0; i <numberOfTrains ; i++) {
            trains.add(new Train(minDist,speed,acceleration,dellay,notificationDist,distBetweenCities,
                    numberOfWagons, wagonsCapacity,numberOfOutputs,platforms));
            if (i != 0)
            trains.get(i-1).setNextTrain(trains.get(i));

        }
        trains.get(numberOfTrains-1).setNextTrain(trains.get(0));
    }
}
