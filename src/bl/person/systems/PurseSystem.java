package bl.person.systems;

import bl.annotations.Tested;

import java.math.BigDecimal;

public class PurseSystem {

    private BigDecimal cashInPurse;

    ///////////////////////////////////////////////////////////////////////////
    private static PurseSystem ourInstance = new PurseSystem();
    public static PurseSystem getInstance() {
        return ourInstance;
    }
    private PurseSystem() {}
    ///////////////////////////////////////////////////////////////////////////

    @Tested
    public void init(String sInitializeCash){
        this.cashInPurse = new BigDecimal(sInitializeCash);
    }

    @Tested
    public boolean deposit(String sCashToDeposit){
        BigDecimal cashToDeposit = new BigDecimal(sCashToDeposit);
        this.cashInPurse = this.cashInPurse.add(cashToDeposit);
        return true;
    }

    @Tested
    public boolean withdraw(String sCashToWithdraw){
        BigDecimal cashToWithdraw = new BigDecimal(sCashToWithdraw);
        if(cashToWithdraw.compareTo(this.cashInPurse) <= 0){
            this.cashInPurse = this.cashInPurse.subtract(cashToWithdraw);
            return true;
        }
        return false;
    }

    @Tested
    public BigDecimal peekCashAsBigDecimal(){return this.cashInPurse;}

    @Tested
    public String peekCashAsString(){return String.valueOf(this.cashInPurse);}

    public boolean canAffordCash(BigDecimal cost){
        return peekCashAsBigDecimal().compareTo(cost) == 1;
    }

    public void depleteAllCash(){
        cashInPurse = new BigDecimal("0");
    }


}
