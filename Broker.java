package blonski_CSCI201L_Assignment2;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Broker extends Thread {
	
	public Semaphore semaphore;
	public static List<Trade> tradeQueue;
	public int numBrokers;
	public Instant now;
	
	public Broker(String tick, int brokers) {
		tradeQueue = Collections.synchronizedList(new ArrayList<Trade>());
		semaphore = new Semaphore(brokers);
		this.numBrokers = brokers;
		this.now = Instant.now();
	}

	public void executeTrade(Trade tr) {

		try {
			semaphore.acquire();
			if(tr.numStock < 0) {
				String sale = "Starting sale of " + tr.numStock*(-1) + " shares of " + tr.ticker;
				Util.printMessage(sale, now);
				Thread.sleep(1000);
				sale = "Finished sale of " + tr.numStock*(-1) + " shares of " + tr.ticker;
				Util.printMessage(sale, now);
			} else if(tr.numStock > 0) {
				String purchase = "Starting purchase of " + tr.numStock + " shares of " + tr.ticker;
				Util.printMessage(purchase, now);
				Thread.sleep(1000);
				purchase = "Finished purchase of " + tr.numStock + " shares of " + tr.ticker;
				Util.printMessage(purchase, now);
			}
		} catch (InterruptedException e) {
			System.out.println("IE on executeTrade of " + tr.ticker);
		} finally {
			semaphore.release();
		}
	}
	
}
