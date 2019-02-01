package Homework_1;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Elliott Pryor on 1/30/2019.
 * @project CSCI_232
 */
public class Creative_1 {
    public static void main(String[] args) {
        int n = 1000000;
        int[] nums = new int[n];
        Random rand  = new Random();
        for (int i = 0; i < n; i++) {//fill the array
            if(i != n/2)
                nums[i] = i - n/2;
        }
        for (int i = 0; i < n; i++) {//scramble
            int temp = nums[i];
            int s = rand.nextInt(n);
            nums[i] = nums[s];
            nums[s] = temp;
        }

        ////////////////////////////////////////////////////////
        //Two Sum Faster
        Arrays.sort(nums);
        int count_twoSum = 0;
        int trail = n-1;
        for (int i = 0; i < n; i++) {
            if(nums[i] > 0)//ends the loop at n/2 roughly
                break;

            while (nums[i] + nums[trail] > 0){
                trail--;
            }
            if(nums[i] + nums[trail] == 0){
                count_twoSum++;
            }

        }
        System.out.println("Two Sum Count: " + count_twoSum);


        for (int i = 0; i < n; i++) {//scramble
            int temp = nums[i];
            int s = rand.nextInt(n);
            nums[i] = nums[s];
            nums[s] = temp;
        }

        //////////////////////////////////////////////////////
        //Three Sum Faster

        Arrays.sort(nums);
        int count_threeSum = 0;
        for (int i = 0; i < n; i++) {
            int k = n - 1;
            for (int j = i + 1; j < n; j++) {
                int target = -(nums[i] + nums[j]);
                while (k > 0 && nums[k] > target) {
                    k--;
                }
                if (nums[k] == target && j < k)
                    count_threeSum++;

            }
        }

        System.out.println("Three Sum Count: " + count_threeSum);

    }
}
