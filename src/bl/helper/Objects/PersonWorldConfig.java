package bl.helper.Objects;

import bl.game.Config;
import bl.person.Person;
import bl.world.World;

public class PersonWorldConfig {

    private Person person;
    private World world;
    private Config config;

    public PersonWorldConfig(Person person, World world, Config config) {
        this.person = person;
        this.world = world;
        this.config = config;
    }

    public Person getPerson() {
        return person;
    }
    public World getWorld() {
        return world;
    }
    public Config getConfig() {
        return config;
    }

}
