package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Subscriber {
    String number;
    CallsLog callsLog;

    Subscriber (String number){
        this.number = number;
        this.callsLog = new CallsLog();
    }

    List<Call> GetCalls(){
        return callsLog.GetCallsList();
    }

    List<Call> MakeSortListForTariff( TariffCode tariffCode){

        List<Call> lCalls = GetCalls().stream()
                .filter(e -> e.tariffCode == tariffCode)
                .sorted((o1,o2)-> o1.GetTimeBegin().compareTo(o2.GetTimeBegin()))
                .collect(Collectors.toList());

        return lCalls;
    }

    HashMap<TariffCode, List<Call>> GetListsCalls (){
        HashMap<TariffCode, List<Call>> list = new HashMap<>();
        Map<TariffCode, Long> map = GetCalls().stream()
                .collect(Collectors.groupingBy(Call::GetTariffCode, Collectors.counting()));
        for (Map.Entry<TariffCode, Long> tar: map.entrySet()){
            list.put(tar.getKey(),MakeSortListForTariff(tar.getKey()));
        }
        return list;
    }

}
