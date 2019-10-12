package com.hk.fs;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.File;
import java.io.IOException;

/**
 * FS Start
 *
 * @author kevin
 * @date 2018-07-13 14:48
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.hk"})
public class FSApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(FSApplication.class, args);

        // 图片压缩
        File file = new File("C:\\Users\\sjq-278\\Desktop/IMG_2533.JPG");
        System.out.println(file.getParent());
        System.out.println(file.getName());
        System.out.println(file.length());
        Thumbnails.of(file)
                .scale(0.25)
                .outputQuality(1)
                .toFile(new File(file.getParent(), "ys_" + file.getName()));
    }
}
