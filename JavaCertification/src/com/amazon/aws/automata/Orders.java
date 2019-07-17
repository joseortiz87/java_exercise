package com.amazon.aws.automata;

import java.util.*;

public class Orders {

    public static void main(String args []){
        List<String> orderList = new ArrayList<>();
        orderList.add("zld 93 12");
        orderList.add("fp kindle book");
        orderList.add("10a echo show");
        orderList.add("17g 12 25 6");
        orderList.add("abl kindle book");
        orderList.add("125 echo dot second generation");
        orderList.add("125 echo dot second generation");
        orderList.add("125 nN AA");
        printOrders(prioritizedOrders(6,orderList));
    }

    public static void printOrders(List<String> orders){
        for(String order : orders){
            System.out.println(order);
        }
    }

    public static List<String> prioritizedOrders(int numOrders, List<String> orderList)
    {
        // WRITE YOUR CODE HERE
        if(null == orderList || orderList.size() == 0){
            return orderList;
        }

        List<PrimeOrder> primeOderList = new ArrayList();
        List<String> nonePrimeOrderList = new ArrayList<>();
        List<String> finalOrderList = new ArrayList<>();

        //Clasify orders split in 2 collections
        for(String order : orderList){
            int metaDataStartIndex = order.indexOf(" ");
            if(validOrder(order.substring(metaDataStartIndex+1))){
                if(isMetaDataNonePrime(order.charAt(metaDataStartIndex+1))){
                    nonePrimeOrderList.add(order);
                }else{
                    //PUT METADATA + ID as the key value in a sorted tree
                    primeOderList.add(new PrimeOrder(order.substring(0,metaDataStartIndex),order.substring(metaDataStartIndex+1)));
                }
            }
        }

        Collections.sort(primeOderList,new Comparator<PrimeOrder> (){
            @Override
            public int compare(PrimeOrder o1, PrimeOrder o2) {
                return (o1.getMetaData() + " " + o1.getId()).compareTo(o2.getMetaData() + " " + o2.getId());
            }
        });

        //Add sorted prime collection
        for(PrimeOrder primeOrder : primeOderList){
            finalOrderList.add(primeOrder.getId() + " " + primeOrder.getMetaData());
        }

        //Add None sorted orders
        finalOrderList.addAll(nonePrimeOrderList);

        return finalOrderList;
    }

    static class PrimeOrder{
        private String id;
        private String metaData;

        public PrimeOrder(String id, String metaData) {
            this.id = id;
            this.metaData = metaData;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMetaData() {
            return metaData;
        }

        public void setMetaData(String metaData) {
            this.metaData = metaData;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PrimeOrder)) return false;
            PrimeOrder that = (PrimeOrder) o;
            return Objects.equals(getId(), that.getId()) &&
                    Objects.equals(getMetaData(), that.getMetaData());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getMetaData(),getId());
        }
    }

    public static boolean isMetaDataNonePrime(char metaDataFirstChar){
        return metaDataFirstChar >= 48 && metaDataFirstChar <= 57;
    }
    public static boolean validOrder(String metaData){
        return !metaData.matches( ".*[A-Z].*");
    }
}
