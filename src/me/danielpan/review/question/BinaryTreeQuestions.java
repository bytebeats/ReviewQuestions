package me.danielpan.review.question;

import me.danielpan.review.model.LevelTreeNode;
import me.danielpan.review.model.TreeNode;

import java.util.*;

/**
 * Created by Daniel Pan on 30/09/2017 : 14:31.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 * 深度优先遍历只能使用先进后出的栈来实现, 又因为先序, 中序, 后序遍历都属于深度优先遍历,
 * 因而先序, 中序, 后序遍历也都只能通过栈这种数据结构来实现
 * 而宽度优先遍历或者叫广度优先遍历, 则只能通过先入先出的队列来实现
 */
public class BinaryTreeQuestions {
    public static List<Integer> depthFirstTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return depthFirstTraversal(root, result);
    }

    /*主要思想就是先将根结点压入栈，然后根结点出栈并访问根结点，而后依次将根结点的右孩子、左孩子入栈，直到栈为空为止*/
    public static List<Integer> depthFirstTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                result.add(node.value);
                if (node.right != null) {
                    stack.add(node.right);
                }
                if (node.left != null) {
                    stack.add(node.left);
                }
            }
        }
        return result;
    }

    public static List<Integer> widthFirstTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return widthFirstTraversal(root, result);
    }

    public static List<Integer> widthFirstTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                result.add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    /*返回二叉树每一层的最大值. 使用dfs*/
    public static List<Integer> findLevelMax(TreeNode root) {//By depth first reversal
        if (root != null) {
            List<Integer> maxs = new ArrayList<>();
            LevelTreeNode parent = new LevelTreeNode();
            parent.level = 0;
            parent.treeNode = root;
            Stack<LevelTreeNode> stack = new Stack<>();
            stack.push(parent);
            while (!stack.isEmpty()) {
                LevelTreeNode levelTreeNode = stack.pop();
                int level = levelTreeNode.level;
                int value = levelTreeNode.treeNode.value;
                if (level == maxs.size()) {
                    maxs.add(value);
                } else {
                    int curValue = maxs.get(level);
                    if (value > curValue) {
                        maxs.set(level, value);
                    }
                }
                if (levelTreeNode.treeNode.right != null) {
                    LevelTreeNode right = new LevelTreeNode();
                    right.level = ++level;
                    right.treeNode = levelTreeNode.treeNode.right;
                    stack.push(right);
                }
                if (levelTreeNode.treeNode.left != null) {
                    LevelTreeNode left = new LevelTreeNode();
                    left.level = ++level;
                    left.treeNode = levelTreeNode.treeNode.left;
                    stack.push(left);
                }
            }
            return maxs;
        }
        return null;
    }

    /*返回二叉树每一层的最大值. 用bfs, */
    public static List<Integer> findLevelMax1(TreeNode root) {//By width first reversal
        if (root != null) {
            List<Integer> maxs = new ArrayList<>();
            LevelTreeNode parent = new LevelTreeNode();
            parent.level = 0;
            parent.treeNode = root;
            Queue<LevelTreeNode> queue = new ArrayDeque<>();
            queue.add(parent);
            while (!queue.isEmpty()) {
                LevelTreeNode levelTreeNode = queue.remove();
                int level = levelTreeNode.level;
                int value = levelTreeNode.treeNode.value;
                if (level == maxs.size()) {
                    maxs.add(value);
                } else {
                    int curValue = maxs.get(level);
                    if (value > curValue) {
                        maxs.set(level, value);
                    }
                }
                if (levelTreeNode.treeNode.left != null) {
                    LevelTreeNode left = new LevelTreeNode();
                    left.level = (level + 1);
                    left.treeNode = levelTreeNode.treeNode.left;
                    queue.add(left);
                }
                if (levelTreeNode.treeNode.right != null) {
                    LevelTreeNode right = new LevelTreeNode();
                    right.level = (level + 1);
                    right.treeNode = levelTreeNode.treeNode.right;
                    queue.add(right);
                }
            }
            return maxs;
        }
        return null;
    }

    public static int getTreeHeight(TreeNode root) {
        if (root != null) {
            return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
        }
        return -1;
    }

    /*使用栈实现二叉树的先序遍历
    * 回溯操作，每次只push一个入栈，保留父节点，确保可以从左节点找到右节点
    * */
    public static List<Integer> preReversalByStack(TreeNode root) {
        if (root != null) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                if (node != null) {
                    result.add(node.value);
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    node = node.right;
                }
            }
            return result;
        }
        return null;
    }

    /*使用栈实现二叉树的中序遍历*/
    public static List<Integer> inReversalByStack(TreeNode root) {
        if (root != null) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    result.add(node.value);
                    node = node.right;
                }
            }
            return result;
        }
        return null;
    }


    /*Push根结点到第一个栈s中。
     *从第一个栈s中Pop出一个结点，并将其Push到第二个栈output中。
     *然后Push结点的左孩子和右孩子到第一个栈s中。
     *重复过程2和3直到栈s为空。
     *完成后，所有结点已经Push到栈output中，且按照后序遍历的顺序存放，直接全部Pop出来即是二叉树后序遍历结果
     * */
    public static List<Integer> postReversalByStack(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> output = new Stack<>();
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                if (node != null) {
                    stack.push(node);
                    output.push(node);
                    node = node.right;
                } else {
                    node = stack.pop();
                    node = node.left;
                }
            }
            List<Integer> result = new ArrayList<>();
            while (!output.isEmpty()) {
                result.add(output.pop().value);
            }
            return result;
        }
        return null;
    }


}
