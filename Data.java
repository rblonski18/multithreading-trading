package blonski_CSCI201L_Assignment2;

import java.util.List;

public class Data {
	/* 
	public Company[] data;
	String fileName;
	Data(Company[] newList) {
		this.data = newList;
	}
	*/
	
	public List<Company> data = null;
	String fileName;
	public List<Company> getData() {
		return this.data;
	}
	public void setData(List<Company> data) {
		this.data = data;
	}
}
