package org.neu.alg.hw.hw10;

import org.neu.alg.hw.IntUtil;

import java.text.DecimalFormat;
import java.util.HashMap;


/**
 * File Name: GraphDot.java
 * Writes graph as a dot file
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019s
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDot.java
 */

public class GraphDot {
  private Graph g;
  private String fname;
  private IntUtil u = new IntUtil();

  //You can have any number of private variables
  // https://dreampuf.github.io/GraphvizOnline/#digraph%20G%20%7B%0A%0A%20%20subgraph%20cluster_0%20%7B%0A%20%20%20%20style%3Dfilled%3B%0A%20%20%20%20color%3Dlightgrey%3B%0A%20%20%20%20node%20%5Bstyle%3Dfilled%2Ccolor%3Dwhite%5D%3B%0A%20%20%20%20a0%20-%3E%20a1%20-%3E%20a2%20-%3E%20a3%3B%0A%20%20%20%20label%20%3D%20%22process%20%231%22%3B%0A%20%20%7D%0A%0A%20%20subgraph%20cluster_1%20%7B%0A%20%20%20%20node%20%5Bstyle%3Dfilled%5D%3B%0A%20%20%20%20b0%20-%3E%20b1%20-%3E%20b2%20-%3E%20b3%3B%0A%20%20%20%20label%20%3D%20%22process%20%232%22%3B%0A%20%20%20%20color%3Dblue%0A%20%20%7D%0A%20%20start%20-%3E%20a0%3B%0A%20%20start%20-%3E%20b0%3B%0A%20%20a1%20-%3E%20b3%3B%0A%20%20b2%20-%3E%20a3%3B%0A%20%20a3%20-%3E%20a0%3B%0A%20%20a3%20-%3E%20end%3B%0A%20%20b3%20-%3E%20end%3B%0A%0A%20%20start%20%5Bshape%3DMdiamond%5D%3B%0A%20%20end%20%5Bshape%3DMsquare%5D%3B%0A%7D

  GraphDot(Graph g, String s) {
    this.g = g;
    this.fname = s;
    //WRITE CODE

    GraphTest.GraphType graphType = g.getType();
    boolean isDirected = graphType == GraphTest.GraphType.DIRECTED || graphType == GraphTest.GraphType.WEIGHTED_DIRECTED;

    u.openDotFile(s);

    int gnumV = g.getnumV();

    HashMap hmReduceUndirectedDuplication = new HashMap<String, Boolean>();

    String directSymbol = "->";

    for (int i = 0; i < gnumV; ++i) {
      String n = g.getRealName(i);
      {
        int f = g.numFanout(i);
        if (f == 0) {
//					System.out.println("NONE");
        } else {
          for (int j = 0; j < f; ++j) {
//						++numedge;
            int fo = g.getNodeFanout(i, j);
            String nf = g.getRealName(fo);

            String edgeExpr = n + directSymbol + nf;
            String edgeExprReverse = nf + directSymbol + n;

            if (!isDirected) {
              if (
                  hmReduceUndirectedDuplication.containsKey(edgeExpr)
                      && hmReduceUndirectedDuplication.get(edgeExpr).equals(true)
              ) {
                continue;
              }

              hmReduceUndirectedDuplication.put(edgeExprReverse, true);
            }

            DecimalFormat df = new DecimalFormat("#.#");

            String weightLabel = graphType == GraphTest.GraphType.WEIGHTED_UNDIRECTED
                || graphType == GraphTest.GraphType.WEIGHTED_DIRECTED ? String.format("label=\"%s\", ", df.format(g.getNodeFanoutEdgeWeight(i, j)))
                : "";

            String extraLabel = String.format(
                "[%s dir=\"%s\", color=\"red\"]",
                weightLabel,
                isDirected ? "forward" : "none"
            );

            u.appendDotFile(
                s,
                n + " " + directSymbol + " " + nf + extraLabel + ";"
            );

          }
        }
      }
    }
    u.closeDotFile(s);
  }


  public static void main(String[] args) {
    System.out.println("GraphDot.java starts");
    System.out.println("Use GraphTester.java to test");
    System.out.println("GraphDot.java Ends");
  }
}