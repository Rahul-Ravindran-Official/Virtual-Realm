package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class LuckSystem extends AbstractSystem {

    private static LuckSystem ourInstance = new LuckSystem();
    public static LuckSystem getInstance() {
        return ourInstance;
    }
    private LuckSystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxLuck").getConfigValueAsInteger();
    }


}
