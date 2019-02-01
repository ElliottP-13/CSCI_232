package JobScheduler;

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

    @Override
    public String toString(){
        return "" + job;
    }

}
