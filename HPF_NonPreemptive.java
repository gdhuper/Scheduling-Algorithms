import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HPF_NonPreemptive {
	
	
	static List<Process> waitQueue = new ArrayList<Process>();
	static List<Process> readyQueue = new ArrayList<Process>();
	static List<Process> bufferQueue = new ArrayList<Process>();
	static List<Process> FinishedQueue = new ArrayList<Process>();


	static int processes = 99;
	
	
	
	
	public HPF_NonPreemptive()
	{
		for(int i = 0; i < 100; i++)
		{
			waitQueue.add(Process.generateProcess("P" + i));
		}
		
		sortAndRename(waitQueue);
		
		
	}
	
	public static void startHPF()
	{
		
		for(int i = 0; i <= processes; i++)
		{
			Process temp = waitQueue.get(0);
			if(i == 0)
			{
				FinishedQueue.add(waitQueue.get(0));
				waitQueue.remove(0);
			
			}
			
			for(int j = 1; j <= temp.getExpRunTime(); j++)
				{
					bufferQueue.add(waitQueue.get(j));
				}
		}
		
		printList(waitQueue);
		printList(bufferQueue);

		
		
		
	}
	
	
	
	
	
	
	
	/**
	 * Helper method to sort and rename the process list
	 * @param list the list to be sorted and renamed
	 */
	public void sortAndRename(List<Process> list)
	{
		Process.sortByAt(waitQueue); //sorting the processes based on arrival time
		int i = 0;
		//System.out.printf("%5s%20s%20s%20s%n", "Process Name", "Arrival Time(ms)", "RunTime(ms)", "Priority");

		for(Process p : list)
		{
			
			p.setName("P" + i);
			//System.out.printf("%5s%20s%20s%20s%n", p.getName(), p.getArrivalTime(), p.getExpRunTime(), p.getPriority());

			i++;
		}
	}
	
	public static void printList(List<Process> list)
	{
		System.out.printf("%5s%20s%20s%20s%n", "Process Name", "Arrival Time(ms)", "RunTime(ms)", "Priority");

		for(Process p : list)
		{
			
			
			System.out.printf("%5s%20s%20s%20s%n", p.getName(), p.getArrivalTime(), p.getExpRunTime(), p.getPriority());

		}
	
	}
	
	
	
	
	
	

	public static void main(String[] args)
	{
		HPF_NonPreemptive npa = new HPF_NonPreemptive();
		
		//npa.startHPF();
		
		npa.printList(waitQueue);
	}
}
