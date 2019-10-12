package com.hk.fs.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.FileUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jpa.repository.JpaBaseRepository;
import com.hk.core.exception.ServiceException;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.fs.domain.FileInfo;
import com.hk.fs.repository.jpa.FileInfoRepository;
import com.hk.fs.service.FileInfoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-08 17:46
 */
@Service
public class FileInfoServiceImpl extends JpaServiceImpl<FileInfo, String> implements FileInfoService {

    private static final String DEFAULT_GROUP = "group1";

    private final FileInfoRepository fileInfoRepository;

    private final AppendFileStorageClient appendFileStorageClient;

    @Autowired
    public FileInfoServiceImpl(FileInfoRepository fileInfoRepository,
                               AppendFileStorageClient appendFileStorageClient) {
        this.fileInfoRepository = fileInfoRepository;
        this.appendFileStorageClient = appendFileStorageClient;
    }

    @Override
    protected JpaBaseRepository<FileInfo, String> getBaseRepository() {
        return fileInfoRepository;
    }

    @Override
    public FileInfo uploadFile(String group, InputStream in, long fileSize, String fileName) {
        if (StringUtils.isEmpty(group)) {
            group = DEFAULT_GROUP;
        }
        try {
            byte[] bytes = IOUtils.toByteArray(in);
            String digest = DigestUtils.sha256Hex(bytes);
            List<FileInfo> digestFileList = findByDigest(digest);

            FileInfo fileInfo = new FileInfo();
            String ext = FileUtils.getExtension(fileName);
            fileInfo.setExtension(ext);
            fileInfo.setFileName(fileName);
            fileInfo.setGroupName(group);
            FileSizeUnit sizeUnit = FileInfoService.convertFileSize(fileSize);
            fileInfo.setFileSize(sizeUnit.getFileSize());
            fileInfo.setUnit(sizeUnit.getUnit());
            fileInfo.setUploadDate(LocalDateTime.now());
            fileInfo.setUserId(getPrincipal().getUserId());
            fileInfo.setDigest(digest);

            if (CollectionUtils.isNotEmpty(digestFileList)) {
                fileInfo.setFilePath(digestFileList.get(0).getFilePath());
            } else {
                StorePath storePath = appendFileStorageClient.uploadAppenderFile(group, new ByteArrayInputStream(bytes), fileSize, ext);
                fileInfo.setFilePath(storePath.getPath());
            }
            return insert(fileInfo);
        } catch (IOException e) {
            throw new ServiceException("文件上传失败！");
        }
    }

    private List<FileInfo> findByDigest(String digest) {
        return fileInfoRepository.findByDigest(digest);
    }
}
