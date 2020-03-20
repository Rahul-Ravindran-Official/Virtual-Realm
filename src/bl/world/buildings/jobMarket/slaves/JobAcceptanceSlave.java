package bl.world.buildings.jobMarket.slaves;

import bl.person.systems.UnlockableSystem;
import bl.world.buildings.jobMarket.modals.OJob;

import java.util.ArrayList;
import java.util.HashMap;

public class JobAcceptanceSlave {

    private static JobAcceptanceSlave ourInstance = new JobAcceptanceSlave();
    public static JobAcceptanceSlave getInstance() {
        return ourInstance;
    }
    private JobAcceptanceSlave() {}

    public boolean applyToJoinJob(int jobId, HashMap<Integer,OJob> jobs){
        OJob job = jobs.get(jobId);
        ArrayList<Integer> requirementsIds = job.getRequirementsAsIntegerOfUnlockedIds();
        for (Integer requirementId : requirementsIds) {
            boolean isUnlocked = UnlockableSystem.getInstance().checkIfIdIsUnlocked(requirementId);
            if(!isUnlocked){return false;}
        }
        return true;
    }


}
