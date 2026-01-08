package arrays;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {

        int contains = 0;

        int n=nums.length;

        for(int i=0; i<nums.length; i++)
        {
            if(nums[i]==1) //check if 1 is present
            {
                contains++;
                break;
            }
        }

        if(contains == 0) //if 1 is not present, return 1
        {
            return 1;
        }

        for(int i=0; i<nums.length; i++) //replace negative numbers, zeros and numbers greater than n by 1s
        {
            if(nums[i]<=0 || nums[i]>n)
            {
                nums[i]=1;
            }
        }

        for(int i=0; i<nums.length; i++)
        {
            int a = Math.abs(nums[i]);
            if(a==n)
            {
                nums[0] = -Math.abs(nums[0]);
            }
            else{
                nums[a] = -Math.abs(nums[a]);
            }
        }

        for(int i=1; i<nums.length; i++)
        {
            if(nums[i]>0)
            {
                return i;
            }
        }

        if(nums[0]>0)
        {
            return n;
        }

        return n+1;
    }
}
