# Leetcode 1: Two Sum

## Problem
Given an integer array `nums` and an integer `target`, return the indices of the two numbers whose sum is equal to `target`. Each input has exactly one solution, and you may not use the same element twice. Return the answer in any order.

## Examples
- **Input:** nums = [2,7,11,15], target = 9  
  **Output:** [0,1]  
  **Explanation:** nums[0] + nums[1] = 2 + 7 = 9.
- **Input:** nums = [3,2,4], target = 6  
  **Output:** [1,2]
- **Input:** nums = [3,3], target = 6  
  **Output:** [0,1]

## Constraints
- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- Exactly one valid answer exists

## Approaches

### 1) Brute force (O(n^2))
Check every pair `(i, j)` with `i < j`. Return the first pair where `nums[i] + nums[j] == target`.

### 2) Hash map (O(n) time, O(n) space)
Use a map from value to index. For each `num` at index `i`:
1. Let `need = target - num`.
2. If `need` is already in the map, return `[map.get(need), i]`.
3. Otherwise, store `num -> i` in the map and continue.

Why it works: when you reach `num`, you check whether the complementary value `need` was seen earlier. Since each index is processed once, time is linear.

## Complexity (hash map approach)
- Time: $O(n)$
- Space: $O(n)$ for the map

## Reference Implementation (Java)
```java
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[] { seen.get(need), i };
            }
            seen.put(nums[i], i);
        }
        return new int[0]; // Problem guarantees a solution.
    }
}
```