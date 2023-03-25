package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tariff_11 extends Tariff {
    double priceMinuteAfterFixTime;
    Tariff_11(){
        name = "11";
        code = TariffCode._11;
        fixCost = 0;
        fixTime = 100;
        priceMinute = 0.5;
        priceMinuteAfterFixTime = 1.5;

    }

    List<CallWithCost> CalculateCost(List<Call> list){
        double _fixTime = fixTime;
        double cost;

        int duration_call;

        List<CallWithCost> listcost = new ArrayList<>();
        if (list.isEmpty()) return listcost;
        for (Call call : list) {
            if (call.type == TypeCall.IN){
                cost = 0;
            }
            else{
                    duration_call = call.getDurationMinute();
                    _fixTime = _fixTime - duration_call;
                    if (_fixTime <= 0 ) {
                        cost = Math.abs(_fixTime * priceMinuteAfterFixTime);
                        cost = cost + ((_fixTime + duration_call)*priceMinute);
                        _fixTime = 0;
                    }
                    else{
                        cost = duration_call *priceMinute;
                    }
                }
            listcost.add(new CallWithCost(call,cost));
        }
        return listcost;
    };
}
