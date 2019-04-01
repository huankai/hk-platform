package com.hk.solr.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author huangkai
 * @date 2019-4-1 15:54
 */
@Getter
@Setter
public class CommodityRequest implements Serializable {

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


}
