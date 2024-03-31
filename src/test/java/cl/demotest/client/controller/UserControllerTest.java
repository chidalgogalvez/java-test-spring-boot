package cl.demotest.client.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.demotest.client.dto.PhonesDTO;
import cl.demotest.client.dto.UserRequestDTO;
import cl.demotest.client.service.UserService;
import cl.demotest.client.util.Utils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

  @InjectMocks()
  private UserController userController;

  @Mock()
  private UserService userService;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test()
  void testAddUser() throws Exception {
    // prepare
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .post("/user/add")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
        .content(Utils.convertToJson(generationUserMock()));

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcResultHandlers.print());
  }


  @Test
  void testGetUser() throws Exception {

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.get("/user/a5e0467d-65f1-44ea-bb3b-5555ac64db27");

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

  }

  @Test
  public void testUpdateUser() throws Exception {

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/user/a5e0467d-65f1-44ea-bb3b-5555ac64db27")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)

            .content(Utils.convertToJson(generationUserMock()));

    this.mockMvc.perform(builder).andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void testDeleteUser() throws Exception {

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.delete("/user/a5e0467d-65f1-44ea-bb3b-5555ac64db27");

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

  }

  @Test
  void testPrueba() throws Exception {

    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.get("/user/prueba");

    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

  }

  private UserRequestDTO generationUserMock(){

    PhonesDTO phone = PhonesDTO.builder()
        .number("1234567")
        .citycode("1")
        .countrycode("57")
        .build();

    return UserRequestDTO.builder()
        .name("Juan Rodriguez")
        .email("juan@rodriguez.org")
        .password("Hidalgo123#")
        .phones(List.of(phone))
        .build();

  }

}
