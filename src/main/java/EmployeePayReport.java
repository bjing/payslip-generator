package main.java;

/**
 * The EmployeePayReport class encapsulates an employee basic as well as his/her tax information 
 * needed in the output report
 * @author brian
 *
 */
public class EmployeePayReport {
	
	private Employee employee;
	private int grossIncome;
	private int netIncome;
	private int superIncome;
	private int incomeTax;
	private String payPeriod;
	
	/**
	 * Default constructor
	 */
	public EmployeePayReport() {
		this.grossIncome = 0;
		this.netIncome = 0;
		this.superIncome = 0;
		this.incomeTax = 0;
	}
	
	/**
	 * Constructor specifying Employee object and pay period string
	 * @param employee an Employee object
	 * @param payPeriod string representing the pay period
	 */
	public EmployeePayReport(Employee employee, String payPeriod) {
		this();
		this.employee = employee;
		this.payPeriod = payPeriod;
	}
	
	public Employee getEmployee() {
		return employee;
	}
		
	public void setGrossIncome(int grossIncome) {
		this.grossIncome = grossIncome;
	}
	
	public int getGrossIncome() {
		return this.grossIncome;
	}
	
	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}
	
	public int getNetIncome() {
		return this.netIncome;
	}
	
	public void setSuperIncome(int superIncome) {
		this.superIncome = superIncome;
	}
	
	public int getSuperIncome() {
		return this.superIncome;
	}
	
	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	public int getIncomeTax() {
		return this.incomeTax;
	}
	
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}
	
	public String getPayPeriod() {
		return this.payPeriod;
	}
	
	/**
	 * String representation of the pay report. This is the format in the pay report output for each employee
	 */
	public String toString() {
		return this.employee.getFirstName() + "," + this.employee.getLastName() + "," + this.payPeriod +
				"," + this.grossIncome + "," + this.incomeTax + "," + this.netIncome + "," + 
				this.superIncome + "\n";
	}
}
