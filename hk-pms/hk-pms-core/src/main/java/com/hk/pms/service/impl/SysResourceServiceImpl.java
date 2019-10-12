package com.hk.pms.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;
import com.hk.pms.mappers.SysResourceMapper;
import com.hk.pms.repository.jdbc.SysResourceRepository;
import com.hk.pms.service.SysResourceService;

/**
 * @author kevin
 * @date 2018-08-28 16:40
 */
@Service
public class SysResourceServiceImpl extends JdbcServiceImpl<SysResource, String> implements SysResourceService {

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
	protected JdbcRepository<SysResource, String> getBaseRepository() {
		return resourceRepository;
	}

	@Override
	public List<ResourceTree> findByPermissionIds(String appId, Collection<String> permissions) {
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
				item -> StringUtils.isEmpty(item.getParentId()) || StringUtils.equals(item.getId(), item.getParentId()))
				.collect(Collectors.toList());
		firstResourceList.forEach(item -> {
			ResourceTree resourceTree = toResourceTree(item);
			resourceTree.setChilds(getChildList(item.getId(), resourceList));
			result.add(resourceTree);
		});
		return result;
	}

	private List<ResourceTree> getChildList(String parentId, List<SysResource> allResourceList) {
		List<ResourceTree> childList = new ArrayList<>();
		List<SysResource> childTreeList = allResourceList.stream()
				.filter(childItem -> StringUtils.notEquals(childItem.getId(), childItem.getParentId())
						&& StringUtils.equals(childItem.getParentId(), parentId))
				.collect(Collectors.toList());
		childTreeList.forEach(item -> {
			ResourceTree resourceTree = toResourceTree(item);
			resourceTree.setChilds(getChildList(item.getId(), allResourceList));
			childList.add(resourceTree);
		});
		return childList;
	}

	private ResourceTree toResourceTree(SysResource resource) {
		ResourceTree resourceTree = ResourceTree.builder().icon(resource.getIcon())
				.resourceType(resource.getResourceType()).resourceUri(resource.getResourceUri())
				.target(resource.getTarget()).build();
		resourceTree.setId(resource.getId());
		resourceTree.setName(resource.getResourceName());
		return resourceTree;
	}

	@Override
	public SysResource insert(SysResource t) {
		return insert(t, item -> {
			if(Objects.isNull(item.getState())) {
				item.setState(ByteConstants.ONE);
			}
			return item;
		});
	}

}
