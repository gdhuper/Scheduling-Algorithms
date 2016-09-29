import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class Process {
	
	private String name;
	private float arrivalTime;
	private float expRunTime;
	private int priority;
	public static final float MIN_ARRIVAL_TIME = 99;
	public static final float MAX_ARRIVAL_TIME = 0;
	public static final float MIN_RUNTIME = (float) 0.1;
	public static final float MAX_RUNTIME = 10;
	public static final int MIN_PRIORITY = 1;
	public static final int MAX_PRIORITY = 4;
	
	
	public Process(String name, float arrivalTime, float expRunTime, int priority)
	{
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.expRunTime = expRunTime;
		this.priority = priority;
	}

	
	
	
	public static Process generateProcess(String name)
	{
		Random random = new Random();
        float arrivalTime =  nextRandomFloat(MIN_ARRIVAL_TIME, MAX_ARRIVAL_TIME); 
        
        arrivalTime = formatDecimal(arrivalTime, 2);
        float runTime = nextRandomFloat(MIN_RUNTIME, MAX_RUNTIME);
        runTime = formatDecimal(runTime, 2);

        int priority = random.nextInt(4) + 1;
        
        Process pro = new Process(name, arrivalTime, runTime, priority);
        return pro;
	}
	
	public static float nextRandomFloat(float min, float max)
	{
		Random random = new Random();
		float temp = min + random.nextFloat() * (max - min);
		return temp;
	}
	
	
	/**
	 * Sorts the process list by arrival time
	 * @param list the process list
	 */
	public static void sortByAt(List<Process> list)
	{
		Collections.sort(list, new Comparator<Process>(){
			   public int compare(Process p1, Process p2){
				      return (int) (p1.getArrivalTime() - p2.getArrivalTime());
				   }
				});
	}
	
	/**
	 * Sorts the process list by priority
	 * @param list the process list 
	 */
	public static void sortByPriority(List<Process> list)
	{
		Collections.sort(list, new Comparator<Process>(){
			   public int compare(Process p1, Process p2){
				      return (p1.getPriority() - p2.getPriority());
				   }
				});
	}
	
	public static float formatDecimal(float d, int decimalPlace) {

        BigDecimal bd = new BigDecimal(Float.toString(d));

        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       

        return bd.floatValue();

    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(float arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public float getExpRunTime() {
		return expRunTime;
	}


	public void setExpRunTime(float expRunTime) {
		this.expRunTime = expRunTime;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String toString()
	{
		return "ProcessName: " + this.getName() + " Arrival Time: " + this.getArrivalTime() + " RunTime: " + this.getExpRunTime() + " Priority: " + this.getPriority();
	}
	
	
	public static void main(String[] args)
	{
		Process temp = generateProcess("P");
		System.out.println(generateProcess("p").toString());
	}

}
