package com.hk.pms.api.feign;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.pms.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-08-13 20:16
 */
@FeignClient("HK-PMS")
public interface UserFeignClient {

    /**
     * @param query query
     * @return QueryPage<User>
     */
    QueryPage<User> findUserByPage(QueryModel<User> query);

    /**
     * @param user user
     * @return list
     */
    List<User> findAll(User user);

    /**
     * @param userId userId
     * @return userId
     */
    Optional<User> findByUserId(String userId);

    /**
     * @param account account
     * @return account
     */
    Optional<User> findByAccount(String account);

    /**
     * @param phone phone
     * @return user
     */
    Optional<User> findByPhone(String phone);

    /**
     * @param email email
     * @return user
     */
    Optional<User> findByEmail(String email);

    /**
     * @param entities entities
     */
    Collection<User> batchSaveOrUpdate(Collection<User> entities);

    /**
     * @param userId userId
     */
    void deleteById(String userId);

    /**
     * @param userIds userIds
     */
    void deleteByIds(Collection<String> userIds);

    /**
     * @param user user
     * @return user
     */
    User saveOrUpdate(User user);
}
