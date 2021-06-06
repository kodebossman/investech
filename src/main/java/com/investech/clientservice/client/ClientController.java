package com.investech.clientservice.client;

import com.investech.clientservice.client.dto.ClientDto;
import com.investech.clientservice.client.service.ClientService;
import com.investech.clientservice.exception.ClientException;
import com.investech.clientservice.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController

public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {

    this.clientService = clientService;
  }

  @PostMapping(value = "/customers/create")

  public ResponseEntity<ClientDto> createClient(@RequestBody @Validated ClientDto clientDto) throws IOException {

    log.info("New Registration : {} ", clientDto);


    if (ValidatorUtil.checkSAIdNumber(clientDto.getIdNumber())) {

      if (ValidatorUtil.isValidPhoneNumber(clientDto.getMobileNumber())) {

        final ClientDto clientDto1 = clientService.createClient(clientDto);
        return new ResponseEntity<>(clientDto1, HttpStatus.CREATED);
      } else {
        throw new ClientException("The provided MobileNumber is not valid " + clientDto.getMobileNumber());
      }

    } else {
      throw new ClientException("The provided IDNumber is not valid: " + clientDto.getIdNumber());
    }
  }

  @GetMapping("/customers/{searchparameter}")
  public ResponseEntity<ClientDto> searchClientByParameter(@PathVariable("searchparameter")
                                                              String searchParameter) {

    log.info("Searching for client with parameter : {} ", searchParameter);
    ClientDto clientDto = clientService.getClient(searchParameter);
    return new ResponseEntity<>(clientDto, HttpStatus.OK);
  }

  @PatchMapping("/customers/update")
  public ResponseEntity<ClientDto> updateClient(@RequestBody @Validated ClientDto clientDto) {

    log.info("Updating client with body : {} ", clientDto);
    ClientDto client = clientService.updateClient(clientDto);
    return new ResponseEntity<>(client, HttpStatus.OK);
  }


}
