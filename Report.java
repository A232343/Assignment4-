package accidentpack;

/**
 * This class's different methods calculate Fibonacci numbers.
 * @author Abdullahi Abdullahi
 */
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;





public class Report {
	
	
	private String iD;
	private String severity;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String street;
	private String city;
	private String county;
	private String state;
	private String temperature;
	private String humidity;
	private Double visibility;
	private String weatherCondition;
	private String crossing;
	private String sunriseSunset;
	ArrayList<Report> AccidentReports;

	/**
	 * Constructs a new Report instance with detailed information about an accident.
	 *
	 * @param iD Unique identifier for the report.
	 * @param severity Severity level of the accident.
	 * @param localDateTime Start time of the accident.
	 * @param localDateTime2 End time of the accident.
	 * @param street Street where the accident occurred.
	 * @param city City where the accident occurred.
	 * @param county County where the accident occurred.
	 * @param state State where the accident occurred.
	 * @param temperature Temperature at the time of the accident.
	 * @param humidity Humidity level at the time of the accident.
	 * @param visibility Visibility at the time of the accident.
	 * @param weatherCondition Weather condition at the time of the accident.
	 * @param crossing Indicates if the accident occurred at a crossing.
	 * @param sunriseSunset Time of sunrise or sunset during the accident.
	 */
	public Report(String iD, String severity, LocalDateTime localDateTime, LocalDateTime localDateTime2, String street, String city,
		String county, String state, String temperature, String humidity, Double visibility,
		String weatherCondition, String crossing, String sunriseSunset) {
		super();
		this.iD = iD;
		this.severity = severity;
		this.startTime = localDateTime;
		this.endTime = localDateTime2;
		this.street = street;
		this.city = city;
		this.county = county;
		this.state = state;
		this.temperature = temperature;
		this.humidity = humidity;
		this.visibility = visibility;
		this.weatherCondition = weatherCondition;
		this.crossing = crossing;
		this.sunriseSunset = sunriseSunset;
	}
	
	
	Report(String filePatch) throws IOException{
		AccidentReports = ReadCSVFile(filePatch);
	}	
	
	
	/**
	 * Reads a CSV file and creates a list of Report objects from its contents.
	 * With gbt Assistance  
	 * @param filePath The path of the CSV file to read.
	 * @return An ArrayList of Report objects.
	 * @throws IOException If an error occurs during file reading.
	 */
	private static ArrayList<Report> ReadCSVFile(String filePath) throws IOException {
	    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
	        return stream.skip(1) // Skip the header
	                    .map(line -> line.split(","))
	                    .map(rawData -> new Report(rawData[0], rawData[1], dateFormatter(rawData[2]),
	                                               dateFormatter(rawData[3]), rawData[4], rawData[5], rawData[6],
	                                               rawData[7], rawData[8], rawData[9], Double.parseDouble(rawData[10]), rawData[11],
	                                               rawData[12], rawData[13]))
	                    .collect(Collectors.toCollection(ArrayList::new));
	    }
	}
		
	    
	
	private static LocalDateTime dateFormatter(String dateInString) {
		String[] SplitedDate = dateInString.split("\\.");
		String dateBefor = SplitedDate[0];
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime dateAfter = LocalDateTime.parse(dateBefor, formatter);

	    return dateAfter;
	}

		
	
	
	
	/**
	 * Filters and returns a list of reports based on the specified state and city.
	 *
	 * @param state The state to filter by.
	 * @param city The city to filter by.
	 * @return An ArrayList of Report objects that match the state and city criteria.
	 */

	public ArrayList<Report> StateCountyReport(String county ,String state ){
	    ArrayList<Report> report = new ArrayList<>();
		for(int i=0;i<AccidentReports.size();i++) {
			if(AccidentReports.get(i).getState().equals(state)&& AccidentReports.get(i).getCounty().equals(county)) {
				report.add(AccidentReports.get(i));
			}
		}
		return report;
	}
	
	 
	
	/**
	 * Sorts a list of Report objects by start time.
	 *
	 * @param arrayL The ArrayList of Report objects to be sorted.
	 * @return The sorted ArrayList of Report objects.
	 */
	public  void SortByStartTime(ArrayList<Report> arrayL){
		ArrayList<Report> data = arrayL;
		Collections.sort(data, new Comparator<Report>() {
			@Override
			public int compare(Report o1, Report o2) {
				int result = o1.getStartTime().compareTo(o2.getStartTime());
				return result;
	            
			}
	    });
		return;
		
	}
	
	
	
	
	public String getCity() {
		return city;
	}


	public String getState() {
		return state;
	}


	
	public int getReportSize(){
		return AccidentReports.size();
		
	}
	
	public String getReportByIndex(int n) {
		return AccidentReports.get(n).toString();
		
	}
	public String getStreet() {
		return street;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	public String getCounty() {
		return county;
		
	}
	public String getSeverity() {
		return severity;
	}

	@Override
	public String toString() {
		return " [iD = " + iD + ", severity = " + severity + ", startTime = " + startTime + ", endTime = " + endTime
				+ ", street = " + street + ", city = " + city + ", county = " + county + ", state = " + state + ", temperature = "
				+ temperature + ", humidity = " + humidity + ", visibility = " + visibility + ", weatherCondition = "
				+ weatherCondition + ", crossing = " + crossing + ", sunriseSunset = " + sunriseSunset + "]";
	}
	  
}
