package tests.bl.world.buildings.realEstate;

import bl.game.Config;
import bl.game.Game;
import bl.helper.Objects.PersonWorldConfig;
import bl.person.Person;
import bl.world.World;
import bl.world.buildings.realEstate.modals.OTrackRent;
import bl.world.buildings.realEstate.slaves.RentTrackerSlave;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RealEstateMasterTest {

    static PersonWorldConfig pwc;
    static Person person;
    static World world;



    /*

        +----------------+
        House ID: 1
        House Name: Sack
        House Rental Cost: 0
        +----------------+
        +----------------+
        House ID: 2
        House Name: Hut House
        House Rental Cost: 100
        +----------------+
        +----------------+
        House ID: 3
        House Name: Small House
        House Rental Cost: 500
        +----------------+

     */


    @BeforeClass
    public static void setUp() throws Exception {
        pwc = Game.getInstance().init();
        person = pwc.getPerson();
        world = pwc.getWorld();

        // Adding Cash => 10,000
        addCash(10000);
        System.out.println("Executed Setup!!");

    }



    @Test
    public void checkIfCashIsCorrectlyDeductedOnAssetRented() throws Exception {
        int houseID = 2;
        int days = 10;
        String currentCash = getCashBalance();
        String costToRent = world.realEstateMaster.costOfRealEstateToRent(houseID, days);
        String expectedBalance = subTwoString(currentCash,costToRent);
        world.realEstateMaster.rentRealEstate(2,10);
        assertEquals(expectedBalance, getCashBalance());
    }

    @Test
    public void checkIfRentalPeriodIsCorrectlyAdded() throws Exception {
        int hid = 2;
        int days = 10;
        int daysInHours = days*24;
        world.realEstateMaster.rentRealEstate(hid,days);
        assertEquals(daysInHours, world.realEstateMaster.getRentalTimeRemainingOnRentedRealEstate(hid));
    }

//    @Test
//    public void checkIf(){
//
//    }



    // Helper
    private static void addCash(int cash){
        person.purseSystem.deposit(String.valueOf(cash));
    }
    private static String getCashBalance(){
        return person.purseSystem.peekCashAsString();
    }
    private static String initialCash(){
        return Config.getInstance().getConfiguration("startingPurseCash").getConfigValueAsString();
    }
    private static String addTwoString(String s1, String s2){
        return String.valueOf(Integer.parseInt(s1) + Integer.parseInt(s2));
    }
    private static String subTwoString(String s1, String s2){
        return String.valueOf(Integer.parseInt(s1) - Integer.parseInt(s2));
    }



}