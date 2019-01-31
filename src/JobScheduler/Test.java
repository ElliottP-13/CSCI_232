package JobScheduler;

import DataStructures.DLLPriorityQueue;
import DataStructures.Heap;

import java.util.Random;

/**
 * Created by pryor on 1/28/2019.
 */
public class Test {
    public static void main(String[] args) {
        Random rand = new Random();
        Heap<Integer> intss = new Heap<>(false);
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(100);
            intss.insertNode(n, n);
        }
        intss.removeHead();
        System.out.println(intss.toString());
    }
}
