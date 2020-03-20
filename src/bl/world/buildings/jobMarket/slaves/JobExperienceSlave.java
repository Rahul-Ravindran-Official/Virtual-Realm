package bl.world.buildings.jobMarket.slaves;

import bl.game.Config;
import bl.person.systems.PurseSystem;
import bl.world.buildings.bank.BankMaster;
import bl.world.buildings.jobMarket.modals.OJob;
import bl.world.buildings.jobMarket.modals.OJobExperienceTrack;

import java.math.BigDecimal;
import java.util.HashMap;

public class JobExperienceSlave {

    // JobID, OJobExperienceTrack
    HashMap<Integer,OJobExperienceTrack> experienceTrackerForJobs = new HashMap<>();

    ///////////////////////////////////////////////////////////////////////////
    public static JobExperienceSlave getInstance() {
        return ourInstance;
    }
    private JobExperienceSlave() {}
    private static JobExperienceSlave ourInstance = new JobExperienceSlave();
    ///////////////////////////////////////////////////////////////////////////

    public void init(HashMap<Integer,OJobExperienceTrack> experienceTrackerForJobs){
        this.experienceTrackerForJobs = experienceTrackerForJobs;
    }
    public OJobExperienceTrack work(OJob job , int numberOfTimes) throws Exception {

        BigDecimal cashEarned = new BigDecimal("0");
        OJobExperienceTrack jobExperienceTrack;

        if(!experienceTrackerForJobs.containsKey(job.getJobID())){
            jobExperienceTrack = new OJobExperienceTrack(
                    Config.getInstance().getConfiguration("clicksForLevel1Experience").getConfigValueAsString(),
                    Float.valueOf(Config.getInstance().getConfiguration("experienceMultiplier").getConfigValueAsString())
            );
        }else{
            jobExperienceTrack = experienceTrackerForJobs.get(job.getJobID());
        }
        for (int i = 0; i < numberOfTimes; i++) {
            float multiplier = jobExperienceTrack.click();
            cashEarned = cashEarned.add(new BigDecimal(job.getCashEarnedPerClick().toString()).multiply(new BigDecimal(String.valueOf(multiplier))));
        }
        experienceTrackerForJobs.put(job.getJobID(),jobExperienceTrack);

        payEmployee(cashEarned);

        return jobExperienceTrack;
    }
    private void payEmployee(BigDecimal cash) {
        PurseSystem.getInstance().deposit(cash.toString());
    }

}
