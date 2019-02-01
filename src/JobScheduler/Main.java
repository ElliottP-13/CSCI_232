package JobScheduler;

import Assets.ReadWrite;
import DataStructures.DLLPriorityQueue;
import DataStructures.Heap;

import java.util.Arrays;

/**
 * Created by pryor on 1/18/2019.
 */
public class Main {
    public static void main(String[] args) {
        ReadWrite r = new ReadWrite();
        String[] in = r.readEntireFile(args[0]).split("\r?\n");
        Job[] jobs = new Job[in.length];
        for (int i = 0; i < in.length; i++) {
            String[] temp = in[i].split(" ");
            int[] data = new int[temp.length];
            for (int j = 0; j < temp.length; j++){
                data[j] = Integer.parseInt(temp[j]);
            }
            jobs[i] = new Job(data[0], data[1], data[2], data[3]);
        }

        sortJobs(jobs);

        Heap<Job> heap = new Heap();

        boolean run = true;
        int nextJob = 0;
        int seconds = 0;

        while (run){
            //Adds the jobs when they get received
            if(nextJob < jobs.length){
                if(seconds == jobs[nextJob].arrival && nextJob < jobs.length){
                    heap.insertNode(jobs[nextJob], jobs[nextJob].priority);
                    nextJob++;

                }
            }
            else if (nextJob == jobs.length && heap.isEmpty()){
                run = false;
            }

            if(!heap.isEmpty()){
                if(heap.getHead().startTime == -1){
                    heap.getHead().startTime = seconds;
                }
                if(heap.getHead().duration-- <= 0){
                    Job j = heap.removeHead();
                    j.endTime = seconds;
                    System.out.println("Finished Job " + j.job);
                    System.out.println("Waiting Time for job " + j.job + ": " + (j.startTime - j.arrival));
                    System.out.println("Execution Time for job " + j.job + ": " + (j.endTime - j.startTime));
                }
            }

            seconds++;
        }
    }

    private static void sortJobs(Job[] array){
        for (int i = 1; i < array.length; i++) {
            Job curr = array[i];
            int j = i;
            while (j > 0 && array[j - 1].arrival > curr.arrival) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = curr;
        }
    }

}
