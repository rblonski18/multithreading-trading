package blonski_CSCI201L_Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class MainLoop {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Data newDat = readJSONFile(input);
		Schedule newSched = readCSVFile(input, newDat);
		for(Company c : newDat.data) {
			c.instantiateBroker();
		}
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(newSched);
		executor.shutdown();
		while(!executor.isTerminated()) {
			Thread.yield();
		}
		
		return;
	}
	
	public static Data readJSONFile(Scanner input) {
		
		// this is the function that reads the JSON file and returns
		// a Data object that contains the list of companies and it's filename. 
		
		String fileName = null;
		
		Data companyList;
		
		System.out.println("What is the name of the company file?");
		
		fileName = input.next();
		
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			companyList = gson.fromJson(reader, Data.class);
			reader.close();
			
		} catch(NoSuchFileException e) {
			
			System.out.println("The file " + fileName + " could not be found. ");
			return readJSONFile(input);
			
		} catch(IOException e) {
			
			System.out.println("The file " + fileName + " is not formatted properly. There was trouble parsing the file. ");
			return readJSONFile(input);
	
		} 
		
		// just a loop that goes through and calls a method that ensures all data
		// is entered in for each company - if not, file not formatted correctly. 
		for(int i = 0; i < companyList.data.size(); i++) {
			if(companyList.data.get(i).allClear()) continue;
			else {
				System.out.println("File not formatted properly. A data point is missing from a company.");
				return readJSONFile(input);
			}
		}
		
		System.out.println("The file has been properly read. ");
		
		companyList.fileName = fileName;
		return companyList;
	}
	
	public static Schedule readCSVFile(Scanner input, Data dataList) {
		// file to read in CSV input. 
		
		// some of the code to read the csv came from a website. 
		// https://stackabuse.com/reading-and-writing-csvs-in-java/
		
		String csvFileName;
		String row;
		List<Trade> schedule = Collections.synchronizedList(new ArrayList<Trade>());
		Schedule retSched = new Schedule(schedule);
		
		System.out.println("What is the name of the csv file? ");
		csvFileName = input.next();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvFileName));
			while((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				int currentTime = Integer.parseInt(data[0]);
				String ticker = data[1];
				int trades = Integer.parseInt(data[2]);
				Trade currentTrade = new Trade(currentTime, ticker, trades, dataList);
				Schedule.tradeSchedule.add(currentTrade);
				row = "";
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File wasn't found. Make sure file is in correct place, and try again. ");
			return readCSVFile(input, dataList);
		} catch (IOException e) {
			System.out.println("File not formatted properly. Format correctly and try again. ");
			return readCSVFile(input, dataList);
		}
		return retSched;
	}

}
