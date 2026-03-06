package arrays;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {

        if(nums.length == k) {
            return nums;
        }

        HashMap<Integer,Integer> map =  new HashMap<>();

        for(int n: nums) {
            map.put(n,map.getOrDefault(n,0)+1);
        }

        Queue<Integer> heap = new PriorityQueue<>(
                (a,b)->map.get(a)-map.get(b)
        );

        for(int n: map.keySet()) {
            heap.add(n);

            if(heap.size()>k) {
                heap.poll();
            }
        }

        int[] ans = new int[k];

        for(int i=0; i<k; i++) {
            ans[i] = heap.poll();
        }

        return ans;

    }
}
