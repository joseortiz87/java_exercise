package com.amazon.aws.automata;
import java.util.*;

public class AirOptimization {

    public static void main(String args []){
        List<List<Integer>> forwardRouteList = new ArrayList<>();
        List<List<Integer>> returnRouteList = new ArrayList<>();
        forwardRouteList.add(Arrays.asList(new Integer[]{1,2000}));
        forwardRouteList.add(Arrays.asList(new Integer[]{2,4000}));
        forwardRouteList.add(Arrays.asList(new Integer[]{3,6000}));

        returnRouteList.add(Arrays.asList(new Integer[]{1,2000}));
        returnRouteList.add(Arrays.asList(new Integer[]{2,2000}));
        printUtilization(optimalUtilization(7000,forwardRouteList,returnRouteList));
    }

    public static void printUtilization(List<List<Integer>> listUtilization){
        for(List<Integer> route : listUtilization){
            System.out.println( route.get(0) + " - " + route.get(1));
        }
    }

    public static List<List<Integer>> optimalUtilization(int maxTravelDist,
                                           List<List<Integer>> forwardRouteList,
                                           List<List<Integer>> returnRouteList)
    {
        // WRITE YOUR CODE HERE
        if(forwardRouteList == null || forwardRouteList.size() == 0){
            return null;
        }

        if(returnRouteList == null || returnRouteList.size() == 0){
            return null;
        }

        int maxCostRoute = 0;
        List<Route> routes = new ArrayList<>();
        for(List<Integer> forwardRoute : forwardRouteList){
            if(forwardRoute != null && forwardRoute.size() == 2){
                for(List<Integer> returnRoute : returnRouteList){
                    if(returnRoute != null && returnRoute.size() == 2){
                        int routeCost = forwardRoute.get(1) + returnRoute.get(1);
                        if(routeCost <= maxTravelDist && routeCost >= maxCostRoute){
                            maxCostRoute = routeCost;
                            routes.add(new Route(routeCost,forwardRoute.get(0),returnRoute.get(0)));
                        }
                    }
                }
            }
        }
        Collections.sort(routes,new Comparator<Route>() {
            @Override
            public int compare(Route h1, Route h2) {
                return h2.getCost() - h1.getCost();
            }
        });

        if(routes.size() == 0){
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(routes.get(0).getRouteIds());
        for(int i=1;i<routes.size();i++){
            if(routes.get(0).getCost() == routes.get(i).getCost()){
                res.add(routes.get(i).getRouteIds());
            }else{
                break;
            }
        }
        return res;
    }

    static class Route{
        private int cost;
        private List<Integer> routeIds;

        Route(int cost,int idForward,int idReturn){
            this.cost = cost;
            this.routeIds = new ArrayList<>();
            this.routeIds.add(idForward);
            this.routeIds.add(idReturn);
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public List<Integer> getRouteIds() {
            return routeIds;
        }

        public void setRouteIds(List<Integer> routeIds) {
            this.routeIds = routeIds;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Route)) return false;
            Route route = (Route) o;
            return getCost() == route.getCost();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getCost());
        }
    }

}
