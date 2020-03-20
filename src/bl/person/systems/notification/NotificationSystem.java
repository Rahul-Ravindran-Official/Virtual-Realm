package bl.person.systems.notification;

import bl.helper.InitializeCop;
import com.sun.tools.corba.se.idl.constExpr.Not;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NotificationSystem extends InitializeCop {

    private static NotificationSystem ourInstance = new NotificationSystem();
    public static NotificationSystem getInstance() {
        return ourInstance;
    }
    private NotificationSystem() {}

    private String dateTimeSyncedUntil = "";
    private ArrayList<Notification> notifications = new ArrayList<>();

    public void init(ArrayList<Notification> notifications, String dateTimeSyncedUntil){
        this.notifications = notifications;
        this.dateTimeSyncedUntil = dateTimeSyncedUntil;
        initialized();
    }

    public ArrayList<Notification> getNotifications(){
        return notifications;
    }

    public void pushNotification(){
        // continue from here ...
        // dateTimeSyncedUntil = DateTimeFormatter
    }
}
