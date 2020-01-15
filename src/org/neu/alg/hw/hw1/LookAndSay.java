package org.neu.alg.hw.hw1;

/**
 * File Name: LookAndSay.java
 * LookAndSay concrete class
 *
 *
 * To Compile: IntUtil.java RandomInt.java LookAndSay.java LookAndSayBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class LookAndSay extends LookAndSayBase {
    LookAndSay() {
        //NOTHING CAN BE CHANGED HERE
        testBench();
    }

    @Override
    protected String lookAndSay(String s) {
        //NOTHING CAN BE CHANGED HERE
        return alg(s) ;
    }

    @Override
    protected String lookAndSay(int n) {
        //NOTHING CAN BE CHANGED HERE
        return alg(n) ;
    }

    private String alg(String s) {
        //WRITE CODE
        //You can have any number of private functions and variables
    }

    private String alg(int n) {
        //WRITE CODE
        //You can have any number of private functions and variables
    }

    public static void main(String[] args) {
        //NOTHING CAN BE CHANGED HERE
        System.out.println("LookAndSay problem STARTS");
        LookAndSay m = new LookAndSay() ;
        System.out.println("All LookAndSay tests passed. You are genius");
        System.out.println("LookAndSay problem ENDS");
    }
}