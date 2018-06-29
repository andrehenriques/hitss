package br.com.globalhitss.claro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.globalhitss.claro.domain.model.Account;
import br.com.globalhitss.claro.domain.service.AccountService;
import br.com.globalhitss.claro.web.dto.AccountInfo;
import br.com.globalhitss.claro.web.dto.CreateAccountDTO;
import br.com.globalhitss.claro.web.dto.CreatedAccountDTO;
import br.com.globalhitss.claro.web.dto.converter.AccountAssembler;
import br.com.globalhitss.claro.web.exception.ResourceAlreadyException;

@RestController
@RequestMapping(path="/account", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController extends BaseController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountAssembler accountAssembler;
	
	@RequestMapping(method= {RequestMethod.POST, RequestMethod.OPTIONS})
	public ResponseEntity<?> create(@Valid @RequestBody CreateAccountDTO createAccountDTO) {
		
		if(accountService.existsEmail(createAccountDTO.email)) {
			throw new ResourceAlreadyException();
		}
		
		Account account = accountAssembler.toAccount(createAccountDTO);
		accountService.create(account);
		CreatedAccountDTO createdAccountDTO = null; 
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountDTO);
	}
	
	@GetMapping("/me")
	public ResponseEntity<AccountInfo> me() {
		return ResponseEntity.ok(getAccount());
	}
	
}
