package org.neu.alg.hw.hw2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * File Name: SelectionDay.java
 * SelectionDay concrete class
 *
 * To Compile: IntUtil.java RandomInt.java SelectionDay.java SelectionDayBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class SelectionDay extends SelectionDayBase{
  int itr ;
  SelectionDay() {
    //NOTHING CAN BE CHANGED HERE
    testBench();
  }

  @Override
  protected int race() {
    //NOTHING CAN BE CHANGED HERE
    itr = 0 ;
    alg() ;
    return itr ;
  }

  //You can have any number of private data structures and procedure
  //YOU WRITE YOUR CODE BELOW. DO NOT CRAM entire code in one procedure
  //Your output must be such that, any idiot, executes your steps should be able to conduct the race.

  //Number of tracks
  private int NT = N/5 ;

  public static void main(String[] args) {
    //NOTHING CAN BE CHANGED HERE
    System.out.println("SelectionDay problem STARTS");
    SelectionDay m = new SelectionDay() ;
    System.out.println("SelectionDay problem ENDS");
  }
}