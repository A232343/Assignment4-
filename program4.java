package accidentpack;
import java.util.Collections;


import java.io.File;

/**
 * This class's different methods calculate Fibonacci numbers.
 * @author Abdullahi Abdullahi
 * @version 2/9/2024
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner; 

public class program4 {
	 
	public static void main(String[] args) throws IOException {

		
		String filePath = null;
		if (args.length > 0) {
             filePath = args[0]; // Get the file path from the first argument
        } else {
            System.out.println("No file path provided.");
        }
		
       
		//if no file patch is provided enter the file path here
		Report Accidents = readFile(filePath);
		
	
        printCounterFor(Accidents, "Los Angeles", "CA");
        System.out.println("\n");
        
        printCounterFor(Accidents, "Orange", "FL");
        System.out.println("\n");
        
        printCounterFor(Accidents, "Harris", "TX");
        System.out.println("\n");
        
        printCounterFor(Accidents, "Hamilton", "OH");
        System.out.println("\n");
        
        printCounterFor(Accidents, "New Castle", "DE");
        System.out.println("\n");
        

        
	}


	
	
	/**
     * Prints the number of counters needed for a given county and state, along with
     * the time taken to simulate the process.
     * 
     * @param Accidents The report object containing accident data.
     * @param County    The county for which the counter number is calculated.
     * @param State     The state where the county is located.
     */
	private static void printCounterFor(Report Accidents, String County, String State) {
		long startTime = System.currentTimeMillis();
		int counters = getCounter(Accidents,County,State);
        long endTime = System.currentTimeMillis();
	    double runtime = (endTime - startTime)/1000.00;
	    System.out.println(runtime +" seconds to simulate the process");
	    System.out.println("The minimum number of counters is: "+counters);
	}

 
	/**
     * Retrieves and calculates the number of counters required for processing 
     * accident reports in a specific county and state.
     * 
     * @param Accidents The report object containing accident data.
     * @param county    The county for which the counter number is calculated.
     * @param State     The state where the county is located.
     * @return The number of counters required.
     */
	private static int getCounter(Report Accidents, String county,String State) {
		System.out.println("county: "+county+" State: "+State);
		// get's the report for a county and sate 
        ArrayList<Report> StateCountyReport = RunsortcityState(Accidents, county, State);
        //sorts the county based on start Time 
        Accidents.SortByStartTime(StateCountyReport);
        
//        System.out.println(StateCountyReport.size() +" reports loaded");
        
        return CounterAmount(StateCountyReport);
	}


	/**
     * Calculates the amount of counters needed to process a list of accident reports.
     * 
     * @param StateCountyReport The list of accident reports to be processed.
     * @return The maximum number of counters required to process the reports.
     */
    private static int CounterAmount(ArrayList<Report> StateCountyReport) {
        
        ArrayList<Report> AccidentsOnDate = new ArrayList<>();        
        List<Integer> totalNumCount = new ArrayList<>();

        //the accidents that happend on this date
        int compareDate = StateCountyReport.get(0).getStartTime().getDayOfMonth();
        int counter =1;
        
        for(int i = 0 ; i<StateCountyReport.size();i++) {
        	if(StateCountyReport.get(i).getStartTime().getDayOfMonth() == compareDate) {
        		AccidentsOnDate.add(StateCountyReport.get(i));
        	}
        	else {
        		Queue<Report> reportInQ =  SimulatingCounter.simulateProcessing(AccidentsOnDate);
//        		System.out.println(reportInQ.size());
        		
        		int updatedCounters= SimulatingCounter.processReports(reportInQ,counter);
        		if(counter < updatedCounters ) {
        		    counter++;
        		    i = 0;
        		}
        		if(counter == updatedCounters) {
        		    totalNumCount.add(counter);
        		    
        		}
        		
        		
        		
        		compareDate = StateCountyReport.get(i).getStartTime().getDayOfMonth();
        		AccidentsOnDate.clear();
        	}
        }
        
        
		return Collections.max(totalNumCount);
        

        
    }
	
    
    /**
     * Reads accident reports from a file and creates a report object.
     * 
     * @param filePath The path of the file containing accident reports.
     * @return A {@code Report} object containing the loaded accident data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
	private static Report readFile(String filePath) throws IOException {
	

        Report Accidents = new Report(filePath);
      
		System.out.println(" ");
		return Accidents;
	}
	
	
	/**
     * Sorts and returns accident reports from a specific county and state.
     * 
     * @param Accidents The report object containing accident data.
     * @param county    The county for which reports are to be sorted.
     * @param State     The state where the county is located.
     * @return An {@code ArrayList} of {@code Report} objects sorted for the specified county and state.
     */
	private static  ArrayList<Report> RunsortcityState(Report Accidents, String county, String State) {


		    // the method 
	        ArrayList<Report> StateCountyReport = Accidents.StateCountyReport(county, State);
		   
//			System.out.println(" ");

			return StateCountyReport;
	}
 
}

   