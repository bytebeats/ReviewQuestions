package me.danielpan.review.model;

/**
 * Created by Daniel Pan on 15/09/2017 : 14:34.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class TreeNodeWithParent {
    public int value;

    public TreeNodeWithParent(int value) {
        this.value = value;
    }

    public TreeNodeWithParent parent;
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;
}
