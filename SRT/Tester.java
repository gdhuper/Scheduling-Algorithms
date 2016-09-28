import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Jaylan Tse on 9/27/2016.
 */
public class Tester {

    private static final int JOB_COUNT = 10;
    private static final int MAX_ARRIVAL_TIME = 99;

    public static void main(String[] args) {
        Process[] processes = createProcesses();

        for (Process p : processes) {
            System.out.println(p.toString());
        }
    }

    private static Process[] createProcesses() {
        Process[] p = new Process[JOB_COUNT];
        for (int i = 0; i < p.length; i++) {
            Random rand = new Random();
            int arrivalTime = rand.nextInt(MAX_ARRIVAL_TIME + 1);
            double serviceTime = rand.nextInt(100 + 1) / 10.0;
            int priority = rand.nextInt(4) + 1;

            p[i] = new Process(arrivalTime, serviceTime, priority);
        }

        Arrays.sort(p, Process::compareTo);

        return p;
    }
}
