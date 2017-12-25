package me.danielpan.review.question;

import me.danielpan.review.model.ListNode;
import me.danielpan.review.model.TreeNode;
import me.danielpan.review.model.TreeNodeWithParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Pan on 15/09/2017 : 10:48.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class ListQuestions {
    /*输入两个链表，找出它们的第一个公共结点*/
    public static ListNode lookForFirstCommonNode(ListNode list1, ListNode list2) {
        if (list1 != null && list2 != null) {
            int listLength1 = 0;
            ListNode tmpListNode1 = list1;
            while (tmpListNode1 != null) {
                listLength1++;
                tmpListNode1 = tmpListNode1.next;
            }
            int listLength2 = 0;
            ListNode tmpListNode2 = list2;
            while (tmpListNode2 != null) {
                listLength2++;
                tmpListNode2 = tmpListNode2.next;
            }
            ListNode listLong = list1;
            ListNode listShort = list2;
            int diff = listLength1 - listLength2;
            if (diff < 0) {
                listLong = list2;
                listShort = list1;
                diff = -diff;
            }
            for (int i = 0; i < diff; i++) {
                listLong = listLong.next;
            }
            while (listLong != null && listShort != null && listLong.value != listShort.value) {
                listLong = listLong.next;
                listShort = listShort.next;
            }
            return listLong;
        }
        return null;
    }

    /*给定二叉树两个节点，找公共父节点*/
    // TODO: 15/09/2017 4 各方式去求lowest common ancestor
    /*
    * 2, 有root节点的树, 通过root节点生成两个链表, 然后比较两个链表的第一个父结点

    * 4, 递归:
    * */

    public static TreeNode lookForFirstCommonNode(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }
        TreeNode list1 = new TreeNode(root.value);
        TreeNode tmp = list1;
        while (tmp != null) {
            if (tmp.value > node1.value) {
                TreeNode node = tmp.left;
                tmp.left = tmp;
                tmp = node;
            } else {
                TreeNode node = tmp.right;
                tmp.right = tmp;
                tmp = node;
            }
        }
        TreeNode list2 = new TreeNode(root.value);
        tmp = list2;
        while (tmp != null) {
            if (tmp.value > node2.value) {
                TreeNode node = tmp.left;
                tmp.left = tmp;
                tmp = node;
            } else {
                TreeNode node = tmp.right;
                tmp.right = tmp;
                tmp = node;
            }
        }
        while (list1 != null && list2 != null) {
            if (list1.left == null && null != list2.right || list1.right == null && list2.left != null) {
                return list1;
            } else {
                if (list1.left == null) {
                    TreeNode right = list1.right;
                    list1.right = list1;
                    list1 = right;

                    TreeNode right1 = list2.right;
                    list2.right = list2;
                    list2 = right1;
                } else if (list1.right == null) {
                    TreeNode left = list1.left;
                    list1.left = list1;
                    list1 = left;
                    TreeNode left1 = list2.left;
                    list2.left = list2;
                    list2 = left1;
                }
            }
        }
        return null;
    }

    /*给定二叉树两个节点，找公共父节点*/
    /* 1, 节点有parent, 这样生成两个链表, 然后比较两个链表的第一个父结点*/
    public static TreeNodeWithParent lookForFirstCommonNode(TreeNodeWithParent node1, TreeNodeWithParent node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        TreeNodeWithParent tmp = node1;
        int length1 = 0;
        while (tmp != null) {
            length1++;
            tmp = tmp.parent;
        }
        tmp = node2;
        int length2 = 0;
        while (tmp != null) {
            length2++;
            tmp = tmp.parent;
        }
        int interval = Math.abs(length1 - length2);
        if (length1 > length2) {
            for (int i = 0; i < interval; i++) {
                node1 = node1.parent;
            }
            for (int i = 0; i < length2; i++) {
                if (node1.value == node2.value) {
                    return node1;
                } else {
                    node1 = node1.parent;
                    node2 = node2.parent;
                }
            }
        } else {
            for (int i = 0; i < interval; i++) {
                node2 = node2.parent;
            }
            for (int i = 0; i < length1; i++) {
                if (node1.value == node2.value) {
                    return node1;
                } else {
                    node2 = node2.parent;
                }
            }
        }
        return null;
    }

    /*
    *  3, 根据root节点, 生成中序跟后序遍历, 在中序遍历中, 两结点值之间的值集合中, 包含LCA, 然后在后序遍历中, 两结点值之后的序列中, 第一个出现在中序遍历集合中的值, 为LCA
    * */
    public static TreeNode lookForFirstCommonNode3(TreeNode root, TreeNode node1, TreeNode node2) {
        List<TreeNode> inList = new ArrayList<>();
        List<TreeNode> postList = new ArrayList<>();
        inflateInList(root, inList);
        inflateInList(root, postList);
        int leftIndex = 0, rightIndex = 0;
        int tmp;
        for (int i = 0; i < inList.size(); i++) {
            if (inList.get(i) == node1) {
                leftIndex = i;
            }
            if (inList.get(i) == node2) {
                rightIndex = i;
            }
        }
        if (leftIndex > rightIndex) {
            tmp = leftIndex;
            leftIndex = rightIndex;
            rightIndex = tmp;
        }
        for (int i = 0; i < postList.size(); i++) {
            TreeNode commonNode = postList.get(i);
            for (int j = leftIndex; j <= rightIndex; j++) {
                if (commonNode == inList.get(j)) {
                    return commonNode;
                }
            }
        }
        return null;
    }

    private static List<TreeNode> inflateInList(TreeNode root, List<TreeNode> inList) {
        if (root != null) {
            if (root.left != null) {
                inflateInList(root.left, inList);
            }
            inList.add(root);
            if (root.right != null) {
                inflateInList(root.right, inList);
            }
        }
        return inList;
    }

    private static List<TreeNode> inflatePostList(TreeNode root, List<TreeNode> postList) {
        if (root != null) {
            if (root.left != null) {
                inflatePostList(root.left, postList);
            }
            if (root.right != null) {
                inflatePostList(root.right, postList);
            }
            postList.add(root);
        }
        return postList;
    }

    /*单链表两两反转。比如 1 2 3 4 变成 3 4 1 2， 1 2 3 变成 3 1 2*/
    public static ListNode reverseListInPair(ListNode header) {
        if (header == null) {
            return null;
        }
        if (header.next == null) {
            return header;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = header;
        ListNode prev, cur;
        prev = header;
        cur = prev.next.next;
        while (cur != null) {
            if (cur.next != null) {
                prev.next = cur.next.next;
                cur.next.next = dummy.next;
                dummy.next = cur;
                cur = prev.next.next;
            } else {
                prev.next = cur.next;
                cur.next = dummy.next;
                dummy = cur;
                cur = prev.next.next;
            }
        }
        return null;
    }

    public static ListNode reverseListByLocal(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        if (listNode.next == null) {
            return listNode;
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev, cur;
        dummy.next = listNode;
        prev = listNode;
        cur = prev.next;
        while (cur != null) {
            prev.next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = prev.next;
        }
        return dummy.next;
    }

    public static ListNode reverseListByNewList(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur;
        dummy.next = listNode;
        cur = listNode;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = node;
        }
        return dummy;
    }

    /*两两反转单项链表就是把每两个数反转一次。如下：
    *A -> B -> C ->D -> E -> F ->G -> H -> I  两两反转后变为 B -> A -> D ->C -> F -> E ->H -> G -> I
    * */
    public static ListNode reverseListInPair1(ListNode header) {
        if (header == null) {
            return null;
        }
        if (header.next == null) {
            return header;
        }
        ListNode dummy, prev, next;
        dummy = new ListNode(-1);
        dummy.next = header;
        prev = header;
        next = prev.next;
        while (prev != null) {
            if (next == null) {
                dummy.next = prev.next;
                prev = null;
            } else {
                prev.next = next.next;
                next.next = dummy.next;
                dummy.next = prev;
                prev = prev.next;
            }
        }
        return header;
    }

    /*列表中元素不重复, 且x != y*/
    public static ListNode swap(ListNode header, int x, int y) {
        ListNode xDummy = new ListNode(-1);
        ListNode yDummy = new ListNode(-1);
        xDummy = yDummy = header;
        ListNode xNode, yNode;
        xNode = yNode = null;
        int count = 0;
        ListNode tmp = header;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        boolean xFound, yFound;
        xFound = yFound = false;
        tmp = header;
        if (count > 1) {
            while (tmp.next != null) {
                if (!xFound && x == tmp.next.value) {
                    xDummy = tmp;
                    xNode = tmp.next;
                    xFound = true;
                }
                if (!yFound && y == tmp.next.value) {
                    yDummy = tmp;
                    yNode = tmp.next;
                    xFound = true;
                }
                if (xFound && yFound) {
                    break;
                }
            }
            if (xNode == null || yNode == null) {
                return header;
            }
            ListNode xNext = xNode.next;
            xDummy.next = yNode;
            yNode.next = xNext;
            ListNode yNext = yNode.next;
            yDummy.next = xNode;
            xNode.next = yNext;
        }
        return header;
    }

    public static void quickSort(ListNode s, ListNode e) {
        if (s == null || e == null || s == e) {
            return;
        }
        ListNode p = s;
        ListNode q = s.next;
        int v = s.value;
        int tmp;
        while (q != e.next) {
            if (q.value < v) {
                p = p.next;
                tmp = p.value;
                p.value = q.value;
                q.value = tmp;
            }
            q = q.next;
        }
        tmp = s.value;
        s.value = p.value;
        p.value = tmp;
        quickSort(s, p);
        quickSort(p.next, e);
    }

}
