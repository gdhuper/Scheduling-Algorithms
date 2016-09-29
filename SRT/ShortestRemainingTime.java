import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Shortest remaining time process scheduler.
 */
public class ShortestRemainingTime {

    private Process runningProcess;
    private int runTime = 0;
    private ArrayList<Process> queuedProcesses = new ArrayList<>();
    private ArrayList<Process> completedProcesses = new ArrayList<>();
    private boolean maxQuantaReached = false;

    /**
     * Schedule a new process.
     *
     * @param newProcesses the processes to schedule
     */
    public void schedule(ArrayList<Process> newProcesses) {
        if (newProcesses.isEmpty()) return;

        // Sort new processes by increasing service time.
        Collections.sort(newProcesses, new SRTComparator());
        // Grab process with shortest service time.
        Process newProcess = newProcesses.remove(0);

        if (runningProcess != null) {
            // If there is a current running process,check
            // if the new process has a lower service
            // time and schedule it and add old process to
            // queue, else add new process to queue.
            if (newProcess.getServiceTime() < runningProcess.getServiceTime()) {
                addToQueue(runningProcess);

                runningProcess = newProcess;
            } else {
                addToQueue(newProcess);
            }
        } else {
            // No running process.
            runningProcess = newProcess;
        }

        // Add remaining processes to queue.
        newProcesses.forEach(this::addToQueue);
    }

    /**
     * Increment run time by 1 quanta and decrement remaining
     * service time left by 1 quanta for running process.
     */
    public void incrementRunTime() {
        runTime++;
        // Increment waiting time for each process in queue.
        queuedProcesses.forEach(Process::incrementWaitTime);

        if (runningProcess != null) {
            System.out.print(runningProcess.getId());
            runningProcess.executeProcess(runTime);
            checkRunningProcessStatus();
        }
    }

    /**
     * Do not execute any new processes from queue.
     */
    public void stopNewProcesses() {
        maxQuantaReached = true;
    }

    /**
     * Check if jobs are still running or in queue.
     *
     * @return whether any jobs have yet to complete
     */
    public boolean hasRemainingJobs() {
        // Only check running process if max quanta reached.
        if (maxQuantaReached) return runningProcess != null;

        return runningProcess != null || !queuedProcesses.isEmpty();
    }

    /**
     * Get statistics for algorithm.
     */
    public void printStatistics() {
        double avgWait, avgResp, avgTurn;
        avgWait = avgResp = avgTurn = 0;

        for (Process p : completedProcesses) {
            avgWait += p.getWaitTime();
            avgResp += p.getStartTime() - p.getArrivalTime();
            avgTurn += p.getEndTime() - p.getArrivalTime();
        }
        avgWait /= completedProcesses.size();
        avgResp /= completedProcesses.size();
        avgTurn /= completedProcesses.size();

        System.out.printf("\n\nAvg wait: %f\nAvg response: %f\nAvg turnaround: %f\nThroughput: %d\n\n",
                avgWait, avgResp, avgTurn, completedProcesses.size());
    }

    /**
     * Add process to queue and sort by increasing service time.
     *
     * @param p the process to add
     */
    private void addToQueue(Process p) {
        queuedProcesses.add(p);
        Collections.sort(queuedProcesses, new SRTComparator());
    }

    /**
     * Check if currently running process is complete and
     * reschedule from queue or set to null.
     */
    private void checkRunningProcessStatus() {
        if (!runningProcess.isComplete()) return;

        // Process is done. Reschedule from queue.
        runningProcess.setEndTime(runTime);
        completedProcesses.add(runningProcess);
        runningProcess = null;

        // Max quanta reached. Run only previously ran processes.
        if (maxQuantaReached) {
            while (!queuedProcesses.isEmpty()){
                Process queuedProcess = queuedProcesses.remove(0);

                if (queuedProcess.hasBeenRun()) {
                    runningProcess = queuedProcess;
                    break;
                }
            }
        } else if (!queuedProcesses.isEmpty()) {
            // Fetch process from queue if not empty.
            runningProcess = queuedProcesses.remove(0);
        }
    }

    public class SRTComparator implements Comparator<Process> {

        @Override
        public int compare(Process o1, Process o2) {
            if (o1.getServiceTime() < o2.getServiceTime()) return -1;
            if (o1.getServiceTime() > o2.getServiceTime()) return 1;
            return 0;
        }
    }
}
