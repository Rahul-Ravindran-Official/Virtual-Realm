package bl.world.buildings.bank;

import bl.helper.InitializeCop;
import bl.person.Person;
import bl.person.systems.PurseSystem;
import bl.world.buildings._commonUtilities.moduleProtocol.Module;
import bl.world.buildings.bank.modals.OBankTransaction;
import bl.world.buildings.bank.slaves.BankMasterSlave;
import bl.world.buildings.bank.slaves.SavingsAccountSlave;
import bl.world.peripherals.time.ITimeSubscriber;
import bl.world.peripherals.time.Time;

import java.math.BigDecimal;
import java.util.HashMap;

public class BankMaster extends InitializeCop implements ITimeSubscriber, Module {

    public SavingsAccountSlave savingsAccountSlave = SavingsAccountSlave.getInstance();
    private HashMap<String,OBankTransaction> oBankTransactions = new HashMap<>();
    private static String payableName = "Bank_INC";

    ///////////////////////////////////////////////////////////////////////////
    public static BankMaster getInstance() {
        return ourInstance;
    }
    private BankMaster() {}
    private static BankMaster ourInstance = new BankMaster();

    ///////////////////////////////////////////////////////////////////////////

    /*
     * @param = int timeInHoursOfInterestLastPaid, BigDecimal cashInSavingsAccount, HashMap<String,OBankTransaction> oBankTransactions
     */
    @Override
    public void init(Object... args) throws Exception {

        Integer timeInHoursOfInterestLastPaid;
        BigDecimal cashInSavingsAccount;
        HashMap<String,OBankTransaction> oBankTransactions;

        timeInHoursOfInterestLastPaid = (Integer) args[0];
        cashInSavingsAccount = (BigDecimal) args[1];
        oBankTransactions = (HashMap<String,OBankTransaction>) args[2];

        Time.getInstance().subscribeForTime(this);
        this.oBankTransactions = oBankTransactions;
        savingsAccountSlave.init(timeInHoursOfInterestLastPaid,cashInSavingsAccount);
        initialized();
    }

    public void init(int timeInHoursOfInterestLastPaid, BigDecimal cashInSavingsAccount, HashMap<String,OBankTransaction> oBankTransactions) throws Exception {
        Time.getInstance().subscribeForTime(this);
        this.oBankTransactions = oBankTransactions;
        savingsAccountSlave.init(timeInHoursOfInterestLastPaid,cashInSavingsAccount);
        initialized();
    }

    // Receive Time onChange()
    public void receiveTimeOnChange(int timePassedInHours) throws Exception {
        checkIfInitialised();
        savingsAccountSlave.addInterestToSavings(timePassedInHours);
    }

    // Functionality Savings Account
    public boolean depositToSavings(BigDecimal cashToAdd, String bankTransactionId) throws Exception{
        boolean paid = BankMaster.getInstance().verifyBankTransaction(bankTransactionId,cashToAdd);
        if(paid){
            savingsAccountSlave.depositToSavings(cashToAdd);
        }
        return paid;
    }
    public String withdrawFromSavings(BigDecimal cashToWithdraw) throws Exception{
        return payToCustomer(getInstance(),cashToWithdraw,Person.getInstance().getPayableName());
    }
    public String peekSavings() throws Exception {
        return savingsAccountSlave.peekBalance();
    }

    // Functionality => Pay To Services
    public String payForCommodity(PurseSystem ps, BigDecimal cashToDeduct, String entityToTransferTo) throws Exception {
        checkIfInitialised();
        OBankTransaction obt = BankMasterSlave.DeductPurseValueAndPayForCommodityGetBnkTx(ps, cashToDeduct, entityToTransferTo) ;
        oBankTransactions.put(obt.getTransactionId(),obt);
        return obt.getTransactionId();
    }
    public String payToCustomer(BankMaster bm, BigDecimal cashToDeduct, String entityToTransferTo) throws Exception {
        checkIfInitialised();
        OBankTransaction obt = BankMasterSlave.DeductFromBankAndPayCustomer(bm, cashToDeduct, entityToTransferTo) ;
        oBankTransactions.put(obt.getTransactionId(),obt);
        return obt.getTransactionId();
    }
    public boolean verifyBankTransaction(String transactionId, BigDecimal cashToBePaid) throws Exception {
        checkIfInitialised();
        if (oBankTransactions.containsKey(transactionId)) {
            OBankTransaction oBankTransaction = oBankTransactions.get(transactionId);
            if ((oBankTransaction.getCashToTransfer().equals(cashToBePaid)) && !oBankTransaction.isVerified()) {
                oBankTransaction.setVerified(true);
                oBankTransactions.put(transactionId,oBankTransaction);
                return true;
            }
        }
        return false;
    }

    // Utility
    public String getPayableName() {
        return payableName;
    }

}
