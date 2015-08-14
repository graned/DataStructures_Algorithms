/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.helper;

import datastructures.SingleLinkedList;
import datastructures.StandardQueue;
import datastructures.nodes.TreeNode;

/**
 *
 * @author j.anaya.villagrana
 * @param <T>
 */
public abstract class Tree<T extends Comparable> {

    /**
     * Helper method that inserts values to the corresponding left or right
     * nodes. If the value that we need to enter is less than the root or
     * parent, the node will be inserted on the right, else the node will be
     * inserted on the right.
     *
     * @param current root or parent node
     * @param node node to be inserted on the tree under the root or parent node
     */
    protected void insertNode(TreeNode<T> current, TreeNode<T> node) {
        //the value of the current node is less than the value of the node to be inserted, this is implemented through the
        //comparable class.
        if (node.getValue().compareTo(current.getValue()) == -1) {
            if (current.getLeftNode() == null) {
                current.setLeftNode(node);
            } else {
                insertNode(current.getLeftNode(), node);
            }
        } else {
            if (current.getRightNode() == null) {
                current.setRightNode(node);
            } else {
                insertNode(current.getRightNode(), node);
            }
        }
    }

    /**
     * Helper method to find the parent node of a given value
     *
     * @param root
     * @param value
     * @return
     */
    protected TreeNode<T> findParent(TreeNode<T> root, T value) {
        if (root == null) {
            return null;
        }
        if (value.compareTo(root.getValue()) == - 1) {
            if (root.getLeftNode() == null) {
                return null;
            } else if (root.getLeftNode().getValue().compareTo(value) == 0) {
                return root;
            } else {
                return findParent(root.getLeftNode(), value);
            }
        } else {
            if (root.getRightNode() == null) {
                return null;
            } else if (root.getRightNode().getValue().compareTo(value) == 0) {
                return root;
            } else {
                return findParent(root.getRightNode(), value);
            }
        }
    }

    /**
     * helper method that will help to find a reference node based on a given
     * value
     *
     * @param root
     * @param value
     * @return
     */
    protected TreeNode<T> findNode(TreeNode<T> root, T value) {
        if (root == null) {
            return null;
        }
        if (root.getValue().compareTo(value) == 0) {
            return root;
        } else if (value.compareTo(root.getValue()) == -1) {
            return findNode(root.getLeftNode(), value);
        } else {
            return findNode(root.getRightNode(), value);
        }
    }

    /**
     * When searching in the tree we go either left or right, depending on the
     * value of of the value that we are searching<br>
     * we will go left if the value x that we are searching is less than the
     * root, otherwise, we go to the right.<br><br>
     * When searching the rules are made more atomic and we should consider the
     * following 4 cases:<br>
     * 1.) The root is null<br>
     * 2.) The root's value is equals to the value we are searching<br>
     * 3.) The value we are searching is less than the value of the root, which
     * means we will inspect the left side of the tree<br>
     * 4.) The value we are searching is greater than the value of the root,
     * which means we will inspect the right side of the tree<br>
     *
     * @param searchValue Value to be searched in the tree
     * @return TRUE - if search value is found<br>FALSE - if search value is not
     * found
     */
    protected boolean containsNode(TreeNode<T> root, T searchValue) {
        //CASE 1
        if (root == null) {
            return false;
        }
        //CASE 2
        if (root.getValue() == searchValue) {
            return true;
        } else if (searchValue.compareTo(root.getValue()) == -1) {
            //CASE 3
            return containsNode(root.getLeftNode(), searchValue);
        } else {
            //CASE 4
            return containsNode(root.getRightNode(), searchValue);
        }
    }

    /**
     * Helper method that returns the minimum value in the BST
     *
     * @param root
     * @return
     */
    protected T findMinNode(TreeNode<T> root) {
        T minValue;
        if (root.getLeftNode() == null) {
            minValue = root.getValue();
        } else {
            minValue = findMinNode(root.getLeftNode());
        }
        return minValue;
    }

    /**
     * Helper method that returns the maximum value in the BST
     *
     * @param root
     * @return
     */
    protected T findMaxNode(TreeNode<T> root) {
        T minValue;
        if (root.getRightNode() == null) {
            minValue = root.getValue();
        } else {
            minValue = findMinNode(root.getRightNode());
        }
        return minValue;
    }

    /**
     * This method will display the elements of a Tree in Preorder
     *
     * @param root root of the tree
     * @param sb stringbuilder object that constructs the output based on the
     * elements of the tree
     * @return string representation of the tree in preorder
     */
    protected String preOrderTraversal(TreeNode<T> root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.getValue());
            sb.append(" ");
            preOrderTraversal(root.getLeftNode(), sb);
            preOrderTraversal(root.getRightNode(), sb);
        }
        return sb.toString();
    }

    /**
     * This method will display the elements of a Tree in Postorder
     *
     * @param root root of the tree
     * @param sb stringbuilder object that constructs the output based on the
     * elements of the tree
     * @return string representation of the tree in postorder
     */
    protected String postOrderTraversal(TreeNode<T> root, StringBuilder sb) {
        if (root != null) {
            postOrderTraversal(root.getLeftNode(), sb);
            postOrderTraversal(root.getRightNode(), sb);
            sb.append(root.getValue());
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * This method will display the elements of a Tree in inOrderTraversal
     *
     * @param root root of the tree
     * @param sb stringbuilder object that constructs the output based on the
     * elements of the tree
     * @return string representation of the tree in inOrderTraversal
     */
    protected String inOrderTraversal(TreeNode<T> root, StringBuilder sb) {
        if (root != null) {
            inOrderTraversal(root.getLeftNode(), sb);
            sb.append(root.getValue());
            sb.append(" ");
            inOrderTraversal(root.getRightNode(), sb);
        }
        return sb.toString();
    }

    /**
     * This method will display the elements of a Tree in breadthFirst
     *
     * @param root root of the tree
     * @param sb stringbuilder object that constructs the output based on the
     * elements of the tree
     * @return string representation of the tree in breadthFirst
     */
    protected String breadthFirst(TreeNode<T> root, StringBuilder sb) {
        StandardQueue<TreeNode<T>> queue = new StandardQueue<>();
        //SingleLinkedList<TreeNode<T>> queue = new SingleLinkedList<>();
        try {
            while (root != null) {
                sb.append(root.getValue());
                sb.append(" ");
                if (root.getLeftNode() != null) {
                    queue.enqueue(root.getLeftNode());
                    //queue.add(root.getLeftNode());
                }
                if (root.getRightNode() != null) {
                    queue.enqueue(root.getRightNode());
                    //queue.add(root.getRightNode());
                }
                if (queue.size() > 0) {
                    root = queue.dequeue();
                } else {
                    root = null;
                }
            }
        } catch (Exception e) {
            if(e.getMessage().equals("Element canot be null")){
                System.out.println("node element is null");
            }
        }
        return sb.toString();
    }
}
