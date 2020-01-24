package org.neu.alg.hw.hw2;


/**
 * File Name: Complex.java class acts as collection of real, imaginary and
 * string s = real +j (imaginary)
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

public class Complex implements Comparable<Complex> {
  // All private data members
  private final int x; // WE cannot change it. Used for Hash
  private int y;
  private String s;
  private static boolean m_display = true;

  public Complex(int x, int y) {
    if (m_display) {
      System.out.println("In complex constructor " + x + " " + y);
    }
    this.x = x;
    this.y = y;
    this.s = null;
    buildString();
  }

  public Complex(int x) {
    this(x, 0); // This must be in first line
    if (m_display) {
      System.out.println("In complex constructor " + x + " " + "0");
    }
  }

  public Complex() {
    this(0, 0); // This must be in first line
    if (m_display) {
      System.out.println("In complex constructor " + "0" + " " + "0");
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
    buildString();
  }

  @Override
  public String toString() {
    return s;
  }

  // Comment hashCode() and equals() for testing Treemap.java
  // Comment hashCode() and equals() for testing JavaHashSet.java

  // A class that overrides equals must also override hashCode

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Complex other = (Complex) obj;
    if (x != other.x)
      return false;
    return true;
  }

  // < operator. TestTreemap works because of compareTo functions
  @Override
  public int compareTo(Complex o) {
    // TODO Auto-generated method stub
    boolean show = true;
    if (show) {
      System.out.println("public int compareTo(Complex o)");
      System.out.println("this = " + this);
      System.out.println("o    = " + o);
    }
    if (this.x < o.x) {
      if (show) {
        System.out.println("this < o. Return -1");
      }
      return -1;
    }
    if (this.x > o.x) {
      if (show) {
        System.out.println("this > o. Return 1");
      }
      return 1;
    }
    if (show) {
      System.out.println("this == o. Return 0");
    }
    return 0;
  }

  private void buildString() {
    StringBuffer n = new StringBuffer();
    n.append(x);
    int t = y;
    if (y > 0) {
      n.append("+j");
    } else {
      n.append("-j");
      t = -y;
    }
    n.append(t);
    s = new String(n);
  }

  private static void testBench() {
    Complex c1 = new Complex(2, 3);
    System.out.println("c1 = " + c1);
    Complex c2 = new Complex(2, -200);
    System.out.println("c2 = " + c2);
    Complex c3 = new Complex(-20, 4);
    System.out.println("c3 = " + c3);
    Complex c4 = new Complex(-18, -99);
    System.out.println("c4 = " + c4);
    c2.setY(3);
    System.out.println("c2 = " + c2);
    if (c1 == c2) {
      System.out.println("c1 == c2");
    } else {
      System.out.println("c1 != c2");
    }
    if (c1.equals(c2)) {
      System.out.println("c1 equals c2");
    } else {
      System.out.println("c1 !equals c2");
    }
    int h1 = c1.hashCode();
    int h2 = c2.hashCode();
    System.out.println("c1 hashcode = " + h1);
    System.out.println("c2 hashcode = " + h2);

    Complex c6 = c1;
    if (c6 == c1) {
      System.out.println("c6 == c1");
    } else {
      System.out.println("c6 != c1");
    }
    h1 = c1.hashCode();
    h2 = c6.hashCode();
    System.out.println("c1 hashcode = " + h1);
    System.out.println("c6 hashcode = " + h2);
  }

  public static void main(String[] args) {
    System.out.println("Complex.java starts");
    testBench();
    System.out.println("Complex.java DONE");
  }
}