package bl.world.peripherals.time;

public interface ITimeSubscriber {
    public void receiveTimeOnChange(int timePassedInHours) throws Exception;
}
