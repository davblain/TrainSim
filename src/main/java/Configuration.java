import Models.City;
import Models.Platform;
import Models.Train;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by gemini on 03.07.17.
 */
public class Configuration {

    private ArrayList<City> cities;
    private ArrayList<Train> trains;
    private TimeService timeService;

    public TimeService getTimeService() {
        return timeService;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public Configuration(int numberOfCities, int  distBetweenCities, int numberOfPersons, int platformCapacity,
                         int numberOfTrains, int numberOfOutputs, int speed, int numberOfWagons,
                         int wagonsCapacity, int acceleration, int notificationDist, int minDist, int dellay, TimeUnit unit ,
                         long tic, long dayDuration, int updateFrequency,long loadingDellay, long unloadingDellay) {

        cities = new ArrayList<>(numberOfCities);
        for (int i = 0; i <numberOfCities ; i++) {
            City city = new City(numberOfPersons,platformCapacity,cities,loadingDellay,unloadingDellay);
        }
        ArrayList<Platform> platforms = new ArrayList<>(numberOfCities);
        cities.forEach(city -> platforms.add(city.getPlatform()));
        trains = new ArrayList<>(numberOfTrains);
        //конфигурация поездов, установление связей между поездами.
        for (int i = 0; i <numberOfTrains ; i++) {
            trains.add(new Train(minDist,speed,acceleration,dellay,notificationDist,distBetweenCities,
                    numberOfWagons, wagonsCapacity,numberOfOutputs,platforms,unit,tic));
            if (i != 0)
            trains.get(i-1).setNextTrain(trains.get(i));

        }
        trains.get(numberOfTrains-1).setNextTrain(trains.get(0));
        timeService = new TimeService(unit,tic,dayDuration,updateFrequency,cities);
    }
}
