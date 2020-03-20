package bl.world.buildings.jobMarket.initializers;

import bl.helper.APIs;
import bl.helper.NxMethods;
import bl.world.buildings.jobMarket.builders.JobBuilder;
import bl.world.buildings.jobMarket.modals.OJob;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

public class InitializeJobs {


    ///////////////////////////////////////////////////////////////////////////
    public static InitializeJobs getInstance() {
        return ourInstance;
    }
    private InitializeJobs() {}
    private static InitializeJobs ourInstance = new InitializeJobs();
    ///////////////////////////////////////////////////////////////////////////

    public HashMap<Integer, OJob> getJobsForInitialization() throws ParseException {
        HashMap<Integer, OJob> jobs = new HashMap<>();

        ArrayList<OJob> bufferJobs = getFinalJobs();

        for (OJob bufferJob : bufferJobs) {
            int jobId = bufferJob.getJobID();
            jobs.put(jobId,bufferJob);
        }

        return jobs;
    }

    private ArrayList<OJob> getFinalJobs() throws ParseException {
        String srcJobs = getJobsWithoutRequirementsFromServer();
        String srcRequirements = getJobRequirementsFromServer();
        ArrayList<JSONObject> jobsAsJSONObject = NxMethods.getUsableJSONObject(srcJobs);
        ArrayList<JSONObject> jobRequirementsAsJSONObject = NxMethods.getUsableJSONObject(srcRequirements);
        ArrayList<OJob> jobsWithoutRequirements = JobBuilder.build(jobsAsJSONObject,jobRequirementsAsJSONObject);
        return jobsWithoutRequirements;
    }

    // Rest API Linkage Here
    private String getJobsWithoutRequirementsFromServer(){
        return NxMethods.getSourceCodeOfWebsite(APIs.getJobs);
    }
    private String getJobRequirementsFromServer(){
        return NxMethods.getSourceCodeOfWebsite(APIs.getJobRequirements);
    }


}
