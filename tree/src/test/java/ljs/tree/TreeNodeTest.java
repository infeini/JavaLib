package ljs.tree;

import org.junit.Test;

import java.util.List;

public class TreeNodeTest {
    @Test
    public void getExpandsTest() {
        TreeNode rootNode = new TreeNode("1");
        rootNode.addChild(new TreeNode("1-1").addChild(new TreeNode("1-1-1")));
        rootNode.addChild(new TreeNode("1-2").addChild(new TreeNode("1-2-1")));
        List<TreeNode> expands = rootNode.getExpands();
        System.out.println(expands);
    }
}
