package fileworker;

import data.base.subscribers.CallWithCost;
import org.example.ViewReport;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileReportMaker implements ViewReport {
    private String reportCatalog;
    public FileReportMaker(){
        reportCatalog = "./src/reports/";

        File catalog = new File(reportCatalog);
        catalog.mkdirs();
    }

    public boolean CreateReport(double fixCost, String tarrifIndex, String number, List<CallWithCost> list) {
        double totalCost = fixCost;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fileName = reportCatalog + "Report_" + number + "_" + tarrifIndex + ".txt";
        try {
            PrintStream printStream = new PrintStream(fileName);

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
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
