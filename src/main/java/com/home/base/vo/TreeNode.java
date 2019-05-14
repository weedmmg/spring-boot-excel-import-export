package com.home.base.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/6/12.
 */
@Data
public class TreeNode {
    protected long id;
    protected long parentId;

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    List<TreeNode> children = new ArrayList<TreeNode>();



    public void add(TreeNode node){
        children.add(node);
    }
}
