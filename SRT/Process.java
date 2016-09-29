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

    /**
     * Gets the arrival time to scheduler.
     * @return the arrival time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Gets the service time left.
     * @return the remaining service time
     */
    public double getServiceTime() {
        return serviceTime;
    }

    /**
     * Gets the id of the process.
     * @return the process id
     */
    public char getId() {
        return id;
    }

    /**
     * Decrements the service time by 1 quanta and
     * sets the start time if not ran before.
     * @param time the current run time in quanta
     */
    public void executeProcess(int time) {
        serviceTime--;

        if (!hasBeenRun) {
            startTime = time;
            hasBeenRun = true;
        }
    }

    /**
     * Increments the wait time by 1 quanta.
     */
    public void incrementWaitTime() {
        waitTime++;
    }

    /**
     * Gets the wait time in quanta.
     * @return the wait time in quanta
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * Gets the start time of the process in quanta.
     * @return the start time in quanta
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the process in quanta.
     * @return the end time in quanta
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the process in quanta.
     * @param endTime the end time
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    /**
     * Whether or not the process has had CPU time before.
     * @return if the process has been ran before
     */
    public boolean hasBeenRun() {
        return hasBeenRun;
    }

    /**
     * Check if the process is complete.
     * @return if the process is complete
     */
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
