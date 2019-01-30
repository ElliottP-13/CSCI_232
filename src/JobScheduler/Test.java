package JobScheduler;

import java.util.Random;

/**
 * Created by pryor on 1/28/2019.
 */
public class Test {
    public static void main(String[] args) {
//        DLLPriorityQueue<Integer> ints = new DLLPriorityQueue<>();
//
//        int[] nums =        {9, 4, 1, 12, 11, 21};
//        int[] priority =    {2, 40, 19, 25, 62, 1};
//
//        Random rand = new Random();
//        long start = System.currentTimeMillis();
//
//        for (int i = 0; i < 1000000; i++) {
//            ints.enqueue(rand.nextInt(100), rand.nextInt(100));
//        }
//        System.out.println("Filling values took: " + (start - System.currentTimeMillis()));
//        System.out.println(ints.toString());
        Heap<Integer> ints = new Heap<>();
        int[] nums =    {9, 4, 1, 12, 11, 21, 5, 2, 27};
        for (int n: nums) {
            ints.insertNode(n, n);
        }
        ints.removeHead();
        System.out.println(ints.toString());
    }
}
