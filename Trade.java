package blonski_CSCI201L_Assignment2;

public class Trade extends Thread {
	public int seconds;
	public String ticker;
	public int numStock;
	public Data companyList;
	
	Trade(int time, String tick, int num, Data companies) {
		this.seconds = time;
		this.ticker = tick;
		this.numStock = num;
		this.companyList = companies;
	}
	
	public void run() {
		for(int i = 0; i < companyList.data.size(); i++) {
			if(companyList.data.get(i).getTicker().equals(this.ticker)) {
				companyList.data.get(i).broker.executeTrade(this);
			}
		}
	}
	
	

}
