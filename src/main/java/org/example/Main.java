package org.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    static  String[] ParseString (String str) {
        return ParseLine(str);
    }

    static String[] ParseLine(String str){
        return str.replaceAll("\\s+", "").split(",");
    }

    static boolean CheckLine(String[] mas){
        if (mas.length != 5) {
            System.out.println("String does not contain 5 elements");
            return false;
        }
        // нужно реализовать проверку элементов массива на основе регулярных выражений
        return true;
    }
    static DataBaseSubscribers dataBaseSubscribers = new DataBaseSubscribers();
    static DatabaseTariff databaseTariff = new DatabaseTariff();
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");

        try {
            File file = new File("d:/Java/TestTask/src/main/java/org/example/cdr.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String[] mas_str;
            String line = reader.readLine();

            String type, number, timeBegin, timeEnd, tariff;
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

            for (Map.Entry<String,Subscriber> sub: dataBaseSubscribers.GetSubscriberMap().entrySet()) {
                CreateFilesReportForNumber(sub.getKey());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void CreateFilesReportForNumber(String number){
        HashMap<TariffCode, List<Call>> listsCalls = dataBaseSubscribers.GetListCalls(number);
        for ( Map.Entry<TariffCode,List<Call>> ls : listsCalls.entrySet()) {
            List<CallWithCost> listWithCost = databaseTariff.CalculateUseTariff(ls.getKey(), ls.getValue());
            CreatFileReport(databaseTariff.GetFixCostForTariff(ls.getKey()),databaseTariff.GetNameTariff(ls.getKey()),number,listWithCost);
        }

    }

    static boolean CreatFileReport(double fixCost, String tarrifIndex,String number,List<CallWithCost> list){
        double totalCost = fixCost;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PrintStream printStream = System.out;//new PrintStream(fileName))
        {
            printStream.printf("Tariff index: %s \n", tarrifIndex);
            printStream.println("----------------------------------------------------------------------------");
            printStream.printf("Report for phone number %S:\n", number);
            printStream.println("----------------------------------------------------------------------------");
            printStream.println("| Call Type |   Start Time        |     End Time        | Duration | Cost  |");
            printStream.println("----------------------------------------------------------------------------");
            for (CallWithCost call : list) {
                printStream.printf("|     %1$s    | %2$s | %3$s | %4$s |  %5$.2f |\n", call.GetTypeCode(),
                                                                                            formater.format(call.GetTimeBegin()),
                                                                                            formater.format(call.GetTimeEnd()),
                                                                                            (call.DurationToString()),
                                                                                            call.GetCost());
                totalCost += call.GetCost();
            }
            printStream.println("----------------------------------------------------------------------------");
            printStream.printf("|                                           Total Cost: |     %.2f rubles |\n", totalCost);
            printStream.println("----------------------------------------------------------------------------");
            //String message = "PrintStream";
            //byte[] message_toBytes = message.getBytes();
            //printStream.write(message_toBytes);

            //System.out.println("The file has been written");
        }
        //catch(IOException ex){

        //    System.out.println(ex.getMessage());
        //}
        return true;
    }



}