package com.cb.controller;

import com.cb.model.Customer;
import com.cb.repository.CustomerRepository;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.ReplicateTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerRepository repository;

  @GetMapping("/searchByPhoneNumber")
  @ResponseStatus(HttpStatus.OK)
  public Customer findByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
    return repository.findByPhoneNumber(phoneNumber);
  }

  @GetMapping("/searchByMclCardNo")
  @ResponseStatus(HttpStatus.OK)
  public Customer findByMlcCardNo(@RequestParam(name = "mlcCardNo") String mlcCardNo) {
    return repository.findByMlcCardNo(mlcCardNo);
  }

  @GetMapping("/phoneNumberAndMlcCard")
  public Customer findByMlcCardAndPhoneNumber(String mlcCardNo, String phoneNumber) {
    return repository.findByMlcCardNoAndPhoneNumber(mlcCardNo, phoneNumber);
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer save(@RequestBody Customer customer) {
    return repository.save(customer);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public Optional<Customer> update(@RequestBody Customer customer) {
    repository.getCouchbaseOperations().update(customer);
    return repository.findById(customer.getCId());
  }

  @DeleteMapping("/{cId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void delete(@PathVariable String cId) {
    repository.deleteById(cId);
  }

}
