package Homework_2;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * This is for Problem 3.1.1 on Homework 2
 * Created by pryor on 2/19/2019.
 */
public class GPACalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        scanner.useDelimiter("\\Z");
        String readString = scanner.next();
        String[] data = readString.split("\n");//Assume that the data is on a new line

        double gpa = 0;
        for(String grade: data){
            gpa += grades.get(grade);
        }

        System.out.println("GPA: " + (gpa/data.length));
    }
}
