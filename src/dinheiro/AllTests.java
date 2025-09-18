package dinheiro;

import junit.framework.TestSuite;

public class AllTests {
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(AllTests.class);
    }

    public static TestSuite suite() {
        TestSuite result = new TestSuite("TFD tests");
        result.addTestSuite(MoneyTest.class);
//        result.addTestSuite(ExchangeTest.class);
//        result.addTestSuite(IdentityRateTest.class);
        return result;
    }
}
