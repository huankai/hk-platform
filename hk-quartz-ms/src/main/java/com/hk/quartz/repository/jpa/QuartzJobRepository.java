package com.hk.quartz.repository.jpa;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.quartz.entity.QuartzJob;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author kevin
 * @date 2019-8-16 15:22
 */
public interface QuartzJobRepository extends BaseJpaRepository<QuartzJob, Long> {

    @Modifying
    @Query(value = "UPDATE QuartzJob SET state = ?2 WHERE id = ?1")
    int updateState(Long id, Byte state);

    Optional<QuartzJob> findByBeanName(String beanName);
}
