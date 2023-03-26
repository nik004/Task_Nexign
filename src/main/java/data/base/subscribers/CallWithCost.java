package data.base.subscribers;

public class CallWithCost extends Call {
    private double cost;

    public CallWithCost(Call call, double cost){
        super(call);
        this.cost = cost;
    }

    public double GetCost(){
        return cost;
    }

}
