package main.java;


/**
 * The employee class that contains basic employee info
 * @author brian
 *
 */
public class Employee {
    private String firstName;
    private String lastName;
    private double salary;
    private double superRate;
    
    /**
     * Class constructor
     * @param firstName employee's first name
     * @param lastName employee's first name
     * @param salary employee's annual salary
     * @param superRate employee's super rate
     */
    public Employee(String firstName, String lastName, int salary, double superRate) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.salary = salary;
    	this.superRate = superRate;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public double getSalary() {
    	return salary;
    }
    
    public double getSuperRate() {
    	return superRate;
    }
    
    /**
     * String representation of employee's basic info in CSV format
     */
    public String toString() {
    	return firstName + "," + lastName + "," + salary + "," + superRate;
    }
}

