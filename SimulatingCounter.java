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
	public static void simulateProcessing(ArrayList<Accident> accidentReports, int counters) {

		int processingTime = 0;
		int totalMinutes = counters * 24 * 60;
		int remainingTime = totalMinutes;
		Queue<Accident> reportQueue = new LinkedList<>();
		for (Accident report : accidentReports) {

// check if the report occurred on the same day
			if (report.getStart_Time().equals(LocalDate.now())) {
				reportQueue.add(report);
			}

			while (reportQueue.isEmpty() == false) {
				Accident accidentreport = reportQueue.poll();
				int severity = accidentreport.getSeverity();
				processingTime = severity * 60;
			}

// check if there is enough time to process the report on the same day

			if (processingTime <= remainingTime) {
				remainingTime -= processingTime;
				System.out.println("Processing report: " + report.toString());
			} else {
				System.out.println("not enough time to process report: " + report.toString());
			}

		}
	}

}
