package com.example.acme.securitytest;

import com.example.acme.conf.SpringSecurityWebAuxTestConfig;
import com.example.acme.entities.AcmeUsers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc(addFilters = true)
public class SecurityRoleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerNoNeedAuthTest() throws Exception {
        AcmeUsers acmeUsers = new AcmeUsers();
        acmeUsers.setUserName("test");
        acmeUsers.setPassword("pass");
        acmeUsers.setRole("INSTR");
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(acmeUsers);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputjson)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration success")));

    }

    @Test
    @WithUserDetails("student")
    public void studentRoleAccessTest() throws Exception{
        mockMvc.perform(get("/instructors/list")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("emm")
    public void instructorRoleAccessTest() throws Exception{
        mockMvc.perform(get("/instructors/list")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }
}
