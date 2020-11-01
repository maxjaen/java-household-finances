package com.finances.app.household.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {

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
