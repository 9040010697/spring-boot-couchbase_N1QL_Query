package com.cb.repository;

import com.cb.model.Customer;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "customer", viewName = "all")
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {

    @Query(RepoConstants.PHONE_NUMBER_SEARCH_QUERY)
    Customer findByPhoneNumber(String phoneNumber);

    @Query(RepoConstants.MLC_CARD_SEARCH_QUERY)
    Customer findByMlcCardNo(String mlcCardNo);

    @Query(RepoConstants.MLC_CARD_PHONE_NUMBER_SEARCH_QUERY)
    Customer findByMlcCardNoAndPhoneNumber(String mlcCardNo, String phoneNumber);


    
}
