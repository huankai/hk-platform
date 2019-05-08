package com.hk.solr.api.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author huangkai
 * @date 2019-4-1 15:54
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class CommodityResponse implements Serializable {

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