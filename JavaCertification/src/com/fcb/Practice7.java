package com.fcb;

public class Practice7 {

    public static void main(String args[]){
        Node a = new Node(2);
        Node b = new Node(2);
        Node c = new Node(2,a,b);
        a = new Node(2,new Node(2),new Node(2));
        b = new Node(2,c,a);
        Node e = new Node(4,new Node(4),new Node(4));
        Node f = new Node(3,new Node(5),e);
        Node parent = new Node(2,b,f);
        System.out.println(countUniversalTree(parent));
        System.out.println(optimizedCountUniversalTree(parent));
    }

    public static boolean isUniversalTree(Node root){
        if(root == null)
            return true;

        if(root.getLeft() != null && root.getLeft().getValue() != root.getValue())
            return false;

        if(root.getRight() != null && root.getRight().getValue() != root.getValue())
            return false;

        return isUniversalTree(root.getLeft()) && isUniversalTree(root.getRight());
    }

    public static int countUniversalTree(Node root){
        if(root == null)
            return 0;
        int totalCount = countUniversalTree(root.getLeft()) + countUniversalTree(root.getRight());
        if(isUniversalTree(root))
            totalCount++;
        return totalCount;
    }

    public static Dupla optimizedCountUniversalTree(Node root){
        if(root == null)
            return new Dupla(0,true);
        Dupla duplaLeft = optimizedCountUniversalTree(root.getLeft());
        Dupla duplaRight = optimizedCountUniversalTree(root.getRight());
        boolean isUniversal = true;
        if(!duplaLeft.isUniversalTree || !duplaRight.isUniversalTree)
            isUniversal = false;
        if(root.getLeft() != null && root.getValue() != root.getLeft().getValue())
            isUniversal = false;
        if( root.getRight() != null && root.getValue() != root.getRight().getValue())
            isUniversal = false;
        int addRoot = isUniversal ? 1 : 0;
        return new Dupla(duplaLeft.getUniversalTreeCount() + duplaRight.getUniversalTreeCount() + addRoot,isUniversal);
    }

    public static class Dupla{
        private int universalTreeCount;
        private boolean isUniversalTree;

        public Dupla(int universalTreeCount, boolean isUniversalTree) {
            this.universalTreeCount = universalTreeCount;
            this.isUniversalTree = isUniversalTree;
        }

        public int getUniversalTreeCount() {
            return universalTreeCount;
        }

        public void setUniversalTreeCount(int universalTreeCount) {
            this.universalTreeCount = universalTreeCount;
        }

        public boolean isUniversalTree() {
            return isUniversalTree;
        }

        public void setUniversalTree(boolean universalTree) {
            isUniversalTree = universalTree;
        }

        @Override
        public String toString() {
            return "{" + universalTreeCount + "," + isUniversalTree + "}";
        }
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(int value){
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
