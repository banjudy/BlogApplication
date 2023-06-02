package progmatic.BlogSpringJu.controllers.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import progmatic.BlogSpringJu.testHelpers.TestConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loadHomePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    void getInvalidEndPoint() throws Exception {
        mockMvc.perform(get("/invalid-endpoint"))
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    void getUserPageWithoutLogin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user"))
                .andExpect(status().is(302))
                .andReturn();

        assertHeaderContains(mvcResult, "Location", "login");
    }
    @Test
    @WithUserDetails("usertest")
    void getUserPageWithUser() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    @WithUserDetails("admintest")
    void getUserPageWithAdmin() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getUsersPageWithoutLogin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users"))
                .andExpect(status().is(302))
                .andReturn();

        assertHeaderContains(mvcResult, "Location", "login");
    }

    @Test
    @WithUserDetails("usertest")
    void getUsersPageWithUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is(403))
                .andReturn();
    }

    @Test
    @WithUserDetails("admintest")
    void getUsersPageWithAdmin() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
    }

   /* @Test
    @WithUserDetails("usertest")
    void getUsersPerIDAsUser() throws Exception {
        //UserController myUserController = Mockito.mock(UserController.class);
        //Mockito.when(myUserController.getLoggedInUser().getUserID()).thenReturn(Long.valueOf(1));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                        .andExpect(status()
                        .isOk()).andReturn();
    }*/

    /*@Test
    @WithUserDetails("usertest")
    void getOtherUsersPerIDAsUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users/2"))
                .andExpect(status().is(302))
                .andReturn();

        //assertHeaderContains(mvcResult, "Location", "login");
    }*/

    @Test
    @WithUserDetails("admintest")
    void getUsersPerIDAsAdmin() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
    }

    /*@Test
    @WithUserDetails("admintest")
    void getOtherUsersPerIDAsAdmin() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is(403))
                .andReturn();
    }*/

    private void assertHeaderContains(MvcResult mvcResult, String header, String containText) {
        String location = mvcResult.getResponse().getHeader(header);

        assertNotNull(location);
        assertTrue(location.contains(containText));
    }

}