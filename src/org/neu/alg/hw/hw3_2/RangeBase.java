package org.neu.alg.hw.hw3_2;

import java.util.Arrays;
import org.neu.alg.hw.*;

/**
 * File Name: RangeBase.java
 * Java11
 * To Compile: IntUtil.java RandomInt.java RangeBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class RangeBase{
  protected IntUtil u = new IntUtil();
  protected int numSteps = 0 ;
  protected boolean show = true ;

  //I don't know how to write it
  //Override by the concrete class
  abstract int[] Range(int [] a, int n) ;

  protected void testBed() {
    basic() ;
    random() ;
  }

  /*
   * Time: O(n)
   * Space: O(1)
   */
  private int[] expectedAnswer(int [] a, int k) {
    int [] ans = {-1, -1} ;
    for (int i = 0; i < a.length; ++i) {
      if (a[i] == k) {
        if (ans[0] == -1) {
          ans[0] = i ;
        }
        ans[1] = i ;
      }
    }
    return ans;
  }

  private void P(int [] a) {
    u.pLn(a.length);
    u.pLn(a);
  }

  private void one(int [] a, int k) {
    if (show) {
      P(a) ;
    }
    int [] y = Range(a,k) ;
    int [] e = expectedAnswer(a,k) ;
    if (show) {
      System.out.println("Correct ans for k = " + k + " min index = " + e[0] + " max index = " + e[1]) ;
    }
    if ((y[0] != e[0]) && (y[1] != e[1])) {
      System.out.println("your ans for k = " + k + " min index = " + y[0] + " max index = " + y[1]) ;
      u.myassert(false);
    }
    if (show) {
      System.out.print("Size " + a.length + " Num steps " + numSteps) ;
    }
    double logn = (Math.log(a.length)/Math.log(2)) ;
    int m = 2 * (int)logn + 1 ;
    if (show) {
      System.out.println(" Max steps allowed " + m);
    }
    if (numSteps == 0) {
      System.out.println("How did you solve in 0 steps. Update numSteps in Range.java") ;
      u.myassert(false);
    }else if (numSteps > m)  {
      System.out.println("You took " + numSteps + " steps. You should take <= " + m + " steps" ) ;
      u.myassert(false);
    }
  }

  private void basic() {
    {
      int [] a = {1,2,2,3} ;
      int k = 2 ;
      one(a,k) ;
    }

    {
      int [] a = {1,2,2,3} ;
      int k = 4 ;
      one(a,k) ;
    }

    {
      int [] a = {1,2,3,4} ;
      int k = 2 ;
      one(a,k) ;
    }

    {
      int [] a = {1,1,1,1} ;
      int k = 1 ;
      one(a,k) ;
    }

    {
      int [] a = {1,1,1,1,7} ;
      int k = 1 ;
      one(a,k) ;
    }
    {
      int N = 1024 ;
      int M = 5 ;
      int [] a = new int[N] ;
      for (int i = 0; i < N; ++i) {
        a[i] = M ;
      }
      one(a,M) ;
    }
    {
      int N = 1024 ;
      int [] a = new int[N] ;
      for (int i = 0; i < N; ++i) {
        a[i] = i ;
      }
      one(a,N) ;
    }

  }

  private void random() {
    show = false ;
    for (int i = 1024; i < 100000; i = i * 2) {
      int[] a = u.generateRandomNumber(i,true,0,101) ;
      Arrays.sort(a) ;
      for (int j = 0; j <= 101; ++j) {
        one(a,j) ;
      }
      System.out.println("Random tests for " + i + " passed");
    }
    System.out.println("All Random tests passed");
  }

  public static void main(String[] args) {
    System.out.println("RangeBase.java STARTS");
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("You cannot instantiate RangeBase class: RangeBase p = new RangeBase() ; ");
    //RangeBase p = new RangeBase() ;
    System.out.println("RangeBase.java ENDS");
  }
}
