/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.main;

import datastructures.BinaryTree;
import datastructures.DoubleLinkedList;
import datastructures.SingleLinkedList;
import datastructures.StandardQueue;
import sortalg.SortAlg;
import datastructures.nodes.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author j.anaya.villagrana
 */
public class Tests {

    public static void main(String[] args) throws Exception {
        SingleLinkedList<Integer> sll = new SingleLinkedList<>();
        sll.add(1);
        sll.add(45);
        sll.add(60);
        sll.add(12);

        System.out.println(sll.traverse());
        System.out.println(sll.remove(1));
        System.out.println(sll.traverse());
        System.out.println(sll.reverseTraverse());

        System.out.println("DOUBLE LINKED LIST");
        DoubleLinkedList<Integer> dll = new DoubleLinkedList<>();
        dll.add(1);
        dll.add(45);
        dll.add(60);
        dll.add(12);

        System.out.println(dll.traverse());
        System.out.println(dll.reverseTraverse());

        System.out.println("testing foreach");
        for (Node<Integer> node : sll) {
            System.out.println(node.getValue());
        }
        System.out.println("end testing foreach");

        System.out.println("testing foreach double linked list");
        for (Node<Integer> node : dll) {
            System.out.println(node);
        };
        System.out.println("end testing foreach double linked list");

        System.out.println("sll[1] = " + sll.get(1).toString());
        System.out.println("dll[2] = " + sll.get(2).toString());
//        
        System.out.println("\nSLL sorted");
        //SortAlg.bubbleSort(sll);
        System.out.println(SortAlg.bubbleSort(sll));
        System.out.println("end of sll sorted");

        System.out.println("\nDLL sorted");
        //SortAlg.bubbleSort(sll);
        System.out.println(SortAlg.bubbleSort(dll));
        System.out.println("end of dll sorted");

//        System.out.println("\n");
//        Integer[] i = {4,2,5,7,89,2,3,1};
//        for (Integer integer : i) {
//            System.out.print(integer+" ");
//        }
//        System.out.println("\napplying bubble sort");
//        SortAlg.bubbleSort(i);
//        for (Integer integer : i) {
//            System.out.print(integer+" ");
//        }
//        System.out.println("");
        System.out.println("TREE TEST START");
        BinaryTree<Integer> bst = new BinaryTree<>();
        bst.insert(23);
        bst.insert(14);
        bst.insert(31);
        bst.insert(7);
        bst.insert(17);
        bst.insert(9);
        int i = 17;
        System.out.println("the parent of " + i + " is: " + bst.findParent(i).getValue());
        System.out.println("preorder: " + bst.preOrder());
        System.out.println("postorder: " + bst.postOrder());
        System.out.println("inorder: " + bst.inOrder());
        System.out.println("breadthfirst: " + bst.breadthFirst());
        System.out.println("number of elements: " + bst.size());
        System.out.println(bst.remove(31));
        System.out.println("number of elements: " + bst.size());
        System.out.println("TREE TEST ENDS");

        System.out.println("\nQUEUE TESTS STARTS");
        StandardQueue<Integer> queue = new StandardQueue<>();
        queue.enqueue(2);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(3);

        System.out.println(queue);
        System.out.println("element removed:" + queue.dequeue());
        System.out.println(queue);
        System.out.println("peek: " + queue.peek());
        System.out.println(queue);

        String s = "ABCSamZhubBXyzxYZabcaBc";
        String s2 = "cbcbc";
        System.out.println(replaceAll("abc", s, "xYYz"));

//        List<Integer> lista = new ArrayList<Integer>();
//        A<Integer> a = new A<Integer>();
//        lista.add(a.getNumber());
//        System.out.println((Integer)lista
//        System.out.println("\n EXPECTED DIFFERENCE TEST START");
//        int max = 500;
//        int[] a = new int[max];
//        for (int j = 0; j < max; j++) {
//            a[j] = (int) (Math.random() * 2000);
//            //System.out.print(a[j] + " ");
//        }
//        System.out.println("");
//        int[] a2 = {18, 15, 21, 16, 22};
//        System.out.println("expectedDifferenceCounter: " + expectedDifferenceCounter(a, 3));
//        //System.out.println("expectedDifferenceCounter2: " + expectedDifferenceCounter2(a, 3));
    }

    public static String replaceAll(String reggex, String text, String replaceWith) {
        System.out.println("original\n" + text);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length();) {
            String t = text.substring(i, reggex.length() + i <= text.length() ? reggex.length() + i : i);
            if (reggex.equalsIgnoreCase(t)) {
                sb.append(replaceWith);
                i += reggex.length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String replaceAllFer(String origStr, String target, String replacement) {

        List<Integer> positions = new ArrayList<Integer>();
        StringBuilder result = new StringBuilder();
        positions = getPosition(origStr.toLowerCase(), target.toLowerCase(), positions);

        if (positions.isEmpty()) {
            return origStr;
        }

        result.append(origStr.substring(0, positions.get(0)));

        int i = 0;
        while (i < positions.size()) {

            int index = positions.get(i);
            result.append(replacement);

            if (i + 1 < positions.size() && (index + target.length()) < origStr.length()) {
                result.append(origStr.substring(index + target.length(), positions.get(++i)));
            } else {
                i++;
            }

        }

        return result.toString();

    }

    private static List<Integer> getPosition(String origStr, String target, List<Integer> positions) {

        int index = origStr.indexOf(target);
        if (index == -1) {
            return positions;
        }

        int pSize = 0;
        if (!positions.isEmpty()) {
            pSize = positions.get(positions.size() - 1) + target.length();
        }
        positions.add(index + pSize);
        origStr = origStr.substring(index + target.length(), origStr.length());

        return getPosition(origStr, target, positions);

    }

    public static long expectedDifferenceCounter(int[] numbers, int k) {
        long counter = 0;
        long counter2 = 0;
        long conditionCounter = 0;
        HashMap<Integer, Long> counterMapping = new HashMap<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            if (counterMapping.keySet().contains(numbers[i])) {
                counter += counterMapping.get(numbers[i]);
            } else {
                //counterMapping.put(numbers[i], 0);
                conditionCounter = 0;
                for (int j = 0; j <= numbers.length - 1; j++) {
                    counter2++;
                    //System.out.println("|" + numbers[i] + "-" + numbers[j] + "|=" + Math.abs(numbers[i] - numbers[j]));
                    if (Math.abs(numbers[i] - numbers[j]) == k) {
                        conditionCounter++;
                        counter++;
                    }
                }
                counterMapping.put(numbers[i], conditionCounter);
            }
        }
        System.out.println("operations:" + counter2);
        System.out.println(counterMapping);
        return counter;
    }

    public static int expectedDifferenceCounter2(int[] numbers, int k) {
        int counter = 0;
        int counter2 = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j <= numbers.length - 1; j++) {
                counter2++;
                //System.out.println("|" + numbers[i] + "-" + numbers[j] + "|=" + Math.abs(numbers[i] - numbers[j]));
                if (Math.abs(numbers[i] - numbers[j]) == k) {
                    counter++;
                }
            }
        }
        System.out.println("operations:" + counter2);
        return counter;

    }
}

class A<T> {

    private T number;

    public T getNumber() {
        return number;
    }
}
