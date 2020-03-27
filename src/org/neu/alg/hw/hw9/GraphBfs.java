package org.neu.alg.hw.hw9;


import org.neu.alg.hw.IntUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * File Name: GraphBfs.java
 * Breadth First Search on a graph
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphBfs.java
 */

class GraphBfs {
    private String t;
    private Graph g;
    String start;
    int startVertexId;
    //Output
    private int[] work;
    private int[] size;
    private int[] bfsorder;
    private int[] bfspath;
    //You can have any number of private variables and private funcions

    private boolean isPrintState;
    public static final IntUtil u = new IntUtil();

    // 0 - unvisited
    // 1 - marked and pushed in stack
    // 2 - visited
    private int[] visited;
    // [[LEVEL, FROM]]
    // - LEVEL: Current iteration level served as the distance from start vertex
    // - FROM: Last vertexId before entering this vertex
    // [[ -1, -1], [ -1, -1]...] is the default value
    private int[][] bfsNodeDescriptor;

    Queue<Integer> bfsQueue = new LinkedList();

    int lastBfsOrderIndexUpdated = 0;
    int lastBfsPathIndexUpdated = 0;

    int currentIterationLevel = 0;

    GraphBfs(String t, Graph g, String start, int[] work, int[] size, int[] bfsorder, int[] bfspath) {
        new GraphBfs(t, g, start, work, size, bfsorder, bfspath, true);
    }

    GraphBfs(String t, Graph g, String start, int[] work, int[] size, int[] bfsorder, int[] bfspath, boolean isPrintState) {
        this.t = t;
        this.g = g;
        this.start = start;
        this.work = work;
        this.size = size;
        this.bfsorder = bfsorder;
        this.bfspath = bfspath;
        this.isPrintState = isPrintState;

        //WRITE YOUR CODE

        this.visited = new int[this.bfsorder.length];
        this.startBfs(start);

        this.checkUnconnectedGraph();

    }

    private void startBfs(String start) {
        this.startBfs(this.getVertexIdByName(start));
    }

    private void startBfs(int startVertexId) {
        this.init(startVertexId);
        while (!this.bfsQueue.isEmpty()) {
            this.bfs();
        }
        if (this.isPrintState) {
            this.stat();
        }
    }

    private void init(int startVertexId) {

        this.bfsNodeDescriptor = new int[this.bfsorder.length][];

        for (int i = 0; i < this.bfsNodeDescriptor.length; i++) {
            this.bfsNodeDescriptor[i] = new int[]{-1, -1};
        }

        this.startVertexId = startVertexId;
        this.bfsQueue.add(this.startVertexId);

        this.bfsNodeDescriptor[this.startVertexId] = new int[]{0, this.startVertexId};

        this.currentIterationLevel = 1;

        this.bfsorder[0] = this.startVertexId;
        this.bfspath[0] = this.startVertexId;

        this.visited[this.startVertexId] = 1;

    }

    private int getVertexIdByName(String vertexName) {
        return this.g.insertOrFind(vertexName, true);
    }

    private void bfs() {

        int queueHeadVertexId = this.bfsQueue.poll();

        this.work[0]++;

        this.bfsorder[this.lastBfsOrderIndexUpdated++] = queueHeadVertexId;
        this.bfspath[this.lastBfsPathIndexUpdated++] = this.bfsNodeDescriptor[queueHeadVertexId][1];

        this.forEachFanoutOfVertexId(queueHeadVertexId, (fanoutEndVertexId, fanoutVertexIndex) -> {

            this.work[0]++;

            if (this.visited[fanoutEndVertexId] != 0) {
                return;
            }

            this.visited[fanoutEndVertexId] = 1;

            this.bfsQueue.add(fanoutEndVertexId);
            this.bfsNodeDescriptor[fanoutEndVertexId][0] = this.currentIterationLevel;
            this.bfsNodeDescriptor[fanoutEndVertexId][1] = queueHeadVertexId;
        });

        this.visited[queueHeadVertexId] = 2;

        this.currentIterationLevel++;
    }

    private void checkUnconnectedGraph() {
        for (int vertexId = 0; vertexId < this.visited.length; vertexId++) {
            if (this.visited[vertexId] != 2) {
                this.startBfs(vertexId);
            }
        }
    }

    private void forEachFanoutOfVertexId(int vertexId, LambdaRun lambdaRun) {
        int fanoutCount = this.g.numFanout(vertexId);

        for (int fanoutVertexIndex = 0; fanoutVertexIndex < fanoutCount; fanoutVertexIndex++) {
            int fanoutEndVertexId = this.g.getNodeFanout(vertexId, fanoutVertexIndex);
            lambdaRun.invoke(fanoutEndVertexId, fanoutVertexIndex);
        }
    }

    public void printShortestPath(int fromVertexId, int toVertexId) {
        StringBuffer resultCollector = new StringBuffer();
        pathHop(resultCollector, toVertexId, fromVertexId);
        System.out.println(resultCollector.toString());
    }

    private void stat() {
        System.out.println("");
        System.out.println(String.format("File %s", this.t));
        System.out.println(String.format("Graph Type    = %s", this.g.getGraphType()));
        System.out.println(String.format("Num Vertices  = %d", this.g.getnumV()));
        System.out.println(String.format("Num Edges     = %d",this.getEdgeCountDisplay() ));
        System.out.println(String.format("Work done     = %d", this.work[0]));
//        System.out.println(String.format("Has Cycle     = %s", this.cycle[0] ? "YES": "NO"));
        System.out.println(String.format("BFS order     = %s", this.getVertexNames(this.bfsorder)));
        System.out.println(String.format("BFS path      = %s", this.getVertexNames(this.bfspath)));
        this.pathStat();
        System.out.println("------");
    }

    private int getEdgeCountDisplay() {
        return this.g.isUndirectedGraph() ? this.g.getnumE() / 2 : this.g.getnumE();
    }

    private String getVertexNames(int[] vertexIds) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < vertexIds.length; i++) {
            int vertexId = vertexIds[i];
            if (i > 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(this.g.getRealName(vertexId));
        }

        return stringBuilder.toString();
    }

    private void pathStat() {
        for (int i = 0; i < this.bfsorder.length; i++) {
            int vertexId = this.bfsorder[i];
            StringBuffer resultCollector = new StringBuffer();
            pathHop(resultCollector, vertexId, this.startVertexId);
            System.out.println(resultCollector.toString());
        }
    }

    private void pathHop(StringBuffer stringBuffer, int vertexId, int startVertexId) {

        int level = this.bfsNodeDescriptor[vertexId][0];
        int fromVertexId = this.bfsNodeDescriptor[vertexId][1];

        if (fromVertexId != vertexId) {
            if (startVertexId != vertexId) {
                pathHop(stringBuffer, fromVertexId, startVertexId);
            }
        } else if (level != 0) {
            System.out.println("[ERROR] self-loop detected");
            u.myassert(false);
        }

        if (vertexId != startVertexId) {
            stringBuffer.append("->");
        }

        stringBuffer.append(this.g.getRealName(vertexId));

    }


    public static void main(String[] args) {
        System.out.println("GraphBfs.java starts");
        System.out.println("Use GraphTest.java to test");
        System.out.println("GraphBfs.java Ends");
    }
}

interface LambdaRun {
    public void invoke(int ele, int idx);
}
