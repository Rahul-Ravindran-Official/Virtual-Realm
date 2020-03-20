package bl.world.buildings.restaurant.modals;

import java.math.BigDecimal;

/**
 * This object helps deliver the energy gained from a dish and its poison if any
 */
public class OConsumableOutput {
    int energy;
    int hpDrainedFromPoison;
    boolean isPoisoned;
    BigDecimal itemCost;
    String itemName;

    public OConsumableOutput(int energy, int hpDrainedFromPoison, boolean isPoisoned, BigDecimal itemCost, String itemName) {
        this.energy = energy;
        this.hpDrainedFromPoison = hpDrainedFromPoison;
        this.isPoisoned = isPoisoned;
        this.itemCost = itemCost;
        this.itemName = itemName;
    }
    public OConsumableOutput() {
    }


    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getHpDrainedFromPoison() {
        return hpDrainedFromPoison;
    }
    public void setHpDrainedFromPoison(int hpDrainedFromPoison) {
        this.hpDrainedFromPoison = hpDrainedFromPoison;
    }
    public boolean isPoisoned() {
        return isPoisoned;
    }
    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }
    public BigDecimal getItemCost() {
        return itemCost;
    }
    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}