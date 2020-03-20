package bl.person.systems.abstractSystems;
import bl.game.Config;
import bl.helper.Constants;
import bl.person.systems.AbstractSystem;

public class SocialSystem extends AbstractSystem {

    private static SocialSystem ourInstance = new SocialSystem();
    public static SocialSystem getInstance() {
        return ourInstance;
    }
    private SocialSystem() {}

    @Override
    public int getMaxEntity() {
        return Config.getInstance().getConfiguration("maxSocial").getConfigValueAsInteger();
    }
}