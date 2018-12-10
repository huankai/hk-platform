package com.hk.solr.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangkai
 * @date 2018-12-2 23:17
 */
@SuppressWarnings("serial")
@Data
public class Commodity implements Serializable {

    /**
     * 商品id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 描述
     */
    private String description;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 商品图片
     */
    private List<String> images;
}
