package com.hk.fs.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.fs.domain.FileInfo;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-08-08 17:45
 */
public interface FileInfoRepository extends StringRepository<FileInfo> {

    List<FileInfo> findByDigest(String digest);
}
