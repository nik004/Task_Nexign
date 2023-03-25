package org.example;

import data.base.DataBase;

public class Main {

    public static void main(String[] args) {
        String cdrFileName = "./src/main/cdr.txt";
        String reportCatalog = "./src/reports/";

        if (args.length!=0) cdrFileName = args[0];

        DataBase dBase = new DataBase();
        dBase.ReadFileCDR(cdrFileName);

        dBase.CreateFileReportForAllSubscribers(reportCatalog);
    }

}