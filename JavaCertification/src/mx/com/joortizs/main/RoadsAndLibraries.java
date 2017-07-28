package mx.com.joortizs.main;

import java.util.*;

public class RoadsAndLibraries {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt(); //number of cities
            int m = in.nextInt(); //number of roads
            long x = in.nextLong(); //the cost to build a library
            long y = in.nextLong(); //the cost to repair a road
            long total = 0;
            long startTime1 = System.currentTimeMillis();
            long startTime2 = 0;
            long startTime3 = 0;
            boolean withTime = true;
            if(y >= x || m == 0){
                total = n*x;
                for(int a1 = 0; a1 < m; a1++){
                    in.nextInt();
                    in.nextInt();                
                }
            }else{
            	//LIST OF CONNECTED TREES
            	HashMap<Integer,HashSet<Integer>> citiesMap = new HashMap<Integer,HashSet<Integer>>();
                LinkedList<HashSet<Integer>> trees = new LinkedList<HashSet<Integer>>();
                HashSet<Integer> tmpTree = null;
                long totalConnectedCities = 0;
                //FOR CONNECTED CITIES - M --> Time O(m^2)
                startTime2 = System.currentTimeMillis();
                for(int a1 = 0; a1 < m; a1++){
                    int city_1 = in.nextInt();
                    int city_2 = in.nextInt();
                    if(citiesMap.containsKey(city_1) && citiesMap.containsKey(city_2)) {
                    	//ALREADY CONNECTED
                    	//IF DIFFERRENT TREES, CONNECT THE TREES!
                    	if(citiesMap.get(city_1) != citiesMap.get(city_2)) {
                    		tmpTree = citiesMap.get(city_1);
                    		tmpTree.addAll(citiesMap.get(city_2)); //MERGE TREE CITY1 WITH CITY2
                    		trees.remove(citiesMap.get(city_2)); //REMOVE TREE CITY2
                    		for(Integer tmpCity : citiesMap.get(city_2)) {
                    			citiesMap.put(tmpCity, tmpTree); //UPDATE REFERENCE
                    		}
                    	}
                    }else if(citiesMap.containsKey(city_1)) {
                    	tmpTree = citiesMap.get(city_1); //GET TREE FOR CITY1
                    	tmpTree.add(city_2); //ADD CITY2 TO THE TREE
                    	citiesMap.put(city_2, citiesMap.get(city_1)); //MARK CITY2 AS VISITED
                    }else if(citiesMap.containsKey(city_2)) {
                    	tmpTree = citiesMap.get(city_2);  //GET TREE FOR CITY2
                    	tmpTree.add(city_1);  //ADD CITY1 TO THE TREE
                    	citiesMap.put(city_1, citiesMap.get(city_2));  //MARK CITY1 AS VISITED
                    }else {
                    	//NEW TREE, UNVISITED CITIES
                    	tmpTree = new HashSet<Integer>();
                    	tmpTree.add(city_1);  //ADD CITY1 TO THE NEW TREE
                    	tmpTree.add(city_2);  //ADD CITY2 TO THE NEW TREE
                    	trees.add(tmpTree); //ADD NEW CONNECTED TREE TO THE CONNECTED TREES ARRAY
                    	citiesMap.put(city_1, tmpTree);  //MARK BOTH CITIES AS VISITED
                    	citiesMap.put(city_2, tmpTree);
                    }
                }
                startTime2 = System.currentTimeMillis()-startTime2;
                //CALCULATE TOTAL FOR CONNECTED CITIES (FOR EACH TREE)
                startTime3 = System.currentTimeMillis();
                for(HashSet<Integer> tree : trees) {
                	total += ((tree.size()-1)*y) + x; //(CitiesConnected-1) * CostRoad + CostLibrary
                	totalConnectedCities += tree.size(); //TOTAL CITIES IN THE TREE
                }
                startTime3 = System.currentTimeMillis()-startTime3;
                //CALCULATE TOTAL FOR NOT CONNECTED CITIES
                total += (n-totalConnectedCities)*x; //NOT CONNECTED NODES NEED A LIBRARY EACH ONE
            }
            startTime1 = System.currentTimeMillis()-startTime1;
            System.out.println( withTime ? 
            		total + " -- [" + startTime2 + "], [" + startTime3 + "], TOTAL: " + startTime1
            		: total );
        }
        in.close();
    }
	
    /*
     * 
5
9 2 91 84
8 2
2 9
5 9 92 23
2 1
5 3
5 1
3 4
3 1
5 4
4 1
5 2
4 2
8 3 10 55
6 4
3 2
7 1
1 0 5 3
2 0 102 1

805
184
80
5
204
     */
}
