package org.neu.alg.hw.hw6;

import org.neu.alg.hw.*;

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
    BigNumber accumulation = new BigNumber(1);

    while (number > 1) {
      accumulation = accumulation.mult(new BigNumber(number));
      number--;
    }

    return accumulation;
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

    if (this.isEqual(0) || numberToMultiply.isEqual(0)) {
      return new BigNumber(0);
    }

    Cstring accumulation = new Cstring(0);

    CustomIterator iteratorRight = numberToMultiply.getD().getIterator(true);

    int leftShift = 0;

    while (iteratorRight.hasNext()) {
      int charAsIntRight = BigNumber.getCharFromIterator(iteratorRight);

      int carrier = 0;
      int bitOrderOfLeft = 0;

      CustomIterator iteratorLeft = this.getD().getIterator(true);

      while (iteratorLeft.hasNext()) {
        int accumulatedLevelForTheBit = bitOrderOfLeft + leftShift;
        int charAsIntLeft = BigNumber.getCharFromIterator(iteratorLeft);
        int productResultOfCurrentBit = charAsIntLeft * charAsIntRight + carrier;
        if (leftShift > 0 && accumulation.charArrLen > accumulatedLevelForTheBit) {
          productResultOfCurrentBit += accumulation.getInt(accumulatedLevelForTheBit);
        }
        if (productResultOfCurrentBit >= 10) {
          carrier = productResultOfCurrentBit / 10;
          productResultOfCurrentBit = productResultOfCurrentBit % 10;
        } else {
          carrier = 0;
        }

        accumulation.set(accumulatedLevelForTheBit, productResultOfCurrentBit);

        bitOrderOfLeft++;
      }

      if (carrier != 0) {
        accumulation.append(carrier);
      }

      leftShift++;

    }

    accumulation.reverse();

    return new BigNumber(accumulation);
  }

  public BigNumber sub(BigNumber numberToSubtract) {

    if (this.size() > numberToSubtract.size()) {
      return BigNumber.sub(this, numberToSubtract);
    }

    if (this.size() < numberToSubtract.size()) {

      return BigNumber.negate(
          BigNumber.sub(numberToSubtract, this)
      );

    } else {
      BigNumber diff = BigNumber.sub(this, numberToSubtract);

      if (diff.getD().get(0) == '-') {
        return BigNumber.negate(
            BigNumber
                .sub(numberToSubtract, this)
        );

      }

      return diff;
    }


  }

  static BigNumber negate(BigNumber bigNumber) {
    return new BigNumber(
        new Cstring('-')
            .append(
                bigNumber.getD()
            ));
  }

  static BigNumber sub(BigNumber num1, BigNumber num2) {

    Cstring resultString = new Cstring();
    Cstring tmpStringWithAllPrefixZero = new Cstring();
    CustomIterator iteratorLeft = num1.getD().getIterator(true);
    CustomIterator iteratorRight = num2.getD().getIterator(true);

    int borrower = 0;

    while (iteratorLeft.hasNext() || iteratorRight.hasNext()) {

      int charAsIntLeft = iteratorLeft.hasNext() ? BigNumber.getCharFromIterator(iteratorLeft) : 0;
      int charAsIntRight = iteratorRight.hasNext() ? BigNumber.getCharFromIterator(iteratorRight) : 0;

      int diff = charAsIntLeft - charAsIntRight - borrower;

      if (diff < 0) {
        diff = 10 + diff;
        borrower = 1;
      } else {
        borrower = 0;
      }

      if (diff == 0) {
//          resultString.append(0);
        tmpStringWithAllPrefixZero.append(0);
      } else {

        resultString.append(tmpStringWithAllPrefixZero);
        resultString.append(diff);

        tmpStringWithAllPrefixZero = new Cstring();
      }
    }

    if (borrower > 0) {
      resultString.append("-");
    }

    if (resultString.charArrLen == 0) {
      resultString = new Cstring(0);
    }

    resultString.reverse();

    return new BigNumber(resultString);
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