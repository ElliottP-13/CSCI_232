package Homework_1;

import DataStructures.Heap;

import java.util.ArrayList;

/**
 * Created by pryor on 1/28/2019.
 */
public class Creative_2 {
    public static void main(String[] args) {
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

        //while the heap isn't empty
        while (points.size() > 0){
            try {
                Point p = points.removeHead();//removes the first node
                //System.out.println("(" + (Math.pow(p.i, 3) + Math.pow(p.j + 1,3)) + ", " + p.i + ", " + p.j + ")");

                //checks to see if the first node is equal to the previous one
                if((long) (Math.pow(p.i, 3) + Math.pow(p.j + 1,3)) == prev){
                    //System.out.println("Hey I found a duplicate");
                    int[] dup = {prevPoints[0], prevPoints[1], p.i, p.j};
                    duplicates.add(dup);//Add it to the list of duplicate points
                }

                i = p.i;
                j = p.j;

                prev = i*i*i + j*j*j;//stores the previous node
                prevPoints[0] = p.i;
                prevPoints[1] = p.j;

                //adds it back into the heap
                if(p.j < p.i - 1)//NOTE: I changed it from p.j<n to avoid double counting
                    j+=1;
                    points.insertNode(new Point(p.i, p.j + 1), (i*i*i + j*j*j));

                //System.out.println(points.toString());
            } catch (NullPointerException ew){
                System.out.println("Null Pointer");
                break;
            }

        }
        System.out.println("Im DONE!");
        for(int[] arr: duplicates){
            System.out.println("A: " + arr[0] + " B: " + arr[1] + " C: " + arr[2] + " D: " + arr[3]);
        }
    }
}
