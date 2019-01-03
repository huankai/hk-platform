package com.hk.pms.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.platform.commons.tree.TreeNode;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;
import com.hk.pms.mappers.SysResourceMapper;
import com.hk.pms.repository.jdbc.SysResourceRepository;
import com.hk.pms.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-08-28 16:40
 */
@Service
public class SysResourceServiceImpl extends JdbcServiceImpl<SysResource, String> implements SysResourceService {

    private SysResourceRepository sysResourceRepository;

    private SysResourceMapper resourceMapper;

    @Autowired
    public SysResourceServiceImpl(SysResourceRepository sysResourceRepository) {
        this.sysResourceRepository = sysResourceRepository;
    }

    @Autowired
    public void setResourceMapper(SysResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    protected JdbcRepository<SysResource, String> getBaseRepository() {
        return sysResourceRepository;
    }

    @Override
    public List<ResourceTree> findByPermissionIds(Collection<String> permissions) {
        if (CollectionUtils.isEmpty(permissions) && !getPrincipal().isAdministrator()) {
            return Collections.emptyList();
        }
        List<SysResource> allResourceList = resourceMapper.findByPermissionIds(permissions);
        List<ResourceTree> result = new ArrayList<>();
        List<SysResource> firstResourceList = allResourceList
                .stream()
                .filter(item -> StringUtils.isEmpty(item.getParentId()) || StringUtils.equals(item.getId(), item.getParentId()))
                .collect(Collectors.toList());
        firstResourceList.forEach(item -> {
            ResourceTree resourceTree = toResourceTree(item);
            resourceTree.setChildList(getChildList(item.getId(), allResourceList));
            result.add(resourceTree);
        });
        return result;
    }

//    @Override
//    protected SysResource saveBefore(SysResource entity) throws ServiceException {
//        entity.setState(ByteConstants.ONE);
//        return super.saveBefore(entity);
//    }

    private List<? extends TreeNode> getChildList(String parentId, List<SysResource> allResourceList) {
        List<ResourceTree> childList = new ArrayList<>();
        List<SysResource> childTreeList = allResourceList
                .stream()
                .filter(childItem -> StringUtils.notEquals(childItem.getId(), childItem.getParentId()) && StringUtils.equals(childItem.getParentId(), parentId))
                .collect(Collectors.toList());
        childTreeList.forEach(item -> {
            ResourceTree resourceTree = toResourceTree(item);
            resourceTree.setChildList(getChildList(item.getId(), allResourceList));
            childList.add(resourceTree);
        });
        return childList;
    }

    private ResourceTree toResourceTree(SysResource resource) {
        ResourceTree resourceTree = ResourceTree
                .builder()
                .icon(resource.getIcon())
                .resourceType(resource.getResourceType())
                .resourceUri(resource.getResourceUri())
                .target(resource.getTarget())
                .build();
        resourceTree.setId(resource.getId());
        resourceTree.setName(resource.getResourceName());
        return resourceTree;

    }

}
