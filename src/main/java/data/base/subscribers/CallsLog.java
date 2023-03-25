package data.base.subscribers;

import java.util.ArrayList;
import java.util.List;

public class CallsLog {

    List<Call> calls;


    CallsLog(){
        calls = new ArrayList<>();
    }
    void Add (Call call){
        calls.add(call);
    }

    List<Call> GetCallsList (){
        return calls;
    }

}
