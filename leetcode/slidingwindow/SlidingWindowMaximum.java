package slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums == null || nums.length == 0 || k<=0) {
            return new int[0];
        }

        int n = nums.length;
        int[] ans = new int[n-k+1];

        Deque<Integer> deque = new LinkedList<>();

        for(int i=0; i<n; i++) {

            while (!deque.isEmpty() && deque.peek()<i-k+1) {
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()]<nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);

            if(i>=k-1) {
                ans[i-k+1] = nums[deque.peek()];
            }



        }

        return ans;

    }
}
