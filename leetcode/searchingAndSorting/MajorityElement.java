package searchingAndSorting;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public int majorityElement(int[] nums) {

        Map<Integer,Integer> map = new HashMap<>();

        for(int n: nums) {
            map.put(n,map.getOrDefault(n,0)+1);
        }

        Map.Entry<Integer,Integer> major = null;

        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(major == null || major.getValue() < entry.getValue()) {
                major = entry;
            }
        }

        return major.getKey();

    }
}
