package com.hk.solr.api.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author huangkai
 * @date 2019-4-1 15:53
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class AppRequest implements Serializable {

    private String id;

    private String appCode;

    private String appName;
}
