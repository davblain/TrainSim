package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Train extends Observable  implements Runnable{
    private static Integer minDist;
    private static Integer speed;
    private static Integer acceleration;
    private static Integer dellay;
    private static Integer disToNotificate;
    private static Integer distBetweenCities;
    private ArrayList<Platform> platforms;
    private LinkedList<Person> personsOnTrain;
    private Train nextTrain;
    private Integer currSpeed;
    private Integer numberOfWagons;
    private Integer allDist ;
    private Integer distToNextCity ;
    private Boolean canMove;
    private Boolean onStation;
    private Platform nextPlatform;
    public void run() {
        int i=0;
        nextPlatform = platforms.get(0);
        addObserver(nextPlatform);
        while (true)
        {
            nextPlatform = platforms.get(i%platforms.size());
            addObserver(nextPlatform);
            currSpeed = 0;
            while (!onStation) {
                Integer distToNextTrain = getDistToNextTrain();
                if (!(nextTrain.onStation && distToNextTrain < disToNotificate) && getDistToNextTrain() > minDist ) {
                    if (currSpeed<speed) currSpeed+=acceleration; else currSpeed=speed;
                    allDist += currSpeed;
                    if (distToNextCity < 0) {
                        notifyObservers();
                        while (onStation) {
                            try {
                                Thread.sleep(15);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        i++;
                        deleteObserver(nextPlatform);
                        break;
                    }

                } else { // кейс с близким расстоянием до следующего поезда
                    currSpeed = 0;
                    if (getDistToNextTrain() < minDist) {
                        try {
                            Thread.sleep(dellay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
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
}
