package com.hk.solr.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author huangkai
 * @date 2019-4-1 15:53
 */
@Getter
@Setter
public class AppResponse implements Serializable {

    private String id;

    private String appCode;

    private String appName;

    private LocalDateTime startDate;
}
