package com.finances.app.household.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {

	@Transient
	public static final String SEQUENCE_NAME = "account_sequence";

	@Id
	private String id;

	private String name;
	private String description;
	private List<Transaction> transactions;

	public Account() {
	}

	public Account(final String name, final String description, final List<Transaction> transactions) {
		this.name = name;
		this.description = description;
		this.setTransactions(transactions);
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(final List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
