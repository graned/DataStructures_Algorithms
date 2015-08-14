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
 * @param <T>
 */
public class SingleLinkedList<T> implements MyCollectionInterface<T>, Iterable<Node<T>> {

    private Node<T> head;
    private Node<T> tail;

    private int size;

    /**
     * This method adds a new element to the Single Linked List
     *
     * @param value Value to be added to the linked list
     */
    public void add(T value) {
        Node<T> node = new Node<T>();
        node.setValue(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNextNode(node);
            tail = node;
        }
        size++;
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
     * This method will handle the deletion of a node. We will be taking care of
     * the following cases:<br>
     * 1) The list is empty<br>
     * 2) The node to remove is the only one on the list<br>
     * 3) Removing the head of the list<br>
     * 4) Removing the tail of the list<br>
     * 5) Removing a node in between the head and tail<br>
     * 6) The item to remove does no exist.<br>
     *
     * @param value The value that will be removed from the list.
     * @return Returns a flag if the deletion was correct or not.
     */
    public boolean remove(T value) {
        Node<T> node;
        if (head == null) {
            //CASE 1
            return false;
        }
        node = head;
        if (node.getValue().equals(value)) {
            if (head == tail) {
                //CASE 2
                head = null;
                tail = null;
            } else {
                //CASE 3
                head = head.getNextNode();
            }
            size--;
            return true;
        }
        while (node.getNextNode() != null && node.getNextNode().getValue() != value) {
            node = node.getNextNode();
        }
        if (node.getNextNode() != null) {
            if (node.getNextNode() == tail) {
                //CASE 4
                tail = node;
            }
            //CASE 5
            node.setNextNode(node.getNextNode().getNextNode());
            size--;
            return true;
        }
        //CASE 6
        return false;
    }

    /**
     * This method will traverse the single linked list and will create a string
     * representation of the list
     *
     * @return String representation of the linked list
     */
    public String traverse() {
        StringBuilder sb = new StringBuilder();
        Node node = head;
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
        if (tail != null) {
            Node curr = tail;
            while (curr != head) {
                Node prev = head;
                while (prev.getNextNode() != curr) {
                    prev = prev.getNextNode();
                }
                sb.append(curr);
                sb.append("<--");
                curr = prev;
            }
            sb.append(curr);
        }
        return sb.toString();
    }

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
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Node<T> next() {
                Node<T> returnNode = currentNode;
                currentNode = currentNode.getNextNode();
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
