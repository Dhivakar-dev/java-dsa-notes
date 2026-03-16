package slidingwindow;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {

        int[] map = new int[26];
        int l=0;
        int r=0;
        int ans=0;
        int maxFq=0;

        for(r=0; r<s.length(); r++) {
            maxFq = Math.max(maxFq,++map[s.charAt(r)-'A']);
            if(r-l+1-maxFq>k) {
                map[s.charAt(l)-'A']--;
                l++;
            }

            ans = Math.max(ans, r-l+1);
        }

        return ans;

    }
}
