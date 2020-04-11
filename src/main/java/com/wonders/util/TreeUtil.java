package com.wonders.util;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 09:46 2020-03-08
 */
public class TreeUtil<T extends TreeUtil.TreeNode> {

    private IRootCondition rootCondition;

    public TreeUtil(IRootCondition rootCondition) {
        this.rootCondition = rootCondition;
    }

    public <T extends TreeUtil.TreeNode> List<T> bulidTree(List<T> list) {
        List<T> tree = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (T node : list) {
                if (rootCondition.isRootNode(node)) {
                    tree.add(node);
                    buid(list, node);
                }
            }
        }
        return tree;
    }

    public <T extends TreeUtil.TreeNode> void  buid(List<T> list, T node) {
        List<T> children = getChildrens(list, node);

        if (!CollectionUtils.isEmpty(children)) {
            node.setChildren(children);
            for (T child : children) {
                buid(list, child);
            }
        }
    }

    public <T extends TreeUtil.TreeNode> List<T> getChildrens(List<T> list,T node) {
        List<T> children = new ArrayList<>();
        for (T child : list) {
            if (node.getId().equals(child.getPid())) {
                children.add(child);
            }
        }
        return children;
    }

    @Data
    @Accessors(chain = true)
    public static class TreeNode<T>{
        @TableField(exist = false)
        protected String id;
        @TableField(exist = false)
        protected String pid;
        @TableField(exist = false)
        protected String name;
        @TableField(exist = false)
        private List<T> children;
    }

    public interface IRootCondition<T extends TreeUtil.TreeNode>{
        default boolean isRootNode(T node){
            return node.getPid() == null;
        }
    }

}
