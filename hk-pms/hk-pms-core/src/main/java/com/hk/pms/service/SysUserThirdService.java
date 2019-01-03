package com.hk.pms.service;


import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.domain.SysUserThird;

/**
 * @author kevin
 * @date 2018-04-12 17:03
 */
public interface SysUserThirdService extends JdbcBaseService<SysUserThird, String> {

    /**
     * 绑定用户
     *
     * @param sysUserThird sysUserThird
     */
    void bindUser(SysUserThird sysUserThird);

    /**
     * 解除绑定的用户
     *
     * @param userId          userId
     * @param bindAccountType bindAccountType(如：微信，QQ等)
     */
    void cancelBindUser(String userId, byte bindAccountType);
}
