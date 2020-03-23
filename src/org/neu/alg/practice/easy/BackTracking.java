package org.neu.alg.practice.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BackTracking {

  public void permutation() {
    String[] students = {"Paul", "Tom", "Peter", "Julia"};
    HashMap<String, Integer> bounding = new HashMap<>();
    bounding.put("Julia", 1);

  }

  private void permute(String[] students, HashMap<String, Integer> bounding) {
    String[] results = new String[students.length];



    System.out.println(Arrays.toString(results));


  }

  public static void main(String... args) {

  }
}
