package com.BankingApplication.service;


import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.AccountUpdateRequest;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto); // done

    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);

    //Same add new column the update account details(updateName,addAddress column)
    public AccountDto updateAccountDetails(Long id, AccountUpdateRequest request);
    // Get All Account details one time
    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);




}
