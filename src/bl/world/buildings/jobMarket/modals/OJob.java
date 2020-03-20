package bl.world.buildings.jobMarket.modals;

import bl.helper.Objects.ProbabilityOfExecuteAndChooseFromRandomRangeOfValues;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OJob implements Comparable<OJob> {

    private int jobID;
    private String jobName;
    private int energyConsumedPerClick;
    private BigDecimal cashEarnedPerClick;
    private ArrayList<Integer> requirementsAsIntegerOfUnlockedIds = new ArrayList<>();
    private BigDecimal bribeToJoin;
    private ProbabilityOfExecuteAndChooseFromRandomRangeOfValues accident;

    public OJob(int jobID, String jobName, int energyConsumedPerClick, BigDecimal cashEarnedPerClick, ArrayList<Integer> requirementsAsIntegerOfUnlockedIds, BigDecimal bribeToJoin, ProbabilityOfExecuteAndChooseFromRandomRangeOfValues accident) {
        this.jobID = jobID;
        this.jobName = jobName;
        this.energyConsumedPerClick = energyConsumedPerClick;
        this.cashEarnedPerClick = cashEarnedPerClick;
        this.requirementsAsIntegerOfUnlockedIds = requirementsAsIntegerOfUnlockedIds;
        this.bribeToJoin = bribeToJoin;
        this.accident = accident;
    }

    public OJob() {
    }

    public int getJobID() {
        return jobID;
    }
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public int getEnergyConsumedPerClick() {
        return energyConsumedPerClick;
    }
    public void setEnergyConsumedPerClick(int energyConsumedPerClick) {
        this.energyConsumedPerClick = energyConsumedPerClick;
    }
    public BigDecimal getCashEarnedPerClick() {
        return cashEarnedPerClick;
    }
    public void setCashEarnedPerClick(BigDecimal cashEarnedPerClick) {
        this.cashEarnedPerClick = cashEarnedPerClick;
    }
    public ArrayList<Integer> getRequirementsAsIntegerOfUnlockedIds() {
        return requirementsAsIntegerOfUnlockedIds;
    }
    public void setRequirementsAsIntegerOfUnlockedIds(ArrayList<Integer> requirementsAsIntegerOfUnlockedIds) {
        this.requirementsAsIntegerOfUnlockedIds = requirementsAsIntegerOfUnlockedIds;
    }
    public BigDecimal getBribeToJoin() {
        return bribeToJoin;
    }
    public void setBribeToJoin(BigDecimal bribeToJoin) {
        this.bribeToJoin = bribeToJoin;
    }
    public ProbabilityOfExecuteAndChooseFromRandomRangeOfValues getAccident() {
        return accident;
    }
    public void setAccident(ProbabilityOfExecuteAndChooseFromRandomRangeOfValues accident) {
        this.accident = accident;
    }

    @Override
    public int compareTo(OJob o) {
        return cashEarnedPerClick.compareTo(o.cashEarnedPerClick);
    }
}