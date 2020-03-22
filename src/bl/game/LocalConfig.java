package bl.game;

import java.math.BigDecimal;

public enum LocalConfig {

    hoursInADay(24),
    daysForInit(50),
    timeInHoursOfInterestPaidInit(-1),
    initialCashInSavingsAccount(new BigDecimal("0")),
    defaultAgeingTimeInHours(2);

    private Object value;

    LocalConfig(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
