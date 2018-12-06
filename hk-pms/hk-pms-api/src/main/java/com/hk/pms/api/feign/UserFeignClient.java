package com.hk.pms.api.feign;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.pms.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-13 20:16
 */
@FeignClient("HK-PMS")
@RequestMapping("/api/user")
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
    @GetMapping("{id}")
    Optional<User> findByUserId(@PathVariable("id") String userId);

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
    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") String userId);

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
