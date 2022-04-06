import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SimulationManager implements Runnable{
    private Integer nrClients;
    private Integer nrQueues;
    private Integer tMaxSimulation;
    private Integer tMinArrival;
    private Integer tMaxArrival;
    private Integer tMinService;
    private Integer tMaxService;

    private boolean started;
    private Integer currentTime;
    private Integer mostClients;
    private Integer peakTime;
    private Double averageWaitingTime;
    private Double averageServiceTime;

    private List<Client> clientList;

    private Scheduler scheduler;

    private View v;
    private Controller c;

    public SimulationManager() throws InterruptedException {

        v = new View();
        c = new Controller(v);

        while(!v.isStarted()) {
            Thread.sleep(10);
        }

        this.started = true;
        this.nrClients = Integer.parseInt(v.getNumberOfClients().getText());
        this.nrQueues = Integer.parseInt(v.getNumberOfQueues().getText());
        this.tMaxSimulation = Integer.parseInt(v.getTMaxSimulation().getText());
        this.tMinArrival = Integer.parseInt(v.getTMinArrival().getText());
        this.tMaxArrival = Integer.parseInt(v.getTMaxArrival().getText());
        this.tMinService = Integer.parseInt(v.getTMinService().getText());
        this.tMaxService = Integer.parseInt(v.getTMaxService().getText());

        this.averageWaitingTime = 0.0;
        this.currentTime = 0;
        this.peakTime = 0;
        this.mostClients = 0;

        this.clientList = new LinkedList<>();

        for(int i = 0; i < this.nrClients; i++) {
            this.clientList.add(new Client(i + 1, tMinArrival, tMaxArrival, tMinService, tMaxService));
        }

        this.clientList.sort(new ClientSortByArrival());

        this.averageServiceTime = 0.0;
        for(Client c: this.clientList) {
            this.averageServiceTime += c.getTService();
        }
        this.averageServiceTime /= this.nrClients;

        this.scheduler = new Scheduler(nrQueues);
    }

    private void newPeakTime(Integer cTime) {
        Integer currentClients = 0;

        for(Queue q: scheduler.getQueues()) {
            currentClients += q.getInQueue().size();
        }

        if(currentClients > this.mostClients) {
            this.peakTime = cTime;
            this.mostClients = currentClients;
        }
    }

    private void log(FileWriter ledger, Integer cTime){
        String message = "\n";
        message += "  Maximum simulation time: " + tMaxSimulation + "\n";
        message += "  Number of clients: " + nrClients + "\n";
        message += "  Number of queues: " + nrQueues + "\n";
        message += "  Arrival time interval: [" + tMinArrival + ", " + tMaxArrival + "]" + "\n";
        message += "  Service time interval: [" + tMinService + ", " + tMaxService + "]" + "\n\n";

        message += "  Time: " + cTime + "\n";

        if(!this.clientList.isEmpty()) {
            message += "  Clients that haven't arrived yet: ";
        }
        else {
            message += "  All of the clients have already arrived.";
        }

        for(int i = 0; i < clientList.size(); i++) {
            message += clientList.get(i);

            if(i != clientList.size() - 1) {
                message += ", ";
            }
        }
        message = message + "\n\n";

        for (Queue q : scheduler.getQueues()) {
            message += "  " + q + "\n";
        }
        message = message + "\n\n";

        this.v.setDisplay(message);

        try {
            ledger.append(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(FileWriter ledger){
        String message = "  Average wait time: " + Math.round(averageWaitingTime * 100.0)/ 100.0 + "\n" +
                "  Average service time: " + Math.round(averageServiceTime * 100.0)/ 100.0 + "\n" +
                "  Peak time at: " + peakTime + " (" + mostClients + " clients)\n\n" +
                "  Queue Management Application by Eric Toader";

        this.v.updateDisplay(message);

        try {
            ledger.append(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void detAvgWaitTime() {
        this.averageWaitingTime = 0.0;

        for(Queue q: scheduler.getQueues()) {
            this.averageWaitingTime += q.getAvgWaitTime();
        }

        this.averageWaitingTime /= scheduler.getQueues().size();
    }

    private void doAction() throws IOException {
        FileWriter ledger = null;

        ledger = new FileWriter("ledger.out");

        while (currentTime < tMaxSimulation && started) {
            if(!clientList.isEmpty()) {
                for (Client c : clientList) {
                    if (c.getTArrival().equals(currentTime)) {
                        scheduler.assignClient(c);
                    }
                }

                Predicate<Client> filter = (Client cl) -> (cl.getAssignedQueue() != 0);
                clientList.removeIf(filter);

                newPeakTime(this.currentTime);
            }

            log(ledger, this.currentTime);

            if(clientList.isEmpty()) {
                boolean fullyFinished = true;
                for(Queue q: scheduler.getQueues()) {
                    if(!q.getInQueue().isEmpty()) {
                        fullyFinished = false;
                        break;
                    }
                }

                if(fullyFinished) {
                    detAvgWaitTime();
                    String message = "  No more clients to assign, all queues are empty\n" +
                            "  No reason to keep the app running, ending simulation..\n\n";
                    this.v.updateDisplay(message);
                    try {
                        ledger.append(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log(ledger);

                    ledger.close();
                    return;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.currentTime++;
        }

        detAvgWaitTime();
        log(ledger);

        ledger.close();
    }


    @Override
    public void run(){
        try {
            doAction();
        } catch (IOException e) {
        }
    }
}
