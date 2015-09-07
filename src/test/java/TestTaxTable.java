package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.TaxTable;

import org.junit.Before;
import org.junit.Test;


public class TestTaxTable {

	TaxTable taxTable;
	
	@Before
	public void setUp() {
		this.taxTable = new TaxTable();
	}
	
	/**
	 * Test TaxTable correctly parses tax data read from file (or in this case, manually mocked up)
	 */
	@Test
	public void testParseTaxTableFileData() {
		// Mock up a tax Table
		List<String> taxTableInStrings = new ArrayList<String>();
		taxTableInStrings.add("0,18200,0,0,0");
		taxTableInStrings.add("18201,37000,0,0.19,18200");
		taxTableInStrings.add("80001,180000,17547,0.37,80000");
		this.taxTable.setTaxTable(this.taxTable.parseTaxTableFileData(taxTableInStrings));
		
		// Test whether the correct values have been inserted into the tex table
		List<Double> expectedResult = Arrays.asList(37000.00,0.00,0.19,18200.00);
		assertEquals(expectedResult, this.taxTable.getTaxTable().get(18201.00));
	}
	
	/**
	 * Test TaxTable returning the correct tax info given a salary amount
	 */
	@Test
	public void testGetTaxInfo() {
		// Mock up a tax Table
		List<String> taxTableInStrings = new ArrayList<String>();
		taxTableInStrings.add("0,18200,0,0,0");
		taxTableInStrings.add("18201,37000,0,0.19,18200");
		taxTableInStrings.add("80001,180000,17547,0.37,80000");
		this.taxTable.setTaxTable(this.taxTable.parseTaxTableFileData(taxTableInStrings));
		
		List<Double> expectedResult = Arrays.asList(37000.00,0.00,0.19,18200.00);
		assertEquals(expectedResult, this.taxTable.getTaxInfo(80000.00));
		
		List<Double> anotherExpectedResult = Arrays.asList(180000.00,17547.00,0.37,80000.00);
		assertEquals(anotherExpectedResult, this.taxTable.getTaxInfo(180000.00));
	}
}
