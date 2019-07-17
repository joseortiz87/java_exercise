package com.fcb;

import java.util.*;
import java.util.stream.Collectors;

public class Interview2<T> implements DataStructure<T> {

    private final LinkedList<T> dataStructure;
    private final Map<T,Integer> itemsMap;

    public static void main(String args[]){
        Interview2<Integer> structure = new Interview2<>();
        structure.put(1);
        structure.put(2);
        structure.put(3);
        structure.put(3);
        structure.put(4);
        structure.put(5);
        structure.put(1);
        structure.printItems();
        structure.delete(3);
        structure.delete(2);
        structure.printItems();
        structure.put(3);
        structure.delete(1);
        List<Integer> items = structure.listItemsOderOfInsertion();
        System.out.println();
        System.out.println(items.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }

    public Interview2(){
        this.dataStructure = new LinkedList<>();
        this.itemsMap = new HashMap<>();
    }

    @Override
    public void put(T k) {
        if(!itemsMap.containsKey(k)){
            dataStructure.add(k);
            itemsMap.put(k,dataStructure.size()-1);
        }
    }

    @Override
    public void delete(T k) {
        int index = searchIndex(k);
        if(index >= 0){
            dataStructure.remove(index);
            itemsMap.remove(k);
            updateMapIndexes(index);
        }
    }

    @Override
    public boolean contains(T k) {
        return itemsMap.containsKey(k);
    }

    @Override
    public List<T> listItemsOderOfInsertion() {
        List<T> listItems = new ArrayList<>();
        for(T t : this.dataStructure){
            listItems.add(t);
        }
        return listItems;
    }

    private int searchIndex(T k){
        return contains(k) ? itemsMap.get(k) : -1;
    }

    //O(n)
    private void updateMapIndexes(int index){
        for(T k : itemsMap.keySet()){
            if(itemsMap.get(k) > index){
                itemsMap.put(k,itemsMap.get(k)-1);
            }
        }
    }

    public void printItems(){
        System.out.println();
        for(T t : this.dataStructure){
            System.out.print(t + " ");
        }
    }

}
