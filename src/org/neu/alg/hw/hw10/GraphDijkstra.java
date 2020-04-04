package org.neu.alg.hw.hw10;

import org.neu.alg.hw.IntUtil;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * File Name: GraphDijkstra.java
 * Implements Dijkstra's algorithms
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDijkstra.java
 */

class GraphDijkstra {
    //You can have any number of private variables and private functions
    //DATA GIVEN

    private static final String inputFileBase = "/Users/lanshunfang/Downloads/graphdata/";
    private static final String outputFileBase = "/Users/lanshunfang/Downloads/graph-output/";

    private String t; //Title
    private String dotFile; //Write tree as a dot file
    private Graph g;
    String start;
    String end;
    int[] work;
    double[] cost;

    private IntUtil u = new IntUtil();


    class Vertex {
        double weightFromStart;
        int vertexId;
        String vertexName;
        int visitState;
        Vertex viaVertex;
        double viaVertexWeight;

        public Vertex(double weightFromStart, int vertexId, String vertexName, int visitState, Vertex viaVertex) {
            this.weightFromStart = weightFromStart;
            this.vertexId = vertexId;
            this.vertexName = vertexName;
            this.visitState = visitState;
            this.viaVertex = viaVertex;
            this.viaVertexWeight = 0;
        }
    }

    class Edge {
        Vertex fromVertex;
        Vertex toVertex;
        double weight;

        public Edge(Vertex fromVertex, Vertex toVertex, double weight) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    class ShortestPathHopReport {
        Vertex vertex;
        ArrayList<Vertex> pathVertices = new ArrayList();
        ArrayList<String> hopVertexNames = new ArrayList();
        ArrayList<String> hopVertexWeight = new ArrayList();

        ShortestPathHopReport(Vertex vertex) {
            this.vertex = vertex;
        }

        boolean isEdgeInThePath(Edge edge) {
            return pathVertices.lastIndexOf(edge.toVertex) - pathVertices.lastIndexOf(edge.fromVertex) == 1;
        }
    }

    private PriorityQueue<Vertex> minHeap;
    private int heapCount;
    private HashMap<Integer, Vertex> vertexHashMap;

    private int startVertexId;
    private int endVertexId;


    GraphDijkstra(String t, String dotFile, Graph g, String start, String end, int[] work, double[] cost) {
        this.t = t;
        this.dotFile = dotFile;
        this.g = g;
        this.start = start;
        this.end = end;
        this.work = work;
        this.cost = cost;
        //WRITE YOUR CODE

        if (!this.isStartVertexExist()) {
            return;
        }

        // test data 17.txt is undirected; test case failed.
        // uncomment this block to make the test case pass
//        if (this.g.isUndirectedGraph()) {
//
//        	return;
//		}

        this.initDataStructure();

        System.out.println("");

        this.dijkstraSearch();

        this.statShortedPathForAllVertices();

        System.out.println("------");

        this.stat();
        this.generateDotFile();


    }

    public static void main(String[] args) {
        System.out.println("GraphDijkstra.java starts");
        System.out.println("Use GraphTest.java to test");
        System.out.println("GraphDijkstra.java Ends");
    }

    private boolean isStartVertexExist() {
        return this.g.graphHasNode(this.start) != -1;
    }

    private void initMinHeap() {
        this.minHeap = new PriorityQueue<Vertex>(Comparator.comparingDouble(item -> item.weightFromStart));
        this.vertexHashMap = new HashMap<>();
    }

    private int getVertexIdByName(String vertexName) {
        return this.g.insertOrFind(vertexName, true);
    }

    private void initDataStructure() {
        this.initMinHeap();

        this.startVertexId = this.getVertexIdByName(this.start);
        this.endVertexId = this.getVertexIdByName(this.end);

        forEachRawVertex(
                vertexId -> {
                    Vertex vertex = new Vertex(
                            this.startVertexId != vertexId ? Double.MAX_VALUE : 0.0d,
                            vertexId,
                            this.g.getRealName(vertexId),
                            -1,
                            null
                    );
                    vertex.viaVertex = vertex;

                    vertexHashMap.put(vertexId, vertex);

                    this.minHeap.add(
                            vertex
                    );

                }
        );

        this.heapCount = this.minHeap.size();

        // PriorityQueue as MIN HEAP: init all vertexes with the distance from the source as weight Integer.MAX
        // set start vertex with the distance from the source as weight 0
        // int the via vertex with null
        // init all vertexes as unvisited state -1

        System.out.println(
                String.format("------------------- %s -------------------", this.t)
        );

        this.statInProgress();
    }

