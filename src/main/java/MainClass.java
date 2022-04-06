
public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        SimulationManager app = new SimulationManager();
        Thread t = new Thread(app);
        t.start();
        t.join();
    }
}
