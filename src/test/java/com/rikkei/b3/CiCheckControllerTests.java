package com.rikkei.b3;

import com.rikkei.b3.controller.CiCheckController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CiCheckController.class)
class CiCheckControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCiStatus() throws Exception {
        mockMvc.perform(get("/api/ci/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.pipelineName").value("GitHub Actions CI Pipeline"))
                .andExpect(jsonPath("$.data.testsPassed").value(true));
    }
}
