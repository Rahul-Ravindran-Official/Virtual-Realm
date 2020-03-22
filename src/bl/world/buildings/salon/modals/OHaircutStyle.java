package bl.world.buildings.salon.modals;

import java.math.BigInteger;

public class OHaircutStyle {

    int hairstyleId;
    String haircutStyleName;
    int increaseInAttractiveness;
    BigInteger costForHaircut;
    String base64HaircutImage;


    public OHaircutStyle(int hairstyleId, String haircutStyleName, int increaseInAttractiveness, BigInteger costForHaircut, String base64HaircutImage) {
        this.hairstyleId = hairstyleId;
        this.haircutStyleName = haircutStyleName;
        this.increaseInAttractiveness = increaseInAttractiveness;
        this.costForHaircut = costForHaircut;
        this.base64HaircutImage = base64HaircutImage;
    }

    public OHaircutStyle() {}

    public String getHaircutStyleName() {
        return haircutStyleName;
    }

    public void setHaircutStyleName(String haircutStyleName) {
        this.haircutStyleName = haircutStyleName;
    }

    public int getIncreaseInAttractiveness() {
        return increaseInAttractiveness;
    }

    public void setIncreaseInAttractiveness(int increaseInAttractiveness) {
        this.increaseInAttractiveness = increaseInAttractiveness;
    }

    public BigInteger getCostForHaircut() {
        return costForHaircut;
    }

    public void setCostForHaircut(BigInteger costForHaircut) {
        this.costForHaircut = costForHaircut;
    }

    public String getBase64HaircutImage() {
        return base64HaircutImage;
    }

    public void setBase64HaircutImage(String base64HaircutImage) {
        this.base64HaircutImage = base64HaircutImage;
    }

    public int getHairstyleId() {
        return hairstyleId;
    }

    public void setHairstyleId(int hairstyleId) {
        this.hairstyleId = hairstyleId;
    }

}
