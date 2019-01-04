package com.hk.pms.service;


import com.hk.commons.util.AssertUtils;
import com.hk.core.data.jdbc.query.CompositeCondition;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.domain.SysUserThird;

import java.util.List;

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

    /**
     * 用户是否有绑定此类型的账号
     *
     * @param userId      用户id
     * @param accountType 第三方账号类型，查看数据字典
     */
    boolean existsByUserIdAndAccountType(String userId, byte accountType);

    /**
     * 查询用户所有 绑定的账号
     *
     * @param userId userId
     * @return {@link SysUserThird}
     */
    default List<SysUserThird> findByUserId(String userId) {
        AssertUtils.notEmpty(userId, "userId");
        return findAll(new CompositeCondition(new SimpleCondition("user_id", userId))).getResult();
    }
}
