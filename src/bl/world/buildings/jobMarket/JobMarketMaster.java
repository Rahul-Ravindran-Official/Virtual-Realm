package bl.world.buildings.jobMarket;

import bl.helper.InitializeCop;
import bl.world.buildings.jobMarket.initializers.InitializeJobs;
import bl.world.buildings.jobMarket.modals.OJob;
import bl.world.buildings.jobMarket.modals.OJobExperienceTrack;
import bl.world.buildings.jobMarket.slaves.JobAcceptanceSlave;
import bl.world.buildings.jobMarket.slaves.JobExperienceSlave;
import bl.world.peripherals.time.Time;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JobMarketMaster extends InitializeCop {


    private HashMap<Integer,OJob> jobs = new HashMap<>();
    private JobExperienceSlave jobExperienceSlave = JobExperienceSlave.getInstance();
    private JobAcceptanceSlave jobAcceptanceSlave = JobAcceptanceSlave.getInstance();
    private OJob currentJob;

    ///////////////////////////////////////////////////////////////////////////
    public static JobMarketMaster getInstance() {
        return ourInstance;
    }
    private JobMarketMaster() {}
    private static JobMarketMaster ourInstance = new JobMarketMaster();
    ///////////////////////////////////////////////////////////////////////////

    public void init(HashMap<Integer,OJobExperienceTrack> experienceTrackerForJobs, OJob currentJob) throws ParseException {
        jobs = InitializeJobs.getInstance().getJobsForInitialization();
        jobExperienceSlave.init(experienceTrackerForJobs);
        this.currentJob = currentJob;
        initialized();
    }

    public ArrayList<OJob> listAllJobs (){

        ArrayList<OJob> outputJobs = new ArrayList<>();

        for (Map.Entry<Integer,OJob> entry : jobs.entrySet()){
            outputJobs.add(entry.getValue());
        }

        Collections.sort(outputJobs);
        return outputJobs;
    }

    public boolean apply(int jobId){
        if(currentJob != null){
            return false;
        }
        boolean isAccepted =  jobAcceptanceSlave.applyToJoinJob(jobId, jobs);
        currentJob = (isAccepted) ? jobs.get(jobId) : null;
        return isAccepted;
    }

    public boolean work(int numberOfTimes) throws Exception {
        if(currentJob == null){
            return false;
        }
        jobExperienceSlave.work(currentJob, numberOfTimes);
        Time.getInstance().incrementTime(2*numberOfTimes);
        return true;
    }

    public boolean leaveJob(){
        if(currentJob == null){
            return false;
        }
        currentJob = null;
        return true;
    }

}
