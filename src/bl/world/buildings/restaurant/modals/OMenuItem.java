package bl.world.buildings.restaurant.modals;

import bl.annotations.Tested;

import java.math.BigDecimal;
import java.util.ArrayList;

@Tested
public class OMenuItem {

    private int itemID;
    private String itemName;
    private BigDecimal itemCost;
    private boolean availability;
    private double probabilityOfPoisoning;
    private int hpDrainFromPoison;
    private ArrayList<Integer> restaurantIdsWhereMade = new ArrayList<>();
    private int energyGain;

    public OMenuItem(int itemID, String itemName, BigDecimal itemCost, boolean availability, double probabilityOfPoisoning, int hpDrainFromPoison, ArrayList<Integer> restaurantIdsWhereMade, int energyGain) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.availability = availability;
        this.probabilityOfPoisoning = probabilityOfPoisoning;
        this.hpDrainFromPoison = hpDrainFromPoison;
        this.restaurantIdsWhereMade = restaurantIdsWhereMade;
        this.energyGain = energyGain;
    }
    public OMenuItem() {
    }

    public void addToRestaurantIdsWhereMade(int restaurantId){
        restaurantIdsWhereMade.add(restaurantId);
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public BigDecimal getItemCost() {
        return itemCost;
    }
    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }
    public boolean isAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public double getProbabilityOfPoisoning() {
        return probabilityOfPoisoning;
    }
    public void setProbabilityOfPoisoning(double probabilityOfPoisoning) {
        this.probabilityOfPoisoning = probabilityOfPoisoning;
    }
    public int getHpDrainFromPoison() {
        return hpDrainFromPoison;
    }
    public void setHpDrainFromPoison(int hpDrainFromPoison) {
        this.hpDrainFromPoison = hpDrainFromPoison;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public ArrayList<Integer> getRestaurantIdsWhereMade() {
        return restaurantIdsWhereMade;
    }
    public void setRestaurantIdsWhereMade(ArrayList<Integer> restaurantIdsWhereMade) {
        this.restaurantIdsWhereMade = restaurantIdsWhereMade;
    }
    public int getEnergyGain() {
        return energyGain;
    }
    public void setEnergyGain(int energyGain) {
        this.energyGain = energyGain;
    }

}