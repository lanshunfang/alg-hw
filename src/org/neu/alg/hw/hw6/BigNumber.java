package org.neu.alg.hw.hw6;

import org.neu.alg.hw.*;
import java.lang.Cloneable;

/**
 * File Name: BigNumber.java
 * Infinite capacity Unsigned Number
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */
/*
 * To compile you require: CharArray.java, IntUtil.java RandomInt.java Cstring.java BigNumber.java
 */


class BigNumber implements Cloneable {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  //YOU CANNOT add any data members
  //YOU CAN add any public or private function so that all the tests will pass

  static BigNumber factorial(int number) {
    return new BigNumber(number);
  }

  BigNumber(int num) {
  }

  BigNumber(String strAsNumber) {
  }

  public void pLn(String stringToPrint) {
    return ;
  }
  public int size() {
    return 1;
  }

  public BigNumber add(BigNumber numberToAdd) {
    return numberToAdd;
  }
  public BigNumber mult(BigNumber numberToMultiply) {
    return numberToMultiply;
  }
  public BigNumber sub(BigNumber numberToSubtract) {
    return numberToSubtract;
  }

  @Override
  public BigNumber clone(){
    return this;
  }

  public boolean isEqual(BigNumber numberToCompare) {
    return true;
  }
  public boolean isEqual(String numberToCompare) {
    return true;
  }
  public boolean isEqual(int numberToCompare) {
    return true;
  }

  private static void test1() {
    BigNumber b = new BigNumber(10);

  }

  private static void testBench() {
    System.out.println("------------test1---------------------");
    test1();
  }

  public static void main(String[] args) {
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("BigNumber.java starts");
    testBench();
    System.out.println("BigNumber.java ends");
  }
}