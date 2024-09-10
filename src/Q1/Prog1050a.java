package Q1;
import java.io.*;
import java.util.*;
import java.text.*;

class SalesRecord {
    public String[] fields;
    public SalesRecord(String[] data) { fields = data; }
    public double getProfit() { return Double.parseDouble(fields[13]); }
    public double getUnitsSold() { return Double.parseDouble(fields[8]); }
}

// Region,Country,Item Type,Sales Channel,Order Priority,Order Date,Order ID,
//   Ship Date,Units Sold,Unit Price,Unit Cost,Total Revenue,Total Cost,Total Profit
public class Prog1050a {
    public static List<SalesRecord> loadSalesData(String filepath) {
        var records = new ArrayList<SalesRecord>();
        try {
            var file = new Scanner(new File(filepath));
            file.nextLine();  // Skip header line
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] data = line.split(",");
                records.add(new SalesRecord(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return records;
    }

    public static void main(String[] args) {
        var records = loadSalesData("Langdat/Lang1050.csv");
        if (records != null) {
            var moneyFormat = new DecimalFormat("$#,###.00");
            System.out.println("Sales to Europe: " +
                    computeCount(records, 0, "Europe"));
            System.out.println("Cereal bought by Cambodia: " +
                    computeUnitsSold(records, 1, "Cambodia", 2, "Cereal"));
            System.out.println("Total profit on Meat: " +
                    moneyFormat.format(computeSum(records, 2, "Meat", 13)));
            System.out.println("High priority sales percentage: " +
                    computePercentage(records, 4, "H") + "%");
            System.out.println("Fruits profit lost in 2012: " +
                    moneyFormat.format(computeProfitLostIn2012(records, "Fruits")));
            // TODO
        }
    }

    public static int computeCount(List<SalesRecord> records, int fIndex, String value) {
        int count = 0;
        for (var record : records)
            if (record.fields[fIndex].equalsIgnoreCase(value))
                count++;
        return count;
    }

    public static int computeCount(List<SalesRecord> records, int fIndex1, String value1,
                                                              int fIndex2, String value2) {
        int count = 0;
        for (var record : records)
            if (record.fields[fIndex1].equalsIgnoreCase(value1) &&
                record.fields[fIndex2].equalsIgnoreCase(value2))
                count++;
        return count;
    }

    public static double computePercentage(List<SalesRecord> records, int fIndex, String value) {
        return ((double) computeCount(records, fIndex, value) / records.size()) * 100;
    }

    public static int computeUnitsSold(List<SalesRecord> records, int fIndex1, String value1,
                                                                  int fIndex2, String value2) {
        int total = 0;
        for (var record : records)
            if (record.fields[fIndex1].equalsIgnoreCase(value1) &&
                    record.fields[fIndex2].equalsIgnoreCase(value2))
                total += (int) record.getUnitsSold();
        return total;
    }

    public static double computeSum(List<SalesRecord> records, int fIndex, String value,
                                                               int sumfIndex) {
        double sum = 0;
        for (var record : records)
            if (record.fields[fIndex].equalsIgnoreCase(value))
                sum += Double.parseDouble(record.fields[sumfIndex]);
        return sum;
    }

    public static double computeProfitLostIn2012(List<SalesRecord> records, String itemType) {
        double lostProfit = 0;
        for (var record : records)
            if (record.fields[2].equalsIgnoreCase(itemType) &&
                record.fields[5].endsWith("2012"))
                lostProfit += Double.parseDouble(record.fields[13]);
        return lostProfit;

    }
}
