package com.finances.app.household.controller;

import static com.finances.app.household.controller.RestConstants.ID;
import static com.finances.app.household.controller.RestConstants.TRANSACTION_PATH;
import static com.finances.app.household.controller.RestConstants.TRANSACTION_PATH_BY_ID;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.app.household.model.Transaction;
import com.finances.app.household.repository.TransactionRepository;

@RestController
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;

	@RequestMapping(method = GET, value = TRANSACTION_PATH)
	public ResponseEntity<List<Transaction>> getAllTransactions() {

		final List<Transaction> transactions = this.transactionRepository.findAll();
		if (transactions.size() > 0) {
			return new ResponseEntity<>(transactions, OK);
		} else {
			return new ResponseEntity<>(NOT_FOUND);
		}
	}

	@RequestMapping(method = POST, value = TRANSACTION_PATH)
	public ResponseEntity<String> createTransaction(@RequestBody final Transaction transaction) {

		try {
			this.transactionRepository.save(transaction);
			return new ResponseEntity<>(String.format("Successfully added transaction %s", transaction), OK);
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = PUT, value = TRANSACTION_PATH_BY_ID)
	public ResponseEntity<String> updateTransactionById(@PathVariable(ID) final String id,
			@RequestBody final Transaction newTransaction) {

		final Optional<Transaction> transactionOptional = this.transactionRepository.findById(id);
		if (transactionOptional.isPresent()) {
			final Transaction transactionToSave = transactionOptional.get();
			this.updateTransaction(newTransaction, transactionToSave);
			this.transactionRepository.save(transactionToSave);
			return new ResponseEntity<>(String.format("Updated transaction with id %s.", id), OK);
		} else {
			return new ResponseEntity<>(String.format("No transaction with id %s found.", id), NOT_FOUND);
		}
	}

	@RequestMapping(method = DELETE, value = TRANSACTION_PATH_BY_ID)
	public ResponseEntity<String> deleteTransactionById(@PathVariable(ID) final String id) {

		try {
			this.transactionRepository.deleteById(id);
			return new ResponseEntity<>(String.format("Successfully deleted transaction with id %s.", id), OK);
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
		}
	}

	private void updateTransaction(final Transaction newTransaction, final Transaction transactionToSave) {
		transactionToSave.setAmount(newTransaction.getAmount());
		transactionToSave.setDescription(newTransaction.getDescription());
		transactionToSave.setOwnerd(newTransaction.getOwnerd());
		transactionToSave.setProcessDate(newTransaction.getProcessDate());
		transactionToSave.setRegular(newTransaction.isRegular());
		transactionToSave.setType(newTransaction.getType());
	}
}
