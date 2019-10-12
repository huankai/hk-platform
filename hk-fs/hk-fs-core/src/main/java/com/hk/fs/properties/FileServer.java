package com.hk.fs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author kevin
 * @date 2018-09-13 09:55
 */
@Data
@ConfigurationProperties(prefix = "hk.fs")
public class FileServer {

    private String fileUrl;
}
