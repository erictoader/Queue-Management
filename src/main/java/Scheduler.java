import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private Integer noQueues;

    public Scheduler(int noQueues) {
        this.noQueues = noQueues;
        this.queues = new ArrayList<>();

        for(int i = 0; i < this.noQueues; i++) {
            Queue newQueue = new Queue(i + 1);
            this.queues.add(newQueue);
            Thread t = new Thread(newQueue);
            t.start();
        }
    }

    public void assignClient(Client c) {
        Integer minWaitTime = 99999;
        Queue bestQueue = null;

        for(Queue q: queues) {
            if(q.getWaitTime().intValue() < minWaitTime) {
                minWaitTime = q.getWaitTime().intValue();
                bestQueue = q;
            }
        }

        bestQueue.addClient(c);
        c.setAssignedQueue(bestQueue.getID());
    }

    public List<Queue> getQueues() {
        return this.queues;
    }
}