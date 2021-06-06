package com.investech.clientservice.client;


import com.investech.clientservice.client.dto.ClientDto;
import com.investech.clientservice.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
class ClientControllerITest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ClientService clientService;

  @Test
  void createClient() throws Exception {

    String json = "{\"firstName\":\"Derrick\" , \"lastName\": \"Garcia\", \"mobileNumber\": \"0777756599\", \"idNumber\": \"2103015800089\", \"physicalAdress\": \"Mrewa Centre\"}";
    // mock post of new User

    // Setup our mocked service
    ClientDto clientToPost = new ClientDto("Derrick", "Garcia",
      "0777756599","2103015800089","Mrewa Centre"  );
    ClientDto clientToReturn = new ClientDto("Derrick", "Garcia",
      "0777756599","2103015800089","Mrewa Centre");

    doReturn(clientToReturn).when(clientService).createClient(any());

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
    .post("/customers/create")
    .contentType(MediaType.APPLICATION_JSON).content(json))
    .andExpect(status().isCreated())
      .andReturn();

    int resultCZ = result.getResponse().getStatus();
    assertEquals(resultCZ, 201);

  }

  @Test
  void searchClientByParameter() throws Exception {

    // Setup our mocked service
    // Setup our mocked service
    ClientDto clientToPost = new ClientDto("Derrick", "Garcia",
      "0777756599","2103015800089","Mrewa Centre"  );
    ClientDto clientToReturn = new ClientDto("Derrick", "Garcia",
      "0777756599","2103015800089","Mrewa Centre");

    // Execute the GET request
    MvcResult result =  mockMvc.perform(get("/customers/{searchparameter}", 1L))
      // Validate the response code and content type
      .andExpect(status().isOk())
      .andReturn();
    int resultCZ = result.getResponse().getStatus();
    assertEquals(resultCZ, 200);


  }

  @Test
  void updateClient() throws Exception {

    String json = "{\"firstName\":\"Derrick\" , \"lastName\": \"Garcia\", \"mobileNumber\": \"0777756599\", \"idNumber\": \"2103015800089\", \"physicalAdress\": \"Mrewa Centre\"}";
    // mock post of new User

    // Setup our mocked service
    ClientDto clientToPost = new ClientDto("Derrick", "Garcia",
      "0777756599","2103015800089","Mrewa Centre"  );
    ClientDto clientToReturn = new ClientDto("Derrick", "Garcia",
      "0777756599","2103015800089","Mrewa Centre");

    doReturn(clientToReturn).when(clientService).createClient(any());

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
      .patch("/customers/update")
      .contentType(MediaType.APPLICATION_JSON).content(json))
      .andExpect(status().isOk())
      .andReturn();

    int resultCZ = result.getResponse().getStatus();
    assertEquals(resultCZ, 200);
  }





}