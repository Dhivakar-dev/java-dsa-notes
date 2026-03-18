package slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {

        if(s==null || s.length() == 0) {
            return 0;
        }

        if(s.length() == 1) {
            return 1;
        }

        int left = 0;
        int right = 0;
        int ans = 0;

        Set<Character> set = new HashSet<>();

        while(right<s.length()) {
            char c = s.charAt(right);
            while(set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            ans = Math.max(ans,right-left+1);
            right ++;
        }

        return ans;

    }
}
