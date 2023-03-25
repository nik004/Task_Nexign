package data.base.tariff;

import data.base.subscribers.Call;
import data.base.subscribers.CallWithCost;

import java.util.ArrayList;
import java.util.List;

public class Tariff_03 extends Tariff {

    Tariff_03(){
        name = "03";
        code = TariffCode._03;
        fixCost = 0;
        fixTime = 0;
        priceMinute = 1.5;
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
