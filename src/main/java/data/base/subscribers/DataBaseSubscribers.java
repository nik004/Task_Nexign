package data.base.subscribers;


import data.base.tariff.DataBaseTariff;
import data.base.tariff.TariffCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fileworker.FileReader.CreatFileReport;

public class DataBaseSubscribers {

        private HashMap<String, Subscriber> subscribersHashMap;

        public DataBaseSubscribers() {
            this.subscribersHashMap = new HashMap<>();
        }

        public HashMap<String, Subscriber> GetSubscriberMap (){
            return subscribersHashMap;
        }
        public Subscriber Add(String number) {
            Subscriber result = subscribersHashMap.get(number);
            if (result == null) {
                result = new Subscriber(number);
                subscribersHashMap.put(number, result);
            }
            return result;
        }

        public void AddCall(String number,String str_type,String str_tariff, String str_timeBegin,String str_timeEnd){
            Subscriber subscriber = Add(number);
            subscriber.callsLog.Add(new Call(str_type,str_tariff,str_timeBegin,str_timeEnd));
        }

        public HashMap<TariffCode, List<Call>> GetListCalls(String number){
            Subscriber subscriber = subscribersHashMap.get(number);
            if (subscriber == null){
                System.out.printf("It is impossible to build a report by number %1.The number is not in the database", number);
                return null;
            }
            return subscriber.GetListsCalls();
        }

        public void CreateReportForAllSubscribers(DataBaseTariff dataBaseTariff){
            for (Map.Entry<String, Subscriber> sub: GetSubscriberMap().entrySet()) {
                CreateReportForNumber(sub.getKey(), dataBaseTariff);
            }
        }

         void CreateReportForNumber(String number,DataBaseTariff dataBaseTariff){
            HashMap<TariffCode, List<Call>> listsCalls = GetListCalls(number);
            for ( Map.Entry<TariffCode,List<Call>> ls : listsCalls.entrySet()) {
                List<CallWithCost> listWithCost = dataBaseTariff.CalculateUseTariff(ls.getKey(), ls.getValue());
                try {
                    CreatFileReport(dataBaseTariff.GetFixCostForTariff(ls.getKey()),dataBaseTariff.GetNameTariff(ls.getKey()),number,listWithCost);
                }
                catch (Exception e){
                    System.out.printf( e.getMessage() + "\n" +
                            "Failed to write report file for nuber: %s \n", number);
                }

            }

        }


}
