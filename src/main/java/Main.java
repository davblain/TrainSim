/**
 * Created by Gemini on 01.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        new Thread(sim).start();
    }
}
