package com.fcb;

public class Sumgrandnode {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String args[]){
        TreeNode n7 = new TreeNode(8);
        TreeNode n8 = new TreeNode(9);
        TreeNode n1 = new TreeNode(4,n7,n8);
        TreeNode n2 = new TreeNode(5,null,new TreeNode(10));
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(7);
        TreeNode n5 = new TreeNode(2,n1,n2);
        TreeNode n6 = new TreeNode(3,n3,n4);
        System.out.println(sumEvenGrandparent(new TreeNode(1,n5,n6)));
    }
    public static int sumEvenGrandparent(TreeNode root) {
        return sumEvenGrandparenthelper(root,null,null);
    }

    /**
     *           1
     *        2     3
     *      4   5   6  7
     *    8  9  10
     */
    public static int sumEvenGrandparenthelper(TreeNode root,TreeNode parent,TreeNode grandParent){
        if(root == null) return 0;
        int sumleft = sumEvenGrandparenthelper(root.left,root,parent);
        int sumright = sumEvenGrandparenthelper(root.right,root,parent);
        int grandParentval = 0;
        if(grandParent != null && grandParent.val % 2 == 0){
            grandParentval = root.val;
        }
        return sumleft + sumright + grandParentval;
    }
}
