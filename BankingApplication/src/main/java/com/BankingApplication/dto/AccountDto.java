package com.BankingApplication.dto;

public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double  balance;

    public String getAddress() {
        return address;
    }


    private String address;

    public AccountDto(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountDto() {
        super();
    }
    public AccountDto(Long id, String accountHolderName, double balance, String address) {
        super();
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.address = address;
    }
}
