package data.base.tariff;

import data.base.subscribers.Call;
import data.base.subscribers.CallWithCost;

import java.util.List;

public abstract class Tariff {
    String name;
    TariffCode code;
    double fixCost;
    double fixTime;
    double priceMinute;

    abstract List<CallWithCost> CalculateCost(List<Call> callList);
    String GetName(){return name;};
    double GetFixCost(){
        return fixCost;
    };
}
