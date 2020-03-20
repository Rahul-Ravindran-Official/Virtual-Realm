package bl.world.buildings.restaurant.modals;

import bl.annotations.Tested;

import java.util.ArrayList;

@Tested
public class OWaiter {
    private int waiterId;
    private String name;
    private int age;
    private boolean tippable;
    private int luck;   // Tipping them will give the gamer this luck * proportionate of their income they give. Example: user gives 50% of his cash as tip who has a luck score of 10. this gives him a luck of 0.5*10
    private ArrayList<Integer> restaurantsIdsWorkFor = new ArrayList<>();

    public OWaiter(int waiterId, String name, int age, boolean tippable, int luck, ArrayList<Integer> restaurantsIdsWorkFor) {
        this.waiterId = waiterId;
        this.name = name;
        this.age = age;
        this.tippable = tippable;
        this.luck = luck;
        this.restaurantsIdsWorkFor = restaurantsIdsWorkFor;
    }
    public OWaiter() {
    }

    public void addRestaurantIWorkForToList(int restaurantId){
        this.restaurantsIdsWorkFor.add(restaurantId);
    }
    public Integer getOneRestaurantID(){
        return restaurantsIdsWorkFor.get(0);
    }

    public int getWaiterId() {
        return waiterId;
    }
    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isTippable() {
        return tippable;
    }
    public void setTippable(boolean tippable) {
        this.tippable = tippable;
    }
    public int getLuck() {
        return luck;
    }
    public void setLuck(int luck) {
        this.luck = luck;
    }
    public ArrayList<Integer> getRestaurantsIdsWorkFor() {
        return restaurantsIdsWorkFor;
    }
    public void setRestaurantsIdsWorkFor(ArrayList<Integer> restaurantsIdsWorkFor) {
        this.restaurantsIdsWorkFor = restaurantsIdsWorkFor;
    }

}