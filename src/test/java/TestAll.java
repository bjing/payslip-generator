package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    // package1
    TestInputParser.class,
    TestPayCalculator.class,
    TestTaxTable.class,
    })

public class TestAll {
}
