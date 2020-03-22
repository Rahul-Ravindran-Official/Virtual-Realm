package bl.helper.Objects;

import java.util.concurrent.ThreadLocalRandom;

public class ProbabilityOfExecuteAndChooseFromRandomRangeOfValues {
    private float probabilityOfExecute;
    private int rangeLow;
    private int rangeHigh;

    public ProbabilityOfExecuteAndChooseFromRandomRangeOfValues(float probabilityOfExecute, int rangeLow, int rangeHigh) {
        this.probabilityOfExecute = probabilityOfExecute;
        this.rangeLow = rangeLow;
        this.rangeHigh = rangeHigh;
    }

    public int getValueConsideringProbability(){
        if (getBinaryFromProbability()){
            return ThreadLocalRandom.current().nextInt(rangeLow, rangeHigh +1);
        }
        return 0;
    }

    private boolean getBinaryFromProbability() {
        int getRandomNumber = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
        int getUpgradedNumber = Integer.parseInt(String.valueOf(Math.round(this.probabilityOfExecute * 100)));
        if (getRandomNumber <= getUpgradedNumber) {
            return true;
        }
        return false;
    }

}