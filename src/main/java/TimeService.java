import Models.City;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by gemini on 03.07.17.
 */
public class TimeService extends Observable implements Runnable {

    private TimeUnit unit;
    private long tic;
    private int updateFequency;
    private long timeOfSimulation;
    private long dayDuration;
    boolean isNight;
    private ExecutorService executorService;
    public TimeService(TimeUnit unit , long tic, long dayDuration, int updateFrequency, ArrayList<City> cities) {
        super();
        cities.forEach(this::addObserver);
        this.unit = unit;
        this.tic = tic;
        this.updateFequency = updateFrequency;
        timeOfSimulation = 0;
        executorService = Executors.newSingleThreadExecutor();
        this.dayDuration = dayDuration;
    }

    @Override
    public void run() {
        while (true) {
            if (timeOfSimulation%dayDuration <= 0.25* dayDuration) isNight = true; else isNight = false;
            if (timeOfSimulation%(isNight? updateFequency*2:updateFequency)  == 0)
                executorService.submit(() -> notifyObservers());
            try {
                unit.sleep(tic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeOfSimulation++;
            //System.out.println(timeOfSimulation);
        }


    }
}
