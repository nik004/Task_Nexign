package data.base.tariff;

import data.base.subscribers.Call;
import data.base.subscribers.CallWithCost;

import java.util.HashMap;
import java.util.List;

public class DataBaseTariff {
    private HashMap<TariffCode, Tariff> dataBaseTariff;

    public DataBaseTariff(){
        dataBaseTariff = new HashMap<>();
        dataBaseTariff.put(TariffCode._03, new Tariff_03());
        dataBaseTariff.put(TariffCode._06, new Tariff_06());
        dataBaseTariff.put(TariffCode._11, new Tariff_11());

    }

    Tariff GetTariff (TariffCode tCode){
        return dataBaseTariff.get(tCode);
    }

    public String GetNameTariff (TariffCode tCode){
        return dataBaseTariff.get(tCode).GetName();
    }

    public double GetFixCostForTariff (TariffCode tCode){
        return dataBaseTariff.get(tCode).GetFixCost();
    }

    public List<CallWithCost> CalculateUseTariff(TariffCode tCode, List<Call> list){
        return  GetTariff(tCode).CalculateCost(list);
    }

}
