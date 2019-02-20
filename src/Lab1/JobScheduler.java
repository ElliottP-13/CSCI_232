package Lab1;

import Assets.ReadWrite;
import DataStructures.Heap;

/**
 * Created by pryor on 1/18/2019.
 */
public class JobScheduler {
    public static void main(String[] args) {
        ReadWrite r = new ReadWrite();//Java.IO helper class
        String[] in = r.readEntireFile(args[0]).split("\r?\n");//splits on newline
        Job[] jobs = new Job[in.length];

        for (int i = 0; i < in.length; i++) {//reads in the data
            String[] temp = in[i].split(" ");
            int[] data = new int[temp.length];
            for (int j = 0; j < temp.length; j++){
                data[j] = Integer.parseInt(temp[j]);
            }
            jobs[i] = new Job(data[0], data[1], data[2], data[3]);
        }

        sortJobs(jobs);//sorts the jobs by arrival time

        int processors = 1;//assumes 1 processor

        try { //If there are more processors use them
            processors = Integer.parseInt(args[1]);
            System.out.println("Number of processors: " + processors);
        } catch (NumberFormatException e){
            //it wasn't a number
            System.out.println("Args: " + args[1] + " was not a number");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No extra processors were passed in... assuming 1 processor");
        }

        Job[] threadedJobs = new Job[processors];//Make an array to hold jobs being processed by multiple processors
        //Use an array to store the jobs to avoid having multiple processors run the same job at once

        Heap<Job> heap = new Heap();//store the ongoing processors

        StringBuilder runtime = new StringBuilder("\n\nJobs run by second\n");//Store what job is running per seccond
        StringBuilder finishTime = new StringBuilder("Jobs Finished\n");//Store the finish print statements

        int nextJob = 0;//index pointing to the next job to arrive
        int seconds = 0;//time elapsed

        boolean run = true;//tells the loop when to stop

        //While there are no new jobs and there are jobs left to be finished
        while (run){
            //Adds the jobs when they get received
            if(nextJob < jobs.length){//makes sure no ArrayindexOutOfBounds Exception
                while(seconds >= jobs[nextJob].arrival){//checks to see if the next job arrived
                    heap.insertNode(jobs[nextJob], jobs[nextJob].priority);
                    nextJob++;
                    if(nextJob >= jobs.length) {
                        break;
                    }
                }
            } else{
                run = !heap.isEmpty();
            }
            if(!heap.isEmpty()){//Run the jobs if there are any
                int threads;//number of things to run this second
                if(heap.size() >= processors){//if we have more tasks than processors use all our processors
                    threads = processors;
                }else{
                    threads = heap.size();//if we have more processors, run all the tasks
                }

                for(int i = 0; i < threads; i++){//runs the tasks
                    Job j = heap.removeHead();//job being run
                    threadedJobs[i] = j;//stores it so if it doesn't finish we can add it back later
                    if(j.startTime == -1){//if this is its first run, set the start time
                        j.startTime = seconds;
                    }

                    if(j.finished()){//if the job is finished
                        threadedJobs[i] = null;//don't add anything back to the array
                        j.endTime = seconds;
                        finishTime.append(j.toString() + "\n");
                    } else{//Job didn't finish
                        runtime.append("Second: " + seconds + " -- " + j.toString() + "\n");
                        threadedJobs[i] = j;//add it back into the array so it can be put in the heap
                    }

                    j.duration--;//subtract 1 time from the duration
                }
                for(int i = 0; i < threads; i++){//adds the jobs back to the heap
                    if(threadedJobs[i] != null){
                        heap.insertNode(threadedJobs[i], threadedJobs[i].priority);
                    }
                }
            }

            seconds++;//one time elapsed
        }

        //WRITES THE OUTPUT TO output.txt
        System.out.print(finishTime.toString() + runtime.toString());
        r.overwriteFileWithString(finishTime.toString() + runtime.toString(), r.createFileIfNotExists("output.txt"));

    }

    //Insertion sort the jobs based on arrival time
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
