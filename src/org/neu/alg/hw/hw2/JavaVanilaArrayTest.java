package org.neu.alg.hw.hw2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * File Name: JavaVanilaArrayTest
 * Test ArrayList provided by java
 *
 * To compile: IntUtil.java RandomInt.java Complex.java JavaVanilaArrayTest.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */


public class JavaVanilaArrayTest {
  static boolean ASCEND = true ;
  static boolean DECEND = false ;

  private void PI(String t, int[] a, boolean useindex) {
    System.out.println("------------ " + t + " ------------------------") ;
    if (useindex) {
      for (int i = 0; i < a.length; ++i) {
        System.out.print("a[" + i + "]= " + a[i] + " ") ;
      }
    }else {
      for (int e:a) {
        System.out.print(e + " ") ;
      }
    }
    System.out.println() ;
  }

  private void PC(String t, Complex[] a, boolean useindex) {
    System.out.println("------------ " + t + " ------------------------") ;
    if (useindex) {
      for (int i = 0; i < a.length; ++i) {
        System.out.print("a[" + i + "]= " + a[i] + " ") ;
      }
    }else {
      for (Complex e:a) {
        System.out.print(e + " ") ;
      }
    }
    System.out.println() ;
  }

  private void P2C(String t, Complex[][]  a) {
    System.out.println("------------ " + t + " ------------------------") ;
    for (Complex[] aarray :a) {
      for (Complex e:aarray) {
        System.out.format("%10s", e);
      }
      System.out.println() ;
    }
  }

  private void intSort(int [] p , boolean ascend) {
    Arrays.sort(p) ;
    if (ascend == false) {
      int l = p.length ;
      int i = 0 ;
      int j = l - 1 ;
      while (j > i) {
        int t = p[i] ;
        p[i] = p[j] ;
        p[j] = t ;
        ++i ;
        --j ;
      }
    }
  }

  private class ComplexComparator implements Comparator<Complex> {
    private boolean ascend ;
    ComplexComparator(boolean ascend) {
      this.ascend = ascend ;
    }
    @Override
    public int compare(Complex x, Complex y) {
      int d = x.getY() - y.getY() ;
      if (ascend) {
        return d ;
      }else {
        return -d ;
      }
    }
  }

  private void ComplexSort(Complex[] p , boolean ascend) {
    ComplexComparator e = new ComplexComparator(ascend);
    Arrays.sort(p,e);
  }

  /*
   * This will sort ArrayList of ArrayLists of complex[2]
   */
  private class Complex2Comparator implements Comparator<Complex[]> {
    private boolean ascend ;
    Complex2Comparator(boolean ascend) {
      this.ascend = ascend ;
    }
    @Override
    public int compare(Complex[] x, Complex[] y) {
      int d = x[2].getY() - y[2].getY() ;
      if (ascend) {
        return d ;
      }else {
        return -d ;
      }
    }
  }

  private void Complex2Sort(Complex[][] p , boolean ascend) {
    Complex2Comparator e = new Complex2Comparator(ascend);
    Arrays.sort(p,e);
  }


  private void testInteger() {
    System.out.println("--------   testInteger()  ---------------- ") ;
    {
      System.out.println("--- How to add elements and change elements in Vanila Array --") ;
      int [] a = new int[5]; //You cannot change afterwards
      for (int i = 0; i < 5; ++i) {
        a[i] = i ;
      }
      PI("after adding 5 elements", a,true);
      /* You cannot add at arbitrary place. This will crash*/
      System.out.println("You cannot set at position 5 as you have not allocated. a[5]= 5 will crash") ;
      //a.set(5, 5);

      a[0] = 17 ;
      a[3] = 100 ;
      PI("after changing a[0] = 17 and a[3] = 100 ", a,true);
    }

    {
      System.out.println("------ SORTING in ArrayList-----------") ;
      int [] a = new int [10] ;
      Random r = new Random() ;
      for (int i = 0; i < 10; ++i) {
        int p = RandomInt.getRandomInt(r,0,10); //This gives number between 0 to 9
        a[i] = p ;
      }
      PI("Before sort in ASCENDING ORDER",a,false);
      intSort(a,ASCEND) ;
      PI("After sort in ASCENDING ORDER",a,false);
      intSort(a,DECEND) ;
      PI("After sort in DESCENDING ORDER",a,false);
    }
  }

  private void testComplex() {
    System.out.println("--------   testComplex()  ---------------- ") ;
    {
      System.out.println("--- How to add complex objects and change elements is Vanila Array --") ;

      Complex [] a = new Complex[5];
      for (int i = 0; i < 5; ++i) {
        a[i] = new Complex(i+5, -(i+10)) ;
      }
      PC("afrer adding 5 elements", a, true);
      /* You cannot add at arbitrary place. This will crash*/
      System.out.println("You cannot set at position 5 as you have not allocated. a[5] = 5 will crash") ;
      //a.set(5, 5);

      a[0] =  new Complex(17, -(17));
      a[3] =  new Complex(100, -(100));
      PC("after changing a[0] = 17 and a[3] = 100 through set command ", a, true);
    }
    {
      System.out.println("------ SORTING COMPLEX in ArrayList-----------") ;
      Complex [] a = new Complex[10] ;
      Random r = new Random() ;
      for (int i = 0; i < 10; ++i) {
        int p = RandomInt.getRandomInt(r,0,10); //This gives number between 0 to 9
        a[i] = new Complex(p,-p);
      }
      PC("Before sort ",a,false);
      ComplexSort(a, ASCEND) ;
      PC("After sort in ASCENDING ORDER on y",a,false);
      ComplexSort(a,DECEND) ;
      PC("After sort in DESCENDING ORDER on y",a,false);
    }
  }

  private void test2DComplex(){
    System.out.println("--------   test2DComplex  ---------------- ") ;
    Complex[][] a = new Complex[5][] ;
    int k = 1 ;
    for (int i = 0; i < 5; ++i) {
      Complex[] p = new Complex[8] ;
      a[i] = p ;
      for (int j = 0; j < 8; ++j) {
        p[j] = new Complex(k, k-1) ;
        ++k ;
      }
    }
    P2C("Before",a) ;
    for (int i = 0; i < 5; ++i) {
      ComplexSort(a[i], DECEND) ;
    }
    P2C("After sorting in descending order on y",a) ;
    Complex2Sort(a, DECEND);
    P2C("After sorting in descending order of 2 column on y",a) ;
    Complex2Sort(a, ASCEND);
    P2C("After sorting in ascending order of 2 column on y",a) ;
  }

  private void testBench() {
    testInteger();
    testComplex();
    test2DComplex() ;
  }

  public static void main(String[] args) {
    System.out.println("JavaVanilaArrayTest.java");
    JavaVanilaArrayTest u = new JavaVanilaArrayTest() ;
    u.testBench();
    System.out.println("Done JavaVanilaArrayTest.java ");
  }
}