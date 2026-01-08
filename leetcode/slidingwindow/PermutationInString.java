package slidingwindow;

//leetcode 567

public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {

        if(s1.length() > s2.length())
        {
            return false;
        }

        int[] map1 = new int[26];
        int[] map2 = new int[26];

        for(int i=0; i<s1.length(); i++)
        {
            map1[s1.charAt(i)-'a']++;
            map2[s2.charAt(i)-'a']++;
        }

        for(int i=0; i<s2.length()-s1.length(); i++)
        {
            if(matches(map1,map2))
            {
                return true;
            }

            map2[s2.charAt(i+s1.length())-'a']++;
            map2[s2.charAt(i)-'a']--;

        }

        return matches(map1,map2);

    }

    public boolean matches(int[] arr, int[] brr)
    {
        for(int i=0; i<26; i++)
        {
            if(arr[i]!=brr[i])
            {
                return false;
            }
        }
        return true;
    }
}
