package com.hk.fs.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author huangkai
 * @date 2019-4-4 11:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse implements Serializable {

    private String bucketName;

    private String filePath;

}
