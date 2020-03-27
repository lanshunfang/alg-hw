package org.neu.alg.hw.hw8;

import java.util.ArrayList;

/**
 * File Name: GraphSnakeandLadderBuilder.java
 * All routines that builds SnakeandLadder Graph
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphSnakeandLadderBuilder.java
 */

class GraphSnakeandLadderBuilder {
    //given data
    private Graph g;
    private int n; //Max number on board
    private int[][] l; //ladder
    private int[][] s; //snakes
    //You can have any number of private variables

    private ArrayList<String[]> graphdata;

    GraphSnakeandLadderBuilder(Graph g, int n, int[][] l, int[][] s) {
        this.g = g;
        this.n = n;
        this.l = l;
        this.s = s;
        buildGraph();
    }

    private void buildGraph() {
        //WRITE YOUR CODE

        this.initGraphData();
        this.applySnakeAndLadder(this.l);
        this.applySnakeAndLadder(this.s);

        this.generateGraphFromString();

        int[] bfsorder = new int[g.getnumV()];
        int[] bfspath = new int[g.getnumV()];
        int[] work = {0};
        int[] size = {0};


        GraphBfs b = new GraphBfs("SnakeAndLadder Graphdata", this.g, "1", work, size, bfsorder, bfspath, false);

        System.out.println("");

        System.out.println("Snake and ladder, the shortest path");
        b.printShortestPath(0, bfsorder.length - 1);
        System.out.println("^^^^^^^^");

    }

    private void initGraphData() {
        this.graphdata = new ArrayList<>();
        // this is a directed graph
        // for each vertex, it always connects to the next 6 vertexes ascent (via dice)

        for (int tmpVertexId = 1; tmpVertexId <= this.n; tmpVertexId++) {
            int diceCount = 6;
            int offset = 1;
            while (offset <= diceCount ) {
                int tmpEndVertexId = tmpVertexId + offset++;
                this.graphdata.add(
                        new String[]{tmpVertexId + "", tmpEndVertexId + ""}
                );
                int tmpEndVertexIdFromSnake = getLinkedEndVertexIdFromSnakeAndLadder(tmpEndVertexId, this.s);
                int tmpEndVertexIdFromLadder = getLinkedEndVertexIdFromSnakeAndLadder(tmpEndVertexId, this.l);
                if (tmpEndVertexIdFromSnake != -1) {
                    this.graphdata.add(
                            new String[]{tmpVertexId + "", tmpEndVertexIdFromSnake + ""}
                    );
                }
                if (tmpEndVertexIdFromLadder != -1) {
                    this.graphdata.add(
                            new String[]{tmpVertexId + "", tmpEndVertexIdFromLadder + ""}
                    );
                }
            }
        }
    }

    private int getLinkedEndVertexIdFromSnakeAndLadder(int tmpFromVertexId, int[][] pairs) {
        int tmpEndVertexId = -1;
        for (int[] pair : pairs) {
            if (pair[0] == tmpFromVertexId) {
                tmpEndVertexId = pair[1];
            }
        }
        return tmpEndVertexId;
    }

    private void applySnakeAndLadder(int[][] pairs) {
        for (int[] pair : pairs) {
            this.graphdata.add(
                    new String[]{pair[0] + "", pair[1] + ""}
            );
        }
    }

    /**
     * Follow same logic as GraphBuilder#generateGraphFromFile
     *
     */
    private void generateGraphFromString() {

        for (String[] tokens : graphdata) {

//            String[] tokens = line.split("\\s+");

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

    }

    public static void main(String[] args) {
        System.out.println("GraphSnakeandLadderBuilder starts");
        System.out.println("Use GraphTester.java to test");
        System.out.println("GraphSnakeandLadderBuilder Ends");
    }
}