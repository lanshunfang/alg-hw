package org.neu.alg.hw.hw2;

import java.util.Random;

/**
 * File Name: SelectionDayBase.java
 * Selection Day base class
 *
 * To Compile: IntUtil.java RandomInt.java SelectionDayBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class SelectionDayBase{
  protected final int N = 25 ; //Number of students
  protected final int S = 3 ; //3 fastest students
  protected int [] a = new int[N] ;
  protected int [] s = new int[S] ;
  protected IntUtil u = new IntUtil();
  protected boolean display = false ;

  //I don't know how to write it
  //Override by the concrete class
  abstract int race() ;

  protected void testBench() {
    simpleTests() ;
    randomTests() ;
  }

  private int[] expectedAnswer() {
    int [] e = {0,0,0} ;
    for (int i = 0; i < N; ++i) {
      if (a[i] == 0) {
        e[0] = i ;
      }else if (a[i] == 1) {
        e[1] = i ;
      }else if (a[i] == 2) {
        e[2] = i ;
      }
    }
    return e ;
  }

  private void randomTests() {
    display = false ;
    int n = 100000 ;
    System.out.println("Testing on " + n + " numbers");
    for (int j = 0; j < n; ++j) {
      for (int i = 0; i < N; ++i) {
        a[i] = i ;
      }
      Random r = new Random() ;
      //Shuffle a 100 times
      for (int i = 0; i < 100; ++i) {
        int p = RandomInt.getRandomInt(r,0,N); //This gives number between 0 to n-1
        int q = RandomInt.getRandomInt(r,0,N);
        u.swap(a,p,q);
      }
      int e[] = expectedAnswer();
      int itr = race();
      for (int i = 0; i < S; ++i) {
        u.myassert(s[i] == e[i]) ;
      }
    }
    System.out.println("All " + n + " cases passed. You are great");
  }

  private void simpleTests() {
    display = true ;
    {
      int [] e = {0,1,2} ;
      for (int i = 0; i < N; ++i) {
        a[i] = i ;
      }
      int itr = race();
      for (int i = 0; i < S; ++i) {
        u.myassert(s[i] == e[i]) ;
      }
    }
    {
      int [] e = {24,23,22} ;
      for (int i = 0; i < N; ++i) {
        a[i] = N-1- i ;
      }
      int itr = race();
      for (int i = 0; i < S; ++i) {
        u.myassert(s[i] == e[i]) ;
      }
    }
    {
      int [] e = {9,6,16} ;
      int b[] = {17,6,18,19,11,14,1,3,16,0,9,24,22,4,5,15,2,8,13,12,23,21,7,20,10} ;
      for (int i = 0; i < N; ++i) {
        a[i] = b[i] ;
      }
      int itr = race();
      for (int i = 0; i < S; ++i) {
        u.myassert(s[i] == e[i]) ;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("SelectionDayBase.java STARTS");
    System.out.println("You cannot instantiate SelectionDay class: SelectionDayBase p = new UscoinBase() ; ");
    //SelectionDayBase p = new SelectionDayBase() ;
    System.out.println("SelectionDayBase.java ENDS");
  }
}