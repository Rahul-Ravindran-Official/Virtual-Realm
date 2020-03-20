package bl.world.buildings.realEstate.modals;

import java.math.BigDecimal;

public class OHouse implements Comparable<OHouse>{

    private int hid;
    private String house_name;
    private int houseOccupancyCount;
    private BigDecimal costOfRent;
    private BigDecimal costToPurchase;
    private int unlockableId;
    private BigDecimal cashEarnedWhileRenting;
    private BigDecimal cashEarnedWhenSold;

    public OHouse(int hid, String house_name, int houseOccupancyCount, BigDecimal costOfRent, BigDecimal costToPurchase, int unlockableId, BigDecimal cashEarnedWhileRenting, BigDecimal cashEarnedWhenSold) {
        this.hid = hid;
        this.house_name = house_name;
        this.houseOccupancyCount = houseOccupancyCount;
        this.costOfRent = costOfRent;
        this.costToPurchase = costToPurchase;
        this.unlockableId = unlockableId;
        this.cashEarnedWhileRenting = cashEarnedWhileRenting;
        this.cashEarnedWhenSold = cashEarnedWhenSold;
    }
    public OHouse() {
    }

    public int getHid() {
        return hid;
    }
    public void setHid(int hid) {
        this.hid = hid;
    }
    public String getHouse_name() {
        return house_name;
    }
    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }
    public int getHouseOccupancyCount() {
        return houseOccupancyCount;
    }
    public void setHouseOccupancyCount(int houseOccupancyCount) {
        this.houseOccupancyCount = houseOccupancyCount;
    }
    public BigDecimal getCostOfRent() {
        return costOfRent;
    }
    public void setCostOfRent(BigDecimal costOfRent) {
        this.costOfRent = costOfRent;
    }
    public BigDecimal getCostToPurchase() {
        return costToPurchase;
    }
    public void setCostToPurchase(BigDecimal costToPurchase) {
        this.costToPurchase = costToPurchase;
    }
    public int getUnlockableId() {
        return unlockableId;
    }
    public void setUnlockableId(int unlockableId) {
        this.unlockableId = unlockableId;
    }
    public BigDecimal getCashEarnedWhileRenting() {
        return cashEarnedWhileRenting;
    }
    public void setCashEarnedWhileRenting(BigDecimal cashEarnedWhileRenting) {
        this.cashEarnedWhileRenting = cashEarnedWhileRenting;
    }
    public BigDecimal getCashEarnedWhenSold() {
        return cashEarnedWhenSold;
    }
    public void setCashEarnedWhenSold(BigDecimal cashEarnedWhenSold) {
        this.cashEarnedWhenSold = cashEarnedWhenSold;
    }

    @Override
    public int compareTo(OHouse o) {
        return getCostToPurchase().compareTo(o.getCostToPurchase());
    }
}
