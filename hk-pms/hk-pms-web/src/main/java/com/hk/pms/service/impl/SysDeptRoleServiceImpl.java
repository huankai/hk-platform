package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysDeptRole;
import com.hk.pms.repository.SysDeptRoleRepository;
import com.hk.pms.service.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 16:48
 */
@Service
public class SysDeptRoleServiceImpl extends BaseServiceImpl<SysDeptRole, String> implements SysDeptRoleService {

    private final SysDeptRoleRepository sysDeptRoleRepository;

    @Autowired
    public SysDeptRoleServiceImpl(SysDeptRoleRepository sysDeptRoleRepository) {
        this.sysDeptRoleRepository = sysDeptRoleRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysDeptRole, String> getBaseRepository() {
        return sysDeptRoleRepository;
    }

    @Override
    public void deleteByDeptIdAndRoleId(String deptId, String roleId) {
        sysDeptRoleRepository.deleteByDeptIdAndRoleId(deptId, roleId);
    }
}
