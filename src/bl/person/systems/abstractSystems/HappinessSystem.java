package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class HappinessSystem extends AbstractSystem {

    private static HappinessSystem ourInstance = new HappinessSystem();
    public static HappinessSystem getInstance() {
        return ourInstance;
    }
    private HappinessSystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxHappiness").getConfigValueAsInteger();
    }


}
