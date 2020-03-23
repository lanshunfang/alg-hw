package org.neu.alg.practice.easy;

import org.neu.alg.hw.hw7.Graph;
import org.neu.alg.hw.hw7.GraphTest;
import org.neu.alg.practice.lib.utils.PathUtil;

public class EulerPath {

  public static void main(String... args) {
    Graph graph = new Graph(GraphTest.GraphType.DIRECTED);

    EulerPath eulerPath = new EulerPath();
    PathUtil pathUtil = new PathUtil();

//    System.out.println(EulerPath.class.getResource("EulerPath.class"));
    System.out.println(PathUtil.getMockDataPath("euler_path.txt"));
//    System.out.println(eulerPath.getClass().getProtectionDomain().getCodeSource().getLocation());

    graph.buildGraph(PathUtil.getMockDataPath("euler_path.txt"));
//    graph.writeDot("../mock_data/euler_path.dot");
    graph.writeDot(PathUtil.getMockDataPath("euler_path.dot"));
  }
}
