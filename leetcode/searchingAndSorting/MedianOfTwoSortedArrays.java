package searchingAndSorting;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;

        int start = 0;
        int end = x;

        while(start<=end) {

            int px = (start+end)/2;
            int py = (x+y+1)/2 - px;

            int xl = px==0 ? Integer.MIN_VALUE : nums1[px-1];
            int xr = px==x ? Integer.MAX_VALUE : nums1[px];
            int yl = py==0 ? Integer.MIN_VALUE : nums2[py-1];
            int yr = py==y ? Integer.MAX_VALUE : nums2[py];

            if(xl<=yr && yl<=xr) {
                if((x+y)%2 == 0) {
                    return ((double) Math.max(xl,yl) + Math.min(xr,yr))/2;
                } else {
                    return ((double) Math.max(xl,yl));
                }
            } else if(xl >= yr) {
                end = px-1;
            } else {
                start = px+1;
            }

        }

        return 0;
    }
}
