package bl.game;

import bl.helper.Objects.PersonWorldConfig;
import bl.person.Person;
import bl.world.World;

import java.math.BigDecimal;

public class Game {

    private boolean isInitialized;

    ///////////////////////////////////////////////////////////////////////////
    private static Game ourInstance = new Game();
    public static Game getInstance() {
        return ourInstance;
    }
    private Game() {}
    ///////////////////////////////////////////////////////////////////////////

    public PersonWorldConfig init() throws Exception {

        // Getting configurations
        Config config = Config.getInstance();
        config.init();

        // Getting World
        World world = World.getInstance();

        int hoursInADayForInit = 24;
        int daysForInit = 50;
        int timePassedInHoursForInit = hoursInADayForInit * daysForInit;
        int timeInHoursOfInterestPaid = -1;
        BigDecimal cashInSavingsAccount = new BigDecimal("0");

        world.init(
                timePassedInHoursForInit,
                timeInHoursOfInterestPaid,
                cashInSavingsAccount
        );


        // Getting Objects of Interest
        Person person = Person.getInstance();
        person.init(
                config.getConfiguration("startingPurseCash").getConfigValueAsString(),
                config.getConfiguration("startingEnergy").getConfigValueAsInteger(),
                config.getConfiguration("startingHappiness").getConfigValueAsInteger(),
                config.getConfiguration("startingHealth").getConfigValueAsInteger(),
                config.getConfiguration("startingLuck").getConfigValueAsInteger()
        );


        return new PersonWorldConfig(person,world,config);
    }

}
