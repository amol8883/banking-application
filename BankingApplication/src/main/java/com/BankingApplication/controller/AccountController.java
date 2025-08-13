package com.BankingApplication.controller;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.AccountUpdateRequest;
import com.BankingApplication.dto.DepositRequest;
import com.BankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {


    // @Autowired one way
    private AccountService accountService;
    // Constructor Injection 2nd way
    public AccountController(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    //add account rest api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        System.out.println(accountDto);
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    // Get only one account by id
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);

    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){

       AccountDto accountDto= accountService.deposit(id,request.get("amount"));
       return ResponseEntity.ok(accountDto);
    }

    //same but write to another way not use Map
    @PutMapping("/{id}/deposit2")
    public ResponseEntity<AccountDto> depositTwo(@PathVariable Long id, @RequestBody DepositRequest request){
        AccountDto accountDto= accountService.deposit(id,request.getAmount());
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request) {

        double amount=   request.get("amount");
        AccountDto accountDto= accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountUpdateRequest request){
       AccountDto updateAccount= accountService.updateAccountDetails(id,request);
        return ResponseEntity.ok(updateAccount);

    }

    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
       List<AccountDto> accountDtos =accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully!! ");
    }

    }
