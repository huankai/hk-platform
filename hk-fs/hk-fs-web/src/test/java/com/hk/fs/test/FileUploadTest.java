package com.hk.fs.test;


import com.hk.core.test.LoginBaseTest;
import com.hk.fs.FSApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: sjq-278
 * @date: 2018-09-14 17:03
 */
@SpringBootTest(classes = FSApplication.class)
public class FileUploadTest extends LoginBaseTest {

    /**
     * 文件上传测试
     *
     * @throws Exception
     */
    @Test
    public void upload() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.multipart("/file/upload")
                .file(new MockMultipartFile("file", "apache-jmeter-4.0.zip", null, new FileInputStream(new File("E:/安装文件/apache-jmeter-4.0.zip"))))
                .header("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc1Byb3RlY3QiOnRydWUsInVzZXJfbmFtZSI6IjE4ODIwMTM2MDkwIiwic2V4IjoxLCJ1c2VySWQiOiI0MDI4YzA4MTYyYmRhOGNlMDE2MmJkYThkZjZhMDAwMCIsImNsaWVudF9pZCI6IjQwMjhjMDgxNjM3MWEwOTcwMTYzNzFhMzhkNWEwMDAwIiwicmVhbE5hbWUiOiLns7vnu5_nrqHnkIblkZgiLCJwaG9uZSI6IjE4ODIwMTM2MDkwIiwic2NvcGUiOlsiYWxsIl0sImNsaWVudEFwcCI6eyJhcHBJZCI6IjQwMjhjMDgxNjM3MWEwOTcwMTYzNzFhMzhkNWEwMDAwIiwiYXBwQ29kZSI6IkhLX0VNSSIsImFwcE5hbWUiOiLlrZflhbjnrqHnkIbns7vnu58iLCJhcHBJY29uIjoiYS5wbmcifSwic2V4Q2hpbmVzZSI6IueUtyIsInVzZXJUeXBlIjowLCJleHAiOjE1MzY5MjI0MTEsImljb25QYXRoIjpudWxsLCJqdGkiOiI4MmI3NmRhMi1jMTAxLTRiZTEtOWQwOS03YzFkMjYzNTUyZjQiLCJhY2NvdW50IjoiMTg4MjAxMzYwOTAiLCJlbWFpbCI6Imh1YW5rYWlAMTM5LmNvbSJ9.E3QeTAx4p1FDA01dYz5X3CI7SCRDVACOJxKH-9C7yqI")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 文件上传测试
     */
    @Test
    public void upload2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 1; i++) {
            threadPool.execute(() -> {
                try {
                    getMockMvc().perform(MockMvcRequestBuilders.multipart("/file/upload")
                            .file(new MockMultipartFile("file", "apache-maven-3.5.3-bin.zip", null, new FileInputStream(new File("E:/安装文件/apache-maven-3.5.3-bin.zip"))))
                            .header("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc1Byb3RlY3QiOnRydWUsInVzZXJfbmFtZSI6IjE4ODIwMTM2MDkwIiwic2V4IjoxLCJ1c2VySWQiOiI0MDI4YzA4MTYyYmRhOGNlMDE2MmJkYThkZjZhMDAwMCIsImNsaWVudF9pZCI6IjQwMjhjMDgxNjM3MWEwOTcwMTYzNzFhMzhkNWEwMDAwIiwicmVhbE5hbWUiOiLns7vnu5_nrqHnkIblkZgiLCJwaG9uZSI6IjE4ODIwMTM2MDkwIiwic2NvcGUiOlsiYWxsIl0sImNsaWVudEFwcCI6eyJhcHBJZCI6IjQwMjhjMDgxNjM3MWEwOTcwMTYzNzFhMzhkNWEwMDAwIiwiYXBwQ29kZSI6IkhLX0VNSSIsImFwcE5hbWUiOiLlrZflhbjnrqHnkIbns7vnu58iLCJhcHBJY29uIjoiYS5wbmcifSwic2V4Q2hpbmVzZSI6IueUtyIsInVzZXJUeXBlIjowLCJleHAiOjE1MzY5MzM5NzksImljb25QYXRoIjpudWxsLCJqdGkiOiI1ZmVlODI5Ny1iYTBkLTQ3NjctYTliZC00N2VhYWY0YTVhMDEiLCJhY2NvdW50IjoiMTg4MjAxMzYwOTAiLCJlbWFpbCI6Imh1YW5rYWlAMTM5LmNvbSJ9.SEuSxbA-tF4d7czXuvfkTGcYP7v95kpfUlSOM3KBY14")
                    )
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(MockMvcResultHandlers.print())
                            .andReturn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
