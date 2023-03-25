package data.base.subscribers;

import data.base.tariff.TariffCode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Call {
    TypeCall type;
    TariffCode tariffCode;
    Date timeBegin;
    Date timeEnd;
    long duration;


    Call(String str_type,String str_tariff, String str_timeBegin,String str_timeEnd){
        type = TypeCallFromStr(str_type);
        tariffCode = TarFromStr(str_tariff);
        timeBegin = TimeFromStr(str_timeBegin);
        timeEnd = TimeFromStr(str_timeEnd);
        duration = CalcDuration();
    }

    public Call() {
    }

    public Date GetTimeBegin(){
        return timeBegin;
    }


    public Date GetTimeEnd(){
        return timeEnd;
    }

    public long getDuration() {
        return duration;
    }

    public int getDurationMinute(){
        return (int)Math.ceil((double) getDuration()/60);
    }

    TariffCode GetTariffCode(){
        return tariffCode;
    }

    public TypeCall GetTypeCall(){
        return type;
    }

    public String GetTypeCode (){
        switch (type){
            case OUT: return "01";
            case IN:  return "02";
            default:  return "";
        }
    }

    long CalcDuration(){
        long t = ((timeEnd.getTime() - timeBegin.getTime())/1000);
        if (t >=0) return t;
        return t;
        //!!!!! throw new RuntimeException("Duration is negative");
    }

    public String DurationToString() {
        long secs = getDuration();
        long hour = secs / 3600,
                min = secs / 60 % 60,
                sec = secs / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
    TypeCall TypeCallFromStr(String str){

        if (str.equals("01")) return TypeCall.OUT;
        if (str.equals("02")) return TypeCall.IN;
        throw new RuntimeException("Error in call type detection");

    }

    TariffCode TarFromStr (String str){
        switch (str){
            case "03": return TariffCode._03;
            case "06": return TariffCode._06;
            case "11": return TariffCode._11;
            default: throw new RuntimeException("Error tariff detection");
        }
    }
    Date TimeFromStr(String str){
        try{
            return new SimpleDateFormat("YYYYMMDDHHmmss").parse(str);
        }catch(Exception e){
            System.out.println( "Start time conversion error" + e);
            throw new RuntimeException("Start time conversion error");
        }

    }
    int compare (Call c1, Call c2 ){
        return c1.timeBegin.compareTo(c2.timeBegin) ;

    }

}
