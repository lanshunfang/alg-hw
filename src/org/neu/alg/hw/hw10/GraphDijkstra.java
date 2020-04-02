package org.neu.alg.hw.hw10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
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
    private String t; //Title
    private String dotFile; //Write tree as a dot file
    private Graph g;
    String start;
    String end;
    int[] work;
    double[] cost;

    class Vertex {
        double weightFromStart;
        int vertexId;
        String vertexName;
        int visitState;
        Vertex viaVertex;

        public Vertex(double weightFromStart, int vertexId, String vertexName, int visitState, Vertex viaVertex) {
            this.weightFromStart = weightFromStart;
            this.vertexId = vertexId;
            this.vertexName = vertexName;
            this.visitState = visitState;
            this.viaVertex = viaVertex;
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

    private PriorityQueue<Vertex> minHeap;
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

        this.dijkstraSearch();
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
                            this.startVertexId != vertexId ? Integer.MAX_VALUE : 0,
                            vertexId,
                            this.g.getRealName(vertexId),
                            -1,
                            null
                    );
                    vertexHashMap.put(vertexId, vertex);

                    this.minHeap.add(
                            vertex
                    );
                }
        );

        // PriorityQueue as MIN HEAP: init all vertexes with the distance from the source as weight Integer.MAX
        // set start vertex with the distance from the source as weight 0
        // int the via vertex with null
        // init all vertexes as unvisited state -1
    }

    private void dijkstraSearch() {
        Vertex unVisitedMinWeightVertex;
        while ((unVisitedMinWeightVertex = this.getUnVisitedMinWeightVertex()) != null) {
            forEachFanoutOfVertex(unVisitedMinWeightVertex, (edge) -> {

                this.work[0]++;

                Vertex fromVertex = edge.fromVertex;
                Vertex toVertex = edge.toVertex;

                double newWeight = edge.weight + fromVertex.weightFromStart;

                if ( newWeight < toVertex.weightFromStart) {
                    toVertex.weightFromStart = newWeight;
                    toVertex.viaVertex = fromVertex;

                    this.minHeap.remove(toVertex);
                    this.minHeap.add(toVertex);

                    if (toVertex.vertexId == this.endVertexId) {
                        this.cost[0] = toVertex.weightFromStart;
                    }
                }


            });

            unVisitedMinWeightVertex.visitState = 1;
        }


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


}