package com.hk.emi.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.core.cache.service.EnableCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.repository.ChildCodeRepostory;
import com.hk.emi.service.ChildCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: kevin
 * @date 2018年1月24日下午1:44:55
 */
@Service
@CacheConfig(cacheNames = "ChildCode")
public class ChildCodeServiceImpl extends EnableCacheServiceImpl<ChildCode, String> implements ChildCodeService {

    private final ChildCodeRepostory childCodeRepostory;

    @Autowired
    public ChildCodeServiceImpl(ChildCodeRepostory childCodeRepostory) {
        this.childCodeRepostory = childCodeRepostory;
    }

    @Override
    protected BaseRepository<ChildCode, String> getBaseRepository() {
        return childCodeRepostory;
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
        List<ChildCode> childCodeList = childCodeRepostory.findByBaseCodeIdOrderByChildCodeAsc(baseCodeId);
        if (ArrayUtils.isNotEmpty(ignoreChildCodes)) {
            childCodeList = childCodeList
                    .stream()
                    .filter(item -> ArrayUtils.noContains(ignoreChildCodes, item))
                    .collect(Collectors.toList());
        }
        return childCodeList;
    }
}
