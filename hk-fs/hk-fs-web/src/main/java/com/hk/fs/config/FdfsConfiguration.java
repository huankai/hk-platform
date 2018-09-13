package com.hk.fs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * FDFS Configuration
 *
 * @author: sjq-278
 * @date: 2018-09-12 17:19
 */
@Configuration
@Import(FdfsClientConfig.class)
@ConditionalOnClass(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableConfigurationProperties(FileServer.class)
public class FdfsConfiguration {


}