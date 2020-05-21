package com.glassdoor;

import java.util.*;

public class Glassdoor {
    public static void main(String[] argv) {

        int[][] board = new int[][] {
                {0,  0,  0, -1, -1},
                {0,  0, -1,  0,  0},
                {0, -1,  0, -1,  0},
                {0,  0, -1,  0,  0},
                {0,  0,  0,  0,  0},
                {0,  0,  0,  0,  0},
                {0,  0,  0,  0,  0},
        };

        int[] start1 = new int[] {3, 1}; //
        int[] start2 = new int[] {5, 3};
        int[] start3 = new int[] {5, 1};
        int[] start4 = new int[] {6, 0};
        int[] start5 = new int[] {6, 4};
        int[] start6 = new int[] {0, 0};
        int[] start7 = new int[] {2, 2};



        int[][] board1 = new int[][] {
                { 0,  0,  0, 0, -1 },
                { 0, -1, -1, 0,  0 },
                { 0,  0,  0, 0,  0 },
                { 0, -1,  0, 0,  0 },
                { 0,  0,  0, 0,  0 },
                { 0,  0,  0, 0,  0 },
        };

        int[][] board2 = new int[][] {
                {  0,  0,  0, 0, -1 },
                {  0, -1, -1, 0,  0 },
                {  0,  0,  0, 0,  0 },
                { -1, -1,  0, 0,  0 },
                {  0, -1,  0, 0,  0 },
                {  0, -1,  0, 0,  0 },
        };

        int[][] board3 = new int[][] {
                { 0,  0,  0,  0,  0,  0, 0 },
                { 0, -1, -1, -1, -1, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1, -1, -1, -1, -1, 0 },
                { 0,  0,  0,  0,  0,  0, 0 },
        };

        int[] end = new int[] {0, 0};

        System.out.println(findLegalMoves(board,start1));
        System.out.println(findLegalMoves(board,start2));
        System.out.println(findLegalMoves(board,start3));
        System.out.println(findLegalMoves(board,start4));
        System.out.println(findLegalMoves(board,start5));
        System.out.println(findLegalMoves(board,start6));
        System.out.println(findLegalMoves(board,start7));

        System.out.println(searchTree(board1,new Node(0,0),new Node(1,3)));
        System.out.println(searchTree(board2,new Node(0,0),new Node(5,4)));
        System.out.println(searchTree(board3,new Node(0,0),new Node(3,3)));

    };

    static List<Node> findLegalMoves(int [][] board,int [] pos){
        if(null != pos && pos.length > 1){
            List<Node> possibleMoves = new ArrayList<>();
            int rowMax = board.length;
            int columnMax = board[0].length;
            int row = pos[0];
            int column = pos[1];
            // up
            if(row-1 >= 0 && board[row-1][column] != -1){
                possibleMoves.add(new Node(row-1,column));
            }
            //down
            if(row +1 < rowMax && board[row +1][column] != -1){
                possibleMoves.add(new Node(row+1,column));
            }
            //left
            if(column +1 < columnMax && board[row][column+1] != -1){
                possibleMoves.add(new Node(row,column+1));
            }
            //right
            if(column-1 >= 0 && board[row][column-1] != -1){
                possibleMoves.add(new Node(row,column-1));
            }
            return possibleMoves;
        }
        return new ArrayList<>();
    }

    static boolean searchTree(int [][] board,Node currentNode,Node target){
        Map<Node,Boolean> vistedNodes = new HashMap<>();
        vistedNodes.put(currentNode,false);
        return searchTreeHelper(board,vistedNodes,currentNode,target,new HashSet<>());
    }

    static boolean searchTreeHelper(int [][] board,Map<Node,Boolean> vistedNodes,Node currentNode,Node target,Set<Node> openSet){
        if( currentNode.row < 0 || currentNode.row >= board.length ||
                currentNode.column < 0 || currentNode.column >= board[0].length){
            return false;
        }
        if(board[currentNode.row][currentNode.column] == -1){
            return false;
        }
        if(target.row == currentNode.row && target.column == currentNode.column ){
            return true;
        }
        Node left = new Node(currentNode.row,currentNode.column-1);
        Node right = new Node(currentNode.row,currentNode.column+1);
        Node up = new Node(currentNode.row-1,currentNode.column);
        Node down = new Node(currentNode.row+1,currentNode.column);
        boolean leftRes = false;
        boolean rightRes = false;
        boolean upRes = false;
        boolean downRes = false;

        //left
        if(vistedNodes.containsKey(left)){
            leftRes = vistedNodes.get(left);
        }else if(!openSet.contains(left)){
            openSet.add(left);
            leftRes = searchTreeHelper(board,vistedNodes,left,target,openSet);
            vistedNodes.put(left,leftRes);
            openSet.remove(left);
        }

        //right
        if(vistedNodes.containsKey(right)){
            rightRes = vistedNodes.get(right);
        }else if(!openSet.contains(right)){
            openSet.add(right);
            rightRes = searchTreeHelper(board,vistedNodes,right,target,openSet);
            vistedNodes.put(right,rightRes);
            openSet.remove(right);
        }

        //up
        if(vistedNodes.containsKey(up)){
            upRes = vistedNodes.get(up);
        }else if(!openSet.contains(up)){
            openSet.add(up);
            upRes = searchTreeHelper(board,vistedNodes,up,target,openSet);
            vistedNodes.put(up,upRes);
            openSet.remove(up);
        }

        //down
        if(vistedNodes.containsKey(down)){
            downRes = vistedNodes.get(down);
        }else if(!openSet.contains(down)){
            openSet.add(down);
            downRes = searchTreeHelper(board,vistedNodes,down,target,openSet);
            vistedNodes.put(down,downRes);
            openSet.remove(down);
        }

        return leftRes || rightRes || upRes || downRes;
    }

    static class Node{
        int row;
        int column;
        public Node(int row,int column){
            this.row = row;
            this.column = column;
        }
        public String toString(){
            return "(" + row + "," + column + ")";
        }
        public boolean equals(Object obj){
            if(obj != null){
                Node tmp = (Node) obj;
                return this.row == tmp.row && this.column == tmp.column;
            }
            return false;
        }
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + column;
            return result;
        }
    }
}
