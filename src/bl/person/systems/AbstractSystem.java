package bl.person.systems;
import bl.annotations.Tested;

/**
 * This class aims to reuse components across various systems through this abstract class.
 * The term 'entity' will be used to represent variables like - health, hunger ... etc ~ You get the idea :).
 */
public abstract class AbstractSystem {

    private int maxEntity = getMaxEntity();
    private int currentEntity;

    @Tested
    public void init(int initializeCurrentEntity){
        this.currentEntity = initializeCurrentEntity;
    }

    @Tested
    public boolean gainEntity(int EntityToGain){
        int afterEntityGain = (currentEntity + EntityToGain);
        currentEntity = (afterEntityGain > maxEntity)? maxEntity : afterEntityGain;
        return true;
    }

    @Tested
    public boolean loseEntity(int EntityToLose){
        int afterEntityLost = (currentEntity - EntityToLose);
        if(afterEntityLost >= 0){
            currentEntity = afterEntityLost;
            return true;
        }else{
            currentEntity = 0;
        }
        return false;
    }

    @Tested
    public boolean fillToMaxEntity(){
        this.currentEntity = maxEntity;
        return true;
    }

    @Tested
    public String peekEntityAsString(){
        return String.valueOf(currentEntity);
    }

    @Tested
    public int peekEntityAsValue(){
        return currentEntity;
    }

    public void setMaxEntity(int maxEntity){
        this.maxEntity = maxEntity;
    }

    @Tested
    public abstract int getMaxEntity();

}
