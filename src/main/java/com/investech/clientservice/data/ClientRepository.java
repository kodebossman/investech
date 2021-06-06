package com.investech.clientservice.data;

import com.investech.clientservice.client.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository {

  Customer add(Customer customer);
  Customer update(Customer customer);
  Customer searchClient( String seachParameter);
}
