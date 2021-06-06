package com.investech.clientservice.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDto {

  private String firstName;
  private String lastName;
  private String mobileNumber;
  private String idNumber;
  private String physicalAdress;


  public ClientDto(String derrick, String garcia, String s, String s1, String mrewa_centre) {
  }
}
