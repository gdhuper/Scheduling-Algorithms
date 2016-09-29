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
    private int waiting;

    public Process(int arrivalTime, double serviceTime, int priority, char id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.id = id;

        hasBeenRun = false;
        startTime = 0;
        endTime = 0;
        waiting = 0;
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

    public void incrementWaitingTime() {
        waiting++;
    }

    public int getWaitTime() {
        return waiting;
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

    public boolean isComplete() {
        return serviceTime <= 0;
    }

    public boolean hasBeenRun() {
        return hasBeenRun;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nArrival: " + arrivalTime + "\nService: " + serviceTime + "\nPriority: " + priority;
    }

    @Override
    public int compareTo(Process o) {
        return arrivalTime - o.arrivalTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
