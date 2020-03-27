package org.neu.alg.hw.hw9;

import org.neu.alg.hw.IntUtil;

import java.util.Arrays;
import java.util.HashMap;

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

  public static final IntUtil u = new IntUtil();

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

    this.lastDfsIndexUpdated = dfsorder.length - 1;

    //WRITE YOUR CODE

    this.numV = this.g.getnumV();

    this.colorWithGreenForAllUnVisitedNodes();

    int vertexId = this.getVertexIdByName(this.start);

    this.dfs(vertexId, -1, -1);

    this.proveTopologicalSortingOrderForDAG();

    this.proceedAnyUnvisitedVertex();

    this.stat();

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
    this.dfsorder[this.lastDfsIndexUpdated--] = vertexId;
    this.size[0]++;
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
   * @param vertexId       - the vertex to visit
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
    if (!this.isDAG()) {
      return;
    }

    HashMap<Integer, Integer> dfsOrderHashMap = new HashMap<>();

    for (int vertexOrder = 0; vertexOrder < this.dfsorder.length; vertexOrder++) {
      dfsOrderHashMap.put(this.dfsorder[vertexOrder], vertexOrder);
    }

    for (int vertexId = 0; vertexId < this.numV; vertexId++) {
      for (int faninIndex = 0; faninIndex < this.g.numFanin(vertexId); faninIndex++) {
        int faninVertexId = this.g.getNodeFanin(vertexId, faninIndex);
        this.ensureFaninPrecededToVertex(faninVertexId, vertexId, dfsOrderHashMap);
      }
    }

  }

  private void ensureFaninPrecededToVertex(int faninVertexId, int vertexId, HashMap<Integer, Integer> dfsOrderHashMap) {
    this.u.myassert(dfsOrderHashMap.get(faninVertexId) < dfsOrderHashMap.get(vertexId));
  }

  private void proceedAnyUnvisitedVertex() {
    for (int vertexId = 0; vertexId < this.color.length; vertexId++) {

      if (this.color[vertexId] == ColorEnum.Green) {
        this.dfs(vertexId, -1, -1);
      }
    }
  }

  private void stat() {
    System.out.println("");
    System.out.println(String.format("File %s", this.t));
    System.out.println(String.format("Num Vertices  = %d", this.numV));
    System.out.println(String.format("Num Edges     = %d", this.g.getnumE()));
    System.out.println(String.format("Work done     = %d", this.work[0]));
    System.out.println(String.format("Has Cycle     = %s", this.cycle[0] ? "YES": "NO"));
    System.out.println(String.format("DFS topological order = %s", Arrays.toString(this.dfsorder)));
    System.out.println("------");
  }

  public static void main(String[] args) {
    System.out.println("GraphDfs.java starts");
    System.out.println("Use GraphTester.java to test");
    System.out.println("GraphDfs.java Ends");
  }
}