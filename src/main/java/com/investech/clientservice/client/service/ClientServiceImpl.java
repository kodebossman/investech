package com.investech.clientservice.client.service;

import com.investech.clientservice.client.dto.ClientDto;
import com.investech.clientservice.client.model.Customer;
import com.investech.clientservice.data.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)

public class ClientServiceImpl implements ClientService{


  private final ClientRepository clientRepository;

  public ClientServiceImpl(ClientRepository clientRepository) {

    this.clientRepository = clientRepository;
  }
  @Override
  public ClientDto createClient(ClientDto client) {
    Customer customer = getCustomer(client);
    Customer cus= clientRepository.add(customer);
    return createClientDto(cus);
  }

  @Override
  public ClientDto updateClient(ClientDto client) {

    Customer customer = getCustomer(client);
    Customer cus= clientRepository.update(customer);
    return createClientDto(cus);
  }

  @Override
  public ClientDto getClient(String searchParameter) {
    Customer customer = clientRepository.searchClient(searchParameter);
    return  createClientDto(customer);
  }

  protected  Customer getCustomer(ClientDto client) {
    Customer customer = new Customer();
    customer.setFirstName(client.getFirstName());
    customer.setIdNumber(client.getIdNumber());
    customer.setMobileNumber(client.getMobileNumber());
    customer.setLastName(client.getLastName());
    customer.setPhysicalAdress(client.getPhysicalAdress());
    return customer;
  }

  public   ClientDto createClientDto( Customer customer){
    ClientDto clientDto = new ClientDto();
    clientDto.setFirstName(customer.getFirstName());
    clientDto.setLastName(customer.getLastName());
    clientDto.setIdNumber(customer.getIdNumber());
    clientDto.setMobileNumber(customer.getMobileNumber());
    clientDto.setPhysicalAdress(customer.getPhysicalAdress());
    return clientDto;
  }

}
