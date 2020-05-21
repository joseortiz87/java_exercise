package com.airbnb;

import java.util.Queue;
import java.util.LinkedList;


public class JumpingJack {

    public static void main(String args []){
        System.out.println(maxStep(800,500));
    }

    static class Node {
        int level;
        int steps;
        public Node(int level,int steps) {
            this.steps = steps;
            this.level = level;
        }

        @Override
        public String toString() {
            return "L-" + this.level + ",S-" + this.steps;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj != null && obj instanceof Node){
                Node tmpNode = (Node)obj;
                return tmpNode.level == this.level && tmpNode.steps == this.steps;
            }
            return false;
        }
    }

    public static int maxStep(int n,int k){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0));
        int maxSteps = 0;
        int maxStepLevel = n;
        while (!queue.isEmpty())
        {
            Node tempNode = queue.poll();
            //System.out.println(tempNode);

            if(tempNode.level == maxStepLevel){
                if(tempNode.steps > maxSteps){
                    maxSteps = tempNode.steps;
                }
            }else{
                int nextLevel = tempNode.level + 1;
                Node left = new Node(nextLevel,tempNode.steps);
                Node right = new Node(nextLevel,tempNode.steps + nextLevel);

                if(left.steps == k || right.steps == k){
                    //System.out.println("Found k at level " + nextLevel);
                    maxStepLevel = nextLevel;
                }

                /*Enqueue left child */
                if (left.steps != k && !queue.contains(left)) {
                    queue.add(left);
                }

                /*Enqueue right child */
                if (right.steps != k && !queue.contains(right)) {
                    queue.add(right);
                }
            }
        }
        if(maxStepLevel < n){
            System.out.println(maxStepLevel + " - " + maxSteps);
            for(int i=maxStepLevel+1;i<=n;i++){
                maxSteps+= i;
            }
        }
        return maxSteps;
    }

    public static int maxStepHelper(int currentLevel,int distance,int k){
        if(currentLevel == 1){
            return distance;
        }

        int leftDistance = 0;
        int rightDistance = 0;
        if(distance != k){
            leftDistance = maxStepHelper(currentLevel--,distance,k);
        }
        if(distance+currentLevel != k){
            rightDistance = maxStepHelper(currentLevel--,distance+currentLevel,k);
        }
        if(rightDistance >= leftDistance){
            return rightDistance;
        }
        return leftDistance;
    }

}
