package tomorrowme.todo.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tomorrowme.todo.api.controller.dto.request.SignUp;
import tomorrowme.todo.api.service.AccountWriteService;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountWriteService accountWriteService;

    @DisplayName("핸드폰 번호가 비어 있으면 예외가 발생한다.")
    @Test
    void occurPhoneBlankException() throws Exception {
        //given
        SignUp signUp = new SignUp(null, "keyword");
        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
            .content(objectMapper.writeValueAsString(signUp))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("핸드폰 번호는 비어있을 수 없습니다."))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @DisplayName("키워드가 비어 있으면 예외가 발생한다.")
    @Test
    void occurKeywordBlankException() throws Exception {
        //given
        SignUp signUp = new SignUp("01012341234", null);
        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                .content(objectMapper.writeValueAsString(signUp))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("키워드는 비어있을 수 없습니다."))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}