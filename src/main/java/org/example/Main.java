package org.example;

import data.base.LocalDataBase;

public class Main {

    public static void main(String[] args) {
        String cdrFileName = "./src/cdr.txt";

        if (args.length!=0) cdrFileName = args[0];

        ControllerDataBase dBase = new LocalDataBase();

        dBase.ReadFileCDR(cdrFileName);

        dBase.CreateReportForAllSubscribers();
    }
}