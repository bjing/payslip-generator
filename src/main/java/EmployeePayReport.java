package main.java;


public class EmployeePayReport {
	
	private Employee employee;
	private int grossIncome;
	private int netIncome;
	private int superIncome;
	private int incomeTax;
	private String payPeriod;
	
	public EmployeePayReport(Employee employee, String payPeriod) {
		this.employee = employee;
		this.payPeriod = payPeriod;
		this.grossIncome = 0;
		this.netIncome = 0;
		this.superIncome = 0;
		this.incomeTax = 0;
	}
	
	public Employee getEmployee() {
		return employee;
	}
		
	public void setGrossIncome(int grossIncome) {
		this.grossIncome = grossIncome;
	}
	
	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}
	
	public void setSuperIncome(int superIncome) {
		this.superIncome = superIncome;
	}
	
	public void setIncomeTax(int incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}
	
	public String toString() {
		return this.employee.getFirstName() + "," + this.employee.getLastName() + "," + this.payPeriod +
				"," + this.grossIncome + "," + this.incomeTax + "," + this.netIncome + "," + 
				this.superIncome + "\n";
	}
}
