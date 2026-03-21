package twoPointer;

public class TrappingRainWater {

    public int trap(int[] height) {

        int left = 0;
        int right = height.length-1;
        int lmax = height[left];
        int rmax = height[right];
        int total = 0;

        while(left < right) {
            if(height[left] < height[right]) {
                lmax = Math.max(lmax, height[left]);
                if(lmax-height[left]>0) {
                    total += lmax-height[left];
                }
                left++;
            }
            else {
                rmax = Math.max(rmax, height[right]);
                if(rmax-height[right]>0) {
                    total += rmax-height[right];
                }

                right--;
            }
        }

        return total;


    }
}
