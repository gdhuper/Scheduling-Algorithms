import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jaylan Tse on 9/27/2016.
 */
public class Tester {

    private static final int JOB_COUNT = 20;
    private static final int MAX_ARRIVAL_TIME = 99;

    public static void main(String[] args) {
        Process[] processes = new Process[JOB_COUNT];
        createProcesses(processes);
        ShortestRemainingTime srt = new ShortestRemainingTime();

        for (Process p : processes) {
            System.out.println(p.toString());
        }

        int scheduledCount = 0;
        for (int i = 0; i <= MAX_ARRIVAL_TIME || srt.hasRemainingJobs(); i++) {
            if (i <= MAX_ARRIVAL_TIME) {
                // Multiple jobs may have same arrival time.
                // Make sure to schedule them all.
                while (scheduledCount != JOB_COUNT) {
                    Process nextProcess = processes[scheduledCount];
                    if (nextProcess.getArrivalTime() == i) {
                        srt.schedule(nextProcess);
                        scheduledCount++;
                    } else {
                        break;
                    }
                }
            }

            srt.incrementRunTime();
        }
    }

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
}
