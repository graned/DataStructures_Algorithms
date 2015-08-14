/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author j.anaya.villagrana
 */
public class StandardQueue<T> {
    private SingleLinkedList<T> sll;
    private int size;
    public StandardQueue(){
        sll = new SingleLinkedList<>();
    }
    /**
     * places an item at the back of the queue;
     * @param element Element to be saved in the queue
     */
    public void enqueue(T element) throws Exception{
        if(element == null){
            throw new Exception("Element canot be null");
        }else{
            sll.add(element);
            size++;
        }
    }
    /**
     * retrieves the item at the front of the queue, and removes it from the queue;
     * @return item removed from the queue
     */
    public T dequeue(){
        T element = sll.get(0);
        sll.remove(element);
        size--;
        return element;
    }
    
    /**
     * retrieves the item at the front of the queue without removing it from the queue
     * @return first item in the queue
     */
    public T peek(){
        return sll.get(0);
    }
    
    /**
     * retrieves the queue size
     * @return returns the queue size
     */
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        return sll.toString();
    }
}
