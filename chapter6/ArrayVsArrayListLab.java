package chapter6;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ArrayVsArrayListLab {

    

    protected static int listRandomAccess(int indices[], ArrayList<Integer> list) {
        int result = 0;
        for (int i = 0; i < indices.length; i++) {
            result += list.get(indices[i]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
       /* long start = System.nanoTime();
        System.out.println("hello, world");
        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("println: " + elapsed + "ns");
        start = System.nanoTime();
        arrayTest(30_000);
        finish = System.nanoTime();
        elapsed = finish - start;
        System.out.println("arrayTest: " + elapsed + "ns"); */
        int arr[] = DataLoader.loadArray("numbers.txt");
        ArrayList<Integer> list = DataLoader.loadArrayList("numbers.txt");
        Random r = new Random();
        int indices[] = new int[100_000];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = r.nextInt(arr.length);
        }

        PrintWriter fileOut = new PrintWriter(new File("results.csv"));
        Target tests[] = new Target[8];
        double testAverages[]=new double[8];

        tests[0] = new ArrayRandom(arr, new ArrayList<>(list), "array,random_access");
        tests[1] = new ListRandom(arr, new ArrayList<>(list), "arraylist,random_access");
        tests[2] = new ArrayAppend(arr, new ArrayList<>(list), "array,append");
        tests[3] = new ListRemove(arr, new ArrayList<>(list), "arraylist,append");
        tests[4] = new ArrayInsert(arr, new ArrayList<>(list), "array,insert_front");
        tests[5] = new ListRandom(arr, new ArrayList<>(list), "arraylist,insert_front");
        tests[6] = new ArrayRemove(arr, new ArrayList<>(list), "array,remove_front");
        tests[7] = new ListRemove(arr, new ArrayList<>(list), "arraylist,remove_front");


        for (int i = 0; i < tests.length; i++) {
            Target target = tests[i];
        if (target != null) {
            testAverages[i] = target.runTests(indices);
            target.writeResults(fileOut);
        }
    }


    if (testAverages[0] < testAverages[1]) {
        System.out.println("Operation: random_access array avg: " + testAverages[0]
                + " ms arraylist avg: " + testAverages[1] + " ms Therefore winner: array");
    } else {
        System.out.println("Operation: random_access array avg: " + testAverages[0]
                + " ms arraylist avg: " + testAverages[1] + " ms Therefore winner: arraylist");
    }


    if (testAverages[2] < testAverages[3]) {
        System.out.println("Operation: append array avg: " + testAverages[2]
                + " ms arraylist avg: " + testAverages[3] + " ms Therefore winner: array");
    } else {
        System.out.println("Operation: append array avg: " + testAverages[2]
                + " ms arraylist avg: " + testAverages[3] + " ms Therefore winner: arraylist");
    }


    if (testAverages[4] < testAverages[5]) {
        System.out.println("Operation: insert_front array avg: " + testAverages[4]
                + " ms arraylist avg: " + testAverages[5] + " ms Therefore winner: array");
    } else {
        System.out.println("Operation: insert_front array avg: " + testAverages[4]
                + " ms arraylist avg: " + testAverages[5] + " ms Therefore winner: arraylist");
    }


    if (testAverages[6] < testAverages[7]) {
        System.out.println("Operation: remove_front array avg: " + testAverages[6]
                + " ms arraylist avg: " + testAverages[7] + " ms Therefore winner: array");
    } else {
        System.out.println("Operation: remove_front array avg: " + testAverages[6]
                + " ms arraylist avg: " + testAverages[7] + " ms Therefore winner: arraylist");
    }
        

        fileOut.close();
        
    }
}
