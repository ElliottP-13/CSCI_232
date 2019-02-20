package Lab2;

/**
 * Created by pryor on 2/15/2019.
 */
public class Bin implements Comparable<Bin>{
    int capacity;
    public Bin(int capacity){
        this.capacity = capacity;
    }

    public void add(int item){
        capacity -= item;
    }

    @Override
    public int compareTo(Bin o) {
        if (this.capacity == o.capacity)
            return 0;
        return this.capacity > o.capacity ? 1: -1;
    }
}
