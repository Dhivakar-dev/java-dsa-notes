package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {

        if(s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> mapT= new HashMap<>();
        Map<Character, Integer> sub= new HashMap<>();
        int l=0;
        int r=0;
        int[] ans = {-1,0,0};

        for(int i=0; i<t.length(); i++) {
            mapT.put(t.charAt(i),mapT.getOrDefault(t.charAt(i),0)+1);
        }

        int required = mapT.size();
        int created = 0;

        for(r=0; r<s.length(); r++) {
            char c = s.charAt(r);
            sub.put(c, sub.getOrDefault(c,0)+1);

            if(mapT.containsKey(c) && sub.get(c).intValue() == mapT.get(c).intValue()) {
                created++;
            }
            while(l<=r && required == created) {

                c = s.charAt(l);

                if(ans[0] == -1 || ans[0] > r-l+1) {
                    ans[0] = r-l+1;
                    ans[1] = l;
                    ans[2] = r;
                }


                sub.put(c,sub.get(c)-1);


                if(mapT.containsKey(c) && sub.get(c).intValue() < mapT.get(c).intValue()) {
                    created--;
                }

                l++;

            }
        }

        if(ans[0] == -1) {
            return "";
        }

        return s.substring(ans[1], ans[2]+1);

    }
}
