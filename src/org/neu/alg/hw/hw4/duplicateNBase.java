package org.neu.alg.hw.hw4;
import org.neu.alg.hw.*;

import java.util.Random;

/**
 * File Name: duplicateNBase.java
 * Sum of N base class
 *
 * To Compile: IntUtil.java RandomInt.java duplicateNBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class duplicateNBase{
  //input
  protected int[] a ; //a[i] guaranteed > 0 && < n-1
  //contents of a should be exactly like the original after your algorithms
  protected boolean show ; //show only is show = true
  //output
  protected IntUtil u = new IntUtil();


  //I don't know how to write it
  //Override by the concrete class
  protected abstract int nsquare_time_constant_space() ; //Returns number of duplicates
  protected abstract int ntime_n_space(); //Returns number of duplicates
  protected abstract int ntime_constant_space(); //Returns number of duplicates

  protected void testBed() {
    simpleTests() ;
    randomTests() ;
    System.out.println("All test cases passed. You will get full grade now");
    System.out.println("Include only duplicateN.java and the output of this program for FULL grade");
  }

  private void test1() {
    if (show) {
      System.out.println("----------------------------");
      u.pLn(a.length) ;
      u.pLn(a);
    }
    int[] copya = u.copyArray(a);
    int d0 = 0; //Number of duplicates from alg 0
    int d1 = 0; //Number of duplicates from alg 1
    int d2 = 0; //Number of duplicates from alg 2
    {
      long startTime = System.nanoTime();
      d0 = nsquare_time_constant_space();
      long endTime = System.nanoTime();
      double d = u.timeInSec(endTime,startTime) ;
      System.out.println("O(n^2)Time O(1)Space Alg.  n = " + a.length + " CPU = " + d + " secs") ;
      boolean  e = u.arrayEqual(a, copya);
      if (e == false) {
        System.out.println("You changed the content of given array a") ;
        u.myassert(false) ;
      }
      u.myassert(e);
    }
    {
      long startTime = System.nanoTime();
      d1 = ntime_n_space();
      long endTime = System.nanoTime();
      double d = u.timeInSec(endTime,startTime) ;
      System.out.println("O(n)Time O(n)Space Alg.    n = " + a.length + " CPU = " + d + " secs") ;
      boolean  e = u.arrayEqual(a, copya);
      if (e == false) {
        System.out.println("You changed the content of given array a") ;
        u.myassert(false) ;
      }
      u.myassert(e);
      u.myassert(d1 == d0);
    }
    {
      long startTime = System.nanoTime();
      d2 = ntime_constant_space();
      long endTime = System.nanoTime();
      double d = u.timeInSec(endTime,startTime) ;
      System.out.println("O(n)Time O(1)Space Alg.    n = " + a.length + " CPU = " + d + " secs") ;
      boolean  e = u.arrayEqual(a, copya);
      if (e == false) {
        System.out.println("You changed the content of given array a") ;
        u.myassert(false) ;
      }
      u.myassert(e);
      u.myassert(d2 == d0);
    }
  }

  private void simpleTests() {
    show = true ;
    {
      int b[] = { 1,1,1 };
      this.a = b ;
      test1();
    }
    {
      int b[] = { 1,0,0,0 };
      this.a = b ;
      test1();
    }
    {
      int b[] = { 1,2,3,1,3,6,6 };
      this.a = b ;
      test1();
    }
    {
      int b[] = { 1,2,3,1,3,0,0 };
      this.a = b ;
      test1();
    }
    {
      int b[] = { 0,0,1 };
      this.a = b ;
      test1();
    }
    {
      int b[] = { 3,2,0,1 };
      this.a = b ;
      test1();
    }
    System.out.println("All simple tests passed");
  }

  private void randomTests() {
    show = false ;
    int N = 500000; //500000 500000
    for (int i = 10000; i < N; i = i + 10000) {
      {
        a = new int[i];
        for (int j = 0; j < i; ++j) {
          a[j] = i - 1;
        }
        test1() ;
      }
      {
        int start = 0 ;
        int end = i-1 ; ;
        int [] b = u.generateRandomNumber(i, true, start,end);
        this.a = b ;
        test1();
      }
    }
    System.out.println(N + " Random tests passed");
  }

  public static void main(String[] args) {
    System.out.println("duplicateNBase.java STARTS");
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("You cannot instantiate duplicateNBase class: duplicateNBase p = new duplicateNBase() ; ");
    //duplicateNBase p = new duplicateNBase() ;
    System.out.println("duplicateNBase.java ENDS");
  }
}