package com.investech.clientservice.client.model;

import lombok.Data;

@Data
public class Customer {

  private String firstName;
  private String lastName;
  private String mobileNumber;
  private String idNumber;
  private String physicalAdress;

  @Override
  public int hashCode() {

    return idNumber.hashCode();
  }

  @Override
  public boolean equals(Object obj) {


    Customer customer = (Customer) obj;

    return (idNumber.equals(customer.getIdNumber()) && mobileNumber.equals(customer.getMobileNumber()));
  }
}


