package slidingwindow;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {


        int[] frequency = new int[26];

        int left = 0;
        int ans = 0;
        int maxFq = 0;

        for(int right = 0; right<s.length(); right++)
        {
            maxFq = Math.max(maxFq,++frequency[s.charAt(right)-'A']);
            if(right-left+1-maxFq>k)
            {
                frequency[s.charAt(left)-'A']--;
                left++;

            }

            ans = Math.max(ans,right-left+1);
        }

        return ans;

    }
}
