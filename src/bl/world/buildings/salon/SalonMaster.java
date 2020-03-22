package bl.world.buildings.salon;

import bl.helper.InitializeCop;
import bl.person.systems.PurseSystem;
import bl.person.systems.abstractSystems.AttractivenessSystem;
import bl.world.buildings.salon.initializers.InitializeSalon;
import bl.world.buildings.salon.modals.OHaircutStyle;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SalonMaster extends InitializeCop {

    private static SalonMaster ourInstance = new SalonMaster();
    public static SalonMaster getInstance() {
        return ourInstance;
    }
    private SalonMaster() {}

    private ArrayList<OHaircutStyle> hairStyle = new ArrayList<>();

    public void init() throws ParseException {
        hairStyle = InitializeSalon.initializeSalon();
        initialized();
    }

    public ArrayList<OHaircutStyle> getListOfHairstyles(){
        return hairStyle;
    }

    public boolean buyAHaircut(OHaircutStyle haircutStyle, PurseSystem purseSystem, AttractivenessSystem attractivenessSystem){
        boolean can = purseSystem.canAffordCash(new BigDecimal(haircutStyle.getCostForHaircut()));
        if(can){
            purseSystem.withdraw(new BigDecimal(haircutStyle.getCostForHaircut()).toString());
            attractivenessSystem.gainEntity(haircutStyle.getIncreaseInAttractiveness());
            return true;
        }
        return false;
    }


}