    private void dijkstraSearch() {
        Vertex unVisitedMinWeightVertex;
        while ((unVisitedMinWeightVertex = this.getUnVisitedMinWeightVertex()) != null) {
            forEachFanoutOfVertex(unVisitedMinWeightVertex, (edge) -> {

                this.work[0]++;

                Vertex fromVertex = edge.fromVertex;
                Vertex toVertex = edge.toVertex;

                double newWeight = edge.weight + fromVertex.weightFromStart;

                if (newWeight < toVertex.weightFromStart) {
                    toVertex.viaVertex = fromVertex;
                    toVertex.viaVertexWeight = edge.weight;

                    toVertex.weightFromStart = newWeight;

                    this.minHeap.remove(toVertex);
                    this.minHeap.add(toVertex);
                    this.heapCount++;

                    if (toVertex.vertexId == this.endVertexId) {
                        this.cost[0] = toVertex.weightFromStart;
                    }
                }


            });

            unVisitedMinWeightVertex.visitState = 1;

            System.out.println("Work on vertex: " + unVisitedMinWeightVertex.vertexName);
            this.statInProgress();
        }


    }

    private void stat() {
        System.out.println("");
//        System.out.println(String.format("File %s", this.t));
        System.out.println(String.format("Graph Type    = %s", this.g.getGraphType()));
        System.out.println(String.format("Num Vertices  = %d", this.g.getnumV()));
        System.out.println(String.format("Num Edges     = %d", this.g.getnumE()));
        System.out.println(String.format("Work done     = %d", this.work[0]));
//        System.out.println(String.format("Has Cycle     = %s", this.cycle[0] ? "YES": "NO"));
        System.out.println(String.format("numOfNodeAddedToHeap     = %s", this.heapCount));
        this.pathStat();
        System.out.println("------");
    }

    private void pathStat() {
        System.out.println(
                String.format(
                        "Shortest path from city %s to city %s   =  %s",
                        this.start,
                        this.end,
                        this.vertexHashMap.get(this.endVertexId).weightFromStart
                )
        );
    }

    private void statShortedPathForAllVertices() {
        forEachRawVertex(
                vertexId -> {
                    if (vertexId == this.startVertexId) {
                        return;
                    }

                    ShortestPathHopReport shortestPathHopReport = new ShortestPathHopReport(
                            this.vertexHashMap.get(vertexId)
                    );

                    this.applyPathHop(shortestPathHopReport);

                    System.out.println(
                            String.format(
                                    "The best way to go from %s to city %s is follows",
                                    this.start,
                                    shortestPathHopReport.vertex.vertexName
                            )
                    );

                    System.out.println(
                            String.format(
                                    "%s  Cost = %s = %s",
                                    String.join(
                                            " -> ",
                                            shortestPathHopReport.hopVertexNames

                                    ),
                                    String.join(" + ", shortestPathHopReport.hopVertexWeight),
                                    shortestPathHopReport.vertex.weightFromStart
                            )
                    );
                }
        );
    }

    private void applyPathHop(ShortestPathHopReport shortestPathHopReport) {

        if (shortestPathHopReport.hopVertexNames.size() > 0) {
            return;
        }

        Vertex vertex = shortestPathHopReport.vertex;
        HashMap<Integer, Boolean> vertexTrack = new HashMap<>();

        while (!(vertexTrack.containsKey(vertex.vertexId))) {
            shortestPathHopReport.hopVertexNames.add(vertex.vertexName);
            shortestPathHopReport.pathVertices.add(vertex);

            if (vertex.vertexId != this.startVertexId) {
                shortestPathHopReport.hopVertexWeight.add(
                        String.format("%.1f", vertex.viaVertexWeight)
                );
            }

            vertexTrack.put(vertex.vertexId, true);

            vertex = vertex.viaVertex;

        }

        Collections.reverse(shortestPathHopReport.pathVertices);
        Collections.reverse(shortestPathHopReport.hopVertexNames);
        Collections.reverse(shortestPathHopReport.hopVertexWeight);
    }

    private void statInProgress() {

        // row 1: vertex name
        System.out.println("");

        forEachRawVertex(
                vertexId -> {
                    this.printFormatIntoColumn(
                            vertexHashMap.get(vertexId).vertexName
                    );
                }
        );

        System.out.print("\n");

        // row 2: is visited
        forEachRawVertex(
                vertexId -> {
                    this.printFormatIntoColumn(
                            vertexHashMap.get(vertexId).visitState == -1 ? "F" : "T"
                    );

                }
        );

        System.out.print("\n");

        // row 3: distance

        forEachRawVertex(
                vertexId -> {
                    double weightFromStart = vertexHashMap.get(vertexId).weightFromStart;
                    this.printFormatIntoColumn(weightFromStart == Double.MAX_VALUE ? "L" : weightFromStart);

                }
        );

        System.out.print("\n");

        // row 4: via vertex name
        forEachRawVertex(
                vertexId -> {
                    Vertex viaVertex = vertexHashMap.get(vertexId).viaVertex;
                    String viaVertexName = viaVertex != null ? viaVertex.vertexName : "null";
                    this.printFormatIntoColumn(viaVertexName);
                }
        );

        System.out.println("");
        System.out.println("");

    }

