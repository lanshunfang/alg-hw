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

  BigNumber(char charAsNumber) {
    this.d = new Cstring(charAsNumber);
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

    return BigNumber.add(this, numberToAdd);

  }

  static BigNumber add(BigNumber left, BigNumber right) {
    CustomIterator iteratorLeft = left.d.getIterator(true);
    CustomIterator iteratorRight = right.d.getIterator(true);

    Cstring resultString = new Cstring();

    int carrier = 0;

    while (iteratorLeft.hasNext() || iteratorRight.hasNext()) {

      int charAsIntLeft = Character.getNumericValue(
          iteratorLeft.hasNext()
              ? iteratorLeft.next()
              : '0'
      );

      int charAsIntRight = Character.getNumericValue(

          iteratorRight.hasNext()
              ? iteratorRight.next()
              : '0'

      );

      int sum = charAsIntLeft + charAsIntRight + carrier;

      if (sum >= 10) {
        sum -= 10;
        carrier = 1;
      } else {
        carrier = 0;
      }

      resultString.append(
          sum
      );

    }

    if (carrier == 1) {
      resultString.append(
          carrier
      );
    }

    resultString.reverse();

    return new BigNumber(resultString);
  }

  public BigNumber mult(BigNumber numberToMultiply) {
    return numberToMultiply;
  }

  public BigNumber sub(BigNumber numberToSubtract) {
    BigNumber complement = BigNumber.getComplement(numberToSubtract);
    if (complement.isEqual(0)){
      return this;
    }
    BigNumber tmpBigNumFromAddingComplement = BigNumber.add(this, complement);
    return BigNumber.subtractComplement(tmpBigNumFromAddingComplement, numberToSubtract.size() + 1);

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


  private static BigNumber getComplement(BigNumber bigNumber) {

    Cstring resultString = new Cstring();
    CustomIterator iterator = bigNumber.getD().getIterator(true);

    int borrower = 0;

    while (iterator.hasNext()) {

      int currentDigit = 10 - borrower;
      int charAsInt = BigNumber.getCharFromIterator(iterator);

      int sum = currentDigit - charAsInt;

      if (sum == 10) {
        resultString.append(0);
      } else {
        borrower = 1;
        resultString.append(sum);
      }

    }

    resultString.reverse();

    return new BigNumber(resultString);

  }


  static BigNumber subtractComplement(BigNumber bigNumber, int complementDigitLength) {

    Cstring resultString = new Cstring();
    Cstring tmpStringWithAllPrefixZero = new Cstring();
    CustomIterator iterator = bigNumber.getD().getIterator(true);

    int borrower = 0;

    // 5992 - 10000 = - (10000 - 5992)

    int posOtherThenZero = complementDigitLength - 1;

    boolean isNegativeNum = bigNumber.size() < complementDigitLength;

    while (iterator.hasNext()) {

      int diff = 0;

      int charAsInt = BigNumber.getCharFromIterator(iterator);

      if (isNegativeNum) {

        int currentDigit = 10 - borrower;

        diff = currentDigit - charAsInt;


      } else if (posOtherThenZero > 0) {
        // 15992 - 10000;
        diff = charAsInt;
//        resultString.append(charAsInt);
        posOtherThenZero--;
      } else if (posOtherThenZero == 0) {
        diff = charAsInt - 1;
      }

      if (diff == 10 || diff == 0) {
//          resultString.append(0);
        tmpStringWithAllPrefixZero.append(0);
      } else {
        if (isNegativeNum) {
          borrower = 1;
        }
        resultString.append(tmpStringWithAllPrefixZero);
        resultString.append(diff);

        tmpStringWithAllPrefixZero = new Cstring();
      }

    }

//    if (isNegativeNum) {
//      resultString.append("-");
//    }

    if (resultString.charArrLen == 0) {
      resultString = new Cstring(0);
    }

    if (isNegativeNum) {
      // remove all prefix
      resultString.append("-");
    }

    resultString.reverse();


    return new BigNumber(resultString);

  }


  static int getCharFromIterator(CustomIterator iterator) {
    char iteratorNextChar = '0';
    if (iterator.hasNext()) {
      iteratorNextChar = iterator.next();

      if (BigNumber.isSign(iteratorNextChar)) {
        iteratorNextChar = '0';
      }
    }
    return Character.getNumericValue(iteratorNextChar);
  }

  private static boolean isSign(char theChar) {
    return theChar == '-' || theChar == '+';
  }


  private static void test1() {
    BigNumber a = new BigNumber(789);
    a.pLn("a = ");


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