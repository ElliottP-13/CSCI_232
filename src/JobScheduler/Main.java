package JobScheduler;

/**
 * Created by pryor on 1/18/2019.
 */
public class Main {
    public static void main(String[] args) {
        String[] in = args[0].split("\n");
        Job[] jobs = new Job[in.length];
        for (int i = 0; i < in.length; i++) {
            String[] temp = in[i].split(" ");
            int[] data = new int[4];
            for (int j = 0; j < 4; j++){
                data[j] = Integer.parseInt(temp[j]);
            }
            jobs[i] = new Job(data[0], data[1], data[2], data[3]);
        }

        sortJobs(jobs);

        PriorityQueue<Job> queue = new PriorityQueue<>();

        boolean run = true;
        int nextJob = 0;
        int seconds = 0;

        while (run){
            //Adds the jobs when they get received
            if(seconds == jobs[nextJob].arrival && nextJob < jobs.length){
                queue.enqueue(jobs[nextJob], jobs[nextJob].priority);
                nextJob++;
            } else if (nextJob >= jobs.length && queue.isEmpty()){
                run = false;
            }

            if(!queue.isEmpty()){
                System.out.println("Working on Job: " + queue.first().job);
                if(queue.first().startTime == -1){
                    queue.first().startTime = seconds;
                }
                if(queue.first().duration-- <= 0){
                    Job j = queue.dequeue();
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
