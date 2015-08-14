/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.interfaces;

import java.util.Iterator;

/**
 *
 * @author j.anaya.villagrana
 */
public interface MyCollectionInterface<T> {
    void add(T element);
    boolean remove(T element);
    int size();
    T get(int index) throws RuntimeException;
    T[] toArray();
    boolean contains(T searchValue);
}
