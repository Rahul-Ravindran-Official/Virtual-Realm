package runner;

import bl.game.Game;
import bl.annotations.Tested;
import bl.annotations.UnitTesting;
import bl.helper.Objects.Coordinate;
import bl.helper.Objects.PersonWorldConfig;
import bl.person.Person;
import bl.person.systems.PurseSystem;
import bl.person.systems.abstractSystems.EnergySystem;
import bl.world.World;
import bl.world.buildings._commonUtilities.modals.OBuildingInfo;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.jobMarket.modals.OJob;
import bl.world.buildings.realEstate.modals.ODisplayableHouse;
import bl.world.buildings.realEstate.modals.OHouse;
import bl.world.buildings.realEstate.modals.OTrackRent;
import bl.world.buildings.realEstate.slaves.RentTrackerSlave;
import bl.world.buildings.restaurant.RestaurantMaster;
import bl.world.buildings.restaurant.modals.ODisplayableRestaurant;
import bl.world.buildings.restaurant.modals.OMenu;
import bl.world.buildings.restaurant.modals.OMenuItem;
import bl.world.buildings.restaurant.modals.OWaiter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UnitTestingCases {

    @UnitTesting @Tested
    public void testFunctionalityAgeing() throws Exception {
        Person p = Person.getInstance();
        p.agePerson();
        System.out.println(p.getDisplayAge());
    }

    @UnitTesting @Tested
    public void testFunctionalityPurseSystem() throws Exception {

        PersonWorldConfig pw = Game.getInstance().init();
        Person p = pw.getPerson();
        World world = pw.getWorld();

        System.out.println(p.getBalanceFromPurse());

        p.depositToPurse("1000");
        System.out.println(p.getBalanceFromPurse());

        p.depositToPurse("8000");
        System.out.println(p.getBalanceFromPurse());

        p.withdrawFromPurse("1000");
        System.out.println(p.getBalanceFromPurse());

        p.withdrawFromPurse("10987887000");
        System.out.println(p.getBalanceFromPurse());

    }

    @UnitTesting @Tested
    public void testFunctionalityEnergySystem() throws Exception {

        PersonWorldConfig pw = Game.getInstance().init();
        Person p = pw.getPerson();
        World world = pw.getWorld();

        p.loseEnergy(100);
        System.out.println(p.getEnergyBalance());

        p.loseEnergy(100);
        System.out.println(p.getEnergyBalance());

        p.loseEnergy(1000000);
        System.out.println(p.getEnergyBalance());

        p.gainFullEnergy();
        System.out.println(p.getEnergyBalance());

        p.gainEnergy(1000000);
        System.out.println(p.getEnergyBalance());


    }

    @UnitTesting @Tested
    public void testFunctionalityHappinessSystem() throws Exception {

        PersonWorldConfig pw = Game.getInstance().init();
        Person p = pw.getPerson();
        World world = pw.getWorld();

        p.loseHappiness(100);
        System.out.println(p.getHappinessBalance());

        p.loseHappiness(100);
        System.out.println(p.getHappinessBalance());

        p.loseHappiness(1000000);
        System.out.println(p.getHappinessBalance());


        p.gainFullHappiness();
        System.out.println(p.getHappinessBalance());


        p.gainHappiness(1000000);
        System.out.println(p.getHappinessBalance());


    }

    @UnitTesting @Tested
    public void testFunctionalityHealthSystem() throws Exception {

        PersonWorldConfig pw = Game.getInstance().init();
        Person p = pw.getPerson();
        World world = pw.getWorld();

        p.loseHealth(100);
        System.out.println(p.getHealthBalance());

        p.loseHealth(100);
        System.out.println(p.getHealthBalance());

        p.loseHealth(1000000);
        System.out.println(p.getHealthBalance());


        p.gainFullHealth();
        System.out.println(p.getHealthBalance());


        p.gainHealth(1000000);
        System.out.println(p.getHealthBalance());


    }

    @UnitTesting @Tested
    public void testFunctionalityGetDistanceToCoord(){
        Coordinate coordinate = new Coordinate(2,5);
        Coordinate secondCoordinate = new Coordinate(0,1);
        System.out.println(coordinate.getDistanceToCoordinate(secondCoordinate));
    }

    @UnitTesting @Tested
    public void testFunctionalityRestaurant() throws Exception {

        RestaurantMaster rm = RestaurantMaster.getInstance();
        BankMaster bm = BankMaster.getInstance();
        PurseSystem ps = PurseSystem.getInstance();
        EnergySystem energySystem = EnergySystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Alternate Life");
        System.out.println("How much cash do you want?");
        int cashToEnter = scanner.nextInt();

        rm.init();
        ps.init(String.valueOf(cashToEnter));

        ArrayList<OBuildingInfo> restaurantsAround = rm.getRestaurantsAround();
        System.out.println("You are hungry ... which restaurant (enter id) do you want to enter ?");
        ArrayList<Integer> restaurantIdsAvailable = new ArrayList<>();
        for (OBuildingInfo oBuildingInfo : restaurantsAround) {
            System.out.println("Restaurant ID: " + oBuildingInfo.getId());
            System.out.println("Restaurant Name: " + oBuildingInfo.getBuildingName());
            System.out.println("Coordinate X: " + oBuildingInfo.getCoordinates().getX() + "~ Coordinate Y: " + oBuildingInfo.getCoordinates().getY());
            restaurantIdsAvailable.add(oBuildingInfo.getId());
        }

        int ridToEnter;
        boolean round1 = true;
        do {
            if(!round1)
                System.out.println("There is no such Restaurant. Try again");
                round1 = false;
            ridToEnter = scanner.nextInt();
        } while (!restaurantIdsAvailable.contains(ridToEnter));

        ODisplayableRestaurant od = rm.enterRestaurant(ridToEnter);

        System.out.println("Welcome to " + od.getRestaurantName());
        System.out.println("Hello Rahul, I am " + od.getWaiter().getName() + " and I'll be your waiter for the day!");

        OMenu getMenu = od.getMenu();
        ArrayList<OMenuItem> menuItems = getMenu.getMenuItems();


        ArrayList<Integer> menuItemIdsAvailable = new ArrayList<>();
        System.out.println("Here is the menu:");
        int position = 0;
        for (OMenuItem menuItem : menuItems) {
            System.out.println("Item: " + position);
            System.out.println("Item Name: " + menuItem.getItemName());
            System.out.println("Cost: " + menuItem.getItemCost());
            System.out.println("Energy Gained: " + menuItem.getEnergyGain());
            menuItemIdsAvailable.add(position++);
        }

        int positionToOrder;
        round1 = true;
        do {
            if(!round1)
                System.out.println("Sorry, We don't have that item!!");
            round1 = false;
            positionToOrder = scanner.nextInt();
        } while (!menuItemIdsAvailable.contains(positionToOrder));

        BigDecimal itemCost = new BigDecimal(String.valueOf(menuItems.get(positionToOrder).getItemCost()));
        System.out.println("Good Choice. That will cost you ~ " + itemCost.toString());
        System.out.println("Processing your request to " + rm.getPayableName() + " ...");
        String txID = bm.payForCommodity(PurseSystem.getInstance(),itemCost,rm.getPayableName());
        int energyGained = rm.consumeDishForEnergy(positionToOrder,txID,energySystem).getEnergy();
        System.out.println("Great, You received " + energyGained + " HP");
        System.out.println("Your bank balance has reduced to $" + ps.peekCashAsString());
    }

    @UnitTesting @Tested
    public void testFunctionalityBanking() throws Exception {

        PersonWorldConfig pw = Game.getInstance().init();
        Person person = pw.getPerson();
        World world = pw.getWorld();

        person.depositToPurse("100000");
        String txID = world.bankMaster.payForCommodity(person.purseSystem,new BigDecimal("100"),world.bankMaster.getPayableName());
        world.bankMaster.depositToSavings(new BigDecimal("100"),txID);
        System.out.println("Savings account Balance : " + world.bankMaster.peekSavings());
        System.out.println("Purse Balance : " + person.purseSystem.peekCashAsString());

    }

    @UnitTesting @Tested
    public void testFunctionalityJobs() throws Exception {

        PersonWorldConfig pwc = Game.getInstance().init();
        Person person = pwc.getPerson();
        World world = pwc.getWorld();

        ArrayList<OJob> jobs = world.jobMarketMaster.listAllJobs();

        for (OJob job : jobs) {
            System.out.println();
            System.out.println("JobID:" + job.getJobID());
            System.out.println("Job Name:" + job.getJobName());
            System.out.println("Job Pay:" + job.getCashEarnedPerClick());
            System.out.println("Job Requirements:" + job.getRequirementsAsIntegerOfUnlockedIds());
            System.out.println(" ------- ");
            System.out.println();
        }

        boolean isSuccess = world.jobMarketMaster.apply(4);
        world.jobMarketMaster.work(1);
        System.out.println(isSuccess);
    }

    @UnitTesting
    public void testFunctionalityTipping() throws Exception {

        PersonWorldConfig pwc = Game.getInstance().init();
        Person person = pwc.getPerson();
        World world = pwc.getWorld();

        person.purseSystem.deposit("10000");
        person.luckSystem.loseEntity(200);
        OWaiter myWaiter = world.restaurantMaster.enterRestaurant(1).getWaiter();
        System.out.println("My Luck: " + myWaiter.getLuck());
        System.out.println("Luck Gained: " + world.restaurantMaster.tipWaiter(person.purseSystem, world.bankMaster, person.luckSystem, 0.2f));
        System.out.println("Current Luck: " + person.luckSystem.peekEntityAsString());
    }

    @UnitTesting
    public void testFunctionalityRealEstate1() throws Exception {

        PersonWorldConfig pwc = Game.getInstance().init();
        Person person = pwc.getPerson();
        World world = pwc.getWorld();

        person.purseSystem.deposit("10000");
        for (ODisplayableHouse realEstate : world.realEstateMaster.getAllRealEstates()) {
            System.out.println("____");
            System.out.println("realEstate.getHid() = " + realEstate.getHid());
            System.out.println("realEstate.getHouse_name() = " + realEstate.getHouse_name());
            System.out.println("realEstate.getCostToPurchase() = " + realEstate.getCostToPurchase());
            System.out.println("realEstate.getCostOfRent() = " + realEstate.getCostOfRent());
            System.out.println("____");
        }
    }

    @UnitTesting
    public void testFunctionalityRealEstate2() throws Exception {

        PersonWorldConfig pwc = Game.getInstance().init();
        Person person = pwc.getPerson();
        World world = pwc.getWorld();

        for (ODisplayableHouse allRealEstate : world.realEstateMaster.getAllRealEstates()) {
            System.out.println("+----------------+");
            System.out.println("House ID: " + allRealEstate.getHid());
            System.out.println("House Name: " + allRealEstate.getHouse_name());
            System.out.println("House Rental Cost: " + allRealEstate.getCostOfRent());
            System.out.println("+----------------+");
        }

        person.purseSystem.deposit("10000");
        System.out.println("person.purseSystem.peekCashAsString() = " + person.purseSystem.peekCashAsString());
        world.realEstateMaster.rentRealEstate(2,100);
        HashMap<Integer, OTrackRent > trackRents= RentTrackerSlave.getInstance().trackRents;
        System.out.println("Cash Remaining => " + person.purseSystem.peekCashAsString());
        world.jobMarketMaster.apply(1);
        world.jobMarketMaster.work(100);
    }


    @UnitTesting @Tested
    public void testFunctionalityCrossPayment() throws Exception {

        PersonWorldConfig pwc = Game.getInstance().init();
        Person person = pwc.getPerson();
        World world = pwc.getWorld();

        person.purseSystem.deposit("10000");
        String transactionId = world.bankMaster.payForCommodity(person.purseSystem, new BigDecimal("1000"), world.realEstateMaster.getPayableName());
        boolean isValid = world.bankMaster.verifyBankTransaction(transactionId, new BigDecimal(1000));

    }

}
