package Homework_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This is for Problem 3.1.1 on Homework 2
 * Created by pryor on 2/19/2019.
 */
public class GPACalculator {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(args[0])); // PASS THE INPUT FILE AS A COMMAND LINE ARGUMENT
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TreeMap<String, Double> grades = new TreeMap<>();
        grades.put("A+", 4.33);
        grades.put("A", 4.00);
        grades.put("B+", 3.33);
        grades.put("B", 3.00);
        grades.put("B-", 2.67);
        grades.put("C+", 2.33);
        grades.put("C", 2.00);
        grades.put("C-", 1.67);
        grades.put("D", 1.00);
        grades.put("F", 0.00);

        scanner.useDelimiter("\\z");
        String readString = scanner.next();

        //INPUT FILE GRADES MUST BE ON NEW LINE
        String[] data = readString.split("\n");//Assume that the data is on a new line
        //USE: String[] data = readString.split(", "); if it is comma delimited instead

        double gpa = 0;
        for(String grade: data){
            gpa += grades.get(grade);
        }

        System.out.println("GPA: " + (gpa/data.length));
    }
}
