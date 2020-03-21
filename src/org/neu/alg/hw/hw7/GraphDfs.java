package org.neu.alg.hw.hw7;

import java.util.ArrayList;

/**
 * File Name: GraphDfs.java
 * Depth First Search on a graph
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDfs.java
 */

class GraphDfs {
  //You can have any number of private variables and private functions
  // file name.txt
  private String t;
  private Graph g;

  private int numV;

  private enum ColorEnum {
    // without visited
    Green,
    // visited but not all of its adjacencies are visited
    Blue,
    // visited and all of its adjacencies are also visited
    Red,
  }

  private ColorEnum[] color;
  // start vertex
  String start;
  //Output
  // cycle[0] == false, if dag, otherwise, non-dag
  boolean[] cycle;
  // iteration
  int[] work;

  // size of the dfsorder array (length)
  int[] size;
  // the topological order
  int[] dfsorder;

  int lastDfsIndexUpdated;


  GraphDfs(String t, Graph g, String start, boolean[] cycle, int[] work, int[] size, int[] dfsorder) {
    this.t = t;
    this.g = g;
    this.start = start;
    this.cycle = cycle;
    this.work = work;
    this.size = size;
    this.dfsorder = dfsorder;

    //WRITE YOUR CODE

    this.numV = this.g.getnumV();

    this.colorWithGreenForAllUnVisitedNodes();

    int vertexId = this.getVertexIdByName(this.start);

    this.dfs(vertexId, -1, -1);

  }

  private int getVertexIdByName(String vertexName) {
    return this.g.insertOrFind(vertexName, true);
  }

  private void colorWithGreenForAllUnVisitedNodes() {

    this.color = new ColorEnum[this.numV];

    for (int vertexId = 0; vertexId < numV; vertexId++) {
      this.color[vertexId] = ColorEnum.Green;
    }
  }

  /**
   * When a node is marked, it's visited, but all of its adjacencies are visited
   *
   * @param vertexId
   */
  private void colorWithBlueForMarkedNode(int vertexId) {

    if (this.color[vertexId] == ColorEnum.Green) {
      this.color[vertexId] = ColorEnum.Blue;
    }

  }

  private boolean isBlue(int vertexId) {
    return this.color[vertexId] == ColorEnum.Blue;
  }

  private boolean isGreen(int vertexId) {
    return this.color[vertexId] == ColorEnum.Green;
  }

  private boolean isRed(int vertexId) {
    return this.color[vertexId] == ColorEnum.Red;
  }

  /**
   * When a node is completed, it's visited and all of its adjacencies are visited
   */
  private void colorWithRedForCompletedNode(int vertexId) {
    this.color[vertexId] = ColorEnum.Red;
  }

  private void setAsCyclicGraph() {
    this.cycle[0] = true;
  }

  private boolean isFromUnDirectedParent(int vertexId, int grandpaVertexId) {
    return this.isUndirected() && vertexId == grandpaVertexId;
  }

  private void updateDfsOrderAndSize(int vertexId) {
    this.dfsorder[this.lastDfsIndexUpdated] = vertexId;
    this.size[0] = ++this.lastDfsIndexUpdated;
  }

  private void updateDfsWorkIteration() {
    this.work[0]++;
  }

  private boolean isUndirected() {
    return this.g.isUndirectedGraph();
  }

  private boolean isDAG() {
    return !this.isUndirected() && this.cycle[0] == false;
  }

  /**
   *
   * @param vertexId - the vertex to visit
   * @param parentVertexId - the vertex comes from
   */
  private void dfs(int vertexId, int parentVertexId, int grandpaVertexId) {

    boolean isAlreadyMarkedAsBlue = this.isBlue(vertexId);
    boolean isFromUnDirectedParent = this.isFromUnDirectedParent(vertexId, grandpaVertexId);

    if (isFromUnDirectedParent) {
      return;
    }

    if (isAlreadyMarkedAsBlue) {
      this.setAsCyclicGraph();
      return;
    }

    if (!this.isGreen(vertexId)) {
      return;
    }

    this.updateDfsWorkIteration();
    this.colorWithBlueForMarkedNode(vertexId);

    int fanoutCount = this.g.numFanout(vertexId);

    for (int fanoutVertexIndex = 0; fanoutVertexIndex < fanoutCount; fanoutVertexIndex++) {
      int fanoutEndVertexId = this.g.getNodeFanout(vertexId, fanoutVertexIndex);
      this.dfs(fanoutEndVertexId, vertexId, parentVertexId);
//      String fanoutEndVertexName = this.g.getRealName(fanoutEndVertexId);
    }

    if (!this.isRed(vertexId)) {
      this.updateDfsOrderAndSize(vertexId);
      this.colorWithRedForCompletedNode(vertexId);
    }



  }

  /**
   * For a given vertex in dfsOrder, all its fanins must appear in its left siblings
   */
  private void proveTopologicalSortingOrderForDAG() {

  }

  public static void main(String[] args) {
    System.out.println("GraphDfs.java starts");
    System.out.println("Use GraphTester.java to test");
    System.out.println("GraphDfs.java Ends");
  }
}