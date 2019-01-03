package com.hk.emi.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.emi.domain.BaseCode;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018年1月24日下午1:46:10
 */
public interface BaseCodeService extends JdbcBaseService<BaseCode, String> {

    Optional<BaseCode> findByBaseCode(String baseCode);

}
