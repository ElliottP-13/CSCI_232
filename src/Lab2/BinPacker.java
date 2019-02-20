package Lab2;

import Assets.ReadWrite;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by pryor on 2/15/2019.
 */
public class BinPacker {

    private class Crate{
        ArrayList<Bin> bins = new ArrayList<>();
        public Crate(Bin b){
            bins.add(b);
        }
        public void add(Bin b){
            bins.add(b);
        }
        public Bin get(){
            return bins.remove(0);
        }
    }

    public static void main(String[] args) {
        ReadWrite r = new ReadWrite();
        int capacity = Integer.parseInt(args[0]);
        String input = r.readEntireFile(args[1]);
        String[] in = input.split("\n");
        int[] data = new int[in.length]; //Stores the stuff to be packed
        for(int i = 0; i < in.length; i++){
            data[i] = Integer.parseInt(in[i]);
        }

        //FIRST FIT PROBLEM
        ArrayList firstFit = new ArrayList<Bin>();
        Bin current = new Bin(capacity);
        for(int i : data){
            if(current.capacity < i){
                firstFit.add(current);
                current = new Bin(capacity);
            }
            current.add(i);
        }
        firstFit.add(current); //Adds the last one
        System.out.println("First fit size: " + firstFit.size());

        //Best fit decreasing
        TreeMap<Integer, Crate> bestFitDecreasing = new TreeMap<>();
        for(int i : data){
            //Crate c = bestFitDecreasing.ceilingEntry(i);
        }
    }
}
