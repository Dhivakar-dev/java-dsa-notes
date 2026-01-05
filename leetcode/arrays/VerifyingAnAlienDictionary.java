/* leetcode 953
* Verifying an Alien Dictionary*/


import java.util.HashMap;
import java.util.Map;

public class VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {

        Map<Character, Integer> map = new HashMap<>();

        for(int i=0;i<order.length();i++)
        {
            map.put(order.charAt(i),i);
        }

        for(int i=0; i<words.length-1; i++)
        {
            for(int j=0; j<words[i].length(); j++)
            {
                if(j>=words[i+1].length())
                {
                    return false;
                }

                if(words[i].charAt(j) != words[i+1].charAt(j))
                {
                    int currLet = map.get(words[i].charAt(j));
                    int nextLet = map.get(words[i+1].charAt(j));
                    if(currLet > nextLet)
                    {
                        return false;
                    }else{
                        break;
                    }
                }
            }
        }

        return true;

    }
}
