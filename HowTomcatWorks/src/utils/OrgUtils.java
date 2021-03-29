package com.atguigu.utils;

import com.google.common.collect.Maps;
import kd.bos.context.RequestContext;
import kd.bos.entity.tree.TreeNode;
import kd.bos.org.model.OrgTreeBuildType;
import kd.bos.org.model.OrgTreeParam;
import kd.bos.org.service.OrgService;
import kd.bos.servicehelper.org.OrgServiceHelper;
import kd.bos.servicehelper.org.OrgUnitServiceHelper;
import kd.bos.servicehelper.org.OrgViewType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author RD_kai_cao
 * @Description
 * @Date 2020/12/7 15:39
 */
public class OrgUtils {

    public static Map<String, String> getOrgIdNameMap() {

        Map<String, String> orgMap = Maps.newHashMap();

        TreeNode node = getOrgTreeRootNode();

        if (node != null){

            Queue<TreeNode> list = TreeUtil.getQueue(node);

            for (TreeNode e : list) {

                orgMap.put(e.getId(), e.getText());
            }
        }

        return orgMap;
    }

    /**
     * 构造组织树
     */
    public static TreeNode getOrgTreeRootNode() {

        OrgTreeParam param = new OrgTreeParam();

        param.setId(RequestContext.get().getOrgId());

        param.setTreeBuildType(OrgTreeBuildType.FILL_HIDDEN_PARENT_ALL);

        param.setOrgViewType(OrgViewType.Produce);

        TreeNode rootNode = OrgUnitServiceHelper.getTreeRootNodeById(param);

        List<TreeNode> childrenList = OrgUnitServiceHelper.getTreeChildren(param);

        if (childrenList.size() > 0) {

            rootNode.setChildren(childrenList);
        }

        return rootNode;
    }

    /**
     * 寻找组织树节点的下级组织和添加节点
     *
     * @param treeChildren
     */
    private static void searcheAndAddTreeChildren(List<TreeNode> treeChildren) {

        for (TreeNode treeNode : treeChildren) {

            List<TreeNode> children = getTreeChildren(treeNode);

            if (children.size() == 0) {
                continue;
            }

            treeNode.setChildren(children);

            searcheAndAddTreeChildren(children);
        }
    }

    /**
     * 寻找组织树节点的下级组织
     *
     * @param treeNode
     * @return
     */
    private static List<TreeNode> getTreeChildren(TreeNode treeNode) {

        String id = treeNode.getId();

        OrgTreeParam orgTreeParam = new OrgTreeParam();

        orgTreeParam.setId(Long.valueOf(id));

        return OrgUnitServiceHelper.getTreeChildren(orgTreeParam);
    }
}
