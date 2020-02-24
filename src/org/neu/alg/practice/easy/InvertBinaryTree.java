package org.neu.alg.practice.easy;

import org.neu.alg.practice.lib.utils.*;
import org.neu.alg.practice.lib.data_types.*;

public class InvertBinaryTree {
  private void invert(BinaryTree binaryTree) {

  }

  public static void main(String... args) {
    InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

    BinaryTree binaryTree = BinaryTreeUtil.generateIntBinaryTree(3, 100);
    BinaryTreeUtil.display(binaryTree);

    invertBinaryTree.invert(binaryTree);

  }
}
