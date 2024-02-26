/*
 * This class simulates the processing of accident reports.
 * @author Marzia Saidi
 * 
 */

package accidentpackage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SimulatingCounter {
	/*
	 * Simulates the processing of accident reports based on severity and available
	 * time.
	 * 
	 * @param accidentReports
	 */
	public static void simulateProcessing(ArrayList<Report> accidentReports) {

		int processingTime = 0;
		int totalMinutes = 7200;
		int remainingTime = totalMinutes;
		Queue<Report> reportQueue = new LinkedList<>();
		for (Accident report : accidentReport) {

// check if the report occured on the same day
			if (report.getStart_Time().toLocalDate().equals(LocalDate.now())) {
				reportQueue.add(report);
			}

			while (reportQueue.isEmpty() == false) {
				Report r = reportQueue.poll();
				int severity = report.getSeverity();
				processingTime = severity * 60;
			}

// check if there is engough time to process the report on the same day

			if (processingTime <= remainingTime) {
				remainingTime -= processingTime;
				System.out.println("Processing report: " + report.toString());
			} else {
				System.out.println("not enough time to process report: " + report.toString());
			}

		}
	}

}
