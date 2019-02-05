package Homework_1;

import DataStructures.Heap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pryor on 1/28/2019.
 */
public class Creative_2 {
    public static void main(String[] args) {
        File output = new File("duplicates.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(output);
            writer.write(""); //empty's the text doc
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = 1000000;

        Heap<Point> points = new Heap<>(false);
        ArrayList<int[]> duplicates = new ArrayList<>();

        //Fills the heap with (n^3, n, 0) points
        for (int i = 0; i <= n; i++) {
            points.insertNode(new Point(i, 0), (long) i*i*i);
        }

        //stores the previous values
        long prev = -1;
        int[] prevPoints = {-1, -1};

        System.out.println("Size: " + points.size());

        long i = 1;
        long j = 1;
        int counter = 0;
        StringBuilder appendMe = new StringBuilder();

        //while the heap isn't empty
        while (points.size() > 0){
            try {
                Point p = points.removeHead();//removes the first node
                //System.out.println(p.toString());

                i = p.i;
                j = p.j;

                //checks to see if the first node is equal to the previous one
                if(i*i*i + j*j*j == prev){
                    //System.out.println("Dup");
                    counter++;

                    appendMe.append("A: " + prevPoints[0] + " B: " + prevPoints[1] + " C: " + p.i + " D: " + p.j);
                    appendMe.append('\n');

                    if(counter % 10000 == 0){
                        System.out.println("Counter: " + counter/10000);
                        writer = new FileWriter(output, true);
                        writer.append(appendMe.toString());
                        writer.close();
                        appendMe = new StringBuilder();
                    }


                    int[] dup = {prevPoints[0], prevPoints[1], p.i, p.j};
                    duplicates.add(dup);//Add it to the list of duplicate points
                }



                prev = i*i*i + j*j*j;//stores the previous node
                prevPoints[0] = p.i;
                prevPoints[1] = p.j;

                //adds it back into the heap
                if(p.j < p.i - 1) {//NOTE: I changed it from p.j<n to avoid double counting
                    j += 1;
                    points.insertNode(new Point(p.i, p.j + 1), (i * i * i + j * j * j));
                }
                //System.out.println(points.toString());
            } catch (NullPointerException ew){
                System.out.println("Null Pointer");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Im DONE!");

        try {
            writer = new FileWriter(output, true);
            writer.append(appendMe.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int[] arr: duplicates){
            System.out.println("A: " + arr[0] + " B: " + arr[1] + " C: " + arr[2] + " D: " + arr[3]);
        }
    }

    public File createFileIfNotExists(String filePath) { // makes a file
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
        return file;
    }

}
