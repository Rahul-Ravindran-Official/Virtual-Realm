package bl.world.buildings._commonUtilities;

/**
 * Implement this class to receive cash
 */
public abstract class CashReceivable {
    public String returnClassNameForTransaction(){
        String className = this.getClass().getSimpleName();
        return className;
    }
}
