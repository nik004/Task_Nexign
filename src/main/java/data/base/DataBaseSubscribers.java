package data.base;

public interface DataBaseSubscribers {
    void CreateReportForAllSubscribers(DataBaseTariff dataBaseTariff);
    void ReadFileCDR (String filename);
}
