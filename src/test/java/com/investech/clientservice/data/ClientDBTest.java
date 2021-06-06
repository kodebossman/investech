package com.investech.clientservice.data;

import com.investech.clientservice.client.model.Customer;
import com.investech.clientservice.exception.ClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ClientDBTest {




ClientDB  clientDBUnderTest = new ClientDB();

  @Test
  void addClientTest() {
    //given
  Customer customer = new Customer();
  customer.setIdNumber("190117 3742 995");
  customer.setFirstName("Kuda");
  customer.setLastName("Muparuri");
  customer.setPhysicalAdress("Joburg Durfen Valley");
  customer.setMobileNumber("0784792910");

    //when
    Customer client = clientDBUnderTest.add(customer);
    //then
    Assertions.assertTrue(client.getIdNumber().equals(customer.getIdNumber()));

    //check if it throws exception on duplicates
    //
    //when
    ClientException exception = Assertions.assertThrows(ClientException.class, () -> {
      clientDBUnderTest.add(customer);
    });

    //check if the exception thrown is the actual exception
    String expectedMessage = "Customer with the given ID number already exists : "
      +customer.getIdNumber();
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));


  }

  @Test
  void addClientDuplicateTrowsExceptionTest() {

    //given
    Customer customer = new Customer();
    customer.setIdNumber("190117 3742 995");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Joburg Durfen Valley");
    customer.setMobileNumber("0784792910");

    //when
    clientDBUnderTest.add(customer);

    //testing for duplicate
    ClientException exception = Assertions.assertThrows(ClientException.class, () -> {
      clientDBUnderTest.add(customer);
    });

    //check if the exception thrown is the actual exception
    String expectedMessage = "Customer with the given ID number already exists : "
      +customer.getIdNumber();
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void updateClientTest() {
     // given
    Customer customer = new Customer();
    customer.setIdNumber("190117 3742 995");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Joburg Durfen Valley");
    customer.setMobileNumber("0784792910");

    //when you add
    clientDBUnderTest.add(customer);
    //and update
    Assertions.assertEquals(customer,clientDBUnderTest.update(customer));

    //check update without data

    //given
    clientDBUnderTest.customerHashSet.remove(customer);

  }

  @Test
  void updateClientTestThrowsExceptionWhenRecordNotFoundTest(){

    Customer customer = new Customer();
    customer.setIdNumber("190117 3742 995");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Joburg Durfen Valley");
    customer.setMobileNumber("0784792910");

    ClientException exception = Assertions.assertThrows(ClientException.class, () -> {
      clientDBUnderTest.update(customer);
    });

    //check if the exception thrown is the actual exception
    String expectedMessage = "No customer with this ID: " + customer.getIdNumber()+
      " Please provide a valid ID";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));

  }

  @Test
  void searchClientTestWithIDNumber() {

    // given
    Customer customer = new Customer();
    customer.setIdNumber("1901173742995");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Joburg Durfen Valley");
    customer.setMobileNumber("0784792910");

    Customer customer1 = new Customer();
    customer1.setIdNumber("5102249614186");
    customer1.setFirstName("Bossman");
    customer1.setLastName("Katche");
    customer1.setPhysicalAdress("Joburg Durfen Valley");
    customer1.setMobileNumber("0784792912");

    Customer customer3 = new Customer();
    customer3.setIdNumber("2103015800089");
    customer3.setFirstName("Kodekwashe");
    customer3.setLastName("Katche");
    customer3.setPhysicalAdress("Joburg Durfen Valley");
    customer3.setMobileNumber("0784792913");

    //add the objects
    clientDBUnderTest.add(customer);
    clientDBUnderTest.add(customer1);
    clientDBUnderTest.add(customer3);

   Customer cust = clientDBUnderTest.searchClient("2103015800089");
   Assertions.assertEquals(cust,customer3);

  }

  @Test
  void searchClientTestWithPhoneNumber() {

    // given
    Customer customer = new Customer();
    customer.setIdNumber("1901173742995");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Joburg Durfen Valley");
    customer.setMobileNumber("0784792910");

    Customer customer1 = new Customer();
    customer1.setIdNumber("5102249614186");
    customer1.setFirstName("Bossman");
    customer1.setLastName("Katche");
    customer1.setPhysicalAdress("Joburg Durfen Valley");
    customer1.setMobileNumber("0784792912");

    Customer customer3 = new Customer();
    customer3.setIdNumber("2103015800089");
    customer3.setFirstName("Kodekwashe");
    customer3.setLastName("Katche");
    customer3.setPhysicalAdress("Joburg Durfen Valley");
    customer3.setMobileNumber("0784792913");

    //add the objects
    clientDBUnderTest.add(customer);
    clientDBUnderTest.add(customer1);
    clientDBUnderTest.add(customer3);

    Customer cust = clientDBUnderTest.searchClient("0784792910");
    Assertions.assertEquals(cust,customer);

  }

  @Test
  void searchClientTestWithFirstName() {

    // given
    Customer customer = new Customer();
    customer.setIdNumber("1901173742995");
    customer.setFirstName("Kuda");
    customer.setLastName("Muparuri");
    customer.setPhysicalAdress("Joburg Durfen Valley");
    customer.setMobileNumber("0784792910");

    Customer customer1 = new Customer();
    customer1.setIdNumber("5102249614186");
    customer1.setFirstName("Bossman");
    customer1.setLastName("Katche");
    customer1.setPhysicalAdress("Joburg Durfen Valley");
    customer1.setMobileNumber("0784792912");

    Customer customer3 = new Customer();
    customer3.setIdNumber("2103015800089");
    customer3.setFirstName("Kodekwashe");
    customer3.setLastName("Katche");
    customer3.setPhysicalAdress("Joburg Durfen Valley");
    customer3.setMobileNumber("0784792913");

    //add the objects
    clientDBUnderTest.add(customer);
    clientDBUnderTest.add(customer1);
    clientDBUnderTest.add(customer3);

    Customer cust = clientDBUnderTest.searchClient("Bossman");
    Assertions.assertEquals(cust,customer1);

  }
  }
