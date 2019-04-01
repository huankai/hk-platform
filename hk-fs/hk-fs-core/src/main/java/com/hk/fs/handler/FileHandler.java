package com.hk.fs.handler;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangkai
 * @date 2019-3-28 12:35
 */
public interface FileHandler {

    String upload(String groupName, InputStream stream, long size, String ext) throws IOException;

    void deleteByPath(String groupName, String filePath);

    String getFullPath(String groupName, String path);
}
