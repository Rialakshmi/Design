/*
Given a binary tree with the following rules:

    root.val == 0
    If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
    If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2

Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

You need to first recover the binary tree and then implement the FindElements class:

    FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
    bool find(int target) Return if the target value exists in the recovered binary tree.

 

Example 1:

Input
["FindElements","find","find"]
[[[-1,null,-1]],[1],[2]]
Output
[null,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1]); 
findElements.find(1); // return False 
findElements.find(2); // return True 

Example 2:

Input
["FindElements","find","find","find"]
[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
Output
[null,true,true,false]
Explanation
FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
findElements.find(1); // return True
findElements.find(3); // return True
findElements.find(5); // return False

Example 3:

Input
["FindElements","find","find","find","find"]
[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
Output
[null,true,false,false,true]
Explanation
FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
findElements.find(2); // return True
findElements.find(3); // return False
findElements.find(4); // return False
findElements.find(5); // return True

 

Constraints:

    TreeNode.val == -1
    The height of the binary tree is less than or equal to 20
    The total number of nodes is between [1, 10^4]
    Total calls of find() is between [1, 10^4]
    0 <= target <= 10^6


*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class FindElements {
    TreeNode root=null;
    HashSet<Integer> hs;
    public FindElements(TreeNode root) {
        hs=new HashSet<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root!=null) 
            queue.offer(root);
        this.root=root;
        while(!queue.isEmpty()) {
            int size=queue.size();
            while(size>0) {
                TreeNode curr = queue.poll();
                if(curr.val==-1)
                    curr.val=0;
                hs.add(curr.val);
                if(curr.left!=null) {
                    if(curr.left.val==-1)
                        curr.left.val=2*curr.val+1;
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    if(curr.right.val==-1)
                        curr.right.val=2*curr.val+2;
                    queue.offer(curr.right);
                }
                size--;
            }
        }
    }
    
    public boolean find(int target) {
        return hs.contains(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
