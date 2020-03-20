package bl.world.buildings.jobMarket.builders;

import bl.helper.Objects.ProbabilityOfExecuteAndChooseFromRandomRangeOfValues;
import bl.world.buildings.jobMarket.modals.OJob;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class JobBuilder {

    public static ArrayList<OJob> build(ArrayList<JSONObject> jobs, ArrayList<JSONObject> jobRequirements){
        ArrayList<OJob> outputJobs = new ArrayList<>();
        // JobID, HashMap<"jobID" | "unlockableID", value>
        HashMap<Integer,ArrayList<HashMap<String,Integer>>> jobRequirementsMap = new HashMap<>();

        for (JSONObject job : jobs) {
            outputJobs.add(buildJob(job));
        }

        for (JSONObject jobRequirement : jobRequirements) {
            HashMap<String,Integer> jobRequirementAsHashMap = buildJobRequirements(jobRequirement);
            int jobID = jobRequirementAsHashMap.get("jobId");

            ArrayList<HashMap<String,Integer>> existingPairs = new ArrayList<>();
            // Checking if jobRequirementsMap already contains key
            if(jobRequirementsMap.containsKey(jobID)){
                existingPairs = jobRequirementsMap.get(jobID);
            }
            existingPairs.add(jobRequirementAsHashMap);
            jobRequirementsMap.put(jobID,existingPairs);
        }

        for (int i = 0; i < outputJobs.size(); i++) {
            OJob bufferJob = outputJobs.get(i);
            int jobID = bufferJob.getJobID();
            ArrayList<Integer> requirementsAsIntegerOfUnlockedIds = new ArrayList<>();
            if(!jobRequirementsMap.containsKey(jobID)){
                continue;
            }
            for (HashMap<String, Integer> jobRequirementAsHashMap : jobRequirementsMap.get(jobID)) {
                int unlockableID = jobRequirementAsHashMap.get("unlockableId");
                requirementsAsIntegerOfUnlockedIds.add(unlockableID);
            }
            bufferJob.setRequirementsAsIntegerOfUnlockedIds(requirementsAsIntegerOfUnlockedIds);
            outputJobs.set(i, bufferJob);
        }

        return outputJobs;
    }

    private static OJob buildJob(JSONObject job) {

        String jobName = (String) job.get("job_name");
        int jid = Integer.parseInt((String) job.get("jid"));
        int energy_consumed_per_click = Integer.parseInt((String) job.get("energy_consumed_per_click"));
        int accident_damage_limit_min = Integer.parseInt((String) job.get("accident_damage_limit_min"));
        int accident_damage_limit_max = Integer.parseInt((String) job.get("accident_damage_limit_max"));
        float probability_of_accident = Float.valueOf((String) job.get("probability_of_accident"));
        BigDecimal cash_earned_per_click = BigDecimal.valueOf(Double.valueOf((String) job.get("cash_earned_per_click")));
        BigDecimal bribe_to_join = BigDecimal.valueOf(Double.valueOf((String) job.get("bribe_to_join")));

        OJob oJob = new OJob();
        oJob.setAccident(
                new ProbabilityOfExecuteAndChooseFromRandomRangeOfValues(
                        probability_of_accident,
                        accident_damage_limit_min,
                        accident_damage_limit_max
                )
        );
        oJob.setBribeToJoin(bribe_to_join);
        oJob.setCashEarnedPerClick(cash_earned_per_click);
        oJob.setEnergyConsumedPerClick(energy_consumed_per_click);
        oJob.setJobID(jid);
        oJob.setJobName(jobName);

        return oJob;
    }

    private static HashMap<String,Integer> buildJobRequirements(JSONObject jobRequirements){

        HashMap<String,Integer> jobRequirementsOutput = new HashMap<>();

        int jobId = Integer.parseInt((String) jobRequirements.get("jid"));
        int unlockableId = Integer.parseInt((String) jobRequirements.get("requirement_id"));

        jobRequirementsOutput.put("jobId",jobId);
        jobRequirementsOutput.put("unlockableId",unlockableId);

        return jobRequirementsOutput;
    }

}
