package com.hk.pms.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;
import com.hk.pms.mappers.SysResourceMapper;
import com.hk.pms.repository.jpa.SysResourceRepository;
import com.hk.pms.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-08-28 16:40
 */
@Service
public class SysResourceServiceImpl extends JpaServiceImpl<SysResource, Long> implements SysResourceService {

    private final SysResourceRepository resourceRepository;

    private SysResourceMapper resourceMapper;

    @Autowired
    public SysResourceServiceImpl(SysResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Autowired
    public void setResourceMapper(SysResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    protected BaseJpaRepository<SysResource, Long> getBaseRepository() {
        return resourceRepository;
    }

    @Override
    public List<ResourceTree> findByPermissionIds(Long appId, Collection<Long> permissions) {
        if (getPrincipal().isAdministrator()) {
            List<SysResource> appResourceList = resourceRepository.findByAppIdOrderByOrderedAsc(appId);
            return toResourceTrees(appResourceList);
        }
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptyList();
        }
        List<SysResource> permissionResourceList = resourceMapper.findByPermissionIds(permissions);
        return toResourceTrees(permissionResourceList);
    }

    private List<ResourceTree> toResourceTrees(List<SysResource> resourceList) {
        List<ResourceTree> result = new ArrayList<>();
        List<SysResource> firstResourceList = resourceList.stream().filter(
                item -> StringUtils.isEmpty(item.getParentId()) || Objects.equals(item.getId(), item.getParentId()))
                .collect(Collectors.toList());
        firstResourceList.forEach(item -> {
            ResourceTree resourceTree = toResourceTree(item);
//            resourceTree.setChilds(getChildList(item.getId(), resourceList));
            result.add(resourceTree);
        });
        return result;
    }

    private List<ResourceTree> getChildList(Long parentId, List<SysResource> allResourceList) {
        List<ResourceTree> childList = new ArrayList<>();
        List<SysResource> childTreeList = allResourceList.stream()
                .filter(childItem -> !Objects.equals(childItem.getId(), childItem.getParentId())
                        && Objects.equals(childItem.getParentId(), parentId))
                .collect(Collectors.toList());
        childTreeList.forEach(item -> {
            ResourceTree resourceTree = toResourceTree(item);
//            resourceTree.setChilds(getChildList(item.getId(), allResourceList));
            childList.add(resourceTree);
        });
        return childList;
    }

    private ResourceTree toResourceTree(SysResource resource) {
        ResourceTree resourceTree = ResourceTree.builder().icon(resource.getIcon())
                .resourceType(resource.getResourceType()).resourceUri(resource.getResourceUri())
                .target(resource.getTarget()).build();
//        resourceTree.setId(resource.getId());
//        resourceTree.setName(resource.getResourceName());
        return resourceTree;
    }

    @Override
    public SysResource insert(SysResource t) {
        return insert(t, item -> {
            if (Objects.isNull(item.getState())) {
                item.setState(ByteConstants.ONE);
            }
            return item;
        });
    }

}
