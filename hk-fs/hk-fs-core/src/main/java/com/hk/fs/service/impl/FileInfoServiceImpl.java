package com.hk.fs.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.FileUtils;
import com.hk.commons.util.IDGenerator;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.fs.domain.FileInfo;
import com.hk.fs.handler.FileHandler;
import com.hk.fs.repository.jpa.FileInfoRepository;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Fastdfs 文件处理
 *
 * @author kevin
 * @date 2018-08-08 17:46
 */
@Service
public class FileInfoServiceImpl extends JpaServiceImpl<FileInfo, String> implements FileInfoService {

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Override
    protected BaseJpaRepository<FileInfo, String> getBaseRepository() {
        return fileInfoRepository;
    }

    @Override
    public List<FileInfo> uploadFile(String group, MultiValueMap<String, MultipartFile> multiFileMap) throws IOException {
        List<FileInfo> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(multiFileMap)) {
            LocalDateTime uploadDate = LocalDateTime.now();
            List<FileInfo> saveList = new ArrayList<>();
            FileInfo fileInfo;
            for (Map.Entry<String, MultipartFile> entry : multiFileMap.toSingleValueMap().entrySet()) {
                MultipartFile file = entry.getValue();
                if (file != null && !file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
                    String ext = FileUtils.getExtension(originalFilename);
                    FileSizeUnit sizeUnit = FileInfoService.convertFileSize(file.getSize());
                    String path = fileHandler.upload(group, file.getInputStream(), file.getSize(), ext);
                    fileInfo = new FileInfo();
                    fileInfo.setExtension(ext);
                    fileInfo.setFileName(originalFilename);
                    fileInfo.setGroupName(group);
                    fileInfo.setFileSize(sizeUnit.getFileSize());
                    fileInfo.setUnit(sizeUnit.getUnit());
                    fileInfo.setUploadDate(uploadDate);
                    fileInfo.setFilePath(path);
                    fileInfo.setUserId(getPrincipal().getUserId());
                    fileInfo.setDigest(IDGenerator.STRING_UUID.generate()); //先随机生成
                    saveList.add(fileInfo);
                }
            }
            CollectionUtils.addAll(result, CollectionUtils.toCollection(batchInsert(saveList)));
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("上传空文件");
            }
        }
        return result;
    }

    @Override
    public String getFullPath(String groupName, String path) {
        return fileHandler.getFullPath(groupName, path);
    }
}
