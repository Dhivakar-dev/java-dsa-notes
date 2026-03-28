package searchingAndSorting;

public class SortColors {

    public void sortColors(int[] nums) {

        int low = 0;
        int high = nums.length-1;

        int i=0;

        while(i<=high) {
            if(nums[i] == 0) {
                swap(nums, i, low);
                low++;
                i++;
            }
            else if(nums[i] == 2) {
                swap(nums, i, high);
                high--;
            }
            else {
                i++;
            }


        }

    }

    public void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }
}
