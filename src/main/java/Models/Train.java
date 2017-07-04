package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Train extends Observable  implements Runnable{
    private  Integer minDist;
    private  Integer speed;
    private  Integer acceleration;
    private  Integer dellay;
    private  Integer disToNotificate;
    private  Integer distBetweenCities;
    private TimeUnit unit;
    private  long tic;
    private ArrayList<Wagon> wagons;
    private ArrayList<Platform> platforms;
    private Train nextTrain;
    private Integer currSpeed;
    private Integer allDist ;
    private Integer currDistToNextCity ;
    private Boolean onStation;
    private Platform nextPlatform;
    long timeOnCurrPlatform; //время проведеннное на текущей платформе
    long timeOnAllPlatforms; //время проведенное на всех платформах
    long downTime;// Общее время  простоя при встерече / оповещении о стоячем поезде на станции;
    public Train(Integer minDist, Integer speed, Integer acceleration, Integer dellay, Integer disToNotificate, Integer distBetweenCities,
                 Integer numberOfWagons ,Integer wagonCapacity,Integer numberOfOutputs, ArrayList<Platform> platforms,TimeUnit unit, long tic) {
        super();
        this.minDist = minDist;
        this.speed = speed;
        this.acceleration = acceleration;
        this.dellay = dellay;
        this.disToNotificate = disToNotificate;
        this.distBetweenCities = distBetweenCities;
        this.platforms = platforms;
        allDist = 0;
        wagons = new ArrayList<>(numberOfWagons);
        for (int i = 0; i <numberOfWagons ; i++) {
            wagons.add(new Wagon(wagonCapacity,numberOfOutputs,unit,tic));
        }
        this.tic = tic;
        this.unit = unit;
        this.timeOnAllPlatforms = 0;
        this.downTime = 0;
    }

    public Train getNextTrain() {
        return nextTrain;
    }

    public void setNextTrain(Train nextTrain) {
        this.nextTrain = nextTrain;
    }

    public Boolean getOnStation() {
        return onStation;
    }

    public void setOnStation(Boolean onStation) {
        this.onStation = onStation;
    }

    public ArrayList<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(ArrayList<Wagon> wagons) {
        this.wagons = wagons;
    }

    public void run() {
        int i=0;

        while (true) {
            timeOnCurrPlatform = 0;
            currDistToNextCity = distBetweenCities;
            nextPlatform = platforms.get(i%platforms.size());
            addObserver(nextPlatform);
            currSpeed = 0;
            while (!onStation) {
                Integer distToNextTrain = getDistToNextTrain();
                if (!(nextTrain.onStation && distToNextTrain < disToNotificate) && getDistToNextTrain() > minDist ) {
                    if (currSpeed<speed) currSpeed+=acceleration; else currSpeed=speed;
                    allDist += currSpeed;
                    if (currDistToNextCity <= 0) {
                        notifyObservers();
                        while (onStation) {
                            try {
                                unit.sleep(tic);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            timeOnCurrPlatform++;
                            timeOnAllPlatforms++;
                        }
                        i++;
                        deleteObserver(nextPlatform);
                        break;
                    }
                    currDistToNextCity-=currSpeed;
                    allDist += currSpeed;
                } else { // кейс с близким расстоянием до следующего поезда
                    currSpeed = 0;
                    if (getDistToNextTrain() < minDist) {
                        try {
                            unit.sleep(tic * dellay );
                            downTime += dellay;
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        downTime++;

                    }

                }
                try { // всегда, когда поезд не на платформе
                    unit.sleep(tic);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        }


    }

    public Train() {
        super();
    }
    private Integer getDistToNextTrain() {
        return nextTrain.allDist - allDist;
    }
    public void resetDownTime()  {
        downTime = 0 ;
    }
    public void resetTimeOnAllPlatforms() {
        timeOnAllPlatforms = 0;
    }
}
