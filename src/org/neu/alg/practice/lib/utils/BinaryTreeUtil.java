package org.neu.alg.practice.lib.utils;

import org.neu.alg.practice.lib.data_types.BinaryTree;
import org.neu.alg.practice.lib.data_types.BinaryTreeNode;
import org.neu.alg.practice.lib.data_types.BinaryTreeNode;

import java.util.Random;

public class BinaryTreeUtil<T, K> {

  static Random random = new Random();

  public static BinaryTree generateIntBinaryTree(int depth, int maxInt) {

    BinaryTreeNode rootTreeNode = new BinaryTreeNode(random.nextInt(maxInt), null, null);

    BinaryTreeUtil binaryTreeUtil = new BinaryTreeUtil();

    binaryTreeUtil.addBinaryTreeNode(rootTreeNode, maxInt, depth - 1);

    return new BinaryTree(rootTreeNode, depth);

  }

  public static void display(BinaryTree binaryTree) {
    System.out.println(traversePreOrder(binaryTree.root));
  }

  private static String traversePreOrder(BinaryTreeNode root) {

    if (root == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(root.value);

    String pointerRight = "└──";
    String pointerLeft = (root.rightTreeNode != null) ? "├──" : "└──";

    traverseNodes(sb, "", pointerLeft, root.leftTreeNode, root.rightTreeNode != null);
    traverseNodes(sb, "", pointerRight, root.rightTreeNode, false);

    return sb.toString();
  }

  private static void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTreeNode node,
                                    boolean hasRightSibling) {
    if (node != null) {
      sb.append("\n");
      sb.append(padding);
      sb.append(pointer);
      sb.append(node.value);

      StringBuilder paddingBuilder = new StringBuilder(padding);
      if (hasRightSibling) {
        paddingBuilder.append("│  ");
      } else {
        paddingBuilder.append("   ");
      }

      String paddingForBoth = paddingBuilder.toString();
      String pointerRight = "└──";
      String pointerLeft = (node.rightTreeNode != null) ? "├──" : "└──";

      traverseNodes(sb, paddingForBoth, pointerLeft, node.leftTreeNode, node.rightTreeNode != null);
      traverseNodes(sb, paddingForBoth, pointerRight, node.rightTreeNode, false);
    }
  }

  private void addBinaryTreeNode(BinaryTreeNode parentBinaryTreeNode, int maxInt, int depth) {

    if (depth <= 0) {
      return;
    }

    depth--;

    parentBinaryTreeNode.leftTreeNode = new BinaryTreeNode(random.nextInt(maxInt), null, null);
    parentBinaryTreeNode.rightTreeNode = new BinaryTreeNode(random.nextInt(maxInt), null, null);

    this.addBinaryTreeNode(parentBinaryTreeNode.leftTreeNode, maxInt, depth);
    this.addBinaryTreeNode(parentBinaryTreeNode.rightTreeNode, maxInt, depth);

  }


}
