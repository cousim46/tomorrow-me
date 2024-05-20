package tomorrowme.todo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tomorrowme.todo.api.controller.account.AccountController;
import tomorrowme.todo.api.controller.account.dto.request.SignUp;
import tomorrowme.todo.api.service.account.AccountWriteService;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountWriteService accountWriteService;

    @DisplayName("핸드폰 번호와 키워드로 회원가입을 할 수 있다.")
    @Test
    void signUp() throws Exception {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);

        SignUp signUp = new SignUp(phone, keyword, wakeUpTime, sleepTime);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                .content(objectMapper.writeValueAsString(signUp))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("핸드폰 번호가 비어 있으면 예외가 발생한다.")
    @Test
    void occurPhoneBlankException() throws Exception {
        //given
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);

        SignUp signUp = new SignUp(null, keyword, wakeUpTime, sleepTime);
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
        String phone = "01012341234";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);

        SignUp signUp = new SignUp(phone, null, wakeUpTime, sleepTime);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                .content(objectMapper.writeValueAsString(signUp))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("키워드는 비어있을 수 없습니다."))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}