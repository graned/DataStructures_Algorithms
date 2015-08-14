/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.nodes;

/**
 *
 * @author j.anaya.villagrana
 * @param <T>
 */
public class Node<T>{
    private T value;
    private Node<T> nextNode;
    //ADDED FOR DOUBLE LINKED LIST IMPLEMENTATION
    private Node<T> previousNode;

    public Node(T value, Node nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public Node() {
    }

    /**
     * This method returns the value of the node object
     * @return Returns a the value of the type specified
     */
    public T getValue() {
        return value;
    }
    /**
     * Sets the value of the node
     * @param value - value of the node
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * This method returns the next node that is related to the current node
     * @return Returns the next node linked to this instance 
     */
    public Node<T> getNextNode() {
        return nextNode;
    }

    /**
     * Sets the next node
     * @param nextNode - Node that will be related to the current node instance
     */
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    ////////////////////////////// ADDED FOR DOUBLE LINKED LIST IMPLEMENTATION ////////////////////////////////////////////
    /**
     * This method returns the previous node that is related to the current node
     * @return Returns the next node linked to this instance 
     */
    public Node<T> getPreviousNode() {
        return previousNode;
    }

    /**
     * Sets the previous node
     * @param previousNode - Node that will be related to the current node instance
     */
    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
    ////////////////////////////// ADDED FOR DOUBLE LINKED LIST IMPLEMENTATION ////////////////////////////////////////////
    
    @Override
    public String toString() {
        return value.toString();
    }
    
}
