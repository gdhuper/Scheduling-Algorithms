/**
 * Process
 */
public class Process implements Comparable<Process> {

    private final int arrivalTime;
    private final int priority;
    private final char id;
    private double serviceTime;
    private boolean hasBeenRun;
    private int startTime;
    private int endTime;
    private int waitTime;

    /**
     * Constructor
     * @param arrivalTime arrival time of process
     * @param serviceTime service time of process
     * @param priority priority of process
     * @param id id of process
     */
    public Process(int arrivalTime, double serviceTime, int priority, char id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.id = id;

        hasBeenRun = false;
        startTime = 0;
        endTime = 0;
        waitTime = 0;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public char getId() {
        return id;
    }

    public void decrementServiceTime() {
        serviceTime--;
    }

    public void incrementWaitTime() {
        waitTime++;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int time) {
        if (!hasBeenRun) {
            startTime = time;
        }
        hasBeenRun = true;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean hasBeenRun() {
        return hasBeenRun;
    }

    public boolean isComplete() {
        return serviceTime <= 0;
    }

    @Override
    public int compareTo(Process o) {
        return arrivalTime - o.arrivalTime;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nArrival: " + arrivalTime + "\nService: " + serviceTime + "\nPriority: " + priority;
    }
}
