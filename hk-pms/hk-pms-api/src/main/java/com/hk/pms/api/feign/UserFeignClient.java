package com.hk.pms.api.feign;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.pms.commons.dto.User;

/**
 * @author kevin
 * @date 2018-08-13 20:16
 */
@FeignClient(name = PmsService.SERVICE_NAME,path = PmsService.CONTEXT_PATH)
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
    Collection<User> saveOrUpdate(User... users);

    /**
     * @param userIds userIds
     */
    void deleteByIds(String... userIds);
}
