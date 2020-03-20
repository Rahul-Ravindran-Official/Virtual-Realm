package bl.world.buildings.bank.modals;

import bl.helper.NxMethods;

import java.math.BigDecimal;

public class OBankTransaction {
    private String transactionId;
    private BigDecimal cashToTransfer;
    private String entityToTransferTo;
    private boolean verified;

    public String generateTxId(){
        String txID = NxMethods.getSaltString();
        setTransactionId(txID);
        return txID;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public BigDecimal getCashToTransfer() {
        return cashToTransfer;
    }
    public void setCashToTransfer(BigDecimal cashToTransfer) {
        this.cashToTransfer = cashToTransfer;
    }
    public String getEntityToTransferTo() {
        return entityToTransferTo;
    }
    public void setEntityToTransferTo(String entityToTransferTo) {
        this.entityToTransferTo = entityToTransferTo;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
