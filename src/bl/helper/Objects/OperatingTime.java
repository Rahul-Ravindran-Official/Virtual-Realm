package bl.helper.Objects;

public class OperatingTime {
    private int openingTime;
    private int closingTime;
    private String closedMessage;

    public OperatingTime(int openingTime, int closingTime, String closedMessage) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.closedMessage = closedMessage;
    }
    public OperatingTime() {
    }

    public int getOpeningTime() {
        return openingTime;
    }
    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }
    public int getClosingTime() {
        return closingTime;
    }
    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }
    public String getClosedMessage() {
        return closedMessage;
    }
    public void setClosedMessage(String closedMessage) {
        this.closedMessage = closedMessage;
    }

}