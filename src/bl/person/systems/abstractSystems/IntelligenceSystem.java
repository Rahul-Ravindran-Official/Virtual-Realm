package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class IntelligenceSystem extends AbstractSystem {

    private static IntelligenceSystem ourInstance = new IntelligenceSystem();
    public static IntelligenceSystem getInstance() {
        return ourInstance;
    }
    private IntelligenceSystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxIntelligence").getConfigValueAsInteger();
    }


}