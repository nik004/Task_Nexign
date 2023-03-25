package org.example;


import java.util.HashMap;
import java.util.List;

public class DataBaseSubscribers {

        private HashMap<String, Subscriber> subscribersHashMap;

        DataBaseSubscribers() {
            this.subscribersHashMap = new HashMap<>();
        }

        HashMap<String, Subscriber> GetSubscriberMap (){
            return subscribersHashMap;
        }
        Subscriber Add(String number) {
            Subscriber result = subscribersHashMap.get(number);
            if (result == null) {
                result = new Subscriber(number);
                subscribersHashMap.put(number, result);
            }
            return result;
        }

        void AddCall(String number,String str_type,String str_tariff, String str_timeBegin,String str_timeEnd){
            Subscriber subscriber = Add(number);
            subscriber.callsLog.Add(new Call(str_type,str_tariff,str_timeBegin,str_timeEnd));
        }

        HashMap<TariffCode, List<Call>> GetListCalls(String number){
            Subscriber subscriber = subscribersHashMap.get(number);
            if (subscriber == null){
                System.out.printf("It is impossible to build a report by number %1.The number is not in the database", number);
                return null;
            }
            return subscriber.GetListsCalls();
        }


}
