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


class BigNumber {
  //class BigNumber implements Cloneable {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  //YOU CANNOT add any data members
  //YOU CAN add any public or private function so that all the tests will pass

  static BigNumber factorial(int number) {
    return new BigNumber(number);
  }

  BigNumber(int num) {
    this.d = new Cstring(num);
  }

  BigNumber(String strAsNumber) {
    this.d = new Cstring(strAsNumber);
  }


  BigNumber(Cstring cstring) {
    this.d = cstring;
  }

  public void pLn(String prefix) {
    this.d.pLn(prefix);
  }

  public int size() {
    return this.d.charArrLen;
  }

  public BigNumber add(BigNumber numberToAdd) {
    CustomIterator iteratorLeft = numberToAdd.d.getIterator(true);
    CustomIterator iteratorRight = this.d.getIterator(true);

    Cstring resultString = new Cstring();

    int carrier = 0;

    while (iteratorLeft.hasNext() && iteratorRight.hasNext()) {
      int charAsIntLeft = Character.getNumericValue(iteratorLeft.next());
      int charAsIntRight = Character.getNumericValue(iteratorRight.next());

      int sum = charAsIntLeft + charAsIntRight;

      if (sum > 10) {
        sum -= 10;
        carrier++;
      }

      resultString.push(
          Character.forDigit(sum + carrier--, 10)
      );

    }

    return new BigNumber(resultString);
  }

  public BigNumber mult(BigNumber numberToMultiply) {
    return numberToMultiply;
  }

  public BigNumber sub(BigNumber numberToSubtract) {
    return numberToSubtract;
  }

  // @Override
  public BigNumber clone() {
    return new BigNumber(this.d.clone());

  }

  public boolean isEqual(BigNumber numberToCompare) {
    return this.getD().isEqual(numberToCompare.getD());
  }

  public boolean isEqual(String stringAsNumberToCompare) {
    return this.isEqual(new BigNumber(stringAsNumberToCompare));
  }

  public boolean isEqual(int numberToCompare) {
    return this.isEqual(new BigNumber(numberToCompare));

  }

  public Cstring getD() {
    return this.d;
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