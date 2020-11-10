package com.finances.app.household.controller;

import static com.finances.app.household.controller.RestConstants.ACCOUNT_PATH;
import static com.finances.app.household.controller.RestConstants.ACCOUNT_PATH_BY_ID;
import static com.finances.app.household.controller.RestConstants.ID;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.app.household.model.Account;
import com.finances.app.household.repository.AccountRepository;
import com.finances.app.household.services.SequenceGeneratorService;

@CrossOrigin
@RestController
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private SequenceGeneratorService nextSequenceService;

	@RequestMapping(method = GET, value = ACCOUNT_PATH)
	public ResponseEntity<List<Account>> getAllAccounts() {

		final List<Account> accounts = this.accountRepository.findAll();
		if (accounts.size() > 0) {
			return new ResponseEntity<>(accounts, OK);
		} else {
			return new ResponseEntity<>(NOT_FOUND);
		}
	}

	@RequestMapping(method = POST, value = ACCOUNT_PATH)
	public ResponseEntity<String> createAccount(@RequestBody final Account account) {

		account.setId(Long.toString(this.nextSequenceService.generateSequence("account_sequence")));

		try {
			this.accountRepository.save(account);
			return new ResponseEntity<>(String.format("Successfully added account %s", account), OK);
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = PUT, value = ACCOUNT_PATH_BY_ID)
	public ResponseEntity<String> updateTransactionById(@PathVariable(ID) final String id,
			@RequestBody final Account newAccount) {

		final Optional<Account> accountOptional = this.accountRepository.findById(id);
		if (accountOptional.isPresent()) {
			final Account accountToSave = accountOptional.get();
			this.updateAccount(newAccount, accountToSave);
			this.accountRepository.save(accountToSave);
			return new ResponseEntity<>(String.format("Updated account with id %s.", id), OK);
		} else {
			return new ResponseEntity<>(String.format("No account with id %s found.", id), NOT_FOUND);
		}
	}

	@RequestMapping(method = DELETE, value = ACCOUNT_PATH_BY_ID)
	public ResponseEntity<String> deleteAccountById(@PathVariable(ID) final String id) {

		try {
			this.accountRepository.deleteById(id);
			return new ResponseEntity<>(String.format("Successfully deleted account with id %s.", id), OK);
		} catch (final Exception e) {
			return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
		}
	}

	private void updateAccount(final Account newAccount, final Account accountToSave) {
		accountToSave.setName(newAccount.getName());
		accountToSave.setDescription(newAccount.getDescription());
		accountToSave.setTransactions(newAccount.getTransactions());
	}
}
