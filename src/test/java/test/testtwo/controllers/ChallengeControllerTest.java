package test.testtwo.controllers;

import test.testtwo.challenge.controllers.ChallengeController;
import test.testtwo.challenge.domain.model.ChallengeDTO;
import test.testtwo.challenge.domain.services.ChallengeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ChallengeControllerTest {
    @Mock
    private ChallengeService challengeService;
    @InjectMocks
    private ChallengeController challengeController;
    private MockMvc mockMvc;
    private ChallengeDTO challengeDTO;
    Integer numberTest;


    @BeforeEach
    void setUp() {
        challengeDTO = ChallengeDTO.builder()
                .x(7)
                .y(5)
                .n(12345)
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(challengeController)
                .setCustomArgumentResolvers(
                        new PageableHandlerMethodArgumentResolver(),
                        new SpecificationArgumentResolver())
                .build();

    }


    @Test
    @DisplayName("Calculate Maximum Post")
    void testCalculateMaximumPost() throws Exception {
        when(challengeService.calculateMaximum(any())).thenReturn(numberTest);

        mockMvc.perform(post("/v1/challenge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(challengeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Calculate Maximum Get")
    void testCalculateMaximumGet() throws Exception {
        when(challengeService.calculateMaximum(any())).thenReturn(numberTest);

        mockMvc.perform(get("/v1/challenge")
                        .param("x", "5")
                        .param("y", "7")
                        .param("n", "12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(challengeDTO)))
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}