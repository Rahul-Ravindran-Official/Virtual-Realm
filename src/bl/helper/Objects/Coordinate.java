package bl.helper.Objects;

import bl.annotations.Tested;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Tested
    public double getDistanceToCoordinate(Coordinate otherCoordinate){
        double xDistance = Math.pow((getX() - otherCoordinate.getX()),2);
        double yDistance = Math.pow((getY() - otherCoordinate.getY()),2);
        double distance = Math.sqrt( xDistance + yDistance );
        return distance;
    }

}