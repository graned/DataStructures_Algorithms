/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import datastructures.helper.Tree;
import datastructures.interfaces.TreeInterface;
import datastructures.nodes.TreeNode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author j.anaya.villagrana
 * @param <T>
 */
public class BinaryTree<T extends Comparable> extends Tree<T> implements TreeInterface<T> {

    private TreeNode<T> root;
    private int size;

    /**
     * Method that inserts a new element into the tree.
     *
     * @param element
     */
    @Override
    public void insert(T element) {
        TreeNode<T> node = new TreeNode<>();
        node.setValue(element);
        if (root == null) {
            root = node;
            size++;
        } else {
            insertNode(root, node);
            size++;
        }
    }

    /**
     * This method will remove a node from the BST, with four cases to
     * consider:<br>
     * 1. the value to remove is a leaf node; <br>
     * 2. the value to remove has a right subtree, but no left subtree; <br>
     * 3. the value to remove has a left subtree, but no right subtree; <br>
     * 4. the value to remove has both a left and right subtree in which case we
     * promote the largest value in the left subtree.<br>
     * <br>
     * There is also an implicit fifth case whereby the node to be removed is
     * the<br>
     * only node in the tree. This case is already covered by the first, but
     * should be<br>
     * noted as a possibility nonetheless.
     *
     * @param element Element that will be removed from the BST
     * @return TRUE - if the element was removed successfully<br>FALSE - if the
     * element was not removed.
     */
    @Override
    public boolean remove(T element) {
        try {
            TreeNode<T> nodeToRemove = findNode(element);
            if (nodeToRemove == null) {
                return false; //VALUE IS NOT IN THE BST
            }
            TreeNode<T> parent = findParent(element);
            if (size == 1) {
                root = null; //REMOVING THE ONLY NODE IN THE TREE
                size--;
            } else if (nodeToRemove.getLeftNode() == null && nodeToRemove.getRightNode() == null) {
                //CASE 1
                if (nodeToRemove.getValue().compareTo(parent.getValue()) == -1) {
                    parent.setLeftNode(null);
                } else {
                    parent.setRightNode(null);
                }
            } else if (nodeToRemove.getLeftNode() == null && nodeToRemove.getRightNode() != null) {
                //CASE 2
                if (nodeToRemove.getValue().compareTo(parent.getValue()) == -1) {
                    parent.setLeftNode(null);
                    insertNode(parent, nodeToRemove.getRightNode());
                } else {
                    parent.setRightNode(null);
                    insertNode(parent, nodeToRemove.getRightNode());
                }
            } else if (nodeToRemove.getLeftNode() != null && nodeToRemove.getRightNode() == null) {
                //CASE 3
                if (nodeToRemove.getValue().compareTo(parent.getValue()) == -1) {
                    parent.setLeftNode(null);
                    insertNode(parent, nodeToRemove.getLeftNode());
                } else {
                    parent.setRightNode(null);
                    insertNode(parent, nodeToRemove.getLeftNode());
                }
            } else {
                //CASE 4
                TreeNode<T> largestNode = nodeToRemove.getLeftNode();
                while (largestNode.getRightNode() != null) {
                    largestNode = largestNode.getRightNode();
                }
                findParent(largestNode.getValue()).setRightNode(null);
                nodeToRemove.setValue(largestNode.getValue());
            }
            size--;
            return true;
        } catch (Exception ex) {
            //NODE NOT FOUND
            System.out.println("NODE NOT FOUND");
            Logger.getLogger(BinaryTree.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }

    /**
     * The purpose of this method is to return the node parent of a given value.
     *
     * @param value
     * @return
     * @throws Exception
     */
    @Override
    public TreeNode<T> findParent(T value) throws Exception {
        return findParent(root, value);
    }

    /**
     * This method will find the reference node of a given value, if not matches
     * are found will return null.
     *
     * @param value
     * @return
     * @throws Exception when there are no matches found
     */
    @Override
    public TreeNode<T> findNode(T value) throws Exception {
        return findNode(root, value);
    }

    @Override
    public T[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * override method that will invoke the contains(TreeNode<T> root, T search)
     * local method. This method will go in<br>
     * the tree searching the value provided, if it finds a match it will return
     * TRUE, else FALSE.
     *
     * @param searchValue Value to be searched in the tree
     * @return TRUE - if search value is found<br>FALSE - if search value is not
     * found
     */
    @Override
    public boolean contains(T searchValue) {
        return containsNode(root, searchValue);
    }

    /**
     * This method returns the minimum value in the tree
     *
     * @return the minimum value in the tree
     */
    public T findMin() {
        return findMinNode(root);
    }

    /**
     * This method returns the maximum value in the tree
     *
     * @return the maximum value in the tree
     */
    public T findMax() {
        return findMaxNode(root);
    }

    /**
     * This method will show the elements in a preorder form
     * @return preorder string representation of the tree
     */
    public String preOrder(){
        return preOrderTraversal(root, new StringBuilder());
    }
    
    /**
     * This method will show the elements in a postOrder form
     * @return postOrder string representation of the tree
     */
    public String postOrder(){
        return postOrderTraversal(root, new StringBuilder());
    }
    
    /**
     * This method will show the elements in a breadthFirst form
     * @return breadthFirst string representation of the tree
     */
    public String breadthFirst(){
        return breadthFirst(root, new StringBuilder());
    }
    /**
     * This method will show the elements in a inOrder form
     * @return postOrder string representation of the tree
     */
    public String inOrder(){
        return inOrderTraversal(root, new StringBuilder());
    }
    
    /**
     * Returns the number of elements that are currently in the tree
     *
     * @return the number of elements that the BST has
     */
    public int size() {
        return size;
    }

}
