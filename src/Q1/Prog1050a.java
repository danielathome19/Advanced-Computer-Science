package Q1;
import java.io.*;
import java.util.*;
import java.text.*;


class SalesRecord {
    String[] fields;

    public SalesRecord(String[] data) { fields = data; }

    public double getProfit() { return Double.parseDouble(fields[13]); }
    public double getUnitsSold() { return Double.parseDouble(fields[8]); }
    public String getField(int index) { return fields[index]; }
}

public class Prog1050a {
    public static void main(String[] args) {
        var records = loadSalesData("Langdat/Lang1050.csv");

        if (records != null) {
            var moneyFormat = new DecimalFormat("$#,###.00");
            System.out.println("Sales to Europe: " + computeCount(records, 0, "Europe"));
            System.out.println("Cereal bought by Cambodia: " + computeUnitsSold(records, 1, "Cambodia", 2, "Cereal"));
            System.out.println("Total profit on Meat: " + moneyFormat.format(computeSum(records, 2, "Meat", 13)));
            System.out.println("High priority sales percentage: " + computePercentage(records, 4, "H") + "%");
            System.out.println("Fruits profit lost in 2012: " + moneyFormat.format(computeProfitLostIn2012(records, "Fruits")));
            System.out.println("High priority sales shipped more than 3 days late: " + computeHighPriorityLateSales(records));
            System.out.println("Country with highest profit on Personal Care: " + highestProfit(records, 2, "Personal Care"));
            System.out.println("Region that bought the most Snacks: " + computeMaxByField(records, 2, "Snacks", 0));
        }
    }

    public static List<SalesRecord> loadSalesData(String filePath) {
        var records = new ArrayList<SalesRecord>();
        try {
            var file = new Scanner(new File(filePath));
            file.nextLine();  // Skip header line
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] data = line.split(",");
                records.add(new SalesRecord(data));
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return records;
    }

    public static int computeCount(List<SalesRecord> records, int fieldIndex, String value) {
        int count = 0;
        for (var record : records)
            if (record.getField(fieldIndex).equalsIgnoreCase(value))
                count++;
        return count;
    }

    public static int computeCount(List<SalesRecord> records, int fieldIndex1, String value1, 
                                                              int fieldIndex2, String value2) {
        int count = 0;
        for (var record : records)
            if (record.getField(fieldIndex1).trim().equalsIgnoreCase(value1) &&
                record.getField(fieldIndex2).trim().equalsIgnoreCase(value2))
                count++;
        return count;
    }

    public static double computePercentage(List<SalesRecord> records, int fieldIndex, String value) {
        return ((double) computeCount(records, fieldIndex, value) / records.size()) * 100;
    }

    public static int computeUnitsSold(List<SalesRecord> records, int fieldIndex1, String value1, 
                                                                  int fieldIndex2, String value2) {
        int total = 0;
        for (var record : records)
            if (record.getField(fieldIndex1).trim().equalsIgnoreCase(value1) &&
                record.getField(fieldIndex2).trim().equalsIgnoreCase(value2))
                    total += record.getUnitsSold();
        return total;
    }

    public static double computeSum(List<SalesRecord> records, int fieldIndex, String value, int sumFieldIndex) {
        double sum = 0;
        for (var record : records)
            if (record.getField(fieldIndex).equalsIgnoreCase(value))
                sum += Double.parseDouble(record.getField(sumFieldIndex));
                return sum;
    }

    public static double computeProfitLostIn2012(List<SalesRecord> records, String itemType) {
        double lostProfit = 0;
        for (var record : records)
            if (record.getField(2).equalsIgnoreCase(itemType) && 
                record.getField(5).endsWith("2012"))
                lostProfit += Double.parseDouble(record.getField(13));
        return lostProfit;
    }

    public static int computeHighPriorityLateSales(List<SalesRecord> records) {
        int count = 0;
        var dateFormat = new SimpleDateFormat("M/d/yyyy");
        for (var record : records) {
            if (record.getField(4).equalsIgnoreCase("H")) {  // Check if it's a high-priority sale
                try {
                    Date orderDate = dateFormat.parse(record.getField(5)); // Parse Order Date
                    Date shipDate = dateFormat.parse(record.getField(7));  // Parse Ship Date
    
                    // Calculate the difference in days between order date and ship date
                    long diffInMillies = Math.abs(shipDate.getTime() - orderDate.getTime());
                    long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
    
                    if (diffInDays > 3) count++;
                } catch (ParseException e) { e.printStackTrace(); }
            }
        }
        return count;
    }

    public static String highestProfit(List<SalesRecord> records, int itemFieldIndex, String itemType) {
        String highestCountry = "No Data";
        double highestProfit = 0;
        for (var record : records) {
            if (record.getField(itemFieldIndex).equalsIgnoreCase(itemType)) {
                double profit = Double.parseDouble(record.getField(13));
                if (profit > highestProfit) {
                    highestProfit = profit;
                    highestCountry = record.getField(1);
                }
            }
        }
        return highestCountry;
    }

    public static String computeMaxByField(List<SalesRecord> records, int itemFieldIndex, 
                                           String itemType, int resultFieldIndex) {
        String topRegion = null;
        int maxCount = 0;
        var regions = new ArrayList<String>();

        // Get a list of all the unique regions in the dataset
        for (var record : records) {
            if (record.getField(itemFieldIndex).equalsIgnoreCase(itemType)) {
                String region = record.getField(resultFieldIndex);
                if (!regions.contains(region)) regions.add(region);
            }
        }

        // Count occurrences of each unique region
        for (var region : regions) {
            int count = 0;
            for (var record : records) {
                if (record.getField(itemFieldIndex).equalsIgnoreCase(itemType) &&
                    record.getField(resultFieldIndex).equalsIgnoreCase(region)) {
                    count++;
                }
            }
            // Keep track of the region with the highest count
            if (count > maxCount) {
                maxCount = count;
                topRegion = region;
            }
        }

        return topRegion;
    }
}
