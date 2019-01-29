package JobScheduler;

/**
 * Created by pryor on 1/28/2019.
 */
public class Test {
    public static void main(String[] args) {
        PriorityQueue<Integer> ints = new PriorityQueue<>();

        int[] nums =        {9, 4, 1, 12, 11, 21};
        int[] priority =    {2, 40, 19, 25, 62, 1};

        for (int i = 0; i < nums.length; i++) {
            ints.enqueue(nums[i], priority[i]);
        }

        System.out.println(ints.toString());

    }
}
