package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This is the class that reads in employee data and tax table to calculate income tax for each
 * employee. 
 * Employee payment reports can be dumped to disk or stdout
 * @author brian
 *
 */
public class PayCalculator {
	private TaxTable taxTable;
	private List<EmployeePayReport> employeePayReports;
	private String inputDirectoryPath = "input";
	private String outputDirectoryPath = "output";
	private static Logger logger = Logger.getLogger("PayCalculator");
	
	/**
	 * Class Constructor
	 * @throws IOException if an input exception occurs
	 */
	public PayCalculator() throws IOException {
		// Load tax table
		taxTable = new TaxTable();
		taxTable.loadTaxTableFromFile();
		// Load employee info
		InputParser inputs = new InputParser();
		this.employeePayReports = inputs.loadEmployeeData(this.inputDirectoryPath);
	}
	
	/**
	 * Class constructor taking in employeePayReports and taxTable directly
	 * @param employeePayReports EmployeePayReport object containing employee and payment info
	 * @param taxTable TaxTable object containing data from ATO tax table
	 */
	public PayCalculator(List<EmployeePayReport> employeePayReports, TaxTable taxTable) {
		this.taxTable = taxTable;
		this.employeePayReports = employeePayReports;
	}
	
	/**
	 * Calculate gross income
	 * @param employee an employee object containing basic employee info
	 * @return the calculated gross income
	 */
	public static int calculateGrossIncome(Employee employee) {
		Double grossIncome;
		grossIncome = employee.getSalary() / 12;
		
		return (int) Math.round(grossIncome);
	}

	/**
	 * Calculate tax income
	 * @param employee an employee object containing basic employee info
	 * @param taxInfo a list of numbers necessary parameters to calculate income tax
	 * @return the calculated tax income
	 */
	public static int calculateIncomeTax(Employee employee, List<Double> taxInfo) {
		Double incomeTax;
		incomeTax = (taxInfo.get(1) + (employee.getSalary() - taxInfo.get(3)) * taxInfo.get(2)) / 12;
				
		return (int) Math.round(incomeTax);
	}
	
	/**
	 * Calculate net income given gross income and income tax
	 * @param grossIncome the gross income
	 * @param incomeTax the income tax
	 * @return the net income
	 */
	public static int calculateNetIncome(int grossIncome, int incomeTax) {
		return grossIncome - incomeTax;
	}
	
	/**
	 * Calculate super income given gross income and super income (from employee object)
	 * @param employee
	 * @param grossIncome
	 * @return
	 */
	public static int calculateSuperIncome(Employee employee, int grossIncome) {
		Double superIncome = grossIncome * employee.getSuperRate();
		
		return (int) Math.round(superIncome);
	}
	
	/**
	 * Dump employee payment reports onto disk
	 * @throws IOException if an output exception occurs
	 */
	public void dumpEmployeePayReportToDisk() throws IOException {
		// If output directory doesn't exist
		File outputDirectory = new File(this.outputDirectoryPath);
		if ( ! outputDirectory.exists()) {
			logger.log(Level.INFO, "Output directory doesn't exist. Creating it...\n");
			new File(this.outputDirectoryPath).mkdirs();
		}
		
		File outputFile = new File(this.outputDirectoryPath + "/output.csv");
		FileWriter fw = new FileWriter(outputFile, false);
		for (EmployeePayReport employeePayReport : this.employeePayReports) {
			fw.write(employeePayReport.toString());
		}
		fw.close();
		
		logger.log(Level.INFO, "Payment report has been generated. Please find it at: " + outputFile.getAbsolutePath());
	}
	
	/**
	 * Print employee payment reports on standard out
	 */
	public void dumpEmployeePayReportToStdout() {
		for (EmployeePayReport employeePayReport : this.employeePayReports) {
			System.out.print(employeePayReport.toString());
		}
	}
	
	/**
	 * Process employee payments
	 * For each employee, get relevant data from the tax table and calculate his/her income tax and
	 * related numbers. Then dump data to screen or disk.
	 * @throws IOException if an output exception occurs
	 */
	public void processEmployeePayments() throws IOException {
		
		// Process Income numbers for all employees we've collected
		if ( this.employeePayReports != null) {
			Employee employee;
			List<Double> taxInfo;
			
			for (EmployeePayReport employeePayReport : this.employeePayReports) {
				
				// Load employee
				employee = employeePayReport.getEmployee();
				logger.info("Processing tax for employee " + employee.getFirstName() + " " 
						+ employee.getLastName() + "\n");
				
				// Load data from tax table
				taxInfo = this.taxTable.getTaxInfo(employee.getSalary());
				// If tax table cannot be loaded, return immediately
				if (taxInfo == null) {
					logger.log(Level.SEVERE, "Could not load data from tax table! Stopping further processing");
					return;
				}
				
				// Calculate required numbers
				int grossIncome = calculateGrossIncome(employee);
				int incomeTax = calculateIncomeTax(employee, taxInfo);
				int netIncome = calculateNetIncome(grossIncome, incomeTax);
				int superIncome = calculateSuperIncome(employee, grossIncome);
				
				employeePayReport.setGrossIncome(grossIncome);
				employeePayReport.setIncomeTax(incomeTax);
				employeePayReport.setNetIncome(netIncome);
				employeePayReport.setSuperIncome(superIncome);
			}
		}
	}
}
