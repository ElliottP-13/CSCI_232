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
        for (int i = 0; i <= n; i++) {
            points.insertNode(new Point(i, 0), (long) Math.pow(i, 3));
        }
        long prev = -1;
        int[] prevPoints = {-1, -1};
        while (points.size() > 0){
            try {
                Point p = points.removeHead();

                if((long) (Math.pow(p.i, 3) + Math.pow(p.j + 1,3)) == prev){
                    int[] dup = {prevPoints[0], prevPoints[1], p.i, p.j};
                    duplicates.add(dup);
                }

                prev = (long) (Math.pow(p.i, 3) + Math.pow(p.j + 1,3));
                prevPoints[0] = p.i;
                prevPoints[1] = p.j;

                if(p.j < p.i)
                    points.insertNode(new Point(p.i, p.j + 1), (long) (Math.pow(p.i, 3) + Math.pow(p.j + 1,3)));
                //System.out.println(points.toString());
            } catch (NullPointerException ew){
                System.out.println(points.size());
                break;
            }

        }
        System.out.println("Im DONE!");
        System.out.println(duplicates.toString());
    }
}
