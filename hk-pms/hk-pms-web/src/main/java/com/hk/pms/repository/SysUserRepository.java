package com.hk.pms.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUser;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRepository extends StringRepository<SysUser> {

    /**
     * 手机号或邮箱号查询用户
     *
     * @param username 手机号或者邮箱号
     * @return 查询结果
     */
    @Query(value = "SELECT id,org_id,dept_id,phone,password,email,real_name,user_type,is_protect,sex,icon_path,birth,privince_id,city_id,user_status,created_by,created_date,last_modified_by,last_modified_date FROM sys_user WHERE (phone = ?1 OR email = ?1)", nativeQuery = true)
    SysUser findByUserName(String username);
}
