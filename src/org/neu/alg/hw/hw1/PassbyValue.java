package org.neu.alg.hw.hw1;

/**
 * File Name: PassByValue.java
 * Teaches concept of pass by value of basic type and objects
 * JAVA 11
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

public class PassbyValue {
    class Int{
        int x ;
        Int() {
            x = 0 ;
        }
        Int(int y)  {
            x = y ;
        }
        void setX(int m) {
            x = m ;
        }
        int getX() {
            return x ;
        }
    }

    void swap1(int x, int y) {
        int t = x ;
        x = y ;
        y = t ;
        System.out.println("End of  swap x = " + x + " y = " + y) ;
    }

    void testSwap1() {
        int x = 10 ;
        int y = 20 ;
        System.out.println("Before swap1 x = " + x + " y = " + y) ;
        swap1(x,y);
        System.out.println("After  swap1 x = " + x + " y = " + y) ;
    }

    void swap2(Integer x, Integer y) {
        Integer t = x ;
        x = y ;
        y = t ;
        System.out.println("End of  swap2 x = " + x + " y = " + y) ;
    }

    void testSwap2() {
        //This may not work in old java.
        Integer x = new Integer(10) ;
        Integer y = new Integer(20);
        //static Integer	vaLueOf(int i)
        //Returns an Integer instance representing the specified int value.
        //Use below 2 lines if above two lines does not compile
        //Integer x = Integer.valueOf(10);
        //Integer y = Integer.valueOf(20);
        System.out.println("Before swap2 x = " + x + " y = " + y) ;
        swap2(x,y);
        System.out.println("After  swap2 x = " + x + " y = " + y) ;
    }

    void swap3(Integer x, Integer y) {
        Integer t = new Integer(x);
        x = y ;
        y = t ;
        System.out.println("End of  swap3 x = " + x + " y = " + y) ;
    }

    void testSwap3() {
        Integer x = new Integer(10);
        Integer y = new Integer(20);
        System.out.println("Before swap3 x = " + x + " y = " + y) ;
        swap3(x,y);
        System.out.println("After  swap3 x = " + x + " y = " + y) ;
    }

    void swap4(Integer x, Integer y) {
        Integer t = new Integer(x);
        x = new Integer(y);  ;
        y = new Integer(t);  ;
        System.out.println("End of  swap4 x = " + x + " y = " + y) ;
    }

    void testSwap4() {
        Integer x = new Integer(10);
        Integer y = new Integer(20);
        System.out.println("Before swap4 x = " + x + " y = " + y) ;
        swap4(x,y);
        System.out.println("After  swap4 x = " + x + " y = " + y) ;
    }

    void swap5(Int x, Int y) {
        Int t = x ;
        x.setX(y.getX());
        y.setX(t.getX()) ;
        System.out.println("End of  swap5 x = " + x.getX() + " y = " + y.getX()) ;
    }

    void testSwap5() {
        Int x = new Int(10) ;
        Int y = new Int(20);
        System.out.println("Before swap5 x = " + x.getX() + " y = " + y.getX()) ;
        swap5(x,y);
        System.out.println("After  swap5 x = " + x.getX() + " y = " + y.getX()) ;
    }

    void swap6(Int x, Int y) {
        int t = x.getX();
        x.setX(y.getX());
        y.setX(t) ;
        System.out.println("End of  swap6 x = " + x.getX() + " y = " + y.getX()) ;
    }

    void testSwap6() {
        Int x = new Int(10) ;
        Int y = new Int(20);
        System.out.println("Before swap6 x = " + x.getX() + " y = " + y.getX()) ;
        swap6(x,y);
        System.out.println("After  swap6 x = " + x.getX() + " y = " + y.getX()) ;
    }

    void swap7(int[] a) {
        int t = a[0] ;
        a[0] = a[1] ;
        a[1] = t ;
    }

    void testSwap7() {
        int x = 10 ;
        int y = 20 ;
        int a[] = {x,y} ;
        System.out.println("Before swap7 x = " + x + " y = " + y) ;
        swap7(a);
        x = a[0] ;
        y = a[1] ;
        System.out.println("After  swap7 x = " + x + " y = " + y) ;
    }

    int[] swap8(int x, int y) {
        int[] t = {y,x} ;
        return t ;
    }

    void testSwap8() {
        int x = 10 ;
        int y = 20 ;
        System.out.println("Before swap8 x = " + x + " y = " + y) ;
        int a[] = swap8(x,y);
        x = a[0] ;
        y = a[1] ;
        System.out.println("After  swap8 x = " + x + " y = " + y) ;
    }

    void f(int n, int s, int square) {
        s = n *(n +1) / 2 ;
        square = n * n ;
    }

    void test1() {
        int n = 5 ;
        int s = 0 ;
        int sq = 0 ;
        f(n,s,sq) ;
        System.out.println("n = " + n + " sum = " + s + " square = " + sq) ;
    }

    void f(int n, Int s, Int square) {
        s.setX(n *(n +1) / 2) ; ;
        square.setX(n * n);
    }

    void test2() {
        int n = 5 ;
        Int s= new Int();
        Int sq = new Int() ;
        f(n,s,sq) ;
        System.out.println("n = " + n + " sum = " + s.getX() + " square = " + sq.getX()) ;
    }


    void test() {
        testSwap1() ;
        testSwap2() ;
        testSwap3() ;
        testSwap4() ;
        testSwap5() ;
        testSwap6() ;
        testSwap7() ;
        testSwap8() ;
        test1();
        test2();
    }

    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        System.out.println("Java version used for this program is " + version);
        PassbyValue p = new PassbyValue() ;
        p.test() ;
        System.out.println("PassByValue.java END");
    }
}