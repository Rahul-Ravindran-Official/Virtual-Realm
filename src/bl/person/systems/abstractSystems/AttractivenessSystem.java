package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class AttractivenessSystem extends AbstractSystem {

    private static AttractivenessSystem ourInstance = new AttractivenessSystem();
    public static AttractivenessSystem getInstance() {
        return ourInstance;
    }
    private AttractivenessSystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxAttractiveness").getConfigValueAsInteger();
    }


}