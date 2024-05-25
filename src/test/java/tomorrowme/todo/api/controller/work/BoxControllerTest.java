package tomorrowme.todo.api.controller.work;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tomorrowme.todo.api.controller.work.dto.request.BoxCreate;
import tomorrowme.todo.api.controller.work.dto.request.BoxFind;
import tomorrowme.todo.api.service.work.BoxReadService;
import tomorrowme.todo.api.service.work.BoxWriteService;
import tomorrowme.todo.api.service.work.dto.response.BoxInfo;

@WebMvcTest(controllers = BoxController.class)
class BoxControllerTest {

    @MockBean
    private BoxWriteService boxWriteService;
    @MockBean
    private BoxReadService boxReadService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("보관함을 생성할 회원의 전화번호, 키워드와 제목을 통해 보관함을 생성한다.")
    @Test
    void create() throws Exception {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        String title = "title";
        BoxCreate boxCreate = new BoxCreate(title, phone, keyword);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/box")
                .content(objectMapper.writeValueAsString(boxCreate))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @DisplayName("보관함을 생성할 회원의 전화번호가 비어있으면 예외가 발생한다.")
    @Test
    void createWithoutPhone() throws Exception {
        //given
        String phone = null;
        String keyword = "keyword";
        String title = "title";
        BoxCreate boxCreate = new BoxCreate(title, phone, keyword);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/box")
                .content(objectMapper.writeValueAsString(boxCreate))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("전화번호는 필수로 입력해야 합나다."));
    }

    @DisplayName("보관함을 생성할 회원의 키워드가 비어있으면 예외가 발생한다.")
    @Test
    void createWithoutKeyword() throws Exception {
        //given
        String phone = "01012341234";
        String keyword = null;
        String title = "title";
        BoxCreate boxCreate = new BoxCreate(title, phone, keyword);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/box")
                .content(objectMapper.writeValueAsString(boxCreate))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("키워드는 필수로 입력해야 합나다."));
    }

    @DisplayName("회원의 전화번호, 키워드로 해당 회원의 모든 보관함을 조회한다.")
    @Test
    void findAll() throws Exception {
        String phone = "01012341234";
        String keyword = "keyword";
        BoxFind boxFind = new BoxFind(phone, keyword);
        given(boxReadService.findAll(phone,keyword)).willReturn(List.of(
            new BoxInfo(LocalDateTime.of(2024,1,1,1,1,1),"title1",LocalDateTime.of(2024,1,1,1,1,1)),
            new BoxInfo(LocalDateTime.of(2024,2,1,1,1,1),"title2",LocalDateTime.of(2024,2,1,1,1,1))
        ));
        //when && then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/box")
                .queryParam("phone", boxFind.phone())
                .queryParam("keyword", boxFind.keyword()))
            .andExpect(jsonPath("$[0]").exists())
            .andExpect(jsonPath("$[1]").exists())
            .andExpect(
                jsonPath("$[0].title").value("title1"))
            .andExpect(jsonPath("$[0].createdAt").value("2024-01-01 01:01:01"))
            .andExpect(jsonPath("$[1].title").value("title2"))
            .andExpect(jsonPath("$[1].createdAt").value("2024-02-01 01:01:01"));
    }


    @DisplayName("보관함을 조회 할 회원의 전화번호가 비어있으면 예외가 발생한다.")
    @Test
    void findAllWithoutPhone() throws Exception {
        //given
        String phone = null;
        String keyword = "keyword";
        BoxFind boxFind = new BoxFind(phone, keyword);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/box")
                .queryParam("phone", boxFind.phone())
                .queryParam("keyword", boxFind.keyword()))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("전화번호는 필수로 입력해야 합나다."));
    }
    @DisplayName("보관함을 조회 할 회원의 키워드가 비어있으면 예외가 발생한다.")
    @Test
    void findAllWithoutKeyword() throws Exception {
        //given
        String phone = "01012341234";
        String keyword = null;
        BoxFind boxFind = new BoxFind(phone, keyword);

        //when && then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/box")
                .queryParam("phone", boxFind.phone())
                .queryParam("keyword", boxFind.keyword()))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("키워드는 필수로 입력해야 합나다."));
    }
}