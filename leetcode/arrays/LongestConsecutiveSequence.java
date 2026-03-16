package arrays;//leetcode 128


import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        if(nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();

        for( int num: nums) {
            set.add(num);
        }

        int lcs = 1;

        for(int num: set) {
            if(set.contains(num-1)) {
                continue;
            } else {
                int currNum = num;
                int currSub = 1;

                while( set.contains(currNum+1)) {
                    currNum++;
                    currSub++;
                }

                lcs =  Math.max(lcs,currSub);
            }


        }

        return lcs;

    }
}
