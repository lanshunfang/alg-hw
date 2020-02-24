package org.neu.alg.hw.hw5;

import org.neu.alg.hw.*;

import java.util.*;
import java.io.*;


/**
 * File Name: ThreeSum.java
 * ThreeSum  class
 * <p>
 * To Compile: IntUtil.java RandomInt.java Tuple.java ThreeSumBase.java ThreeSum.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class ThreeSum extends ThreeSumBase {
  ThreeSum() {
    //NOTHING CAN BE CHANGED HERE
    testBench();
  }

  @Override
  protected String inputFileBase() {
    //Where is the input file?
    //Change this to your location
    return "/Users/lanshunfang/Documents/NEU-Classes-Courses/JAVA/alg-hw/src/org/neu/alg/hw/hw5/";
//    return "/Users/lanshunfang/Documents/NEU-Classes-Courses/JAVA/alg-hw/src/org/neu/alg/hw/hw5/";
  }

  @Override
  protected List<List<Integer>> threeSum(int[] nums, int n, int method) {
    //NOTHING CAN BE CHANGED HERE
    if (method == 1) {
      return N3TimeConstantSpace(nums, n);
    }
    if (method == 2) {
      return N2TimeNSpace(nums, n);
    }
    if (method == 3) {
      return N2TimeConstantSpace(nums, n);
    }
    return null;
  }


  /*
   * Time complexity O(N^3)
   * Space complexity O(1)
   */
  private List<List<Integer>> N3TimeConstantSpace(int[] nums, long n) {
    //WRITE CODE
    // sort array to get rid of duplication found
    // duplication: assert({-5, 0, 5}).toEqual({0, 5, -5})
    Arrays.sort(nums);

    List<List<Integer>> results = new ArrayList();
    // TODO: Should eliminate duplication
    long len = nums.length;
    int i = 0;
    while (i < len) {

      int j = i + 1;
      while (j < len) {
        final long eleI = Long.valueOf(nums[i]);
        final long eleJ = Long.valueOf(nums[j]);

        int k = j + 1;
        while (k < len) {

          final long eleK = Long.valueOf(nums[k]);
          if (eleI + eleJ + eleK == n) {
            List<Integer> found = Arrays.asList((int)eleI, (int)eleJ, (int)eleK);
            results.add(
                found
            );
//            System.out.println("Found 1:" + Arrays.toString(found.toArray()));

          }

          k++;
          while (k < len && nums[k - 1] == nums[k]) {
            k++;
          }

        }

        j++;

        while (j < len && nums[j - 1] == nums[j]) {
          j++;
        }

      }

      i++;
      while (i < len && nums[i - 1] == nums[i]) {
        i++;
      }
    }
    return results;
  }


  /*
   * Time complexity O(N^2)
   * Space complexity O(N)
   */
  private List<List<Integer>> N2TimeNSpace(int[] nums, int n) {
    List<List<Integer>> results = new ArrayList();

    Arrays.sort(nums);

    int len = nums.length;

    // the new array which include expected sum for a given array index
    long[] numRemnant = new long[len];
    for (int i = 0; i < len; i++) {
      numRemnant[i] = Long.valueOf(n - nums[i]);
    }

    int i = 0;
    while (i < len) {
      // simplify the issue of 3-sum to the issue of 2-sum
      long twoSumTarget = Long.valueOf(numRemnant[i]);
      long firstEle = Long.valueOf(nums[i]);

      HashMap<Long, Long> twoSumDescriptor = new HashMap();
      HashMap<Long, Long> twoSumDuplicationRecord = new HashMap();

      for (int j = i + 1; j < len; j++) {
        long secondEle = Long.valueOf(nums[j]);
        long thirdEleFromSubtract = twoSumTarget - secondEle;
        if (thirdEleFromSubtract == secondEle) {
          if (!twoSumDuplicationRecord.containsKey(secondEle)) {
            twoSumDuplicationRecord.put(secondEle, Long.valueOf(1));
            continue;
          }
        }

        twoSumDescriptor.put(secondEle, thirdEleFromSubtract);
      }


      int j = i + 1;
      while (j < len) {
        long secondEle = Long.valueOf(nums[j]);

        if (twoSumDescriptor.containsKey(secondEle)) {

          long thirdEleFromSubtract = twoSumDescriptor.get(secondEle);
          if (twoSumDescriptor.containsKey(thirdEleFromSubtract)) {
            if (firstEle <= secondEle && secondEle <= thirdEleFromSubtract) {
              List<Integer> found = Arrays.asList((int)firstEle, (int)secondEle, (int)thirdEleFromSubtract);
              results.add(
                  found
              );
//              if (len == 8000) {
//                System.out.println("Found 2:" + Arrays.toString(found.toArray()));
//
//              }


            }

            twoSumDescriptor.remove(thirdEleFromSubtract);

          }

          twoSumDescriptor.remove(secondEle);

        }

        j++;

        while (j > 0 && j < len && nums[j] == nums[j - 1]) {
          j++;
        }

      }

      i++;
      while (i > 0 && i < len && nums[i] == nums[i - 1]) {
        i++;
      }

    }

    return results;
  }

  /*
   * Time complexity O(N^2)
   * Space complexity O(1)
   */
  private List<List<Integer>> N2TimeConstantSpace(int[] nums, int n) {
    List<List<Integer>> results = new ArrayList();

    Arrays.sort(nums);

    int len = nums.length;
    int i = 0;

    while (i < len) {

      long currentNum = Long.valueOf(nums[i]);
      long sumTarget = Long.valueOf(n - currentNum);

      int leftIndex = i + 1;

      int rightIndex = len - 1;

      while (leftIndex < rightIndex) {

        long leftNum = Long.valueOf(nums[leftIndex]);
        long rightNum = Long.valueOf(nums[rightIndex]);

        if (leftNum + rightNum == sumTarget) {
          List<Integer> found = Arrays.asList((int)currentNum, (int)leftNum, (int)rightNum);
          results.add(
              found
          );

          // when leftNum is increased, rightNum must be decreased to make the sum
          // Note that, leftIndex++ may be equal to leftIndex; It will be handled in the next step
          leftIndex++;
          rightIndex--;
//          if (len == 8000) {
//            System.out.println("Found 3:" + Arrays.toString(found.toArray()));
//
//          }

          // increase index to bypass duplications
          boolean isShouldContinue = true;

          while (leftIndex < rightIndex && isShouldContinue) {

            isShouldContinue = false;
            if (nums[leftIndex] == nums[leftIndex - 1]) {
              isShouldContinue = true;
              leftIndex++;
            }
            if (nums[rightIndex] == nums[rightIndex + 1]) {
              isShouldContinue = true;
              rightIndex--;
            }
          }


        } else if (leftIndex < rightIndex) {
          if (leftNum + rightNum < sumTarget) {
            leftIndex++;

          } else {
            rightIndex--;

          }

        }

      }

      i++;

      while (i < len && nums[i - 1] == nums[i]) {
        i++;
      }

    }

    return results;

  }

  public static void main(String[] args) {
    if (true) { //Change it true, if you want to dump all output to a file
      System.out.println("Writing to 3sumoutput.txt");
      try {
        System.setOut(new PrintStream(new FileOutputStream("3sumoutput.txt")));
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("ThreeSum.java STARTS");
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    ThreeSum p = new ThreeSum();
    System.out.println("ThreeSum.java ENDS");
  }
}
