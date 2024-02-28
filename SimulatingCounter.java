/*
 * This class simulates the processing of accident reports.
 * @author Marzia Saidi
 * 
 */

package accidentpack;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//import accidentpack.program4.ArrayDequeQueue;

public class SimulatingCounter {
	
	
	/**
     * Simulates the queuing of accident reports. This method takes a list of accident
     * reports and adds them to a queue, preparing them for processing.
     *
     * @param accidentReports An {@code ArrayList} of {@code Report} objects representing accident reports.
     * @return A {@code Queue} of {@code Report} objects ready to be processed.
     */
    public static  Queue<Report> simulateProcessing(ArrayList<Report> accidentReports) {
    	
        Queue<Report> reportQueue = new LinkedList<>();

        // Add reports to the queue
        for (Report report : accidentReports) {
        	
             reportQueue.add(report);
        }
		return reportQueue;

        
          
    } 
    
//    public static int processReports(Queue<Report> reportQueue, int totalMinutes) {
//        int originalTotalMinutes = totalMinutes;
//        int waitingTime= 0;
//        int numCount = 0;
//		while(!reportQueue.isEmpty() || totalMinutes>0) {
//			Report currentReport =  reportQueue.peek();
//			
//			 int processingTime = currentReport.getSeverity() * 60;                                      
//			 waitingTime+=processingTime;
//			 
//			 if (totalMinutes >= processingTime) {
//				 	reportQueue.remove();
//		            totalMinutes -= processingTime;
//		            
//		        } else {
////		            System.out.println("you do not have enough counters. The time it takes to process this report is more than the remaining time ");
//		            numCount+=1;
//		        }
//			 
//				// check how long the currentReport has been waiting and if it is more than a day, then you do not have enough counters
//	            if (waitingTime > 1440) {
////	                System.out.println("A report has been waiting for more than a day. You do not have enough counters.");
//	                numCount+=(waitingTime/1440);
//	                waitingTime %= 1440;
//	            }
//	            if(waitingTime>0) {
//	            	numCount+=1;
//	            }
//		}
//		return numCount;
//	}
    
    
    /**
     * Processes the reports in a queue and calculates the number of counters needed
     * to process all reports within a day. This method takes into account the severity
     * of each report, which determines the processing time.
     *
     * @param reportQueue A {@code Queue} of {@code Report} objects to be processed.
     * @param numN        The initial number of counters available for processing.
     * @return The number of counters needed to process all reports within a day .
     */
    public static int processReports(Queue<Report> reportQueue, int numN) {
        int totalMinutesPerDay = 1440*numN;
        int numCountersNeeded = 0;
        int currentTimeSpent = 0;

        while (!reportQueue.isEmpty()) {
            Report currentReport = reportQueue.poll();
            int processingTime = currentReport.getSeverity() * 60;
            
            if (currentTimeSpent + processingTime <= totalMinutesPerDay) {
                currentTimeSpent += processingTime;
            } else {
                // Increment counters if a single report cannot be processed within the remaining time of the day
                numCountersNeeded++;
                currentTimeSpent = processingTime; // Reset time spent for the new counter
                return numN+1;
            }
        }

//       // Check if any remaining processing time requires an additional counter
//        if (currentTimeSpent > 0) {
//            numCountersNeeded++;
//        }

        return numN;
    }

}

