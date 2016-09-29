import java.util.ArrayList;
import java.util.Collections;	
import java.util.List;


public class HPF_NonPreemptive {
	
	
	static List<Process> waitQueue = new ArrayList<Process>();
	static List<Process> readyQueue = new ArrayList<Process>();
	static List<Process> bufferQueue = new ArrayList<Process>();
	static List<Process> recordQ = new ArrayList<Process>();

	static List<Process> finishedQueue = new ArrayList<Process>();

    static List<Float> bT = new ArrayList<Float>();
    
    static float timeLapsed = 1;
    static int startIdx = 0;
    static int endIdx = 0;
	static int processes = 99;
	
	
	
	
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
		
			Process temp = readyQ.get(startIndex);
			temp.setFinished(true);
			finishedQueue.add(temp);
			recordQ.add(temp); //for keeping track of index
			if(bT.isEmpty())
			{
			
			bT.add((float) 0);
			bT.add(temp.getExpRunTime());
			System.out.println(bT);
			}
			else if(!bT.isEmpty())
			{
				bT.add(bT.get(bT.size()-1) + temp.getExpRunTime());
				System.out.println(bT);

			}
			
			if(startIdx == 0)
			{
				startIdx = startIndex + 1;
				System.out.println("in startINdex 0 state");

			}
			else if(startIdx != 0 && (endIdx + 1) <= 100)
			{
				startIdx = endIdx  + 1;
				System.out.println("in startINdex : " + startIdx);
			}
			else if(startIdx > 100)
			{
				return;
			}
			
			
			if(endIdx == 0)
			{
				endIdx = (startIdx + (int) temp.getExpRunTime()) - 1;
				System.out.println("in edndIDx 0 state : " + endIdx);

			}
			else if(endIdx != 0 && (startIdx + temp.getExpRunTime() <=100) && startIdx != 100)
			{
				endIdx = (startIdx + (int) temp.getExpRunTime());
				System.out.println("in endINdex : " + endIdx);

			}
			else if(endIdx > 100)
			{
				System.out.println("All processes arrived");
				return;
			}
			
			if(startIdx < 100 && endIdx <=100)
			{
			addProcessesArrived(startIdx, endIdx);
			}
			else if (startIdx == 100)
			{
				bufferQueue.add(waitQueue.get(99));
				startHPF(bufferQueue, 0);
				
			}
			
			//printList(bufferQueue);


		
		//printList(waitQueue);
		//printList(bufferQueue);
		
		
		
		
	}
	
	
	
	
	public static void addProcessesArrived(int start, int end)
	{
		
		for(int  i = start; i <= end; i++)
		{
			recordQ.add(waitQueue.get(i));
			bufferQueue.add(waitQueue.get(i));
			
			
		}
		
		Process.sortByAt(bufferQueue);
		Process.sortByPriority(bufferQueue);
		
		
		if(bufferQueue.size() < waitQueue.size())
		{
			startHPF(bufferQueue, 0);
		}
		
		else{
			System.out.println("All processes arrived");
			printList(bufferQueue);
			return;
		}

		
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

		npa.printList(waitQueue);
		npa.startHPF(waitQueue, 0);
		
	}
}
