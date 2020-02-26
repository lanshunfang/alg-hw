package org.neu.alg.practice.easy;

import org.neu.alg.practice.lib.utils.*;
import org.neu.alg.practice.lib.data_types.*;

public class InvertBinaryTree {
  private void invert(BinaryTree binaryTree) {

    BinaryTreeNode rootNode = binaryTree.root;
    this.invertRecursive(rootNode);


  }

  private void invertRecursive(BinaryTreeNode node) {

    if (node == null) {
      return;
    }

    BinaryTreeNode leftNode = node.leftTreeNode;
    node.leftTreeNode = node.rightTreeNode;
    node.rightTreeNode = leftNode;

    if (node.leftTreeNode != null) {
      this.invertRecursive(node.leftTreeNode);
    }
    if (node.rightTreeNode != null) {
      this.invertRecursive(node.rightTreeNode);
    }

  }

  public static void main(String... args) {
    InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

    BinaryTree binaryTree = BinaryTreeUtil.generateIntBinaryTree(3, 100);

    System.out.println("[INFO] Before invert");
    BinaryTreeUtil.display(binaryTree);

    invertBinaryTree.invert(binaryTree);

    System.out.println("[INFO] After invert");
    BinaryTreeUtil.display(binaryTree);


  }
}
