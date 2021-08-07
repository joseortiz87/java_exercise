package com.hackerrank.kit;

import java.util.*;

public class SwapNodes {
    public static void main(String args []){
        List<List<Integer>> indexes = new ArrayList<>();
        indexes.add(Arrays.asList(2,3));

        indexes.add(Arrays.asList(4,-1));
        indexes.add(Arrays.asList(5,-1));
        indexes.add(Arrays.asList(6,-1));
        indexes.add(Arrays.asList(7,8));
        indexes.add(Arrays.asList(-1,9));
        indexes.add(Arrays.asList(-1,-1));
        indexes.add(Arrays.asList(10,11));
        indexes.add(Arrays.asList(-1,-1));
        indexes.add(Arrays.asList(-1,-1));
        indexes.add(Arrays.asList(-1,-1));

        System.out.println(swapNodes(indexes,Arrays.asList(2,4)));
    }
    public static class Node{
        Node left;
        Node right;
        Integer level;
        Integer value;
        Node(Integer level,Integer value){
            this.level = level;
            this.value = value;
            this.left = null;
            this.right = null;
        }
        public String toString(){
            return "{v=" + value + ",l=" + level + "}";
        }
    }
    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Write your code here
        Node root = new Node(1,1);
        LinkedList<Node> nodeQueue = new LinkedList<>();
        Map<Integer,List<Node>> levelMap = new HashMap<>();
        nodeQueue.add(root);
        levelMap.put(1,Arrays.asList(root));
        int index = 0;
        int treeDepth = 1;
        // BUILD TREE AND CREATE LEVEL MAP
        while(!nodeQueue.isEmpty()){
            List<Integer> tmpIndexes = indexes.get(index);
            Node parent = nodeQueue.pollFirst();
            Integer childLevel = parent.level+1;
            Node left = tmpIndexes.get(0) > -1 ? new Node(childLevel,tmpIndexes.get(0)) : null;
            Node right = tmpIndexes.get(1) > -1 ? new Node(childLevel,tmpIndexes.get(1)) : null;
            parent.left = left;
            parent.right = right;
            if(left != null) nodeQueue.add(left);
            if(right != null) nodeQueue.add(right);
            if(levelMap.containsKey(childLevel)){
                if(left != null) levelMap.get(childLevel).add(left);
                if(right != null) levelMap.get(childLevel).add(right);
            }else{
                List<Node> levelNode = new ArrayList<>();
                if(left != null) levelNode.add(left);
                if(right != null) levelNode.add(right);
                levelMap.put(childLevel,levelNode);
            }
            if(childLevel > treeDepth && !levelMap.get(childLevel).isEmpty()) treeDepth = childLevel;
            index++;
        }
        //System.out.println(treeDepth);
        //System.out.println(levelMap);
        // APPLY QUERIES
        List<List<Integer>> result = new ArrayList<>();
        for(Integer query : queries){
            int k= 1;
            while(k*query <= treeDepth){
                List<Node> nodesToSwap = levelMap.get(k*query);
                if(null != nodesToSwap){
                    for(Node node : nodesToSwap){
                        Node tmp = node.left;
                        node.left = node.right;
                        node.right = tmp;
                    }
                }
                k++;
            }
            List<Integer> order = new ArrayList<>();
            readInOrderTraversal(root,order);
            result.add(order);
        }
        return result;
    }

    public static void readInOrderTraversal(Node root,List<Integer> order){
        if(null == root) return;
        readInOrderTraversal(root.left,order);

        System.out.println(root.value + " - " + order);
        if(!order.contains(root.value)){
            order.add(root.value);
            //System.out.println(root.value);
        }

        readInOrderTraversal(root.right,order);
    }
}
