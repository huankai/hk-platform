package com.hk.emi.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.validator.DictService;
import com.hk.core.cache.service.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.repository.jdbc.ChildCodeRepository;
import com.hk.emi.service.ChildCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: kevin
 * @date: 2018年1月24日下午1:44:55
 */
@Service
@CacheConfig(cacheNames = "ChildCode")
public class ChildCodeServiceImpl extends EnableJdbcCacheServiceImpl<ChildCode, String> implements ChildCodeService, DictService {

    private final ChildCodeRepository childCodeRepository;

    @Autowired
    public ChildCodeServiceImpl(ChildCodeRepository childCodeRepository) {
        this.childCodeRepository = childCodeRepository;
    }

    @Override
    protected JdbcRepository<ChildCode, String> getBaseRepository() {
        return childCodeRepository;
    }

    /**
     * 查询子字典，并忽略指定的Code
     *
     * @param baseCodeId       baseCodeId
     * @param ignoreChildCodes 忽略的编号
     * @return childCodeList
     */
    @Override
    public List<ChildCode> findByBaseCodeIgnoreChildCodes(String baseCodeId, String... ignoreChildCodes) {
        List<ChildCode> childCodeList = childCodeRepository.findByBaseCodeIdOrderByCodeValueAsc(baseCodeId);
        if (ArrayUtils.isNotEmpty(ignoreChildCodes)) {
            childCodeList = childCodeList
                    .stream()
                    .filter(item -> ArrayUtils.noContains(ignoreChildCodes, item))
                    .collect(Collectors.toList());
        }
        return childCodeList;
    }

    @Override
    public List<Byte> getDictValueListByCodeId(String codeId) {
        List<ChildCode> list = findByBaseCodeIgnoreChildCodes(codeId);
        return list.stream().map(ChildCode::getCodeValue).collect(Collectors.toList());
    }

    @Override
    public String getCodeName(String baseCodeId, byte value) {
        return childCodeRepository.findByBaseCodeIdAndCodeValue(baseCodeId, value);
    }
}
