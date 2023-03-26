package data.base.subscribers;


import data.base.DataBaseSubscribers;
import data.base.DataBaseTariff;
import data.base.tariff.LocalDataBaseTariff;
import data.base.tariff.TariffCode;
import fileworker.DataFileReader;
import fileworker.FileReportMaker;
import org.example.DataReader;
import org.example.ViewReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalDataBaseSubscribers implements DataBaseSubscribers {

        private HashMap<String, Subscriber> subscribersHashMap;
        private ViewReport viewReport;
        private DataReader dataReader;

        public LocalDataBaseSubscribers() {
            this.subscribersHashMap = new HashMap<>();
            viewReport = new FileReportMaker();
            dataReader = new DataFileReader();
        }

        private HashMap<String, Subscriber> GetSubscriberMap (){
            return subscribersHashMap;
        }
        private Subscriber Add(String number) {
            Subscriber result = subscribersHashMap.get(number);
            if (result == null) {
                result = new Subscriber(number);
                subscribersHashMap.put(number, result);
            }
            return result;
        }

        public void AddCall(String str_type,String number,String str_tariff, String str_timeBegin,String str_timeEnd){
            Subscriber subscriber = Add(number);
            subscriber.AddCall(new Call(str_type,str_tariff,str_timeBegin,str_timeEnd));
        }

        public void AddCall(String[] mas){
            Subscriber subscriber = Add(mas[1]);
            subscriber.AddCall(new Call(mas[0],mas[4],mas[2],mas[3]));
        }

        public void AddCallsFromList_str_array (List<String[]> list){
            for (String[] mas:list){
                AddCall(mas);
            }
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

        private void CreateReportForNumber(String number, DataBaseTariff dataBaseTariff){
            HashMap<TariffCode, List<Call>> listsCalls = GetListCalls(number);
            for ( Map.Entry<TariffCode,List<Call>> ls : listsCalls.entrySet()) {
                List<CallWithCost> listWithCost = dataBaseTariff.CalculateUseTariff(ls.getKey(), ls.getValue());
                try {
                    viewReport.CreateReport(dataBaseTariff.GetFixCostForTariff(ls.getKey()),dataBaseTariff.GetNameTariff(ls.getKey()),number,listWithCost);
                }
                catch (Exception e){
                    System.out.printf( e.getMessage() + "\n" +
                            "Failed to write report file for nuber: %s \n", number);
                }
            }
        }

        public void ReadFileCDR (String filename){
            AddCallsFromList_str_array(dataReader.ReadFileCDR(filename));
        }

}
