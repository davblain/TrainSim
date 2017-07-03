/**
 * Created by Gemini on 01.07.2017.
 */
public class Main {
    public static void main(String[] args) {

        Simulation sim = new Simulation(new Configuration(3,70,10,10,2
        ,2,3,2,5,1,20,10,2));
        new Thread(sim).start();
    }
}
