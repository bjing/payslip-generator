package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is for extracting employee information from input files
 */
public class InputParser {
	private String csvSplitBy = ",";
	private String inputFileFormat = "csv";
	private static Logger logger = Logger.getLogger("PayCalculator");
	
	/**
	 * List all files recursively in the input directory
	 * @param directory
	 * @throws IOException
	 */
	public List<String> extractStringsFromInputFiles(final File directory) throws IOException {
		List<String> employeeRecords = new ArrayList<String>();
		
		for (final File fileEntry : directory.listFiles()) {
			if (fileEntry.isFile() && fileEntry.getName().endsWith(inputFileFormat)) {
				// Read the file
				List<String> lines = Files.readAllLines(Paths.get(fileEntry.getPath()), StandardCharsets.UTF_8);
				employeeRecords.addAll(lines);
			}
		}
		return employeeRecords;
	}
	
	/**
	 * Parse strings of employee records read from input files
	 * @param employeeRecords
	 * @return
	 */
	public List<EmployeePayReport> parseEmployeeRecords(List<String> employeeRecords) {
		List<EmployeePayReport> employeePayReports = new ArrayList<EmployeePayReport>();
		for (String line : employeeRecords) {
			
			String[] tokens = line.split(csvSplitBy);
			
			try {
				Employee employee = new Employee(tokens[0], tokens[1], 
						Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]));
				EmployeePayReport employeePayReport = new EmployeePayReport(employee, tokens[4]);
				employeePayReports.add(employeePayReport);
			} catch (NumberFormatException e) {
				//If a line doesn't get parsed correctly, warn and move on to the next
				logger.log(Level.WARNING, "Input line cannot be correctly parsed! Skipping record.\nLine: " 
						+ line + "\nError message: " + e.getMessage() + "\n");
				continue;
			}
		}
		return employeePayReports;
	}
	
	/**
	 * Load data into EmployeePayReport for each employee 
	 * @return
	 * @throws IOException
	 */
	public List<EmployeePayReport> loadEmployeeData(String inputDirectoryPath) throws IOException{
		// Parse all the files in the input directory
		final File inputDirectory = new File(inputDirectoryPath);
		List<String> employeeRecords = extractStringsFromInputFiles(inputDirectory);
		
		// Get all the employee records and return them
		return parseEmployeeRecords(employeeRecords);
	}
}
