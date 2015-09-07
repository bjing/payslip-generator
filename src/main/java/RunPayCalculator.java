package main.java;

import java.io.IOException;

/**
 * This is the wrapper that runs the whole program
 * @author brian
 *
 */
public class RunPayCalculator {
	public static void main(String[] args) throws IOException {
		// Create a PayCalculator object
		PayCalculator payCalculator = new PayCalculator();
		
		// Run employee payment report
		payCalculator.processEmployeePayments();
		
		// Dump reports to disk and/or stdout
		payCalculator.dumpEmployeePayReportToDisk();
		//payCalculator.dumpEmployeePayReportToStdout();
	}
}
