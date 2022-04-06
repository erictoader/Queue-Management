import java.util.Random;

public class Client {
    private final Integer ID;
    private final Integer tArrival;
    private Integer tService;
    private Integer assignedQueue;

    public Client(Integer clientID, Integer tMinArrival, Integer tMaxArrival, Integer tMinService, Integer tMaxService) {
        Random randomInteger = new Random();
        this.ID = clientID;
        this.tArrival = randomInteger.nextInt(tMinArrival, tMaxArrival);
        this.tService = randomInteger.nextInt(tMinService, tMaxService);
        this.assignedQueue = 0;
    }

    public Integer getTArrival() {
        return tArrival;
    }

    public Integer getTService() {
        return tService;
    }

    public void decreaseTService() {
        this.tService--;
    }

    public Integer getAssignedQueue() {return assignedQueue;}

    public void setAssignedQueue(Integer assignedQueue) {this.assignedQueue = assignedQueue;}

    @Override
    public String toString() {
        return "(" + this.ID + ", " + this.tArrival + ", " + this.tService + ")";
    }
}
