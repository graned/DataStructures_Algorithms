/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortalg;

import datastructures.interfaces.MyCollectionInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author j.anaya.villagrana
 */
public class SortAlg {
    /**
     * Re-arranges the values from the array received, by applying the bubble sort algorithm.
     * @param <T> Generic array type.
     * @param array This is the array that will be sorted.
     */
    public static<T extends Comparable> void bubbleSort(T[] array){
        for (int i = array.length - 1; i >= 0 ; i--) {
            for (int j = 0; j < i ; j++) {
                if(array[j].compareTo(array[j+1]) == 1){
                    swap(array,i,j);
                }   
            }
        }
    }
    /**
     * Overloaded method that applies the bubble sort algorithm, for any collection that inherits from MyCollectionInterface. 
     * @param <T> Type of elements that are contained in the collection. This elements need to implement Comparable class
     * @param collection Collection variable that will be sorted
     * @return Returns a new ordered instance of the same type of the collection passed as parameter
     */
    public static <T extends Comparable> MyCollectionInterface<T> bubbleSort(MyCollectionInterface<T> collection){
        T[] elements = collection.toArray();
        bubbleSort(elements);
        try {
            collection = collection.getClass().newInstance();
            for (T element : elements) {
                collection.add(element);
            }  
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SortAlg.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return collection;
    }
    private static<T>  void swap(T[] array, int i, int j){
        T temporal = array[i];
        array[i] = array[j];
        array[j] = temporal;
    }
}
