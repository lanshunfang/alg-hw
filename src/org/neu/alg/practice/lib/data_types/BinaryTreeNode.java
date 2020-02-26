package org.neu.alg.practice.lib.data_types;

public class BinaryTreeNode<T> extends Node {
  public BinaryTreeNode leftTreeNode;
  public BinaryTreeNode rightTreeNode;

  public BinaryTreeNode(T value, BinaryTreeNode leftTreeNode, BinaryTreeNode rightTreeNode) {
    super(value);
    this.setLeftNode(leftTreeNode);
    this.setRightNode(rightTreeNode);
  }

  public void setLeftNode(BinaryTreeNode leftTreeNode) {
    this.leftTreeNode = leftTreeNode;
  }

  public void setRightNode(BinaryTreeNode rightTreeNode) {
    this.rightTreeNode = rightTreeNode;
  }
}
