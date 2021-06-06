package com.investech.clientservice.client.service;

import com.investech.clientservice.client.dto.ClientDto;
import com.investech.clientservice.client.model.Customer;
import com.investech.clientservice.data.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

  @Mock
  private ClientRepository clientRepository;
  //private AutoCloseable autoCloseable;
  private ClientServiceImpl clientServiceUnderTest;
  private String searchParameter = "2103015800089";
  private ClientDto clientDto;


  @BeforeEach
  void setUp() {
    // autoCloseable=  MockitoAnnotations.openMocks(this);
    clientServiceUnderTest = new ClientServiceImpl(clientRepository);

  }

//  @AfterEach
//  void tearDown() throws Exception {
//    autoCloseable.close();
//  }

  @Test
  void createClient() {
    //given
    clientDto = new ClientDto();
    clientDto.setFirstName("Kuda");
    clientDto.setLastName("Muparuri");
    clientDto.setIdNumber(searchParameter);
    clientDto.setPhysicalAdress("Bossman.com");

    Customer customer = new Customer();
    customer.setMobileNumber("0777756599");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Mrewa");
    customer.setIdNumber("2103015800089");

    BDDMockito.given(clientRepository.add(customer))
      .willReturn(customer);

    //when
    clientServiceUnderTest.createClient(clientDto);

    //then
    ArgumentCaptor<Customer> customerArgumentCapture = ArgumentCaptor.forClass(Customer.class);

    Mockito.verify(clientRepository).add(customerArgumentCapture.capture());
    Customer cust = customerArgumentCapture.getValue();
    Assertions.assertEquals(cust, customer);

  }

  @Test
  void updateClient() {

    //given
    clientDto = new ClientDto();
    clientDto.setFirstName("Kuda");
    clientDto.setLastName("Muparuri");
    clientDto.setIdNumber(searchParameter);
    clientDto.setPhysicalAdress("Bossman.com");

    Customer customer = new Customer();
    customer.setMobileNumber("0777756599");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Mrewa");
    customer.setIdNumber("2103015800089");

    BDDMockito.given(clientRepository.add(customer))
      .willReturn(customer);

    //when
    clientServiceUnderTest.createClient(clientDto);
    //then
    ArgumentCaptor<Customer> customerArgumentCapture = ArgumentCaptor.forClass(Customer.class);

    Mockito.verify(clientRepository).update(customerArgumentCapture.capture());
    Customer cust = customerArgumentCapture.getValue();
    Assertions.assertEquals(cust, customer);


  }

  @Test
  void canGetClientTest() {

    //given
    Customer customer = new Customer();
    customer.setMobileNumber("0777756599");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Mrewa");
    customer.setIdNumber("2103015800089");

    clientDto = new ClientDto();
    clientDto.setFirstName("Kuda");
    clientDto.setLastName("Muparuri");
    clientDto.setIdNumber(searchParameter);
    clientDto.setPhysicalAdress("Bossman.com");

    //given

    BDDMockito.given(clientRepository.add(customer))
      .willReturn(customer);

    //when
    clientServiceUnderTest.getClient(searchParameter);
    //then
    Mockito.verify(clientRepository).searchClient(searchParameter);
  }
}