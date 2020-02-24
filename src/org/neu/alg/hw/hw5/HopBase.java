package org.neu.alg.hw.hw5;
import java.util.Random;
import org.neu.alg.hw.*;

/**
 * File Name: HopBase.java
 * Java11
 * To Compile: IntUtil.java RandomInt.java HopBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class HopBase{
  protected IntUtil u = new IntUtil();

  //I don't know how to write it
  //Override by the concrete class
  abstract int hopSmart(int [] s, int x) ;

  protected void testBed() {
    basic() ;
    random() ;
  }

  private int hopEasy(int [] s, int x) {
    int l = 0 ;
    int gx = x ;
    while (true) {
      if (s[x] == gx) {
        return l ;
      }
      x = s[x] ;
      ++l ;
    }
  }

  private void one(int [] s, int j, int [] b) {
    System.out.println("---------------------------------------");
    int y = hopEasy(s,j) ;
    System.out.println("hopEasy y = " + y);
    int x = hopSmart(s,j) ;
    System.out.println("hopSmart x = " + x);
    u.myassert(x == y) ;
    for (int i = 0; i < s.length; ++i) {
      u.myassert(s[i] == b[i]);
    }
  }

  private void basic() {
    int s[] = {5,1,0,4,2,3} ;
    int b[] = {5,1,0,4,2,3} ;
    one(s,3,b) ;
    int l = s.length ;
    for (int j = 0; j < l ; ++j) {
      one(s,j,b) ;
    }
  }

  private void random() {
    int n = 10000 ;
    System.out.println("Testing on " + n + " numbers");
    int[] a = u.generateNumberInIncreasingOrder(n, 1);
    //shuffle a
    Random r = new Random() ;
    for (int i = 0; i < n; ++i) {
      int p = RandomInt.getRandomInt(r,0,n); //This gives number between 0 to n-1
      int q = RandomInt.getRandomInt(r,0,n);
      u.swap(a,p,q);
    }
    //make a copy of array a as b
    int[] b = u.copyArray(a) ;

    for (int j = 0 ; j < n; ++j) {
      one(a,j,b) ;
    }
    System.out.println("All " + n + " cases passed. You are great");
  }

  public static void main(String[] args) {
    System.out.println("HopBase.java STARTS");
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("You cannot instantiate HopBase class: HopBase p = new HopBase() ; ");
    //HopBase p = new HopBase() ;
    System.out.println("HopBase.java ENDS");
  }
}