package test.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.EmployeePayReport;
import main.java.InputParser;

public class TestInputParser {
	private InputParser inputParser = null;
	
	@Before
	public void setUp() {		
		this.inputParser = new InputParser();	
	}
	
	@Test
	public void testExractStringsFromInputFiles() throws IOException {
		String inputDirectoryPath = "src/test/resources/input";
		final File inputDirectory = new File(inputDirectoryPath);
		List<String> employeeRecords = this.inputParser.extractStringsFromInputFiles(inputDirectory);
		
		// Total number of records should be 10
		assertEquals(2, employeeRecords.size());
	}
	
	@Test
	public void testParseEmployeeRecords() {
		// Mock up some records on the fly
		List<String> employeeRecords = new ArrayList<String>();
		employeeRecords.add("David,Rudd,60050,0.09,01 March – 31 March");
		employeeRecords.add("Ryan,Chen,120000,0.1,01 March – 31 March");
		employeeRecords.add("Brian,King,120000,0.1,01 March – 31 March");
		
		// Parse the input records and assert some equal statements
		List<EmployeePayReport> employeePayReports = this.inputParser.parseEmployeeRecords(employeeRecords);
		assertEquals("Rudd", employeePayReports.get(0).getEmployee().getLastName());
		assertEquals("Ryan", employeePayReports.get(1).getEmployee().getFirstName());
		assertEquals(120000, employeePayReports.get(1).getEmployee().getSalary(), 0.02);
	}
}
