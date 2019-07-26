package com.airbnb;

import java.util.HashSet;
import java.util.LinkedList;

public class PaginateListings {

    public static void main(String arg []){
        String [] listing = {"host_id,listing_id,score,city",
                "1,28,300.1,San Francisco",
                "4,5,209.1,San Francisco",
                "20,7,208.1,San Francisco",
                "23,8,207.1,San Francisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,San Francisco",
                "1,31,204.6,San Francisco",
                "6,29,204.1,San Francisco",
                "7,20,203.1,San Francisco",
                "8,21,202.1,San Francisco",
                "2,18,201.1,San Francisco",
                "2,30,200.1,San Francisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,San Jose",
                "6,25,10.1,Oakland",
                "19,15,9.1,San Jose",
                "3,19,8.1,San Jose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,San Jose",
                "5,6,3.1,San Jose",
                "29,22,2.1,San Jose",
                "30,23,1.1,San Jose"};
        printListing(doPagination(listing,12),12);
    }

    static void printListing(LinkedList<Listing> linkedListing,int size){
        int page = 1;
        int count = 0;
        System.out.println("Page1");
        for(Listing list : linkedListing){
            if(count == size){
                page++;
                count = 0;
                System.out.println("Page" + page);
            }
            System.out.println(list);
            count++;
        }
    }

    static class Listing {
        int hostId;
        int listingId;
        double score;
        String city;
        public Listing(int hostId,int listingId,double score,String city){
            this.hostId = hostId;
            this.listingId = listingId;
            this.score = score;
            this.city = city;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append(hostId)
                    .append(",")
                    .append(listingId)
                    .append(",")
                    .append(score)
                    .append(",")
                    .append(city).toString();
        }
    }

    public static LinkedList<Listing> doPagination(String [] listing,int size){
        LinkedList<Listing> linkedListing = new LinkedList<>();
        // N
        int i = 0;
        for(String item : listing){
            if(i > 0) {
                String[] data = item.split(",");
                linkedListing.add(new Listing(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Double.parseDouble(data[2]),
                        data[3]
                ));
            }else{
                i++;
            }
        }
        if(linkedListing.size() > size){
            int page = 1;
            HashSet<Integer> hostSet = new HashSet<>();
            LinkedList<Listing> duplicates = new LinkedList<>();
            for(int j = 0;j < linkedListing.size();j++){
                if(hostSet.size() == size){
                    hostSet.clear();
                    linkedListing.addAll(page*size,duplicates);
                    page++;
                    duplicates.clear();
                    if(linkedListing.size()-j < size){
                        break;
                    }
                }
                Listing list = linkedListing.get(j);
                if(hostSet.contains(list.hostId)){
                    duplicates.add(linkedListing.remove(j));
                    j--;
                }else{
                    hostSet.add(list.hostId);
                }
            }
            if(!duplicates.isEmpty()){
                linkedListing.addAll(duplicates);
            }
        }
        return linkedListing;
    }

    /*


                "1,28,300.1,San Francisco",
                "4,5,209.1,San Francisco",
                "20,7,208.1,San Francisco",
                "23,8,207.1,San Francisco",
                "16,10,206.1,Oakland",
                "6,29,204.1,San Francisco",
                "7,20,203.1,San Francisco",
                "8,21,202.1,San Francisco",
                "2,30,200.1,San Francisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",


                "1,16,205.1,San Francisco",
                "2,18,201.1,San Francisco",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,San Jose",
                "6,25,10.1,Oakland",
                "19,15,9.1,San Jose",
                "3,19,8.1,San Jose",
                "27,12,6.1,Oakland",
                "25,4,4.1,San Jose",

                "1,31,204.6,San Francisco",
                "3,11,7.1,Oakland",
                "1,3,5.1,Oakland",
                "5,6,3.1,San Jose",
                "29,22,2.1,San Jose",
                "30,23,1.1,San Jose"

     */

}
