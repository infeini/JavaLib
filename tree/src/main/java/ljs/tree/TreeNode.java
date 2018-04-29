package ljs.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private List<TreeNode<T>> childs = new ArrayList<>();
    private boolean expand;
    private T data;
    private int level;

    public List<TreeNode<T>> expands = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(boolean expand) {
        this.expand = expand;
    }

    public TreeNode(T data, boolean expand) {
        this.data = data;
        this.expand = expand;
    }

    public TreeNode addChild(TreeNode<T> treeNode) {
        childs.add(treeNode);
        return this;
    }

    public boolean isChildsEmpty() {
        return this.childs.isEmpty();
    }

    public boolean isExpand() {
        return expand;
    }

    public int getLevel() {
        return level;
    }

    public T getData() {
        return data;
    }

    public void open() {
        this.expand = true;
    }

    public void close() {
        this.expand = false;
    }

    public void doSwitch() {
        expand = !expand;
    }

    public List<TreeNode<T>> getChilds() {
        return childs;
    }

    public List<TreeNode<T>> getExpands() {
        expands.clear();
        expands.addAll(getExpands(level));
        return expands;
    }

    private List<TreeNode<T>> getExpands(int level) {
        List<TreeNode<T>> expands = new ArrayList<>();
        this.level = level;
        expands.add(this);
        if (expand) {
            List<TreeNode<T>> childs = getChilds();
            for (int i = 0; i < childs.size(); i++) {
                TreeNode child = childs.get(i);
                expands.addAll(child.getExpands(level + 1));
            }
        }
        return expands;
    }

    @Override
    public String toString() {
        return data == null ? super.toString() : data.toString();
    }
}
