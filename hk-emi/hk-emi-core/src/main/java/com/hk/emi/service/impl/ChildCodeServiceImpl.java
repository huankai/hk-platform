package com.hk.emi.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.validator.DictService;
import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.exception.ServiceException;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.repository.jpa.ChildCodeRepository;
import com.hk.emi.service.ChildCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018年1月24日下午1:44:55
 */
@Service
@CacheConfig(cacheNames = "ChildCode")
public class ChildCodeServiceImpl extends EnableJpaCacheServiceImpl<ChildCode, Long> implements ChildCodeService, DictService {

    private final ChildCodeRepository childCodeRepository;

    @Autowired
    public ChildCodeServiceImpl(ChildCodeRepository childCodeRepository) {
        this.childCodeRepository = childCodeRepository;
    }

    @Override
    protected BaseJpaRepository<ChildCode, Long> getBaseRepository() {
        return childCodeRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("childCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("codeName", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public long countByBaseCodeId(Long baseCodeId) {
        return childCodeRepository.countByBaseCodeId(baseCodeId);
    }

    /**
     * 查询子字典，并忽略指定的Code
     *
     * @param baseCodeId       baseCodeId
     * @param ignoreChildCodes 忽略的编号
     * @return childCodeList
     */
    @Override
    public List<ChildCode> findByBaseCodeIgnoreChildCodes(Long baseCodeId, String... ignoreChildCodes) {
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
    public List<Byte> getDictValueListByCodeId(Long codeId) {
        List<ChildCode> list = findByBaseCodeIgnoreChildCodes(codeId);
        return list.stream().map(ChildCode::getCodeValue).collect(Collectors.toList());
    }

    @Override
    public ChildCode updateByIdSelective(ChildCode childCode) {
        childCodeRepository.findByBaseCodeIdAndCodeValue(childCode.getBaseCodeId(), childCode.getCodeValue()).ifPresent(item -> {
            if (!item.getId().equals(childCode.getId())) {
                throw new ServiceException("当前子级已存在[" + item.getCodeName() + "]的值为:" + item.getCodeValue());
            }
        });
        return super.updateByIdSelective(childCode);
    }

    @Override
    public String getCodeName(Long baseCodeId, Number value) {
        return childCodeRepository.findByBaseCodeIdAndCodeValue(baseCodeId, value).map(ChildCode::getCodeName).orElse(null);
    }

    @Override
    public ChildCode insert(ChildCode childCode) {
        return insert(childCode, item -> {
            if (Objects.isNull(item.getState())) {
                item.setState(true);
            }
            if (Objects.isNull(item.getIsGb())) {
                item.setIsGb(Boolean.FALSE);
            }
            return item;
        });
    }
}
