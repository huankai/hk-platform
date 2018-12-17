package com.hk.solr.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sjq-278
 * @date 2018-12-04 16:45
 */
@SuppressWarnings("serial")
@Data
public class App implements Serializable {

    private String id;

    private String appCode;

    private String appName;

    private LocalDateTime startDate;
}
