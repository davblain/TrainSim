
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Gemini on 01.07.2017.
 */
public class Simulation implements Runnable{
    private ExecutorService service;
    Configuration conf;
    Simulation(Configuration conf) {
        this.conf = conf;
        service = Executors.newFixedThreadPool(conf.getTrains().size()+1);
    }
    public void run() {

    }
}
