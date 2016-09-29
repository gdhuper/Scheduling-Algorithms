import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jaylan Tse on 9/27/2016.
 */
public class ShortestRemainingTime {

    private int runTime;
    private Process currentProcess;
    private ArrayList<Process> queuedProcesses;
    private boolean hasScheduledJob;

    public ShortestRemainingTime() {
        runTime = 0;
        queuedProcesses = new ArrayList<>();
        hasScheduledJob = false;
    }

    public void schedule(Process newProcess) {
        if (currentProcess != null) {
            // If there is a current running process,check
            // if the new process has a lower service
            // time and schedule it and add old process to
            // queue, else add new process to queue.
            if (newProcess.getServiceTime() < currentProcess.getServiceTime()) {
                addToQueue(currentProcess);
                currentProcess = newProcess;
            } else {
                addToQueue(newProcess);
            }
        } else {
            // No running process.
            currentProcess = newProcess;
        }
        hasScheduledJob = true;
    }

    public void incrementRunTime() {
        runTime++;
        if (currentProcess != null) {
            System.out.print(currentProcess.getId());
            currentProcess.decrementServiceTime();
            checkProcessStatus();
        }
    }

    public boolean hasRemainingJobs() {
        return currentProcess != null || !queuedProcesses.isEmpty() || !hasScheduledJob;
    }

    private void addToQueue(Process p) {
        queuedProcesses.add(p);
        Collections.sort(queuedProcesses, (o1, o2) -> {
            if (o1.getServiceTime() < o2.getServiceTime()) return -1;
            if (o1.getServiceTime() > o2.getServiceTime()) return 1;
            return 0;
        });
    }

    private void checkProcessStatus() {
        if (currentProcess.isComplete()) {
            if (!queuedProcesses.isEmpty()) {
                currentProcess = queuedProcesses.remove(0);
            } else {
                currentProcess = null;
            }
        }
    }
}
