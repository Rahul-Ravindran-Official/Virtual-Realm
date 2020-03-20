package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class EnergySystem extends AbstractSystem {

    private static EnergySystem ourInstance = new EnergySystem();
    public static EnergySystem getInstance() {
        return ourInstance;
    }
    private EnergySystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxEnergy").getConfigValueAsInteger();
    }


}