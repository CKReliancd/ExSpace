package atguigu.utils;

import com.google.common.collect.Lists;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.tree.TreeNode;
import kd.dfc.dfcbkl.common.bean.Recorder;
import kd.dfc.dfcbkl.common.constant.Bom;
import kd.dfc.dfcbkl.common.constant.DfcbklConstant;
import kd.dfc.dfcbkl.common.constant.Material;

import java.util.*;

/**
 * @author rd_kai_cao
 * @Description
 * @Date 2020/10/27 17:01
 */
public class TreeUtil {

    /**
     * 获取当前树的遍历队列(广度优先)
     *
     * @param root
     * @return
     */
    public static Queue<TreeNode> getQueue(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        Queue<TreeNode> result = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            result.add(node);

            List<TreeNode> children = node.getChildren() == null ?
                    Lists.newArrayList() : node.getChildren();

            for (TreeNode child : children) {
                queue.add(child);
            }
        }
        return result;
    }

    /**
     * 获取当前树的遍历队列(深度优先)
     *
     * @param root
     * @return
     */
    public static List<TreeNode> getNodeList(TreeNode root) {

        Recorder<List<TreeNode>> r = new Recorder<>(new ArrayList<>());

        r.getRecord().add(root);

        if (root.getChildren() != null) {

            addChildren2List(root, r);
        }

        return r.getRecord();
    }

    private static void addChildren2List(TreeNode node, Recorder<List<TreeNode>> r) {

        node.getChildren().forEach(t -> {

            r.getRecord().add(node);

            if (t.getChildren() != null) {

                addChildren2List(t, r);

            } else if (t != null) {

                r.getRecord().add(t);
            }
        });
    }

    /**
     * 树形基础资料节点映射成TreeNode
     *
     * @param dynamicObject
     * @return
     */
    public static TreeNode createNode(DynamicObject dynamicObject) {

        TreeNode treeNode = new TreeNode();

        String id = dynamicObject.getString(DfcbklConstant.ID);

        treeNode.setId(id);

        String name = dynamicObject.getLocaleString(DfcbklConstant.NAME).getLocaleValue_zh_CN();

        treeNode.setText(name);

        DynamicObject parent = dynamicObject.getDynamicObject(DfcbklConstant.PARENT);

        String parentId = parent == null ? "" : parent.getString(DfcbklConstant.ID);

        treeNode.setParentid(parentId);

        treeNode.setData(dynamicObject);

        treeNode.setChildren(new ArrayList<>());

        return treeNode;
    }

    /**
     * Map转TreeNode
     *
     * @param map
     * @return
     */
    public static TreeNode changeMapToTreeNode(Map<String, String> map) {

        if (map.isEmpty()) {
            return null;
        } else {
            TreeNode node = new TreeNode();
            node.setId(map.get("id"));
            node.setText(map.get("name"));
            node.setParentid(map.get("parentid"));
            String isleaf = map.get("isleaf");
            if (isleaf == null) {
                isleaf = "0";
            }

            if ("0".equals(isleaf)) {
                node.setChildren(new ArrayList());
            }

            return node;
        }
    }


}
