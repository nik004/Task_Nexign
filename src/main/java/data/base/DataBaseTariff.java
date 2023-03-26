package data.base;

import data.base.subscribers.Call;
import data.base.subscribers.CallWithCost;
import data.base.tariff.TariffCode;

import java.util.List;

public interface DataBaseTariff {
    List<CallWithCost> CalculateUseTariff(TariffCode tCode, List<Call> list);
    double GetFixCostForTariff (TariffCode tCode);
    String GetNameTariff (TariffCode tCode);
}
