package arrays;//leetcode 128


import java.util.HashSet;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        if(nums.length == 0)
        {
            return 0;
        }

        HashSet<Integer> numSet = new HashSet<>();

        for(int i=0; i<nums.length; i++)
        {
            numSet.add(nums[i]);
        }

        int longestSub = 1;

        for(int num : numSet)
        {
            if(numSet.contains(num-1))
            {
                continue; //till we find the start of a sequence
            }else{
                int currNum = num; //start of a sequence
                int currSub = 1;
                while(numSet.contains(currNum+1))
                {
                    currNum++;
                    currSub++;
                }
                longestSub = Math.max(currSub,longestSub);
            }
        }



        return longestSub;

    }
}
