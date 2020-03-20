package bl.world.buildings.bank.helper;

import java.util.HashMap;

public class Constants {
    // As a percentage
    public static float savingsAccountInterestRate = 1;

    // Key => Integer -> Days to keep in deposit
    // Value => float -> Rate%
    public static HashMap<Integer, Float> fixedDepositInterestRate = new HashMap<Integer, Float>(){{
        put(15,2.0f);
        put(30 * 1,3.0f);
        put(30 * 3,5.0f);
        put(30 * 6,8.0f);
        put(30 * 12,12.0f);
    }};


}
