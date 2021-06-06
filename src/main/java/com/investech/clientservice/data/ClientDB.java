package com.investech.clientservice.data;

import com.investech.clientservice.client.model.Customer;
import com.investech.clientservice.exception.ClientException;
import com.investech.clientservice.util.ValidatorUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class ClientDB  implements ClientRepository{

  Set<Customer> customerHashSet = new HashSet<>();

  @Override
  public Customer add(Customer customer) {

     if(customerHashSet.add(customer)){
       return customer;
     }else{
       throw new ClientException("Customer with the given ID number already exists : "
         +customer.getIdNumber());
     }

  }

  @Override
  public Customer update(Customer customer) {
    if (customerHashSet.add(customer)){
      customerHashSet.remove(customer);
      throw new ClientException("No customer with this ID: " + customer.getIdNumber()+
        " Please provide a valid ID");
    }else{
      //remove customer
      customerHashSet.remove(customer);
      customerHashSet.add(customer);
      return customer;
    }

  }

  @Override
  public Customer searchClient(String searchParameter) {

    //alternatively could use a search algorithm * binary search or pass an object to search for item*
    if(searchParameter.length()==13){
    if (ValidatorUtil.checkSAIdNumber(searchParameter)) {
      for (Customer customer : customerHashSet) {
        if (customer.getIdNumber().equals(searchParameter)) {
          return customer;
        }
      }
    }
    } else if (ValidatorUtil.isValidPhoneNumber(searchParameter)) {
      for (Customer customer : customerHashSet) {
        if (customer.getMobileNumber().equals(searchParameter)) {
          return customer;
        }
      }
    } else {
      for (Customer customer : customerHashSet) {
        if (customer.getFirstName().equals(searchParameter)) {
          return customer;
        }
      }
    }
    return new Customer();
  }


}
