# payslip-generator

## What assumptions have I made?
1. If employee data cannot be parsed correctly from the input file, the application skips the particular line(s) and moves on. However, the problematic line(s) will be printed out to stdout.
2. The application assumes employee data size is small. No parallel or asynchronous processing is supported.
3. The application assumes there's only one input file containing employee data.
4. Since I cannot access the link (http://www.ato.gov.au/content/12333.htm) provided in the specification and I'm not quite clear where the actual table is on the ATO site, I used the following table included in the spec. The table is coded in a file called tax_table.csv in the resources folder.

Salary range        |   Tax formula 
------------------  |   -----------------------------------------
0 - $18,200         |   Nil
$18,201 - $37,000   |   19c for each $1 over $18,200
$37,001 - $80,000   |   $3,572 plus 32.5c for each $1 over $37,000
$80,001 - $180,000  |   $17,547 plus 37c for each $1 over $80,000
$180,001 and over   |   $54,547 plus 45c for each $1 over $180,000



## How do I run the application?
### Java version
I've only tested this application with java 8. 

### Input
You can place your own input csv files under payslip-generator/input. The application will try to load all input files under this folder.

### Run the application
All the class files have been compiled already and are placed under "bin" subfolder. So to run the application, you need to check out a local copy of the repo and run main.java.RunPayCalculator within the repo's root folder like the following

```
git clone https://github.com/bjing/payslip-generator.git
cd payslip-generator/
java -cp bin main.java.RunPayCalculator

```

### Output
Output will be generated at payslip-generator/output/output.csv. Please note each time the application runs, the existing output.csv file will be overwritten.



## How do I run all the tests?
Make sure the junit jar is in the class path. then under the root of payslip-generator run: 
```
java -cp bin org.junit.runner.JUnitCore test.java.TestAll
```

For example, on my own GNU/Linux machine, I run the tests like this: 
```
java -cp bin:/usr/share/java/junit4.jar org.junit.runner.JUnitCore test.java.TestAll
```

JUnit produces the following result:
```
JUnit version 4.11
........
Time: 0.023

OK (8 tests)
```

