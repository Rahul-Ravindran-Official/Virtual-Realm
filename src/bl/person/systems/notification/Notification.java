package bl.person.systems.notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Notification {
    private String notification;
    private String timeStamp;
}
