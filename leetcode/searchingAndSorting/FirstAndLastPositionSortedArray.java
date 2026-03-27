package searchingAndSorting;

public class FirstAndLastPositionSortedArray {

    public int[] searchRange(int[] nums, int target) {

        int first = binarySearch(nums, target, true);

        if( first == -1) {
            return new int[]{-1,-1};
        }

        int last = binarySearch(nums, target, false);

        return new int[]{first,last};

    }

    public int binarySearch(int[] nums, int target, boolean first) {
        int s = 0;
        int e = nums.length-1;

        while(s<=e) {
            int m = s + (e-s)/2;

            if(nums[m] == target) {

                if(first) {
                    if(s == m || nums[m-1] != target) {
                        return m;
                    }

                    e = m-1;
                }
                else {
                    if(e == m || nums[m+1] != target) {
                        return m;
                    }

                    s = m+1;
                }

            }
            else if(target > nums[m]) {
                s = m+1;
            }
            else {
                e = m-1;
            }
        }

        return -1;
    }
}
