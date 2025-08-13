package com.BankingApplication.service.impl;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.AccountUpdateRequest;
import com.BankingApplication.entity.Account;
import com.BankingApplication.exception.InsufficientBalanceException;
import com.BankingApplication.exception.InvalidAmountException;
import com.BankingApplication.exception.ResourceNotFoundException;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.repository.AccountRepository;
import com.BankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    // if we  use annotation otherwise next way
    //@Autowired
    private AccountRepository accountRepository;
    // next way constructor injection
    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account= accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account does not exit  "+id));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero");
        }

        Account account= accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account does not exit  "+id));
        double totalBalance= account.getBalance()+amount;
          account.setBalance(totalBalance);
      Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account does not exit  "+id));
        if (account.getBalance()<amount){
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
                    //RuntimeException("Insufficient Balance");
        }

        double totalBalance= account.getBalance()-amount;
        account.setBalance(totalBalance);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto updateAccountDetails(Long id, AccountUpdateRequest request) {

      Account account=  accountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account not fount with id : "+id));
        if (request.getAccountHolderName()!=null){
            account.setAccountHolderName(request.getAccountHolderName());
        }
        if (request.getAddress()!=null){
            account.setAddress(request.getAddress());
        }
      /*  if (request.getBalance()!=null){
            account.setBalance(request.getBalance());
        }
      */
       // Account savedAccount= accountRepository.save(account);
        //return AccountMapper.mapToAccountDto(savedAccount);
        accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(account ->AccountMapper.mapToAccountDto(account)).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        Account account=  accountRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Account not fount with id : "+id));

        accountRepository.delete(account);


    }


}
