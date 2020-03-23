package org.neu.alg.practice.easy;

import org.neu.alg.hw.hw7.Graph;
import org.neu.alg.hw.hw7.GraphTest;
import org.neu.alg.practice.lib.utils.PathUtil;

/**
 *
 */
public class EulerPath {

  public void showPath(Graph graph) {

  }

  private void connectStartToEnd() {

  }


  /**
   * Euler Path exists iif:
   *
   * - a start vertex which indegree = outdegree - 1
   * - a end vertex which indegree = outdegree + 1
   * - All other vertexes has the same indegree and outdegree
   *
   * @return
   */
  private boolean isEulerPathExist() {
    return true;
  }

  private void getStart() {

  }
  private void getEnd() {

  }

  private void dfs() {

  }

  public static void main(String... args) {
    Graph graph = new Graph(GraphTest.GraphType.DIRECTED);

    EulerPath eulerPath = new EulerPath();
    PathUtil pathUtil = new PathUtil();

    graph.buildGraph(PathUtil.getMockDataPath("euler_path.txt"));
//    graph.writeDot("../mock_data/euler_path.dot");
    graph.writeDot(PathUtil.getMockDataPath("euler_path.dot"));
  }
}
