package fileworker;

import org.example.DataReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileReader implements DataReader {
    private String[] ParseString (String str) {
        return ParseLine(str,",");
    }

    private String[] ParseLine(String str, String devider){
        return str.replaceAll("\\s+", "").split(devider);
    }

    private boolean CheckLine(String[] mas){
        if (mas.length != 5) {
            System.out.println("String does not contain 5 elements");
            return false;
        }
        // нужно реализовать проверку элементов массива на основе регулярных выражений
        return true;
    }

    public List<String[]> ReadFileCDR (String filename) {
        List<String[]> list = new ArrayList<>();
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
                   list.add(mas_str);
                }
                line = reader.readLine();
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
