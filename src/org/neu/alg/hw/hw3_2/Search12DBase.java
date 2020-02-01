package org.neu.alg.hw.hw3_2;

import org.neu.alg.hw.*;

/**
 * File Name: Search12DBase.java
 * Java11
 * To Compile: IntUtil.java RandomInt.java Search12DBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class Search12DBase{
    protected IntUtil u = new IntUtil();
    private int matrix[][] ;
    private int kount ;

    //I don't know how to write it
    //Override by the concrete class
    abstract protected boolean search12D(int n) ;

    protected int r() {
        return matrix.length;
    }

    //It is assumed each rows has same numbers of columns
    protected int c() {
        return matrix[0].length;
    }

    protected int kount() {
        return kount;
    }

    /*
     * O(1) complexity
     * All access to the matrix goes through this routines
     */
    protected int get(int r, int c) {
        ++kount ;
        int rows = r();
        int cols = c();
        u.myassert(r >= 0 && r < rows);
        u.myassert(c >= 0 && c < cols);
        return matrix[r][c] ;
    }

    private void pm() {
        System.out.println() ;
        System.out.printf("%8c",' ') ;
        for (int i = 0; i < c(); ++i) {
            System.out.printf("%4d", i);
        }
        System.out.println() ;

        System.out.println() ;
        for (int i = 0; i < r(); ++i) {
            System.out.printf("%4d", i);
            char ch = '|' ;
            System.out.printf("%4c",ch) ;
            for (int j = 0; j < c(); ++j) {
            System.out.printf("%4d", matrix[i][j]);
        }
            System.out.println() ;
        }
        System.out.println() ;
    }

    private void one(String s, int m[][], int t, boolean e) {
        System.out.println("-----------" + s + " ----------------------") ;
        boolean f = false ;
        kount = 0 ;
        if (m != null && m.length > 0) {
            matrix = m ;
            pm() ;
            f = search12D(t) ;
        }
        u.myassert(f == e);
    }

    protected void testBench() {
        tests() ;
    }

    private void tests() {
        {
            int m[][] = {
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
            };
            int t = 3;
            one("1", m, t, true);

            t = 13;
            one("2", m, t, false);
        }
        {
            int m[][] = {
                {1}
            };
            int t = 1;
            one("ONEELEMENT", m, t, true);

            t = 3;
            one("ONEELEMENTNOTTHERE", m, t, false);
        }
        {
            int m[][] = null ;
            int t = 1;
            one("EMPTY", m, t, false);
        }
        {
            int m[][] = {
                {1,1},
            };
            int t = 1;
            one("ONE ROW TWO COL", m, t, true);

            t = 2;
            one("ONE ROW TWO COL ", m, t, false);
        }
    }


    public static void main(String[] args) {
        System.out.println("Search12DBase.java STARTS");
        String version = System.getProperty("java.version");
        System.out.println("Java version used for this program is " + version);
        System.out.println("You cannot instantiate Search12DBase class: Search12DBase p = new Search12DBase() ; ");
        //Search12DBase p = new Search12DBase() ;
        System.out.println("Search12DBase.java ENDS");
    }
}