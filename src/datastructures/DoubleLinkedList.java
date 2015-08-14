/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import datastructures.interfaces.MyCollectionInterface;
import datastructures.nodes.Node;
import java.lang.reflect.Array;
import java.util.Iterator;

/**
 *
 * @author j.anaya.villagrana
 */
public class DoubleLinkedList<T extends Comparable> implements MyCollectionInterface<T>, Iterable<Node<T>> {
    Node<T> head;
    Node<T> tail;
    
    private int size;
    
    /**
     * This method adds a new element to the Double Linked List
     *
     * @param element Value to be added to the linked list
     */
    @Override
    public void add(T element) {
        Node<T> node = new Node<T>();
        node.setValue(element);
        if(head == null){
            head = node;
            tail = node;
        }else{
            node.setPreviousNode(tail);
            tail.setNextNode(node);
            tail = node;
        }
        size++;
    }

    /**
     * This method will handle the deletion of a node. We will be taking care of
     * the following cases:<br>
     * 1) The list is empty<br>
     * 2) The node to remove is the only one on the list<br>
     * 3) Removing the head of the list<br>
     * 4) Removing the tail of the list<br>
     * 5) Removing a node in between the head and tail<br>
     * 6) The item to remove does no exist.<br>
     *
     * @param element The value that will be removed from the list.
     * @return Returns a flag if the deletion was correct or not.
     */
    @Override
    public boolean remove(T element) {
        Node<T> node = new Node<T>();
        //CASE 1
        if(head==null){
            return false;
        }
        if(head.getValue().equals(element)){
            if(head == tail){
                //CASE 2
                head = null;
                tail = null;
            }else{
                //CASE 3
                head=head.getNextNode();
                head.setPreviousNode(null);
            }
            size--;
            return true;
        }
        node = head.getNextNode();
        while(node!=null && !element.equals(node.getValue())){
            node=node.getNextNode();
        }
        if(node == tail){
            //CASE 4
            tail=tail.getPreviousNode();
            tail.setNextNode(null);
            size--;
            return true;
        }else if(node!=null){
            //CASE 5
            node.getPreviousNode().setNextNode(node.getNextNode());
            node.getNextNode().setPreviousNode(node.getPreviousNode());
            size--;
            return true;
        }
        //CASE 6
        return false;
    }

    /**
     * This method will search for a value in the linked list. It will search
     * until it finds the element that we are looking for.
     *
     * @param searchValue - Value to look for in the list
     * @return Returns TRUE if we were able to find the element. Otherwise it
     * will return FALSE.
     */
    public boolean contains(T searchValue) {
        Node<T> node = head;
        while (node != null && !searchValue.equals(node.getValue())) {
            node = node.getNextNode();
        }
        if (node == null) {
            return false;
        }
        return true;
    }
    
    /**
     * This method will traverse the single linked list and will create a string
     * representation of the list
     *
     * @return String representation of the linked list
     */
    public String traverse() {
        StringBuilder sb = new StringBuilder();
        Node<T> node = head;
        while (node != null) {
            sb.append(node);
            if (node != tail) {
                sb.append("-->");
            }
            node = node.getNextNode();
        }
        return sb.toString();
    }
    /**
     * This method will traverse the single linked list backwards, in order to
     * do this, an inner loop will be extracting the previous node from the one
     * we will be currently checking, this will involve an inner loop, causing
     * this algorithm to have a complexity of O(n^2).
     *
     * @return String representation of the reverse traverse.
     */
    public String reverseTraverse() {
        StringBuilder sb = new StringBuilder();
        Node node = tail;
        while (node != null) {
            sb.append(node);
            if (node != head) {
                sb.append("<--");
            }
            node = node.getPreviousNode();
        }
        return sb.toString();
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) throws RuntimeException {
        Node<T> node = head;
        int counter = 0;
        if (node == null || index >= size) {
            throw new RuntimeException("Value not found");
        }
        while (node != null && counter != index) {
            node = node.getNextNode();
            counter++;
        }
        
        return node.getValue();
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new Iterator<Node<T>>() {
            Node<T> currentNode=head;
            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Node<T> next() {
                Node<T> returnNode = currentNode;
                currentNode=currentNode.getNextNode();
                return returnNode;
            }

            @Override
            public void remove() {
                //nothing to add here;
            }
        };
    }

    @Override
    public T[] toArray() {
        if(head != null){
            T[] array = (T[]) Array.newInstance(head.getValue().getClass(), size);
            Node<T> node = head;
            int counter = 0;
            while(node != null){
                array[counter] = node.getValue();
                node = node.getNextNode();
                counter++;
            }
            return array;
        }else{
            throw new RuntimeException("COLLECTION IS EMPTY");
        }
    }
    
    public String toString(){
        return traverse();
    }
}
