package com.hk.emi.test;

import com.hk.core.test.LoginBaseTest;
import com.hk.emi.EMIApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author: sjq-278
 * @date: 2018-09-14 15:42
 */
@SpringBootTest(classes = EMIApplication.class)
public class CityControllerTest extends LoginBaseTest {

    @Test
    public void get() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/city/{id}", "4028c08162be57660162be5779cb0000")
                .header("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc1Byb3RlY3QiOnRydWUsInVzZXJfbmFtZSI6IjE4ODIwMTM2MDkwIiwic2V4IjoxLCJ1c2VySWQiOiI0MDI4YzA4MTYyYmRhOGNlMDE2MmJkYThkZjZhMDAwMCIsImNsaWVudF9pZCI6IjQwMjhjMDgxNjM3MWEwOTcwMTYzNzFhMzhkNWEwMDAwIiwicmVhbE5hbWUiOiLns7vnu5_nrqHnkIblkZgiLCJwaG9uZSI6IjE4ODIwMTM2MDkwIiwic2NvcGUiOlsiYWxsIl0sImNsaWVudEFwcCI6eyJhcHBJZCI6IjQwMjhjMDgxNjM3MWEwOTcwMTYzNzFhMzhkNWEwMDAwIiwiYXBwQ29kZSI6IkhLX0VNSSIsImFwcE5hbWUiOiLlrZflhbjnrqHnkIbns7vnu58iLCJhcHBJY29uIjoiYS5wbmcifSwic2V4Q2hpbmVzZSI6IueUtyIsInVzZXJUeXBlIjowLCJleHAiOjE1MzY5MjI0MTEsImljb25QYXRoIjpudWxsLCJqdGkiOiI4MmI3NmRhMi1jMTAxLTRiZTEtOWQwOS03YzFkMjYzNTUyZjQiLCJhY2NvdW50IjoiMTg4MjAxMzYwOTAiLCJlbWFpbCI6Imh1YW5rYWlAMTM5LmNvbSJ9.E3QeTAx4p1FDA01dYz5X3CI7SCRDVACOJxKH-9C7yqI")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
