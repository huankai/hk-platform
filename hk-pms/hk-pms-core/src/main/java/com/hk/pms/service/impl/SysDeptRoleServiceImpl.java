package com.hk.pms.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysDeptRole;
import com.hk.pms.repository.jdbc.SysDeptRoleRepository;
import com.hk.pms.service.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 17:02
 */
@Service
public class SysDeptRoleServiceImpl extends JdbcServiceImpl<SysDeptRole, String> implements SysDeptRoleService {

    private final SysDeptRoleRepository deptRoleRepository;

    @Autowired
    public SysDeptRoleServiceImpl(SysDeptRoleRepository deptRoleRepository) {
        this.deptRoleRepository = deptRoleRepository;
    }

    /**
     * 返回 BaseRepository
     */
    @Override
    protected JdbcRepository<SysDeptRole, String> getBaseRepository() {
        return deptRoleRepository;
    }

    @Override
    public void deleteByDeptIdAndRoleId(String deptId, String roleId) {
        deptRoleRepository.deleteByDeptIdAndRoleId(deptId, roleId);
    }

    /**
     * 先删除，再添加
     *
     * @param userId  deptId
     * @param roleIds roleIds
     */
    @Override
    public void updateDeptRole(String deptId, String[] roleIds) {
        AssertUtils.notEmpty(deptId, "deptId");
        deleteByDeptId(deptId);
        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<SysDeptRole> addList = new ArrayList<>();
            for (String roleId : roleIds) {
                addList.add(SysDeptRole.builder().deptId(deptId).roleId(roleId).build());
            }
            batchInsert(addList);
        }
    }

    private void deleteByDeptId(String deptId) {
        deptRoleRepository.deleteByDeptId(deptId);
    }

    /**
     * 角色添加，已关联的不会添加
     *
     * @param roleId  roleId
     * @param deptIds deptIds
     */
    @Override
    public void addRoleDept(String roleId, String[] deptIds) {
        AssertUtils.notEmpty(roleId, "roleId");
        if (ArrayUtils.isNotEmpty(deptIds)) {
            List<SysDeptRole> userRoleList = findByRoleId(roleId);
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<SysDeptRole> addList = new ArrayList<>();
                List<String> deptIdList = userRoleList.stream().map(SysDeptRole::getDeptId).collect(Collectors.toList());
                for (String deptId : deptIds) {
                    if (!deptIdList.contains(deptId)) {
                        addList.add(SysDeptRole.builder().deptId(deptId).roleId(roleId).build());
                    }
                }
                batchInsert(addList);
            }
        }
    }

    private List<SysDeptRole> findByRoleId(String roleId) {
        return deptRoleRepository.findByRoleId(roleId);
    }
}
