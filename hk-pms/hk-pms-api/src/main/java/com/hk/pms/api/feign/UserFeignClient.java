package com.hk.pms.api.feign;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.pms.api.request.UserRequest;
import com.hk.pms.api.response.UserResponse;
import com.hk.pms.api.feign.fallback.UserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author kevin
 * @date 2018-08-13 20:16
 */
@FeignClient(name = PmsService.SERVICE_NAME,
        path = PmsService.CONTEXT_PATH,
        fallbackFactory = UserFallbackFactory.class)
@RequestMapping("/api/user")
public interface UserFeignClient {

    /**
     * @param query query
     * @return QueryPage<User>
     */
    QueryPage<UserResponse> findUserByPage(QueryModel<UserRequest> query);

    /**
     * @param user user
     * @return list
     */
    List<UserResponse> findAll(UserRequest user);

    /**
     * @param userId userId
     * @return userId
     */
    @GetMapping("{id}")
    Optional<UserResponse> findByUserId(@PathVariable("id") Long userId);

    /**
     * @param account account
     * @return account
     */
    Optional<UserResponse> findByAccount(String account);

    /**
     * @param phone phone
     * @return user
     */
    Optional<UserResponse> findByPhone(String phone);

    /**
     * @param email email
     * @return user
     */
    Optional<UserResponse> findByEmail(String email);

    /**
     * @param users entities
     */
    Collection<UserResponse> saveOrUpdate(UserRequest... users);

    /**
     * @param userIds userIds
     */
    void deleteByIds(Long... userIds);
}
