package bl.world.buildings.realEstate.modals;

public class OTrackRent {

    private int houseId;
    private int unlockableId;
    private int hoursLeft;
    private int fromTimePassedInHours;
    private int toTimePassedInHours;


    public OTrackRent(int houseId, int unlockableId, int hoursLeft, int fromTimePassedInHours, int toTimePassedInHours) {
        this.houseId = houseId;
        this.unlockableId = unlockableId;
        this.hoursLeft = hoursLeft;
        this.fromTimePassedInHours = fromTimePassedInHours;
        this.toTimePassedInHours = toTimePassedInHours;
    }

    public OTrackRent() {

    }

    // Custom Methods
    public int timeGoes(int hours){
        int tentativeHoursLeft = this.getHoursLeft() - hours;
        if(tentativeHoursLeft > 0){
            this.hoursLeft = tentativeHoursLeft;
        }else{
            this.hoursLeft = 0;
        }
        return this.hoursLeft;
    }

    public String timeLeftInDaysHours(){
        int hours = timeLeftInHours() % 24;
        int days = (timeLeftInHours() - hours) / 24;
        return String.valueOf( "Days: " + days + " Hours: " + hours );
    }

    public int timeLeftInHours(){
        return this.getHoursLeft();
    }

    public int addHoursLeft(int hours){
        this.hoursLeft += hours;
        return this.getHoursLeft();
    }

    // Getters and Setters

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getUnlockableId() {
        return unlockableId;
    }

    public void setUnlockableId(int unlockableId) {
        this.unlockableId = unlockableId;
    }

    public int getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(int hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public int getFromTimePassedInHours() {
        return fromTimePassedInHours;
    }

    public void setFromTimePassedInHours(int fromTimePassedInHours) {
        this.fromTimePassedInHours = fromTimePassedInHours;
    }

    public int getToTimePassedInHours() {
        return toTimePassedInHours;
    }

    public void setToTimePassedInHours(int toTimePassedInHours) {
        this.toTimePassedInHours = toTimePassedInHours;
    }
}
