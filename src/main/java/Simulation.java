import Models.City;
import Models.Train;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Simulation implements Runnable{
    private ExecutorService service;
    private ArrayList<City> Cities;
    private ArrayList<Train> Trains;
    Simulation(Integer dist,Integer personNumber, Integer platformCapacity,Integer numberOfTrains,Integer numberOfOutputs,Integer Speed,
    Integer wagonsCapacity,Integer acceleration,Integer notificationDist,Integer maxDist,Integer stopTime,Integer dellay) {

    }
    public void run() {

    }
}
