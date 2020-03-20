package bl.app;

import bl.game.Game;
import bl.helper.Objects.PersonWorldConfig;
import bl.person.Person;
import bl.world.World;

public class LogicAppBinder {


    private static LogicAppBinder ourInstance = new LogicAppBinder();
    public static LogicAppBinder getInstance() {
        return ourInstance;
    }
    private LogicAppBinder() {}

    private static PersonWorldConfig pwc = null;

    public Person getPerson(){
        return getPersonWorldConfig().getPerson();
    }

    public World getWorld(){
        return pwc.getWorld();
    }

    private static PersonWorldConfig getPersonWorldConfig(){
        if(pwc == null){
            try {
                pwc = Game.getInstance().init();
            } catch (Exception e) {
                System.out.println("e.getMessage() = " + e.getMessage());
            }
        }
        return pwc;
    }

}
