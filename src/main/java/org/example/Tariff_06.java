package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tariff_06 extends Tariff {

    Tariff_06(){
        name = "06";
        code = TariffCode._06;
        fixCost = 100;
        fixTime = 300;
        priceMinute = 1;
    }

    List<CallWithCost> CalculateCost(List<Call> list){
        double _fixTime = fixTime;
        double _priceMinute = priceMinute;
        double cost;

        List<CallWithCost> listCost = new ArrayList<>();
        if (list.isEmpty()) return listCost;
        for (Call call : list) {
            _fixTime = _fixTime - call.getDurationMinute();
            if (_fixTime <= 0 ){
                cost = -_fixTime * _priceMinute;
                _fixTime = 0;
            }
            else{
                cost = 0;
            }
            listCost.add(new CallWithCost(call,cost));
        }
        return listCost;
    };
}
