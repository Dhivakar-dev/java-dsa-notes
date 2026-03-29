package searchingAndSorting;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {

        int left = 1;
        int right = 1;

        for(int n: piles) {
            right = Math.max(right, n);
        }

        while(left<right) {
            int mid = left + (right-left)/2;
            if(canFinish(piles, mid, h)) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return left;

    }


    public boolean canFinish(int[] piles, int speed, int h) {

        int hrs = 0;

        for (int pile: piles) {
            hrs += Math.ceil((double) pile/speed);
        }

        return hrs <= h;

    }
}
