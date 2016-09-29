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

	
	
	
	public Process generateProcess(String name)
	{
		Random random = new Random();
        float arrivalTime =  nextRandomFloat(MIN_ARRIVAL_TIME, MAX_ARRIVAL_TIME); 
        float runTime = nextRandomFloat(MIN_RUNTIME, MAX_RUNTIME);
        
        int priority = random.nextInt(4) + 1;
        
        Process pro = new Process(name, arrivalTime, runTime, priority);
        return pro;
	}
	
	public float nextRandomFloat(float min, float max)
	{
		Random random = new Random();
		float temp = min + random.nextFloat() * (max - min);
		return temp;
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

}
