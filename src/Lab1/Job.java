package Lab1;

/**
 * Created by pryor on 1/18/2019.
 */
public class Job {
    int job;
    int priority;
    int arrival;
    int duration;

    int startTime = -1;
    int endTime;

    public Job(int j, int p, int a, int d){
        job = j;
        priority = p;
        arrival = a;
        duration = d;
    }

    /**
     *
     * @return True if the duration is 0
     */
    public boolean finished(){
        return duration <= 0;
    }

    @Override
    public String toString(){
        if(finished()){
            return "Finished Job: " + job + ", Priority: " + priority + ", Arrival Time: " + arrival
                    + "\n\t\tStart Time: " + startTime + ", End Time: " + endTime
                    + "\n\t\tWaiting Time: " + (startTime - arrival) + ", Execution Time: " + (endTime - startTime);
        }
        return "Job: " + job + ", Priority: " + priority + ", Arrival Time: " + arrival
                + "\n\t\tTime Left: " + duration + '\n';
    }

}
