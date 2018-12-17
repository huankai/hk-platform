package com.hk.pms.service.impl;


import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysUserThird;
import com.hk.pms.repository.jdbc.SysUserThirdRepository;
import com.hk.pms.service.SysUserThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-04-12 17:03
 */
@Service
public class SysUserThirdServiceImpl extends JdbcServiceImpl<SysUserThird, String> implements SysUserThirdService {

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
    protected JdbcRepository<SysUserThird, String> getBaseRepository() {
        return sysUserThirdRepository;
    }

//    @Override
//    protected ExampleMatcher ofExampleMatcher() {
//        return super.ofExampleMatcher()
//                .withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("accountType", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("userThirdName", ExampleMatcher.GenericPropertyMatchers.contains());
//    }


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
