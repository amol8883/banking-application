package com.BankingApplication.mapper;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;

public class AccountMapper {

    // method return type account
    // this method use for accountDto  to data transfer to account entity class.
    public static Account mapToAccount(AccountDto accountDto){

        Account account=new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getAddress()        // update add new

        );
        return account;
    }
    // create one more method data transfer account to accountDto

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
           account.getId(),
           account.getAccountHolderName(),
           account.getBalance(),
                account.getAddress()    // update add new
        );
        return accountDto;
    }

}
