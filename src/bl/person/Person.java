package bl.person;

import bl.annotations.Tested;
import bl.person.systems.*;
import bl.person.systems.abstractSystems.EnergySystem;
import bl.person.systems.abstractSystems.HappinessSystem;
import bl.person.systems.abstractSystems.HealthSystem;
import bl.person.systems.abstractSystems.LuckSystem;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.restaurant.RestaurantMaster;
import bl.world.peripherals.time.Time;

public class Person {

    private static String personName = "Rahul Ravindran";
    private static String payableName = "PERSON";

    // External Systems Access
    private Time time = Time.getInstance();

    // Internal Systems
    public EnergySystem energySystem = EnergySystem.getInstance();
    public HealthSystem healthSystem = HealthSystem.getInstance();
    public PurseSystem purseSystem = PurseSystem.getInstance();
    public HappinessSystem happinessSystem = HappinessSystem.getInstance();
    public LuckSystem luckSystem = LuckSystem.getInstance();


    ///////////////////////////////////////////////////////////////////////////
    private static Person ourInstance = new Person();
    public static Person getInstance() {
        return ourInstance;
    }
    private Person() {}
    ///////////////////////////////////////////////////////////////////////////

    public void init(String startingCash, int startingEnergy, int startingHappiness, int startingHealth, int startingLuck){
        purseSystem.init(startingCash);
        energySystem.init(startingEnergy);
        happinessSystem.init(startingHappiness);
        healthSystem.init(startingHealth);
        luckSystem.init(startingLuck);
    }

    // Ageing System
    @Tested
    public boolean agePerson() throws Exception {
        time.incrementTime(2);
        return true;
    }
    @Tested
    public String getDisplayAge() throws Exception {
        return time.getTimeDateMonthYear();
    }

    // Purse System
    @Tested
    public boolean depositToPurse(String cashToDeposit){
        return purseSystem.deposit(cashToDeposit);
    }
    @Tested
    public boolean withdrawFromPurse(String cashToWithdraw){
        return purseSystem.withdraw(cashToWithdraw);
    }
    @Tested
    public String getBalanceFromPurse(){return purseSystem.peekCashAsString();}

    // Energy System
    @Tested
    public boolean gainEnergy(int energyToGain){
        return energySystem.gainEntity(energyToGain);
    }
    @Tested
    public boolean loseEnergy(int energyToLose){
        return energySystem.loseEntity(energyToLose);
    }
    @Tested
    public String getEnergyBalance(){
        return energySystem.peekEntityAsString();
    }
    @Tested
    public boolean gainFullEnergy(){
        return energySystem.fillToMaxEntity();
    }

    // Happiness System
    @Tested
    public boolean gainHappiness(int happinessToGain){
        return happinessSystem.gainEntity(happinessToGain);
    }
    @Tested
    public boolean loseHappiness(int happinessToLose){
        return happinessSystem.loseEntity(happinessToLose);
    }
    @Tested
    public String getHappinessBalance(){
        return happinessSystem.peekEntityAsString();
    }
    @Tested
    public boolean gainFullHappiness(){
        return happinessSystem.fillToMaxEntity();
    }

    // Health System
    @Tested
    public boolean gainHealth(int healthToGain){
        return healthSystem.gainEntity(healthToGain);
    }
    @Tested
    public boolean loseHealth(int healthToLose){
        return healthSystem.loseEntity(healthToLose);
    }
    @Tested
    public String getHealthBalance(){
        return healthSystem.peekEntityAsString();
    }
    @Tested
    public boolean gainFullHealth(){
        return healthSystem.fillToMaxEntity();
    }

    // Auxiliary Methods
    public String getPayableName() {
        return payableName;
    }
    public String getName(){
        return personName;
    }
}