package bl.world.buildings.transport.modal.vehicleSystems;

import bl.helper.Objects.ProbabilityOfExecuteAndChooseFromRandomRangeOfValues;
import bl.world.buildings.transport.modal.TransportAccidentSubscriber;

import java.util.ArrayList;

public class OSafetyModule {

    ArrayList<TransportAccidentSubscriber> accidentSubscribers = new ArrayList<>();
    ProbabilityOfExecuteAndChooseFromRandomRangeOfValues accidentProbabilityOfExecuteAndChooseFromRandomRangeOfValues;

    public OSafetyModule(float probabilityOfExecute, int rangeLow, int rangeHigh) {
        accidentProbabilityOfExecuteAndChooseFromRandomRangeOfValues = new ProbabilityOfExecuteAndChooseFromRandomRangeOfValues(probabilityOfExecute, rangeLow, rangeHigh);
    }

    public int getIsAccident(){
        return accidentProbabilityOfExecuteAndChooseFromRandomRangeOfValues.getValueConsideringProbability();
     }

}
