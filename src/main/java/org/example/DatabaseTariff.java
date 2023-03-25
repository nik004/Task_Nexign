package org.example;

import java.util.HashMap;
import java.util.List;

public class DatabaseTariff {
    private HashMap<TariffCode,Tariff> dataBaseTariff;

    DatabaseTariff(){
        dataBaseTariff = new HashMap<>();
        dataBaseTariff.put(TariffCode._03, new Tariff_03());
        dataBaseTariff.put(TariffCode._06, new Tariff_06());
        dataBaseTariff.put(TariffCode._11, new Tariff_11());

    }

    Tariff GetTariff (TariffCode tCode){
        return dataBaseTariff.get(tCode);
    }

    String GetNameTariff (TariffCode tCode){
        return dataBaseTariff.get(tCode).GetName();
    }

    double GetFixCostForTariff (TariffCode tCode){
        return dataBaseTariff.get(tCode).GetFixCost();
    }

    List<CallWithCost> CalculateUseTariff(TariffCode tCode, List<Call> list){
        return  GetTariff(tCode).CalculateCost(list);
    }

}
