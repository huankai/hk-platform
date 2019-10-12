package com.hk.fs.repository.jpa;


import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.fs.domain.FileInfo;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-08 17:45
 */
public interface FileInfoRepository extends StringIdJpaRepository<FileInfo> {

    List<FileInfo> findByDigest(String digest);
}
