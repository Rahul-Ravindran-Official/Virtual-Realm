package bl.world.buildings.transport.modal.vehicleSystems;

import bl.person.systems.AbstractSystem;

public class OGasTank extends AbstractSystem {

    public OGasTank(int maxCapacityInUnits) {
        setMaxEntity(maxCapacityInUnits);
    }

    @Override
    public int getMaxEntity() {
        // To Set
        return 0;
    }

}
