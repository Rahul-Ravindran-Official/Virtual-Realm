package bl.world.buildings.bank.slaves;

import bl.helper.InitializeCop;
import bl.world.buildings.bank.helper.Constants;

import java.math.BigDecimal;

public class SavingsAccountSlave extends InitializeCop {

    private int timePassedInHoursOfInterestLastPaid;
    private BigDecimal cashInSavingsAccount;

    ///////////////////////////////////////////////////////////////////////////
    public static SavingsAccountSlave getInstance() {
        return ourInstance;
    }
    private SavingsAccountSlave() {}
    private static SavingsAccountSlave ourInstance = new SavingsAccountSlave();
    ///////////////////////////////////////////////////////////////////////////

    public void init(int timeInHoursOfInterestLastPaid, BigDecimal cashInSavingsAccount){
        this.timePassedInHoursOfInterestLastPaid = timeInHoursOfInterestLastPaid;
        this.cashInSavingsAccount = cashInSavingsAccount;
        initialized();
    }

    public boolean depositToSavings(BigDecimal cashToAdd) throws Exception {
        checkIfInitialised();
        cashInSavingsAccount = cashInSavingsAccount.add(cashToAdd);
        return true;
    }

    public boolean withdrawFromSavings(BigDecimal cashToWithdraw) throws Exception {
        checkIfInitialised();

        if(cashToWithdraw.compareTo(this.cashInSavingsAccount) == -1){
            this.cashInSavingsAccount = this.cashInSavingsAccount.subtract(cashToWithdraw);
            return true;
        }
        return false;
    }

    public String peekBalance() throws Exception {
        checkIfInitialised();
        return cashInSavingsAccount.toString();
    }

    public boolean addInterestToSavings(int timePassedInHours) throws Exception {
        checkIfInitialised();

        // for first time
        if(timePassedInHoursOfInterestLastPaid == -1){
            timePassedInHoursOfInterestLastPaid = timePassedInHours;
            return false;
        }

        int timeInHoursToPayInterest = Math.abs(timePassedInHours - timePassedInHoursOfInterestLastPaid);
        BigDecimal interestGainedPerHour = interestGainedPerHour();
        cashInSavingsAccount = cashInSavingsAccount.add(interestGainedPerHour.multiply(BigDecimal.valueOf(timeInHoursToPayInterest)));
        return true;
    }

    private BigDecimal interestGainedPerHour(){
        BigDecimal interestGainedPerHour;
        int hoursInAYear = 12*30*24;
        interestGainedPerHour = cashInSavingsAccount.multiply(
                BigDecimal.valueOf(
                        Constants.savingsAccountInterestRate/hoursInAYear
                )
        );
        return interestGainedPerHour;
    }

}