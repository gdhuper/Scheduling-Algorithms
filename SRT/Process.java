/**
 * Created by Jaylan Tse on 9/27/2016.
 */
public class Process implements Comparable<Process> {

    private int arrivalTime;
    private double serviceTime;
    private int priority;

    public Process(int arrivalTime, double runTime, int priority) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = runTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Arrival: " + arrivalTime + "\nService: " + serviceTime + "\nPriority: " + priority;
    }

    @Override
    public int compareTo(Process o) {
        return arrivalTime - o.arrivalTime;
    }
}
