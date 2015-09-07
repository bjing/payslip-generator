package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The TaxTable class takes care of storing ATO tax table and makes it easy to look up data 
 * in the tax table when calculating income tax
 * @author brian
 *
 */
public class TaxTable {
	private NavigableMap<Double,List<Double>> taxTable = new TreeMap<Double, List<Double>>();
	private String taxTableFilePath;
	private String csvSplitBy = ",";
	private List<String> taxTableInStrings;
	private static Logger logger = Logger.getLogger("TaxTable");
	
	/**
	 * Class constructor with default location of tax table file
	 */
	public TaxTable() {
		this.taxTableFilePath = "src/main/resources/tax_table.csv";
	}
	
	/**
	 * Class constructor specifying file location where to load tax table
	 * @param taxTableFilePath path of tax table file
	 */
	public TaxTable(String taxTableFilePath) {
		this.taxTableFilePath = taxTableFilePath;
	}
	
	/**
	 * Set taxtable directly
	 * @param taxTable the ATO tax tables in a navigable map
	 */
	public void setTaxTable(NavigableMap<Double,List<Double>> taxTable) {
		this.taxTable = taxTable;
	}
	
	/**
	 * Get tax table directly
	 * @return the ATO tax tables in a navigable map
	 */
	public NavigableMap<Double,List<Double>> getTaxTable() {
		return this.taxTable;
	}
	
	/**
	 * Load tax table from File
	 * @throws IOException if an input error occurs
	 */
	public void loadTaxTableFromFile() throws IOException {
		// Read tax table file from disk
		List<String> taxTableDataStrings = Files.readAllLines(Paths.get(taxTableFilePath), StandardCharsets.UTF_8);
		this.taxTable = parseTaxTableFileData(taxTableDataStrings);
	}
	
	/**
	 * Parse tax table data and store it in a navigable map
	 * @param taxTableData a list of String that contains tax table data read from file
	 * @return tax table data that's parsed 
	 */
	public NavigableMap<Double,List<Double>> parseTaxTableFileData(List<String> taxTableData) {
		NavigableMap<Double,List<Double>> taxTable = new TreeMap<Double, List<Double>>();
		
		// Process each line of the file
		if (taxTableData != null) {
			for (String line: taxTableData) {
				String[] tokens = line.split(csvSplitBy);
		
				try {
				
					double lowerBound = Double.parseDouble(tokens[0]);
					// Store tax info in taxInfo
					List<String> tokensList = new ArrayList<String>(Arrays.asList(tokens));
					tokensList.remove(0);
					List<Double> taxInfo = new ArrayList<Double>();
					for (String s : tokensList) {
						taxInfo.add(Double.parseDouble(s));
					}
					
					taxTable.put(lowerBound, taxInfo);
				} catch (Exception e) {
					logger.log(Level.WARNING, "Tax table row doesn't appear to be correct\n" + "Error message: " 
							+ e.getMessage() + "\n");
					return null;
				}
			}
		}
		
		return taxTable;
	}
	
	/**
	 * Return the relevant info from ATO tax table as per the salary amount given
	 * @param salary employee salary
	 * @return the tax table data corresponding to the employee salary needed to calculate income tax
	 */
	public List<Double> getTaxInfo(Double salary) {
		if (this.taxTable == null) {
			return null;
		} else {
			return this.taxTable.get(taxTable.floorKey(salary));
		}
	}

	/**
	 * String representation of the tab table, retaining the original format from the file where it's extracted.
	 */
	public String toString() {
		String taxTableString = "";
		for (String s : this.taxTableInStrings) {
			taxTableString += s + "\n";
		}
		return taxTableString;
	}
}

