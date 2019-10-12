package com.hk.emi.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.hk.core.cache.service.impl.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.repository.jdbc.BaseCodeRepository;
import com.hk.emi.service.BaseCodeService;

/**
 * @author kevin
 * @date 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = { "BaseCode" })
public class BaseCodeServiceImpl extends EnableJdbcCacheServiceImpl<BaseCode, String> implements BaseCodeService {

	private final BaseCodeRepository baseCodeRepository;

	@Autowired
	public BaseCodeServiceImpl(BaseCodeRepository baseCodeRepository) {
		this.baseCodeRepository = baseCodeRepository;
	}

	@Override
	protected JdbcRepository<BaseCode, String> getBaseRepository() {
		return baseCodeRepository;
	}

	public Optional<BaseCode> findByBaseCode(String baseCode) {
		return baseCodeRepository.findByBaseCode(baseCode);
	}

	@Override
	public BaseCode insert(BaseCode t) {
		return insert(t, item -> {
			if (Objects.isNull(item.getIsGb())) {
				item.setIsGb(Boolean.FALSE);
			}
			return item;
		});
	}
}
