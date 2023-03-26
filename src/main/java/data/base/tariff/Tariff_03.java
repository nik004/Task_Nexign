package data.base.tariff;

import data.base.subscribers.Call;
import data.base.subscribers.CallWithCost;

import java.util.ArrayList;
import java.util.List;

public class Tariff_03 extends Tariff {

    Tariff_03(){
        super("03",TariffCode._03,0,0,1.5);
    }

    List<CallWithCost> CalculateCost(List<Call> list){
        double _fixTime = GetFixTime();
        double _priceMinute = GetPriceMinute();
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
