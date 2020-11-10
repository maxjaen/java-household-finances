package com.finances.app.household.model;

import java.util.Date;

public class Transaction {

	private String description;
	private String type;
	private Date processDate;
	private int ownerId;
	private double amount;
	private boolean regular;

	public Transaction() {
	}

	public Transaction(final String description, final String type, final Date processDate, final int ownerId,
			final double amount, final boolean regular) {
		this.description = description;
		this.type = type;
		this.processDate = processDate;
		this.ownerId = ownerId;
		this.amount = amount;
		this.regular = regular;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(final Date processDate) {
		this.processDate = processDate;
	}

	public int getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerd(final int ownerd) {
		this.ownerId = ownerd;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public boolean isRegular() {
		return this.regular;
	}

	public void setRegular(final boolean regular) {
		this.regular = regular;
	}
}
