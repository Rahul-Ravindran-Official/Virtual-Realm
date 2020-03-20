package bl.world.buildings.bank.slaves;

import bl.person.systems.PurseSystem;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.bank.modals.OBankTransaction;

import java.math.BigDecimal;
import java.math.BigDecimal;

public class BankMasterSlave {

    public static OBankTransaction DeductPurseValueAndPayForCommodityGetBnkTx(PurseSystem ps, BigDecimal cashToDeduct, String entityToTransferTo){
        ps.withdraw(cashToDeduct.toString());
        return createTransaction(cashToDeduct,entityToTransferTo);
    }

    public static OBankTransaction DeductFromBankAndPayCustomer(BankMaster bm, BigDecimal cashToDeduct, String entityToTransferTo) throws Exception {
        bm.savingsAccountSlave.withdrawFromSavings(cashToDeduct);
        return createTransaction(cashToDeduct,entityToTransferTo);
    }

    private static OBankTransaction createTransaction(BigDecimal cashToTransfer, String entityToTransferTo){
        OBankTransaction oBankTransaction = new OBankTransaction();
        oBankTransaction.setCashToTransfer(cashToTransfer);
        oBankTransaction.setEntityToTransferTo(entityToTransferTo);
        oBankTransaction.generateTxId();
        return oBankTransaction;
    }

}
