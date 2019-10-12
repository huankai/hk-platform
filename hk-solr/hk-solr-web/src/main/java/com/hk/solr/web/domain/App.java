package com.hk.solr.web.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.time.*;

/**
 * @author sjq-278
 * @date 2018-12-03 11:43
 */
@SuppressWarnings("serial")
@Data
@SolrDocument(collection = "mysolr")
public class App implements Serializable {

    private String id;

    private String appCode;

    private String appName;

    @Field(value = "start_date")
    private LocalDateTime startDate;

}
