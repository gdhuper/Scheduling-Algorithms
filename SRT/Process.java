/**
 * Process
 */
public class Process implements Comparable<Process> {

    private int arrivalTime;
    private double serviceTime;
    private int priority;
    private char id;
    private boolean hasBeenRun;

    public Process(int arrivalTime, double serviceTime, int priority, char id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.id = id;

        hasBeenRun = false;
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
        return "ID: " + id + "\nArrival: " + arrivalTime + "\nService: " + serviceTime + "\nPriority: " + priority + "\n";
    }

    @Override
    public int compareTo(Process o) {
        return arrivalTime - o.arrivalTime;
    }
}
