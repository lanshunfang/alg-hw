package org.neu.alg.hw.hw3_2;
import org.neu.alg.hw.*;

/**
 * File Name: Search12D.java
 * Search12D concrete class
 *
 *
 * To Compile: IntUtil.java RandomInt.java Search12D.java Search12DBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Search12D extends Search12DBase {
  Search12D() {
    //NOTHING CAN BE CHANGED HERE
    testBench();
  }

  @Override
  protected boolean search12D(int t) {
    //NOTHING CAN BE CHANGED HERE
    return alg(t) ;
  }

  private boolean alg(int t) {
    //WRITE CODE
    return true;
  }


  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("Search12D problem STARTS");
    Search12D m = new Search12D() ;
    System.out.println("All Search12D tests passed. You are genius");
    System.out.println("Search12D problem ENDS");
  }
}