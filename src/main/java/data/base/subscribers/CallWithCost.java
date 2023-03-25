package data.base.subscribers;

public class CallWithCost extends Call {
    double cost;

    CallWithCost(String str_type, String str_tariff, String str_timeBegin, String str_timeEnd) {
        super(str_type, str_tariff, str_timeBegin, str_timeEnd);
    }

    public CallWithCost(Call call, double cost){
        super();
        this.type = call.type;
        this.tariffCode = call.tariffCode;
        this.timeBegin = call.timeBegin;
        this.timeEnd = call.timeEnd;
        this.duration = call.duration;
        this.cost = cost;
    }

    public double GetCost(){
        return cost;
    }

}
