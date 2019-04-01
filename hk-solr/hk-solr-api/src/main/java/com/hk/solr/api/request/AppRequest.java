package com.hk.solr.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author huangkai
 * @date 2019-4-1 15:53
 */
@Getter
@Setter
public class AppRequest implements Serializable {

    private String id;

    private String appCode;

    private String appName;
}
