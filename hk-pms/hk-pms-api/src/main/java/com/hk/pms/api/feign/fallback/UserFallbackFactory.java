package com.hk.pms.api.feign.fallback;

import com.hk.core.page.QueryPage;
import com.hk.core.page.SimpleQueryPage;
import com.hk.core.query.QueryModel;
import com.hk.pms.api.feign.UserFeignClient;
import com.hk.pms.api.request.UserRequest;
import com.hk.pms.api.response.UserResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author huangkai
 * @date 2019-4-1 15:34
 */
@Component
public class UserFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public QueryPage<UserResponse> findUserByPage(QueryModel<UserRequest> query) {
                return new SimpleQueryPage<>();
            }

            @Override
            public List<UserResponse> findAll(UserRequest user) {
                return Collections.emptyList();
            }

            @Override
            public Optional<UserResponse> findByUserId(Long userId) {
                return Optional.empty();
            }

            @Override
            public Optional<UserResponse> findByAccount(String account) {
                return Optional.empty();
            }

            @Override
            public Optional<UserResponse> findByPhone(String phone) {
                return Optional.empty();
            }

            @Override
            public Optional<UserResponse> findByEmail(String email) {
                return Optional.empty();
            }

            @Override
            public Collection<UserResponse> saveOrUpdate(UserRequest... users) {
                return Collections.emptyList();
            }

            @Override
            public void deleteByIds(Long... userIds) {

            }
        };
    }
}
