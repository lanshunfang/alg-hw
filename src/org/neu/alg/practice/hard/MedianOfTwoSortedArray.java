package org.neu.alg.practice.hard;

import java.util.Arrays;

public class MedianOfTwoSortedArray {
  public static void main(String... args) {
    Solution Solution = new Solution();
//    int[] nums1 = {1, 2, 3};
//    int[] nums2 = {1, 2, 3};
//    System.out.println(Solution.findMedianSortedArrays(
//        new int[]{1, 2, 3, 6, 8},
//        new int[]{3, 6, 9}
//        )
//    );
    System.out.println(Solution.findMedianSortedArrays(
        new int[]{1, 2,},
        new int[]{3, 6, 9, 11, 100}
        )
    );
  }
}


class Solution {
  /**
   * Repeat, until the two medians of the two number lists converge
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public float findMedianSortedArrays(int[] nums1, int[] nums2) {

//    if (nums1[nums1.length - 1] < nums2[0]) {
//      return this.getMedianIfItemsOfAListAlwaysBigger(nums1, nums2);
//    } else if (nums2[nums2.length - 1] < nums1[0]) {
//      return this.getMedianIfItemsOfAListAlwaysBigger(nums2, nums1);
//    }

//    return this.repeatUntilMedianConverge(nums1, nums2, 0, nums1.length -1, 0, nums2.length -1);

    System.out.println("Search Median in 2 sorted array");
    System.out.println(Arrays.toString(nums1));
    System.out.println(Arrays.toString(nums2));

    if (nums1.length > nums2.length) {
      return this.searchMedianWithRangeConverge(
          nums2,
          nums1,
          0,
          nums2.length - 1,
          0,
          nums1.length - 1
      );
    }

    return this.searchMedianWithRangeConverge(
        nums1,
        nums2,
        0,
        nums1.length - 1,
        0,
        nums2.length - 1
    );

  }

  private int getMedianIfItemsOfAListAlwaysBigger(int[] ShorterList, int[] biggerList) {
    int lenShorterList = ShorterList.length;
    int lenBiggerList = biggerList.length;
    int medianPos = (lenShorterList + lenBiggerList) / 2 - 1;
    if (medianPos > lenShorterList) {
      return biggerList[medianPos - lenShorterList];
    } else if (medianPos < lenShorterList) {
      return ShorterList[medianPos];
    } else {
      return (ShorterList[lenShorterList - 1] + biggerList[0]) / 2;
    }
  }

  private float searchMedianWithRangeConverge(
      int[] numsShorter,
      int[] numsLonger,
      int startPosShorter,
      int endPosShorter,
      int startPosLonger,
      int endPosLonger
  ) {

    if (endPosLonger - startPosLonger <= 1) {
      boolean isTotalCountEven = (numsShorter.length + numsLonger.length) % 2 == 0;

      float maxLeftSiblingToTheMedian = Math.max(numsShorter[startPosShorter],numsLonger[startPosLonger]);
      float minRightSiblingToTheMedian = Math.min(numsShorter[endPosShorter],numsLonger[endPosLonger]);

      if (isTotalCountEven) {
        return ( maxLeftSiblingToTheMedian + minRightSiblingToTheMedian ) / 2;
      } else {
        return minRightSiblingToTheMedian;
      }

    }

    int medianPosOfLonger = startPosLonger + (endPosLonger - startPosLonger + 1) / 2;

    int matchedPosInShorter = this.findPosByValue(numsShorter, numsLonger[medianPosOfLonger], 0, numsShorter.length - 1);

    int medianPosShift = (endPosShorter - startPosShorter + 1) / 2 - matchedPosInShorter;

    if (medianPosShift < 0) {
      startPosShorter = endPosShorter - medianPosShift;
    } else {
      endPosShorter = startPosShorter + medianPosShift;
    }

    int newMedianPos = medianPosOfLonger + medianPosShift;

    if (newMedianPos < medianPosOfLonger) {
      startPosLonger = newMedianPos;
      endPosLonger = medianPosOfLonger;
    } else {
      startPosLonger = medianPosOfLonger;
      endPosLonger = newMedianPos;
    }

    if (endPosShorter - startPosShorter > endPosLonger - startPosLonger) {
      return searchMedianWithRangeConverge(numsLonger, numsShorter, endPosLonger, startPosLonger, startPosShorter, endPosShorter);
    }

    return searchMedianWithRangeConverge(numsShorter, numsLonger, startPosShorter, endPosShorter, startPosLonger, endPosLonger);

  }

  private int findPosByValue(int[] nums, int val, int startPos, int endPos) {

    if (startPos > endPos) {
      int maxEleInNums =nums[nums.length - 1];
      return val > maxEleInNums ? nums.length : -1;
    }

    int medianPos = startPos + (endPos - startPos) / 2;
    int median = nums[medianPos];

    if (median == val) {
      return medianPos;
    } else if (median > val) {
      return findPosByValue(nums, val, startPos, medianPos - 1);
    } else {
      return findPosByValue(nums, val, medianPos + 1, endPos);
    }

  }
}