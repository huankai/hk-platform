package com.hk.fs.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.fs.domain.FileShare;
import com.hk.fs.repository.jpa.FileShareRepository;
import com.hk.fs.service.FileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-08-08 17:46
 */
@Service
public class FileShareServiceImpl extends JpaServiceImpl<FileShare, String> implements FileShareService {

    private final FileShareRepository fileShareRepository;

    @Autowired
    public FileShareServiceImpl(FileShareRepository fileShareRepository) {
        this.fileShareRepository = fileShareRepository;
    }

    @Override
    protected BaseJpaRepository<FileShare, String> getBaseRepository() {
        return fileShareRepository;
    }
}
