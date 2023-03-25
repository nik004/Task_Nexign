package data.base;

import data.base.subscribers.DataBaseSubscribers;
import data.base.tariff.DataBaseTariff;

import java.io.*;

import static fileworker.FileReader.*;

public class DataBase {
    private DataBaseSubscribers dataBaseSubscribers;
    private DataBaseTariff dataBaseTariff;

    public DataBase(){
        dataBaseSubscribers = new DataBaseSubscribers();
        dataBaseTariff = new DataBaseTariff();

    }

    public void CreateFileReportForAllSubscribers(String reportCatalog){
        UseReportCatalog(reportCatalog);
        dataBaseSubscribers.CreateReportForAllSubscribers(dataBaseTariff);
    }

    public void ReadFileCDR (String filename) {
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            String type, number, timeBegin, timeEnd, tariff;
            String[] mas_str;
            while (line != null) {
                mas_str = ParseString(line);
                if (CheckLine(mas_str)){
                    type = mas_str[0];
                    number = mas_str[1];
                    timeBegin = mas_str[2];
                    timeEnd = mas_str[3];
                    tariff = mas_str[4];
                    dataBaseSubscribers.AddCall(number,type,tariff,timeBegin,timeEnd);
                }
                line = reader.readLine();
            }
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
