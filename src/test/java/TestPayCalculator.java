package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.Employee;
import main.java.EmployeePayReport;
import main.java.PayCalculator;
import main.java.TaxTable;

import org.junit.Before;
import org.junit.Test;


public class TestPayCalculator {

	private PayCalculator payCalculator;
	
	@Before
	public void setUp() throws Exception {
		// Mock up a tax Table
		TaxTable taxTable = new TaxTable();
		List<String> taxTableInStrings = new ArrayList<String>();
		taxTableInStrings.add("0,18200,0,0,0");
		taxTableInStrings.add("18201,37000,0,0.19,18200");
		taxTableInStrings.add("80001,180000,17547,0.37,80000");
		taxTable.setTaxTable(taxTable.parseTaxTableFileData(taxTableInStrings));
		
		// Mock up employee pay reports
		List<EmployeePayReport> employeePayReports = new ArrayList<EmployeePayReport>();
		String payPeriod = "01 March – 31 March";
		employeePayReports.add(new EmployeePayReport(new Employee("Bruce", "Weyne", 66000, 0.09), payPeriod));
		employeePayReports.add(new EmployeePayReport(new Employee("David", "Ho", 66000, 0.09), payPeriod));
		employeePayReports.add(new EmployeePayReport(new Employee("Liam", "Hamilton", 66000, 0.09), payPeriod));
		
		this.payCalculator = new PayCalculator(employeePayReports, taxTable);
	}

	/**
	 * Test if gross income can be correctly calculated
	 */
	@Test
	public void testCalculateGrossIncome() {
		Employee employee = new Employee("Ivy", "Hamilton", 60050, 0.09);
		
		int grossMonthlyIncome = PayCalculator.calculateGrossIncome(employee);
		assertEquals(5004, grossMonthlyIncome);
	}
	
	/**
	 * Test if income tax can be correctly calculated
	 */
	@Test
	public void testCalculateIncomeTax() {
		List<Double> taxInfo = Arrays.asList(80000.00,3572.00,0.325,37000.00);
		Employee employee = new Employee("Bruce", "Weyne", 60050, 0.09);
		
		int incomeTax = PayCalculator.calculateIncomeTax(employee, taxInfo);
		
		// incomeTax = (taxInfo.get(2) + (employee.getSalary() - taxInfo.get(0)) * taxInfo.get(3)) / 12;
		// 3572.00 + (66000) * 0.325 / 12
		// income tax = (3,572 + (60,050 - 37,000) x 0.325) / 12  = 921.9375 (round up) = 922
		assertEquals(922, incomeTax);
	}
	
	/**
	 * Test if net income can be correctly calculated
	 */
	@Test
	public void testCalculateNetIncome() {
		int grossIncome = 90000;
		int incomeTax = 25000;
		
		assertEquals(65000, PayCalculator.calculateNetIncome(grossIncome, incomeTax));
	}
	
	/**
	 * Test if super income can be correctly calculated
	 */
	@Test
	public void testCalculateSuperIncome() {
		Employee employee = new Employee("Bruce", "Weyne", 60050, 0.09);
		int grossIncome = 5004;
		
		assertEquals(450, PayCalculator.calculateSuperIncome(employee, grossIncome));
	}
}
