package bl.world.buildings.jobMarket.modals;

import java.math.BigInteger;

public class OJobExperienceTrack {

    private int totalClicks;
    private int experienceLevel;
    private BigInteger clicksRequiredToProgressToNextExperienceLevel;
    private BigInteger clicksDoneThisExperienceLevel = new BigInteger("0");
    private float multiplierConstant;
    private float currentMultiplier = 1;

    public OJobExperienceTrack(String clicksRequiredToProgressToFirstExperienceLevel, float multiplierConstant) {
        this.clicksRequiredToProgressToNextExperienceLevel = new BigInteger(clicksRequiredToProgressToFirstExperienceLevel);
        this.multiplierConstant = multiplierConstant;

    }

    public float click(){
        this.totalClicks++;
        BigInteger clicksRequired = clicksDoneThisExperienceLevel.add(new BigInteger("1"));
        if(clicksRequiredToProgressToNextExperienceLevel.equals(clicksRequired)){
            clicksDoneThisExperienceLevel = new BigInteger("0");
            clicksRequiredToProgressToNextExperienceLevel = clicksRequiredToProgressToNextExperienceLevel.multiply(new BigInteger("2"));
            experienceLevel++;
            currentMultiplier *= multiplierConstant;
        }else{
            clicksDoneThisExperienceLevel = clicksDoneThisExperienceLevel.add(new BigInteger("1"));
        }
        return this.currentMultiplier;
    }

    public int getTotalClicks() {
        return totalClicks;
    }
    public int getExperienceLevel() {
        return experienceLevel;
    }
    public BigInteger getClicksRequiredToProgressToNextExperienceLevel() {
        return clicksRequiredToProgressToNextExperienceLevel;
    }
    public BigInteger getClicksDoneThisExperienceLevel() {
        return clicksDoneThisExperienceLevel;
    }
    public float getMultiplierConstant() {
        return multiplierConstant;
    }
    public float getCurrentMultiplier() {
        return currentMultiplier;
    }
}