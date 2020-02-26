package org.neu.alg.hw.hw6;

import org.neu.alg.hw.*;

/**
 * File Name: Cstring.java
 * Implements C String
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */
/*
 * To compile you require: CharArray.java Cstring
 */

class Cstring {
  private CharArray d; //Infinite array of char
  static IntUtil u = new IntUtil();

  private static void testBench() {

  }

  public static void main(String[] args) {
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("Cstring.java starts");
    testBench();
    System.out.println("Cstring.java ends");
  }

}