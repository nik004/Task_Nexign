package data.base.tariff;

import data.base.subscribers.Call;
import data.base.subscribers.CallWithCost;

import java.util.List;

public abstract class Tariff {
    private String name;
    private TariffCode code;
    private double fixCost;
    private double fixTime;
    private double priceMinute;

    public Tariff(String _name, TariffCode _code, double _fixCost, double _fixTime, double _priceMinute){
        name = _name;
        code = _code;
        fixCost = _fixCost;
        fixTime = _fixTime;
        priceMinute = _priceMinute;
    }

    abstract List<CallWithCost> CalculateCost(List<Call> callList);
    public String GetName(){return name;};
    public double GetFixCost(){
        return fixCost;
    };
    public double GetFixTime() {return fixTime;}
    public double GetPriceMinute() {return priceMinute;}
}
