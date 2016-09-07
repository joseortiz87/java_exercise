package mx.com.joortizs.main;

import java.util.*;

public class BomberMan {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int columns = in.nextInt();
        long endTime = in.nextLong();
        long initTime = 1l;
        Node[][] grid = new Node[rows][columns];
        LinkedList<Node> bombs = new LinkedList<Node>();
        Set<Node> emptyNodes = new HashSet<Node>();
        in.nextLine();
        //READ GRID
        for(int i=0;i<rows;i++){
            String row = in.nextLine();
            for(int j=0;j<row.length();j++){
                grid[i][j] = new Node(i,j,initTime,row.charAt(j));
                if(row.charAt(j) == 79){
                    bombs.add(grid[i][j]);
                }else{
                    emptyNodes.add(grid[i][j]);
                }
            }
        }
        
        //Initial state
        //System.out.println(initTime);
        //printGrid(grid,rows,columns);
        
        //AFTER FIRST TASK
        initTime++;
        while(initTime <= endTime){
            //Bombs explote
            if(initTime > 2  && bombs.size() > 0){
                boolean flag = true;
                ArrayList<Node> nodesNeight = new ArrayList<Node>();
                while(flag && !bombs.isEmpty()){
                    if(bombs.peek().isReady2Explote(initTime)){
                        Node tmpNode = bombs.poll();
                        tmpNode.resetNode(initTime);
                        emptyNodes.add(tmpNode);
                        nodesNeight.addAll(getEdgesNodes(tmpNode,grid,rows,columns));
                    }else if(bombs.peek().isResetNode()){
                        emptyNodes.add(bombs.poll());
                    }else{
                       flag = false; 
                    }
                }
                for(Node nodeNeight : nodesNeight){
                    nodeNeight.resetNode(initTime);
                    emptyNodes.add(nodeNeight);
                }
            }
            
            //Fill empty spaces with bombs
            if(initTime%2 == 0){
                for(Node tmpNode : emptyNodes){
                    tmpNode.putBomb(initTime);
                }
                bombs.addAll(emptyNodes);
                emptyNodes.clear();
            }
            //System.out.println(initTime);
            //printGrid(grid,rows,columns);
            initTime++;
        }
        //System.out.println("final");
        printGrid(grid,rows,columns);
        in.close();
    }
    
    public static ArrayList<Node> getEdgesNodes(Node node,Node[][] grid,int rows,int columns){
        int rowPlus = node.getIndexRow()+1;
        int rowMinus = node.getIndexRow()-1;
        int columnPlus = node.getIndexColumn()+1;
        int columnMinus = node.getIndexColumn()-1;
        ArrayList<Node> nodesEdges = new ArrayList<Node>();
        //top
        if(rowMinus >= 0){
            nodesEdges.add(grid[rowMinus][node.getIndexColumn()]);
        }
        //bottom
        if(rowPlus < rows){
            nodesEdges.add(grid[rowPlus][node.getIndexColumn()]);
        }
        //left
        if(columnMinus >= 0){
            nodesEdges.add(grid[node.getIndexRow()][columnMinus]);
        }
        //right
        if(columnPlus < columns){
            nodesEdges.add(grid[node.getIndexRow()][columnPlus]);
        }
        return nodesEdges;
    }
    
    public static void printGrid(Node[][] grid,int rows,int columns){
        StringBuffer rowStr = null;
        for(int i=0;i<rows;i++){
            rowStr = new StringBuffer();
            for(int j=0;j<columns;j++){
                rowStr.append(grid[i][j]);
            }
            System.out.println(rowStr.toString());
        }
    }
    
    public static class Node{
        private int indexRow;
        private int indexColumn;
        private long timeStamp;
        private char value;
        
        public Node (int indexRow,int indexColumn,long timeStamp,char value){
            this.indexRow = indexRow;
            this.indexColumn = indexColumn;
            this.timeStamp = timeStamp;
            this.value = value;
        }
        public String toString(){
            return "" + this.value;
        }
        public void setIndexRow(int indexRow){
             this.indexRow = indexRow;
        }
        public void setIndexColumn(int indexColumn){
             this.indexColumn = indexColumn;
        }
        public void setTimeStamp(long timeStamp){
             this.timeStamp = timeStamp;
        }
        public void setValue(char value){
             this.value = value;
        }
        public int getIndexRow(){
             return this.indexRow;
        }
        public int getIndexColumn(){
             return this.indexColumn;
        }
        public long getTimeStamp(){
             return this.timeStamp;
        }
        public char getValue(){
             return this.value;
        }
        public boolean isReady2Explote(long currentTime){
            return value == 79 && (currentTime-this.timeStamp) == 2;
        }
        public void resetNode(long currentTime){
            setValue('.');
            setTimeStamp(currentTime);
        }
        public void putBomb(long currentTime){
            setValue((char)79);
            setTimeStamp(currentTime);
        }
        public boolean isResetNode(){
            return value == '.';
        }
    }
    
}