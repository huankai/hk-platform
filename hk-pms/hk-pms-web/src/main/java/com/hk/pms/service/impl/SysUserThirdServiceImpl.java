package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysUserThird;
import com.hk.pms.repository.SysUserThirdRepository;
import com.hk.pms.service.SysUserThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 17:03
 */
@Service
public class SysUserThirdServiceImpl extends BaseServiceImpl<SysUserThird, String> implements SysUserThirdService {

    private final SysUserThirdRepository sysUserThirdRepository;

    @Autowired
    public SysUserThirdServiceImpl(SysUserThirdRepository sysUserThirdRepository) {
        this.sysUserThirdRepository = sysUserThirdRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return sysUserThirdRepository
     */
    @Override
    protected BaseRepository<SysUserThird, String> getBaseRepository() {
        return sysUserThirdRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("accountType", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("userThirdName", ExampleMatcher.GenericPropertyMatchers.contains());
    }


    @Override
    public void bindUser(SysUserThird sysUserThird) {
        if (!exists(sysUserThird)) {
            insert(sysUserThird);
        }
    }

    @Override
    public void cancelBindUser(String userId, byte bindAccountType) {
        sysUserThirdRepository.deleteByUserIdAndAccountType(userId, bindAccountType);
    }
}
