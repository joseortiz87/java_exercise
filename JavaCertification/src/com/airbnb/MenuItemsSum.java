package com.airbnb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class MenuItemsSum {

    // https://imgs.xkcd.com/comics/np_complete.png / https://imgur.com/a/hG7em
// You are the waiter in this story. Given a total sum for appetizers (in the comic $15.05), write a program which writes to the screen what the customer’s order could be.
// "Fruit": 2.15,
// "Fries": 2.75,
// "Salad": 3.35,
// "Wings": 3.55,
// "Mozzarella": 4.20,
// "Plate": 5.80

    public static void main(String args []){
        List<MenuItem> listItems = new ArrayList<>();
        listItems.add(new MenuItem("Fruit",2.15));
        listItems.add(new MenuItem("Fries",2.75));
        listItems.add(new MenuItem("Salad",3.35));
        listItems.add(new MenuItem("Wings",3.55));
        listItems.add(new MenuItem("Mozzarella",4.20));
        listItems.add(new MenuItem("Plate",5.80));
        System.out.println(findOptions(listItems,15.05));

        listItems = new ArrayList<>();
        listItems.add(new MenuItem("a",2.0));
        listItems.add(new MenuItem("b",4.0));
        listItems.add(new MenuItem("c",6.0));
        listItems.add(new MenuItem("d",8.0));
        System.out.println(findOptions(listItems,8.0));
    }

    public static class MenuItem {
        String name;
        Double price;
        public MenuItem(String name, Double price){
            this.name = name;
            this.price = price;
        }
        public String toString(){
            return name + ":" + price;
        }
    }

    public static List<List<MenuItem>> findOptions(List<MenuItem> listItems, Double targetPrice){
        if(listItems.isEmpty()) return new ArrayList<>();
        List<List<MenuItem>> result = new ArrayList<>();
        List<MenuItem> listItemsFiltered = new ArrayList<>();

        //filter the items
        for(MenuItem item : listItems){
            if(item.price <= targetPrice) listItemsFiltered.add(item);
        }

        // Find sums using recursion and backtracking
        if(listItemsFiltered.size() > 0){
            List<List<MenuItem>> solutions = new ArrayList<>();
            findSumOptions(solutions,listItemsFiltered,targetPrice,0,new ArrayList<>());
            result.addAll(solutions);
        }

        return result;
    }

    public static void findSumOptions(List<List<MenuItem>> solutions,List<MenuItem> listItems, Double currentSum, int index, List<MenuItem> temp){
        if(currentSum == 0){
            solutions.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i < listItems.size(); i++){
            // checking that sum does not become negative

            if ((currentSum - listItems.get(i).price) >= 0) {

                // adding element which can contribute to
                // sum

                temp.add(listItems.get(i));

                BigDecimal res = new BigDecimal(currentSum).subtract(new BigDecimal(listItems.get(i).price));
                BigDecimal roundOff = res.setScale(2, RoundingMode.HALF_EVEN);
                findSumOptions(solutions, listItems, roundOff.doubleValue(), i,
                        temp);

                // removing element from list (backtracking)
                temp.remove(listItems.get(i));
            }
        }
    }
}
