package ljs.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNode<T> {
    private List<TreeNode<T>> childs = new ArrayList<>();
    private boolean expand = true;
    private T data;
    private int level;

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode addChild(TreeNode<T> treeNode) {
        childs.add(treeNode);
        return this;
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

    public List<TreeNode<T>> getExpandChilds() {
        return childs.stream().filter(treeNode -> treeNode.expand).collect(Collectors.toList());
    }

    public List<TreeNode<T>> getExpands() {
        return getExpands(level);
    }

    private List<TreeNode<T>> getExpands(int level) {
        List<TreeNode<T>> expands = new ArrayList<>();
        this.level = level;
        expands.add(this);
        getExpandChilds().stream().forEach(treeNode -> expands.addAll(treeNode.getExpands(level + 1)));
        return expands;
    }

    @Override
    public String toString() {
        return data == null ? super.toString() : data.toString();
    }
}
