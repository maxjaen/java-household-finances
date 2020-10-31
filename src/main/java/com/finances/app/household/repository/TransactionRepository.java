package com.finances.app.household.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finances.app.household.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
