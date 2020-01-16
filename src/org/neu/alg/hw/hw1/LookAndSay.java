package org.neu.alg.hw.hw1;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

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

    /**
     * Return a human-like speaking for the given string
     *
     * @exception
     *
     * @example
     * assert(alg("123")).toBe("111213")
     *
     * @example
     * assert(alg("99999999")).toBe("109")
     *
     * @example
     * assert(alg("9876543210")).toBe("19181716151413121110")
     *
     * @param s - the input string
     * @return
     */
    private String alg(String s) {
        //WRITE CODE
        //You can have any number of private functions and variables

        StringBuilder readOutResult = new StringBuilder();

        final int[] statArr = new int[10];

        CharacterIterator it = new StringCharacterIterator(s);

        while (it.current() != CharacterIterator.DONE) {
            int col1 = charToInt(it.current());
            statArr[col1]++;
            it.next();
        }

        it = new StringCharacterIterator(s);

        while (it.current() != CharacterIterator.DONE) {
            int currentNum = charToInt(it.current());
            int count = statArr[currentNum];

            if (count != 0) {
                statArr[currentNum] = 0;
                readOutResult.append("" + count + currentNum);
            }

            it.next();
        }

        consoleLog(readOutResult.toString());

        return readOutResult.toString();
    }

    private String alg(int n) {
        //WRITE CODE
        //You can have any number of private functions and variables
        String readOutResult = "";

        return readOutResult;
    }

    private int charToInt(char s) {
        return Integer.parseInt(String.valueOf(s));
    }

    private void consoleLog(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        //NOTHING CAN BE CHANGED HERE
        System.out.println("LookAndSay problem STARTS");
        LookAndSay m = new LookAndSay() ;
        System.out.println("All LookAndSay tests passed. You are genius");
        System.out.println("LookAndSay problem ENDS");
    }
}