package org.neu.alg.hw.hw5;
import org.neu.alg.hw.*;
import java.util.*;
import java.io.*;


/**
 * File Name: ThreeSum.java
 * ThreeSum  class
 *
 * To Compile: IntUtil.java RandomInt.java Tuple.java ThreeSumBase.java ThreeSum.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class ThreeSum extends ThreeSumBase{
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
      return N3TimeConstantSpace(nums,n) ;
    }
    if (method == 2) {
      return N2TimeNSpace(nums,n) ;
    }
    if (method == 3) {
      return N2TimeConstantSpace(nums,n) ;
    }
    return null ;
  }


  /*
   * Time complexity O(N^3)
   * Space complexity O(1)
   */
  private List<List<Integer>> N3TimeConstantSpace(int[] nums, int n) {
    //WRITE CODE
  }


  /*
   * Time complexity O(N^2)
   * Space complexity O(N)
   */
  private List<List<Integer>> N2TimeNSpace(int[] nums, int n) {
    //WRITE CODE
  }

  /*
   * Time complexity O(N^2)
   * Space complexity O(1)
   */
  private List<List<Integer>> N2TimeConstantSpace(int[] nums, int n) {
    //WRITE CODE
  }

  public static void main(String[] args) {
    if (false) { //Change it true, if you want to dump all output to a file
      System.out.println("Writing to 3sumoutput.txt") ;
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
    ThreeSum p = new ThreeSum() ;
    System.out.println("ThreeSum.java ENDS");
  }
}
