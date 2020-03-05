package org.neu.alg.hw.hw6;

import org.neu.alg.hw.*;

/**
 * File Name: CharArray.java
 * Infinite capacity char array
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */
/*
 * To compile you require: CharArray.java, IntUtil.java RandomInt.java
 */

class CharArray {
  /*
   * ALL PRIVATE DATA BELOW
   */
  private int capacity;
  private char[] darray;
  static private boolean display = false;
  static IntUtil u = new IntUtil();

  /*
   * ALL PUBLIC ROUTINES BELOW
   */
  static void setDisplay(boolean x) {
    display = x;
  }

  //Constructor that takes integer
  public CharArray(int s) {
    allocate(s);
    if (display == true) {
      System.out.println("Creating darray of int of capacity " + capacity);
    }
  }

  //Constructor that takes nothing
  public CharArray() {
    this(16); // This must be a first line
  }

  public char get(int pos) {
    if (pos < 0) {
      u.myassert(false);
      return 'a' ; //Make compiler happy
    }
    if (pos < capacity) {
      return darray[pos];
    }
    grow(pos);
    return darray[pos];
  }

  public void set(int pos, char val) {
    if (pos < 0) {
      u.myassert(false);
    }
    if (pos >= capacity) {
      grow(pos);
    }
    darray[pos] = val;
  }

  public void swap(int a, int b) {
    char x = darray[a] ;
    darray[a] = darray[b] ;
    darray[b] = x ;
  }

  /*
   * ALL PRIVATES ROUTINES BELOW
   */

  private void allocate(int s) {
    capacity = s;
    darray = new char[s];
  }

  private void grow(int s) {
    char[] ta = darray;
    int ts = capacity ;
    int ns = capacity;
    do {
      ns = ns * 2;
    } while (ns <= s);

    if (display == true) {
      System.out.println("Array grew from " + ts + " to " + ns);
    }
    u.myassert(s < ns);
    allocate(ns);
    for (int i = 0; i < ts; ++i) {
      darray[i] = ta[i];
    }
    ta = null;
  }

  /*
   * All test routines
   */

  private static void test1() {
    CharArray b = new CharArray();
    int s = 0 ;
    for (int i = 0; i < 8; ++i) {
      b.set(i, (char)('a'+i));
      ++s ;
    }
    CharArray a = new CharArray();
    a.set(3, 'Z');
    a.set(56, 'U');
    char x = a.get(3);
    char y = a.get(56);
    char z = a.get(100);
    System.out.println("a[3]= " + x + " a[56] = " + y + " a[100] = " + z);
  }

  private static void testBench() {
    CharArray.setDisplay(true);
    System.out.println("------------test1---------------------");
    test1();
  }

  public static void main(String[] args) {
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("CharArray.java starts");
    testBench();
    System.out.println("CharArray.java ends");
  }
}