package com.hk.pms.service.impl;


import com.hk.commons.util.ByteConstants;
import com.hk.core.data.commons.dao.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysRole;
import com.hk.pms.repository.SysRoleRepository;
import com.hk.pms.service.SysRoleService;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-04-12 16:59
 */
@Service
@CacheConfig(cacheNames = {"SYS_ROLE"})
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {

    private final SysRoleRepository sysRoleRepository;

    private SysUserService userService;

    @Autowired
    public void setUserService(SysUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysRole, String> getBaseDao() {
        return sysRoleRepository;
    }

    @Override
    public List<SysRole> getRoleList(String userId, String appId) {
//        SysUser user = userService.getOne(userId);
//        Set<SysRole> deptRoleSet = user.getOrgDept().getRoleSet();
//        List<SysRole> deptRoleList = deptRoleSet.stream()
//                .filter(item -> ByteConstants.ZERO.equals(item.getRoleStatus())
//                        && StringUtils.notEquals(item.getApp().getId(), appId))
//                .collect(Collectors.toList());
//        Set<SysRole> roleSet = user.getRoleSet();
//        List<SysRole> roleList = roleSet.stream()
//                .filter(item -> ByteConstants.ZERO.equals(item.getRoleStatus())
//                        && StringUtils.notEquals(item.getApp().getId(), appId))
//                .collect(Collectors.toList());
//        roleList.addAll(deptRoleList);
//        return Lists.newArrayList(roleSet);
        // TODO :
        throw new RuntimeException("未实现...");
    }

    @Override
    public void disable(String id) {
        SysRole role = getOne(id);
        role.setRoleStatus(ByteConstants.ZERO);
        insertOrUpdate(role);
    }

    @Override
    public void enable(String id) {
        SysRole role = getOne(id);
        role.setRoleStatus(ByteConstants.ONE);
        insertOrUpdate(role);
    }

    @Override
    protected SysRole saveBefore(SysRole entity) {
        if (null == entity.getRoleStatus()) {
            entity.setRoleStatus(ByteConstants.ONE);
        }
        return super.saveBefore(entity);
    }

}
