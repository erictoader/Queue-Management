import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{
    private final Integer ID;
    private BlockingQueue<Client> inQueue;
    private AtomicInteger waitTime;

    private Double avgWaitTime;
    private Integer nrClients;

    public Queue(Integer ID) {
        this.ID = ID;
        this.inQueue = new LinkedBlockingDeque<>();
        this.waitTime = new AtomicInteger(0);

        this.avgWaitTime = 0.0;
        this.nrClients = 0;
    }

    public void run() {
        while(true) {
            if(this.inQueue.isEmpty()) {
                computeWaitTime();
                continue;
            }

            Client c;

            try {
                c = this.inQueue.peek();

                while(c.getTService() != 0) {
                    Thread.sleep(1000);
                    c.decreaseTService();
                    computeWaitTime();
                }

                finishClient();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient(Client c){
        try {
            inQueue.put(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nrClients++;
        computeWaitTime();
    }

    public void finishClient() throws InterruptedException {
        inQueue.take();
        computeWaitTime();
        this.avgWaitTime += this.waitTime.intValue();
    }

    private void computeWaitTime() {
        this.waitTime.getAndSet(0);

        if(inQueue.isEmpty()) {
            return;
        }

        for(Client cl: inQueue) {
            this.waitTime.getAndAdd(cl.getTService());
        }
    }

    public AtomicInteger getWaitTime() {
        return this.waitTime;
    }

    public Double getAvgWaitTime() {
        return avgWaitTime / nrClients;
    }

    public BlockingQueue<Client> getInQueue() {
        return this.inQueue;
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        String string = "Queue #" + this.ID + ": ";

        if(this.inQueue.isEmpty()) {
            string += "FREE";
            return string;
        }

        int index = 0;
        for(Client c: this.inQueue) {
            string += c.toString();

            if(index == this.inQueue.size()) {
                string += ", ";
            }

            index++;
        }

        return string;
    }
}