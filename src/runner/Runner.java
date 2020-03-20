package runner;

public class Runner {

    static UnitTestingCases utc = new UnitTestingCases();

    public static void main(String[] args) throws Exception {
        utc.testFunctionalityCrossPayment();
    }

}
