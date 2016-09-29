import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Tester for shortest remaining time.
 */
public class Tester {

    private static final int JOB_COUNT = 30;
    private static final int MAX_ARRIVAL_TIME = 99;

    public static void main(String[] args) {
        System.out.println("Shortest Remaining Time\n");

        for (int i = 0; i < 5; i++) {
            System.out.println("RUN: " + (i + 1) + "\n");
            Process[] processes = new Process[JOB_COUNT];
            createProcesses(processes);
            for (Process p : processes) {
                System.out.println(p.toString() + "\n");
            }

            scheduleProcesses(processes);
        }
    }

    /**
     * Create processes and add to array.
     * @param p the array of processes
     */
    private static void createProcesses(Process[] p) {
        Random rand = new Random();
        for (int i = 0; i < p.length; i++) {
            int arrivalTime = rand.nextInt(MAX_ARRIVAL_TIME + 1);
            double serviceTime = (rand.nextInt(100) + 1) / 10.0;
            int priority = rand.nextInt(4) + 1;
            char id = (char)(i + 'A');

            p[i] = new Process(arrivalTime, serviceTime, priority, id);
        }

        Arrays.sort(p, Process::compareTo);
    }

    /**
     * Schedule processes using Shortest Remaining Time.
     * @param processes the processes to schedule
     */
    private static void scheduleProcesses(Process[] processes) {
        ShortestRemainingTime srt = new ShortestRemainingTime();
        int scheduledCount = 0;

        for (int i = 0; i <= MAX_ARRIVAL_TIME || srt.hasRemainingJobs(); i++) {
            if (i <= MAX_ARRIVAL_TIME) {
                // Multiple jobs may have same arrival time.
                // Make sure to schedule them all.
                ArrayList<Process> newProcesses = new ArrayList<>();
                while (scheduledCount != JOB_COUNT) {
                    Process nextProcess = processes[scheduledCount];
                    if (nextProcess.getArrivalTime() == i) {
                        newProcesses.add(nextProcess);
                        scheduledCount++;
                    } else {
                        break;
                    }
                }
                if (!newProcesses.isEmpty()) {
                    srt.schedule(newProcesses);
                }
            } else {
                srt.stopNewProcesses();
            }

            srt.incrementRunTime();
        }
        srt.printStatistics();
    }
}
