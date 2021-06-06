package com.investech.clientservice.client.service;

import com.investech.clientservice.client.dto.ClientDto;

public interface ClientService {

  ClientDto createClient(ClientDto client);
  ClientDto updateClient( ClientDto client);
  ClientDto getClient( String searchParameter);

}
