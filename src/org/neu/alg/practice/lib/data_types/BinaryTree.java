package org.neu.alg.practice.lib.data_types;

public class BinaryTree {
  public final BinaryTreeNode root;
  public final int depth;

  public BinaryTree(BinaryTreeNode rootNode, int depth) {
    this.root = rootNode;
    this.depth = depth;
  }
}
