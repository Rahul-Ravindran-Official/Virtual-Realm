package bl.person.systems;

import java.util.ArrayList;

public class UnlockableSystem {

    private ArrayList<Integer> idsUnlocked = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////
    private static UnlockableSystem ourInstance = new UnlockableSystem();
    public static UnlockableSystem getInstance() {
        return ourInstance;
    }
    private UnlockableSystem() {}
    ///////////////////////////////////////////////////////////////////////////

    public void init(ArrayList<Integer> idsUnlocked){
        this.idsUnlocked = idsUnlocked;
    }

    public boolean addUnlockedItem(int unlockableId){
        if(!idsUnlocked.contains(unlockableId)){
            idsUnlocked.add(unlockableId);
            return true;
        }
        return false;
    }

    public boolean checkIfIdIsUnlocked(int unlockableId){
        return idsUnlocked.contains(unlockableId);
    }

    public boolean removeUnlockedItemIfExists(int unlockableId){
        if (idsUnlocked.contains(unlockableId)) {
            idsUnlocked.remove(unlockableId);
            return true;
        }
        return false;
    }

}
