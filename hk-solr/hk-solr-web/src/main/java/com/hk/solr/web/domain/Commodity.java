package com.hk.solr.web.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangkai
 * @date 2018-12-2 21:32
 */
@SuppressWarnings("serial")
@Data
@SolrDocument(collection = "commodity")
public class Commodity implements Serializable {

	/**
	 * 商品id
	 */
	@Field
	private String id;

	/**
	 * 名称
	 */
	@Field
	private String name;

	/**
	 * 价格
	 */
	@Field
	private BigDecimal price;

	/**
	 * 描述
	 */
	@Field
	private String description;

	/**
	 * 店铺id
	 */
	@Field
	private String storeId;

	/**
	 * 店铺名称
	 */
	@Field
	private String storeName;

	/**
	 * 评论数
	 */
	@Field
	private Long commentCount;

	/**
	 * 商品图片
	 */
	@Field
	private List<String> images;
}
