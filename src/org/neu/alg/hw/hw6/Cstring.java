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
  public int charArrLen = 0;
  static IntUtil u = new IntUtil();

  Cstring(String stringAsNumber) {
    this.setCharArray(stringAsNumber);
  }

  Cstring(char charAsNumber) {
    this.setCharArray(Character.toString(charAsNumber));
  }

  Cstring(int number) {
    this.setCharArray("" + number);
  }

  Cstring() {
    this.setCharArray("");
  }

  Cstring(CharArray charArray, int lenStr) {
    this.d = charArray;
    this.setCharArrLen(lenStr);
  }

  public CharArray getCharArray() {
    return this.d;
  }

  public void pLn(String prefix) {
    System.out.print(prefix);
    CustomIterator iterator = this.getIterator(false);

    while (iterator.hasNext()) {
      System.out.print(iterator.next());
    }

    System.out.print("\n");

  }

  public boolean isEqual(Cstring cstring) {

    if (cstring == null || cstring.charArrLen != this.charArrLen) {
      return false;
    }

    CustomIterator iteratorLeft = this.getIterator(false);
    CustomIterator iteratorRight = cstring.getIterator(false);

    while (iteratorLeft.hasNext() || iteratorRight.hasNext()) {

      if (!(iteratorLeft.hasNext() && iteratorRight.hasNext() && iteratorLeft.next() == iteratorRight.next())) {
        return false;
      }

    }

    return true;

  }

  public Cstring clone() {
    return this.add(new Cstring());
  }

  public void reverse() {
    for (int i = 0, j = this.charArrLen - 1; i < j; i++, j--) {
      this.d.swap(i, j);
    }
  }

  private void setCharArray(String stringAsNumber) {
    this.d = new CharArray();

    int i = 0;
    int lenStr = stringAsNumber.length();
    for (; i < lenStr; i++) {
      this.d.set(i, stringAsNumber.charAt(i));
    }

    this.setCharArrLen(lenStr);

  }

  private void setCharArrLen(int lenStr) {
    this.charArrLen = lenStr;
  }

  /**
   * immutable add, return a new Cstring
   *
   * @param cstring
   */
  public Cstring add(Cstring cstring) {

    CustomIterator iteratorLeft = this.getIterator(false);
    CustomIterator iteratorRight = cstring.getIterator(false);

    CharArray tmpCharArray = new CharArray();

    int i = 0;

    while (iteratorLeft.hasNext()) {
      tmpCharArray.set(i++, iteratorLeft.next());
    }
    while (iteratorRight.hasNext()) {
      tmpCharArray.set(i++, iteratorRight.next());
    }

    return new Cstring(tmpCharArray, i);

  }

  public Cstring add(String strToAdd) {
    return this.add(new Cstring(strToAdd));
  }

  /**
   * mutable add, return the raw Cstring
   *
   * @param cstring
   */
  public Cstring append(Cstring cstring) {

    CustomIterator iteratorRight = cstring.getIterator(false);

    CharArray tmpCharArray = this.d;

    int len = this.charArrLen;

    while (iteratorRight.hasNext()) {
      tmpCharArray.set(len++, iteratorRight.next());
    }

    this.setCharArrLen(len);

    return this;
  }

  public Cstring append(String strToAppend) {

    return this.append(new Cstring(strToAppend));
  }

  public Cstring append(int number) {

    return this.append(new Cstring(number));
  }

//  public void push(char charAsNum) {
//    this.d.set(this.nextPushPos++, charAsNum);
//    this.charArrLen++;
//  }

  public CustomIterator getIterator(boolean rightToLeft) {
    return new CustomIterator(this.d, this.charArrLen, rightToLeft);
  }

  private static void testBench() {
    Cstring.testBasic();
    Cstring.testAdd();
    Cstring.testEqual();
  }

  private static void testAdd() {
    Cstring a = new Cstring("UCSC");
    Cstring b = new Cstring("Extension");
    Cstring c = a.add(b);
    a.pLn("a = ");
    b.pLn("b = ");
    c.pLn("c = ");
    Cstring d = c.add("USA");
    d.pLn("d = ");
    a.append(b);
    a.pLn("a+b = ");
    a.append("World");
    a.pLn("a+b+World = ");
  }


  private static void testEqual() {
    Cstring a = new Cstring("123456789012345678901234567890123456789012345678901234567890");
    a.pLn("a = ");
    Cstring b = new Cstring("123456789012345678901234567890123456789012345678901234567890");
    b.pLn("b = ");
    u.myassert(a.isEqual(b));
    Cstring c = new Cstring("12345678901234567890123456789012345678901234567890123456789");
    c.pLn("c = ");
    u.myassert(a.isEqual(c) == false);
  }

  private static void testBasic() {
    Cstring a = new Cstring('b');
    a.pLn("a = ");
    Cstring b = new Cstring('7');
    b.pLn("b = ");
    Cstring c = new Cstring("123456789012345678901234567890123456789012345678901234567890");
    c.pLn("c = ");
    Cstring d = c.clone();
    d.pLn("d = ");
    Cstring e = new Cstring("A quick brown fox junped over a lazy dog");
    e.pLn("e = ");
    Cstring f = new Cstring("Gateman sees name garageman sees nametag");
    f.pLn("f = ");
    f.reverse();
    f.pLn("f' = ");

  }

  public static void main(String[] args) {
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("Cstring.java starts");
    testBench();
    System.out.println("Cstring.java ends");
  }

}

class CustomIterator {

  private CharArray charArray;

  private int charArrayNextPos = 0;
  //  private int charArrLen;
  private boolean rightToLeft;

  public CustomIterator(CharArray charArray, int charArrLen, boolean rightToLeft) {
    // initialize cursor
    this.charArray = charArray;

//    this.charArrLen = charArrLen;
    this.rightToLeft = rightToLeft;
    if (rightToLeft) {
      this.charArrayNextPos = charArrLen - 1;
    }

  }

  // Checks if the next element exists
  public boolean hasNext() {
    return this.charArrayNextPos >= 0 && charArray.get(this.charArrayNextPos) != '\0';
  }

  // moves the cursor/iterator to next element
  public char next() {
    char nextChar = charArray.get(this.charArrayNextPos);
    if (this.rightToLeft) {
      this.charArrayNextPos--;
    } else {
      this.charArrayNextPos++;
    }
    return nextChar;
  }

}