package blonski_CSCI201L_Assignment2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Company {
	private String name;
	private String ticker;
	private String description;
	public int stockBrokers;
	private Date startDate;
	private String exchangeCode;
	transient public Broker broker;
	
	public Company(String name, String ticker, String description, String startDate, int stockBrokers, String exchangeCode) {
		this.name = name;
		this.ticker = ticker;
		this.description = description;
		this.stockBrokers = stockBrokers;
		Date formattedDate = this.formatDate(startDate);
		this.startDate = formattedDate;
		this.exchangeCode = exchangeCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String companyName) {
		this.name = companyName;
	}
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public int getstockBrokers() {
		return stockBrokers;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date formatDate(String startDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date formattedDate = new Date();
		
		// eclipse is telling me to handle case of parsing error. 
		try {
			formattedDate = format.parse(startDate);
		}catch(Exception e) {
			System.out.println("There was trouble parsing the date. ");
		}
		return formattedDate;
	}
	
	public String getExchangeCode() {
		return exchangeCode;
	}
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
	
	public void instantiateBroker() {
		this.broker = new Broker(this.ticker, this.stockBrokers);
	}
	
	public boolean allClear() {
		if(this.name == null) return false;
		else if(this.ticker == null) return false;
		else if(this.startDate == null) return false;
		else if(this.ticker == null) return false;
		else if(this.description == null) return false;
		else if(this.stockBrokers == 0) return false;
		else return true;
	}
}
