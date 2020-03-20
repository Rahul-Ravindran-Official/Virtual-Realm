package bl.world.peripherals.time;

import bl.helper.InitializeCop;
import bl.world.peripherals.time.helper.TimeConstants;

import java.util.ArrayList;

public class Time extends InitializeCop {

    ArrayList<ITimeSubscriber> timeSubscribers = new ArrayList<>();
    private int timePassedInHours;

    ///////////////////////////////////////////////////////////////////////////
    private Time() {}
    private static Time ourInstance = new Time();
    public static Time getInstance() {
        return ourInstance;
    }
    ///////////////////////////////////////////////////////////////////////////

    public void init(int timePassedInHours){
        this.timePassedInHours = timePassedInHours;
        initialized();
    }

    public String getTimeDateMonthYear() throws Exception {
        checkIfInitialised();
        int currentAgeInHours = timePassedInHours + TimeConstants.startTime;
        int hours = currentAgeInHours % 24;
        int days = (((currentAgeInHours) - hours)/24) % 365;
        int years = (currentAgeInHours - (hours + days*24)) /(365*24) ;
        return String.valueOf(years) + " Years " + String.valueOf(days) + " Days " + String.valueOf(hours) + " Hours";
    }
    public boolean incrementTime(int hours) throws Exception {
        checkIfInitialised();
        for (int i = 0; i < hours; i++) {
            timePassedInHours++;
        }
        BroadcastTime();
        return true;
    }
    public int getTimePassedInHours() throws Exception {
        checkIfInitialised();
        return this.timePassedInHours;
    }
    public void subscribeForTime(ITimeSubscriber timeSubscriber) throws Exception {
        checkIfInitialised();
        this.timeSubscribers.add(timeSubscriber);
    }

    // Add all subscribers to time here
    private void BroadcastTime() throws Exception {
        for (ITimeSubscriber timeSubscriber : timeSubscribers) {
            timeSubscriber.receiveTimeOnChange(timePassedInHours);
        }
    }

}