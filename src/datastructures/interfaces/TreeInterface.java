/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.interfaces;

import datastructures.nodes.TreeNode;


/**
 *
 * @author j.anaya.villagrana
 */
public interface TreeInterface<T extends Comparable> {
    void insert(T element);
    boolean remove(T element);
    TreeNode<T> findParent(T value) throws Exception;
    TreeNode<T> findNode(T value) throws Exception;
    T[] toArray();
    boolean contains(T searchValue);
    int size();
    //TreeNode<T> findNode(T value);
}
