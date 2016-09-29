import java.util.ArrayList;
import java.util.Collections;	
import java.util.HashMap;
import java.util.List;


public class HPF_NonPreemptive {
	
	
	static List<Process> waitQueue = new ArrayList<Process>();
	static List<Process> readyQueue = new ArrayList<Process>();
	static List<Process> bufferQueue = new ArrayList<Process>();
	static List<Process> recordQ = new ArrayList<Process>();

	static List<Process> finishedQueue = new ArrayList<Process>();
	static HashMap<String, Float> map = new HashMap<String, Float>();

    static List<Float> cT = new ArrayList<Float>();
    
    static float timeLapsed = 1;
    static int startIdx = 0;
    static int endIdx = 0;
	static int processes = 99;
	static boolean allProcessesArrived = false;
	
	
	
	public HPF_NonPreemptive()
	{
		for(int i = 0; i < 100; i++)
		{
			waitQueue.add(Process.generateProcess("P" + i));
		}
		
		sortAndRename(waitQueue);
		
		
	}
	
	public static void startHPF(List<Process> readyQ, int startIndex)
	{
		   
		if(bufferQueue.size() == 99)
		{
			executeRemainingProcesses();
		}
		else{
			Process temp = readyQ.get(startIndex);
			
			temp.setFinished(true);
			
			finishedQueue.add(temp); //Keeping track of finished processes
			recordQ.add(temp); //for keeping track of index
			
			
			//Adding completion time of each process to the list
			if(cT.isEmpty())
			{
			cT.add((float) 0);
			cT.add(temp.getExpRunTime());
			addToMap(temp.getName(), temp.getExpRunTime());
			System.out.println(cT);
			}
			
			else if(!cT.isEmpty())
			{
				cT.add(cT.get(cT.size()-1) + temp.getExpRunTime());
				addToMap(temp.getName(), (cT.get(cT.size()-1) + temp.getExpRunTime()));

				System.out.println(cT);
			}
			
			/////////////////////////////////////////
			
			
			
			if(startIdx == 0)
			{
				startIdx = startIndex + 1;
				System.out.println("in startINdex 0 state");

			}
			else if(startIdx != 0 && (endIdx + 1) <= 99)
			{
				startIdx = endIdx  + 1;
				System.out.println("in startINdex : " + startIdx);
			}
			else if(startIdx > 99)
			{
				executeRemainingProcesses();

				return;
			}
			
			
			if(endIdx == 0)
			{
				endIdx = (int) ((startIdx + Math.ceil((double)temp.getExpRunTime())) - 1);
				System.out.println("in edndIDx 0 state : " + endIdx);

			}
			else if(endIdx != 0 && (startIdx + Math.ceil((double)temp.getExpRunTime())) - 1 <=99)
			{
				endIdx = (int) (startIdx + Math.ceil((double)temp.getExpRunTime()) - 1);
				System.out.println("in endINdex : " + endIdx);

			}
			else if(endIdx > 99)
			{
				System.out.println("All processes arrived");
				return;
			}
			
			if(startIdx < 99 && endIdx < 99)
			{
			addProcessesArrived(startIdx, endIdx);
			}
			else if (startIdx == 99)
			{
				bufferQueue.add(waitQueue.get(99));
				
				executeRemainingProcesses();
			}
		}
			
		
	}
	
	
	
	
	public static void addProcessesArrived(int start, int end)
	{
		//if(allProcessesArrived == false)
		//{
		if(end < waitQueue.size() - 1){
		for(int  i = start; i <= end; i++)
		{
			recordQ.add(waitQueue.get(i));
			bufferQueue.add(waitQueue.get(i));
			
			
		}
		
		
		Process.sortByAt(bufferQueue);
		Process.sortByPriority(bufferQueue);
		
		
		if(bufferQueue.size() < waitQueue.size()  - 1)
		{
			startHPF(bufferQueue, 0);
		}
		
		else{
			System.out.println("All processes arrived");
			printList(bufferQueue);
			return;
		}
		
		}
		else
		{
			return;
		}
		
	}
	
	public static void executeRemainingProcesses()
	{
		for(int i = 0; i < bufferQueue.size(); i++)
		{
			Process temp = bufferQueue.get(i);
			if(temp.isFinished() == true)
			{
				continue;
			}
			else if(temp.isFinished() == false)
			{
				temp.setFinished(true);
				cT.add(cT.get(cT.size()-1) + temp.getExpRunTime());
				addToMap(temp.getName(), (cT.get(cT.size()-1) + temp.getExpRunTime()));
				
			}
		}
		
	}
	
	
	public static void addToMap(String key, float value)
	{
		map.put(key, value);
	}
	

	
	
	
	/**
	 * Helper method to sort and rename the process list
	 * @param list the list to be sorted and renamed
	 */
	public void sortAndRename(List<Process> list)
	{
		Process.sortByAt(list); //sorting the processes based on arrival time
		int i = 0;
		for(Process p : list)
		{	
			p.setName("P" + i);
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

		//HPF_NonPreemptive.printList(waitQueue);
		HPF_NonPreemptive.startHPF(waitQueue, 0);
		
	}
}
