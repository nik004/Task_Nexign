package data.base;

import data.base.subscribers.LocalDataBaseSubscribers;
import data.base.tariff.LocalDataBaseTariff;
import org.example.ControllerDataBase;

public class LocalDataBase implements ControllerDataBase {
    private DataBaseSubscribers dataBaseSubscribers;
    private DataBaseTariff dataBaseTariff;


    public LocalDataBase(){
        dataBaseSubscribers = new LocalDataBaseSubscribers();
        dataBaseTariff = new LocalDataBaseTariff();
    }

    public void CreateReportForAllSubscribers(){
        dataBaseSubscribers.CreateReportForAllSubscribers(dataBaseTariff);
    }

    public void ReadFileCDR (String filename) {
        dataBaseSubscribers.ReadFileCDR (filename);
    }


}
