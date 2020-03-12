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

class GraphDfs{
	//You can have any number of private variables and private functions
	private String t ;
	private Graph g ;
	String start;
	//Output
	boolean [] cycle;
	int [] work ;
	int [] size ;
	int [] dfsorder;

	
	GraphDfs(String t, Graph g, String start, boolean [] cycle, int [] work, int [] size, int [] dfsorder) {
		this.t = t ;
		this.g = g ;
		this.start = start;
		this.cycle = cycle ;
		this.work = work ;
		this.size = size ;
		this.dfsorder = dfsorder ;

		//WRITE YOUR CODE
	}

	public static void main(String[] args) {
		System.out.println("GraphDfs.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphDfs.java Ends");
	}
}