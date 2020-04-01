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
        double weight;
        int vertexId;
        int visitState;
        Vertex viaVertex;

        public Vertex(double weight, int vertexId, int visitState, Vertex viaVertex) {
            this.weight = weight;
            this.vertexId = vertexId;
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

        if (this.g.isUndirectedGraph()) {
        	return;
		}

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
        this.minHeap = new PriorityQueue<Vertex>(Comparator.comparingDouble(item -> item.weight));
        this.vertexHashMap = new HashMap<>();
    }

    private void initDataStructure() {
        this.initMinHeap();
        forEachRawVertex(
                vertexId -> {
                    Vertex vertex = new Vertex(
                            Integer.MAX_VALUE,
                            vertexId,
                            -1,
                            null
                    );
                    this.minHeap.add(
                            vertex
                    );
                    vertexHashMap.put(vertexId, vertex);
                }
        );
        // PriorityQueue as MIN HEAP: init all vertexes with the distance from the source as weight Integer.MAX
        // int the via vertex with null
        // init all vertexes as unvisited state -1
    }

    private void dijkstraSearch() {
        Vertex unVisitedMinWeightVertex;
        while ((unVisitedMinWeightVertex = this.getUnVisitedMinWeightVertex()) != null) {
            forEachFanoutOfVertex(unVisitedMinWeightVertex, (edge) -> {

                double weight = edge.weight;

            });
        }


    }

    private Vertex getUnVisitedMinWeightVertex() {
        return this.minHeap.poll();
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