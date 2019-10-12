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

/**
 * @author kevin
 * @date 2018-09-14 17:03
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
                .header("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXB0TmFtZSI6IuagueacuuaehOmDqOmXqCIsIm9yZ05hbWUiOiLmoLnoioLngrkiLCJ1c2VyX25hbWUiOiIxODgyMDEzMjAxNCIsInNleCI6MSwiYXBwSW5mbyI6eyJhcHBJZCI6Ijc4NzJjNmY3ZjBjYjQxZjBiYTBmOGFhMDYwY2I1YzM3IiwiYXBwQ29kZSI6IkhLLVBNUyIsImFwcE5hbWUiOiLmnYPpmZDnrqHnkIbns7vnu58iLCJhcHBJY29uIjoiL3B1YmxpYy9kZWZhdWx0X2xvZ28uanBnIn0sImRlcHRJZCI6IjQwMjhjMDgxNjJiZGE4NGQwMTYyYmRhODVkNmIwMDAwIiwidXNlcklkIjoiZTAzYTBhYjYzNWNmNDhiZDk3ZDc2ZjBhYjkwZjE0YzgiLCJvcmdJZCI6IjQwMjg4MWU2NjJiYTVmZmYwMTYyYmE2MDJiZmYwMDAwIiwiY2xpZW50X2lkIjoiNzg3MmM2ZjdmMGNiNDFmMGJhMGY4YWEwNjBjYjVjMzciLCJyZWFsTmFtZSI6IjE4ODIwMTMyMDE0IiwicGVybWlzc2lvblNldCI6bnVsbCwicGhvbmUiOiIxODgyMDEzMjAxNCIsInNjb3BlIjpbImFsbCJdLCJzZXhDaGluZXNlIjoi55S3IiwidXNlclR5cGUiOjAsImV4cCI6MTU0NTg0NDA2MywiaWNvblBhdGgiOm51bGwsInByb3RlY3RVc2VyIjpmYWxzZSwicm9sZVNldCI6bnVsbCwianRpIjoiYjVkY2ZlYWUtNDYzZC00MTY1LThlNjctMzZiNWMwZWE4N2VhIiwiYWNjb3VudCI6IjE4ODIwMTMyMDE0IiwiZW1haWwiOiIxODgyMDEzMjAxNCJ9.RyOtO7vNMFVIaFTNuPIghh8SdUIkJmvnEOf1Y187rgM")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 文件上传测试
     */
    @Test
    public void upload2() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.multipart("/file/upload")
                .file(new MockMultipartFile("file", "apache-jmeter-4.0.zip", null, new FileInputStream(new File("E:/安装文件/apache-jmeter-4.0.zip"))))
                .header("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXB0TmFtZSI6IuagueacuuaehOmDqOmXqCIsIm9yZ05hbWUiOiLmoLnoioLngrkiLCJ1c2VyX25hbWUiOiIxODgyMDEzMjAxNCIsInNleCI6MSwiYXBwSW5mbyI6eyJhcHBJZCI6Ijc4NzJjNmY3ZjBjYjQxZjBiYTBmOGFhMDYwY2I1YzM3IiwiYXBwQ29kZSI6IkhLLVBNUyIsImFwcE5hbWUiOiLmnYPpmZDnrqHnkIbns7vnu58iLCJhcHBJY29uIjoiL3B1YmxpYy9kZWZhdWx0X2xvZ28uanBnIn0sImRlcHRJZCI6IjQwMjhjMDgxNjJiZGE4NGQwMTYyYmRhODVkNmIwMDAwIiwidXNlcklkIjoiZTAzYTBhYjYzNWNmNDhiZDk3ZDc2ZjBhYjkwZjE0YzgiLCJvcmdJZCI6IjQwMjg4MWU2NjJiYTVmZmYwMTYyYmE2MDJiZmYwMDAwIiwiY2xpZW50X2lkIjoiNzg3MmM2ZjdmMGNiNDFmMGJhMGY4YWEwNjBjYjVjMzciLCJyZWFsTmFtZSI6IjE4ODIwMTMyMDE0IiwicGVybWlzc2lvblNldCI6bnVsbCwicGhvbmUiOiIxODgyMDEzMjAxNCIsInNjb3BlIjpbImFsbCJdLCJzZXhDaGluZXNlIjoi55S3IiwidXNlclR5cGUiOjAsImV4cCI6MTU0NTg0NDA2MywiaWNvblBhdGgiOm51bGwsInByb3RlY3RVc2VyIjpmYWxzZSwicm9sZVNldCI6bnVsbCwianRpIjoiYjVkY2ZlYWUtNDYzZC00MTY1LThlNjctMzZiNWMwZWE4N2VhIiwiYWNjb3VudCI6IjE4ODIwMTMyMDE0IiwiZW1haWwiOiIxODgyMDEzMjAxNCJ9.RyOtO7vNMFVIaFTNuPIghh8SdUIkJmvnEOf1Y187rgM")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }


}
