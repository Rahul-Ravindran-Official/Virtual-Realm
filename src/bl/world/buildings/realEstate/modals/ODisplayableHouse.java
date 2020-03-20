package bl.world.buildings.realEstate.modals;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ODisplayableHouse {

    private int hid;
    private String house_name;
    private int houseOccupancyCount;
    private BigDecimal costOfRent;
    private BigDecimal costToPurchase;
    private boolean bought;
    private BigInteger hoursLeftOfOccupancy;


    public ODisplayableHouse(int hid, String house_name, int houseOccupancyCount, BigDecimal costOfRent, BigDecimal costToPurchase, boolean bought, BigInteger hoursLeftOfOccupancy) {
        this.hid = hid;
        this.house_name = house_name;
        this.houseOccupancyCount = houseOccupancyCount;
        this.costOfRent = costOfRent;
        this.costToPurchase = costToPurchase;
        this.bought = bought;
        this.hoursLeftOfOccupancy = hoursLeftOfOccupancy;
    }
    public ODisplayableHouse() {
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

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public BigInteger getHoursLeftOfOccupancy() {
        return hoursLeftOfOccupancy;
    }

    public void setHoursLeftOfOccupancy(BigInteger hoursLeftOfOccupancy) {
        this.hoursLeftOfOccupancy = hoursLeftOfOccupancy;
    }

}
