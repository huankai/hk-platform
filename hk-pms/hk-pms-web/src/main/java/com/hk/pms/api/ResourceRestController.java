package com.hk.pms.api;

import com.hk.platform.commons.web.BaseController;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.service.SysPermissionService;
import com.hk.pms.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: kevin
 * @date: 2018-08-28 16:53
 */
@RestController
@RequestMapping("/api/resource")
public class ResourceRestController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 返回用户指定 app的菜单资源
     *
     * @param appId appId
     * @return {@link ResourceTree}
     */
    @GetMapping("{appId}")
    public List<ResourceTree> myResourceTreeList(@PathVariable String appId) {
        List<SysPermission> permissionList = permissionService.getCurrentUserPermissionList(appId);
        Set<String> permissionIds = permissionList.stream().map(SysPermission::getId).collect(Collectors.toSet());
        return sysResourceService.findByPermissionIds(permissionIds);
    }
}
