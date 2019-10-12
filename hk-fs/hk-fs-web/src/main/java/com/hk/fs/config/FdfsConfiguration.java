package com.hk.fs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.hk.fs.handler.FastdfsFileHandler;
import com.hk.fs.properties.FileServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * FDFS Configuration
 *
 * @author kevin
 * @date 2018-09-12 17:19
 */
@Configuration
@Import(FdfsClientConfig.class)
@ConditionalOnClass(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableConfigurationProperties(FileServer.class)
public class FdfsConfiguration {

    private FileServer fileServer;

    @Autowired
    private AppendFileStorageClient fileStorageClient;

    public FdfsConfiguration(FileServer fileServer) {
        this.fileServer = fileServer;
    }

    @Bean
    public FastdfsFileHandler fastdfsFileHandler() {
        FastdfsFileHandler fileHandler = new FastdfsFileHandler(fileStorageClient);
        fileHandler.setFileServer(fileServer);
        return fileHandler;
    }

}
