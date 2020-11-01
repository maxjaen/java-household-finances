package com.finances.app.household.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finances.app.household.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

}
