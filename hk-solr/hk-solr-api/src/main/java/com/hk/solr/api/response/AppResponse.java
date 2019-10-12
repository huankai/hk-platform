package com.hk.solr.api.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author huangkai
 * @date 2019-4-1 15:53
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class AppResponse implements Serializable {

    private String id;

    private String appCode;

    private String appName;

    private LocalDateTime startDate;
}
