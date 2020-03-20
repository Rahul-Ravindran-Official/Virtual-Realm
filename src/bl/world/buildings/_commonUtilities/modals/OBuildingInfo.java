package bl.world.buildings._commonUtilities.modals;

import bl.helper.Objects.Coordinate;

public class OBuildingInfo {


    private String buildingName;
    private Coordinate coordinates;
    private int id;
    private Object type;

    public OBuildingInfo(String buildingName, Coordinate coordinates, int id, Object type) {
        this.buildingName = buildingName;
        this.coordinates = coordinates;
        this.id = id;
        this.type = type;
    }
    public OBuildingInfo() {
    }

    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public Coordinate getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Object getType() {
        return type;
    }
    public void setType(Object type) {
        this.type = type;
    }


}
