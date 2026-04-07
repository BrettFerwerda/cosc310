package chapter14;

import java.util.ArrayList;
import java.util.List;
import json.BikeDataRecord;
import json.BikeDataReader;
import chapter14.Sorting;
import chapter14.Searching;

public class Lab3Assignment {
    public static void main(String[] args) throws Exception {
        // ======== Load all 4 days of data ========
        ArrayList<BikeDataRecord> records = new ArrayList<>();
        records.addAll(BikeDataReader.parse("json/day1.json"));
        records.addAll(BikeDataReader.parse("json/day2.json"));
        records.addAll(BikeDataReader.parse("json/day3.json"));
        records.addAll(BikeDataReader.parse("json/day4.json"));

        // ======== QUESTION 1: Top 10 steepest elevation points ========
        BikeDataRecord.sortCriteria = 4; // 4 = altitude
        List<BikeDataRecord> sortedByAlt = Sorting.mergeSort(records);

        System.out.println("=== Top 10 Elevation Points ===");
        int count = 0;
        for (int i = sortedByAlt.size() - 1; i >= 0 && count < 10; i--) {
            System.out.println(sortedByAlt.get(i));
            count++;
        }

        // ======== QUESTION 2: Find all timestamps where speed > 16 m/s ========
        // Option 1: Use Java streams
        List<BikeDataRecord> highSpeedPoints = records.stream()
                .filter(r -> r.getSpeed() > 16.0f)
                .toList();

        System.out.println("\n=== High-Speed Points (speed > 16 m/s) ===");
        for (BikeDataRecord r : highSpeedPoints) {
            System.out.println("Timestamp: " + r.getTimestamp() + ", Speed: " + r.getSpeed() + ", Heartrate: " + r.getHeartrate());
        }

        // Optional: calculate average heartrate for high-speed points
        int totalHR = highSpeedPoints.stream().mapToInt(BikeDataRecord::getHeartrate).sum();
        int avgHR = highSpeedPoints.size() > 0 ? totalHR / highSpeedPoints.size() : 0;
        System.out.println("Average Heartrate for High-Speed Points: " + avgHR);
    }
}
