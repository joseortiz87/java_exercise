package mx.com.joortizs.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class RoadsAndLibraries2 {
	
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
            	HashSet<Integer> tmpSet = null;
            	Stack<Integer> stack = null;
            	HashMap<Integer,Boolean> visitedCitiesMap = new HashMap<Integer,Boolean>();
            	long connectedCities = 0;
                //FOR CONNECTED CITIES - M  --> Time O(m)
            	startTime2 = System.currentTimeMillis();
                for(int a1 = 0; a1 < m; a1++){
                    int city_1 = in.nextInt();
                    int city_2 = in.nextInt();
                    if(citiesMap.containsKey(city_1) && citiesMap.containsKey(city_2)) {
                    	//ADD CITY2/CITY1 TO EDGE SET FOR EACH VERTIX CITY1/CITY2
                    	citiesMap.get(city_1).add(city_2);
                    	citiesMap.get(city_2).add(city_1);
                    }else if(citiesMap.containsKey(city_1)) {
                    	//ADD CITY2 TO EDGE SET FOR VERTIX CITY1
                    	citiesMap.get(city_1).add(city_2);
                    	tmpSet = new HashSet<Integer>();
                    	tmpSet.add(city_1);
                    	//NEW EDGE SET FOR CITY2 & ADD CITY1
                    	citiesMap.put(city_2, tmpSet);
                    }else if(citiesMap.containsKey(city_2)) {
                    	//ADD CITY1 TO EDGE SET FOR VERTIX CITY2
                    	citiesMap.get(city_2).add(city_1);
                    	tmpSet = new HashSet<Integer>();
                    	tmpSet.add(city_2);
                    	//NEW EDGE SET FOR CITY1 & ADD CITY2
                    	citiesMap.put(city_1, tmpSet);
                    }else {
                    	//NEW EDGE SET FOR CITY1 & ADD CITY2
                    	tmpSet = new HashSet<Integer>();
                    	tmpSet.add(city_2);
                    	citiesMap.put(city_1, tmpSet);
                    	//NEW EDGE SET FOR CITY2 & ADD CITY1
                    	tmpSet = new HashSet<Integer>();
                    	tmpSet.add(city_1);
                    	citiesMap.put(city_2, tmpSet);
                    }
                }
                startTime2 = System.currentTimeMillis()-startTime2;
                //DFS - DEPTH FIRST SEARCH TO FOUND CONNECTE COMPONENTS
                startTime3 = System.currentTimeMillis();
                for(Integer city : citiesMap.keySet()) {
                	//FOR EACH NOT VISITED CITY
                	if(!visitedCitiesMap.containsKey(city)) {
                		//NEW CONNECTED COMPONENT
                		connectedCities = 0;
                		stack = new Stack<Integer>();
                		stack.push(city);
                		//SEARCH UNTIL ALL ARE VISITED OR NO MORE CONNECTIONS EXIST
                		while(!stack.isEmpty()) {
                			Integer v = stack.pop();
                			if(!visitedCitiesMap.containsKey(v)) {
                				visitedCitiesMap.put(v, Boolean.TRUE); //TRACK AS VISITED
                				connectedCities++;
                				for(Integer w : citiesMap.get(v) ) {
                					stack.push(w);
                				}
                			}
                		}
                		//CALCULATE COST FOR COMPONENT
                		total += ((connectedCities-1)*y) + x; //(CitiesConnected-1) * CostRoad + CostLibrary
                	}
                }
                startTime3 = System.currentTimeMillis()-startTime3;
                //CALCULATE TOTAL FOR NOT CONNECTED CITIES
                total += (n-visitedCitiesMap.size())*x; //NOT CONNECTED NODES NEED A LIBRARY EACH ONE
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