    private void printFormatIntoColumn(Object... obj) {
        System.out.print(String.format("%-7.7s  ", obj));
    }

    private Vertex getUnVisitedMinWeightVertex() {
        Vertex inVertex = this.minHeap.poll();
        if (inVertex != null && inVertex.visitState == -1) {
            return inVertex;
        }
        return null;
    }

    private void forEachRawVertex(IntConsumer consumer) {
        IntStream.range(0, this.g.getnumV()).forEach(consumer);
    }

    private void forEachFanoutOfVertex(Vertex vertex, Consumer<Edge> consumer) {
        int fanoutCount = this.g.numFanout(vertex.vertexId);
        IntStream.range(0, fanoutCount)
                .mapToObj(
                        fangoutIndex -> new Edge(
                                vertex,
                                getVertexById(
                                        this.g.getNodeFanout(
                                                vertex.vertexId,
                                                fangoutIndex
                                        )
                                ),
                                this.g.getNodeFanoutEdgeWeight(
                                        vertex.vertexId,
                                        fangoutIndex
                                )
                        )
                )
                .forEach(consumer);
    }

    private Vertex getVertexById(int vertexId) {
        return this.vertexHashMap.get(vertexId);
    }


    private void generateDotFile() {

        ShortestPathHopReport shortestPathHopReportToEndVertex = new ShortestPathHopReport(
                this.vertexHashMap.get(this.endVertexId)
        );
        this.applyPathHop(shortestPathHopReportToEndVertex);

        GraphTest.GraphType graphType = g.getType();
        boolean isDirected = graphType == GraphTest.GraphType.DIRECTED || graphType == GraphTest.GraphType.WEIGHTED_DIRECTED;

        String dotFileFullPath = outputFileBase + this.t + ".dijkstra.dot";
        u.openDotFile(dotFileFullPath);

        HashMap hmReduceUndirectedDuplication = new HashMap<String, Boolean>();

        String directSymbol = "->";

        forEachRawVertex(
                vertexId -> {


                    forEachFanoutOfVertex(vertexHashMap.get(vertexId), (edge) -> {
//                        int fo = g.getNodeFanout(i, j);
//                        String nf = g.getRealName(fo);

                        String fromVertexName = edge.fromVertex.vertexName;
                        String toVertexName = edge.toVertex.vertexName;

                        String edgeExpr = fromVertexName + directSymbol + toVertexName;
                        String edgeExprReverse = toVertexName + directSymbol + fromVertexName;

                        if (!isDirected) {
                            if (
                                    hmReduceUndirectedDuplication.containsKey(edgeExpr)
                                            && hmReduceUndirectedDuplication.get(edgeExpr).equals(true)
                            ) {
                                return;
                            }

                            hmReduceUndirectedDuplication.put(edgeExprReverse, true);
                        }

                        DecimalFormat df = new DecimalFormat("#.#");

                        boolean isEdgeInThePath = shortestPathHopReportToEndVertex.isEdgeInThePath(edge);

                        String weightOnPathAttr = isEdgeInThePath
                                ? String.format(
                                        " <U><FONT color=\"red\" POINT-SIZE=\"15\">%s</FONT></U>",
                                edge.toVertex.weightFromStart
                                )
                                : "";

                        String weightLabel = graphType == GraphTest.GraphType.WEIGHTED_UNDIRECTED
                                || graphType == GraphTest.GraphType.WEIGHTED_DIRECTED
                                ? String.format(
                                "label=<\n<B>%s</B>%s\n>, ",
                                df.format(edge.weight),
                                weightOnPathAttr
                        )
                                : "";

                        String edgeColor = isEdgeInThePath
                                ? "red"
                                : "black";


                        String extraLabel = String.format(
                                "[%s dir=\"%s\", color=\"%s\"]",
                                weightLabel,
                                isDirected ? "forward" : "none",
                                edgeColor
                        );

                        u.appendDotFile(
                                dotFileFullPath,
                                fromVertexName + " " + directSymbol + " " + toVertexName + extraLabel + ";"
                        );
                    });
                }
        );
//						++numedge;


        u.closeDotFile(dotFileFullPath);

        System.out.println("Generated dot file with shorted path annotation in " + dotFileFullPath);
    }


}