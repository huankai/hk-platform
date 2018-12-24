package com.hk.fs;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * FS Start
 *
 * @author kevin
 * @date 2018-07-13 14:48
 */
//@SpringCloudApplication
public class FSApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(FSApplication.class, args);

        // 图片压缩
        Thumbnails.of(new File("C:\\Users\\sjq-278\\Desktop/021411549t5w.jpg"))
                .scale(0.25)
                .outputQuality(1)
                .toFile(new File("C:\\Users\\sjq-278\\Desktop/2.jpg"));
    }
}
