package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class HealthSystem extends AbstractSystem {

    private static HealthSystem ourInstance = new HealthSystem();
    public static HealthSystem getInstance() {
        return ourInstance;
    }
    private HealthSystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxHealth").getConfigValueAsInteger();
    }


}
