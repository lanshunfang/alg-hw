package org.neu.alg.hw.hw3_3;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {

  /**
   * Method to sum two integers which only demands time complexity O(n);
   *
   * @return {M,N} - which arr[M] + arr[N] == n
   * @example params: { {2,7,11,15}, 9 }; return {0, 1}
   */
  public int[] twoSum(int[] nums, int target) {
    final HashMap<Integer, Integer> lookupTable = new HashMap<>();
    int[] result = new int[2];

    boolean isPairFound = false;

    for (int i = 0; i < nums.length; i++) {
      // construct a lookup table which record down the matched pair to return
      // e.g. [1, 2, 3], given target == 4
      // lookupTable: {3 => 0, 2 => 1, 1 => 2}
      // so, in the next iteration, we could know  lookupTable.get(1) == 2 (array index 2, nums[2] == 3)

      final int currentElement = nums[i];
      isPairFound = lookupTable.containsKey(currentElement);

      if (isPairFound) {
        final int pairFound = lookupTable.get(currentElement);
        result[0] = i;
        result[1] = pairFound;
        break;
      }
      lookupTable.put(target - nums[i], i);
    }

    if (isPairFound) {
      return result;
    }

    return null;

  }

  static void print(int[] arr, int n, int[] result) {
    String msg;
    if (result == null) {
      msg = "[INFO] The result is null. No pair of integer found";
    } else {
      msg = "[INFO] "
          + "arr:" + Arrays.toString(arr)
          + "\n"
          + "target: "
          + n
          + "\n"
          + "result:"
          + Arrays.toString(result)
      + "\n-------------------------------------\n";
    }

    System.out.println(msg);

  }

  static void main(String[] args) {
    final Solution solution = new Solution();
    int[] inputArr = new int[]{6, 4, 5, 7, 9, 1, 2};
    int expect = 10;
    final int[] result = solution.twoSum(inputArr, expect);
    print(inputArr, expect, result);
  }
}
