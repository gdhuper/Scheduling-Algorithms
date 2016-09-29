/**
 * Created by Jaylan Tse on 9/27/2016.
 */
public class Process implements Comparable<Process> {

    private int arrivalTime;
    private double serviceTime;
    private int priority;
    private char id;

    public Process(int arrivalTime, double serviceTime, int priority, char id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.id = id;
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

    public boolean isComplete() {
        return serviceTime <= 0;
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
