package blonski_CSCI201L_Assignment2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Schedule extends Thread {
	public static List<Trade> tradeSchedule;
	
	public Schedule(List<Trade> sched) {
		tradeSchedule = sched;
	}
	
	public void run() {
		
		System.out.println("Starting trade execution ");
		
		int counter = 0;
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		while(!tradeSchedule.isEmpty()) {
			
			if(tradeSchedule.get(0).seconds == counter) {
				
				Trade current = tradeSchedule.remove(0);

				executor.execute(current);
				//current.start();
				
			} else {
				
				try {
					
					Thread.sleep(1000);
					
					counter++;
					
				} catch(InterruptedException e) {
					
					System.out.println("IE when sleepin ");
					
				}
			}
		}
		
		
		executor.shutdown();
		while(!executor.isTerminated()) {
			Thread.yield();
		}
		
		System.out.println("All trades completed. ");
		
	}

}
