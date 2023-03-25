package fileworker;

import data.base.subscribers.CallWithCost;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileReader {
    public static  String[] ParseString (String str) {
        return ParseLine(str,",");
    }

    public static String[] ParseLine(String str, String devider){
        return str.replaceAll("\\s+", "").split(devider);
    }

    public static boolean CheckLine(String[] mas){
        if (mas.length != 5) {
            System.out.println("String does not contain 5 elements");
            return false;
        }
        // нужно реализовать проверку элементов массива на основе регулярных выражений
        return true;
    }
    public static void UseReportCatalog(String cat){
        reportCatalog = cat;
        File catalog = new File(reportCatalog);
        catalog.mkdirs();
    }

    static public String reportCatalog = "";
    public static boolean CreatFileReport(double fixCost, String tarrifIndex, String number, List<CallWithCost> list) throws FileNotFoundException {
        double totalCost = fixCost;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PrintStream printStream;

        if (reportCatalog =="") printStream = System.out;
        else{
            String fileName = reportCatalog + "Report_" + number + "_" + tarrifIndex + ".txt";
            printStream = new PrintStream(fileName);
        }
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

        System.out.printf("The file has been written (number: %1$s   tariff: %2$s ) \n",number,tarrifIndex);

        return true;
    }
}
