package com.hk.fs.service.impl;

import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.fs.entity.FileInfo;
import com.hk.fs.repository.FileInfoRepository;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-08-08 17:46
 */
@Service
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfo, String> implements FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    @Autowired
    public FileInfoServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @Override
    protected BaseRepository<FileInfo, String> getBaseRepository() {
        return fileInfoRepository;
    }
}
