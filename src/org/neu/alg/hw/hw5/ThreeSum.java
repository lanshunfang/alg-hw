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
    return "C:\\work\\java11\\3sumdata\\";
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
  private List<List<Integer>> N3TimeConstantSpace(int[] nums, int n) {
    //WRITE CODE
    // sort array to get rid of duplication found
    // duplication: assert({-5, 0, 5}).toEqual({0, 5, -5})
    Arrays.sort(nums);

    List<List<Integer>> results = new ArrayList();
    // TODO: Should eliminate duplication
    int len = nums.length;
    int i = 0;
    while (i < len) {

      int j = i + 1;
      while (j < len) {
        final int eleI = nums[i];
        final int eleJ = nums[j];

        int k = j + 1;
        while (k < len) {

          final int eleK = nums[k];
          if (eleI + eleJ + eleK == n) {
            List<Integer> found = Arrays.asList(eleI, eleJ, eleK);
            results.add(
                found
            );
            System.out.println("Found 1:" + Arrays.toString(found.toArray()));

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

    int lenArr = nums.length;

    // the new array which include expected sum for a given array index
    int[] numRemnant = new int[lenArr];
    for (int i = 0; i < lenArr; i++) {
      numRemnant[i] = n - nums[i];
    }

    int i = 0;
    while (i < lenArr) {
      // simplify the issue of 3-sum to the issue of 2-sum
      int twoSumTarget = numRemnant[i];
      int firstEle = nums[i];

      HashMap<Integer, Integer> twoSumDescriptor = new HashMap();

      for (int j = 0; j < lenArr; j++) {
        int ele = nums[j];
        twoSumDescriptor.put(ele, twoSumTarget - ele);
      }


      int j = 0;
      while (j < lenArr) {
        int secondEle = nums[j];

        if (twoSumDescriptor.containsKey(secondEle)) {

          int thirdEle = twoSumDescriptor.get(secondEle);
          if (twoSumDescriptor.containsKey(thirdEle)) {
            if (firstEle <= secondEle && secondEle <= thirdEle) {
              List<Integer> found = Arrays.asList(firstEle, secondEle, thirdEle);
              results.add(
                  found
              );
              System.out.println("Found 2:" + Arrays.toString(found.toArray()));

            }

            twoSumDescriptor.remove(thirdEle);

          }

          twoSumDescriptor.remove(secondEle);

        }

        j++;

        while (j > 0 && j < lenArr && nums[j] == nums[j - 1]) {
          j++;
        }

      }

      i++;
      while (i > 0 && i < lenArr && nums[i] == nums[i - 1]) {
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

      int currentNum = nums[i];
      int sumTarget = 0 - currentNum;

      int leftIndex = i + 1;

      int rightIndex = len - 1;

      while (leftIndex < rightIndex) {

        int leftNum = nums[leftIndex];
        int rightNum = nums[rightIndex];


        if (leftNum + rightNum == sumTarget) {
          List<Integer> found = Arrays.asList(currentNum, leftNum, rightNum);
          results.add(
              found
          );

          // when leftNum is increased, rightNum must be decreased to make the sum
          // Note that, leftIndex++ may be equal to leftIndex; It will be handled in the next step
          leftIndex++;
          rightIndex--;
          System.out.println("Found 3:" + Arrays.toString(found.toArray()));
        }

        while (nums[leftIndex - 1] == nums[leftIndex]) {
          leftIndex++;
        }

        while (nums[rightIndex - 1] == nums[rightIndex]) {
          rightIndex--;
        }

        // faster pointer move
        if (leftIndex < rightIndex && leftNum + rightNum < sumTarget) {
          // if the sum is too small, that blames left index is too low
          // with this step, we could save some calculations
          leftIndex++;
          leftNum = nums[leftIndex];
        }

        // faster pointer move
        if (rightIndex > leftIndex && leftNum + rightNum > sumTarget) {
          // if the sum is too big, that blames right index is too big
          rightIndex--;
        }

//

        //

      }

      i++;

      while (i < len && nums[i - 1] == nums[i]) {
        i++;
      }

    }

    return results;

  }

  public static void main(String[] args) {
    if (false) { //Change it true, if you want to dump all output to a file
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
