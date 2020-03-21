package org.neu.alg.hw.hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * File Name: GraphBuilder.java
 * All routines that builds Graph
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphBuilder.java
 */

class GraphBuilder {
  private Graph g;
  //You can have any number of private variables and private funcions

  /**
   * @param g - Graph to set
   *          -  insertOrFind(String name, boolean mustbethere)
   * @param f - file path
   */
  GraphBuilder(Graph g, String f) {
    this.g = g;
    //WRITE YOUR CODE

    this.generateGraphFromFile(f);
  }

  private void generateGraphFromFile(String filePath) {

    try {

      BufferedReader br = new BufferedReader(new FileReader(filePath));

      while (br.ready()) {

        String line = br.readLine().trim();
        String[] tokens = line.split("\\s+");

        if (tokens.length < 2) {
          // the first line is the num of the vertex
          // the second line is the num of the edges
          continue;
        }

        String fromNodeName = tokens[0];
        String toNodeName = tokens[1];

        float weight = 0;

        if (tokens.length == 3) {
          weight = Float.valueOf(tokens[2]);
        }

        Integer fromNodeId = this.g.insertOrFind(fromNodeName, false);
        Integer toNodeId = this.g.insertOrFind(toNodeName, false);

        this.g.createEdge(fromNodeId, toNodeId, weight, true);
        this.g.createEdge(toNodeId, fromNodeId, weight, false);

        GraphTest.GraphType graphType = g.getType();
        if (graphType == GraphTest.GraphType.UNDIRECTED || graphType == GraphTest.GraphType.WEIGHTED_UNDIRECTED) {

          this.g.createEdge(toNodeId, fromNodeId, weight, true);
          this.g.createEdge(fromNodeId, toNodeId, weight, false);

        }

      }

    } catch (Exception e) {
      System.out.println(e.fillInStackTrace());
    }

  }


  public static void main(String[] args) {
    System.out.println("GraphBuilder.java starts");
    System.out.println("Use GraphTester.java to test");
    System.out.println("GraphBuilder.java Ends");
  }
}