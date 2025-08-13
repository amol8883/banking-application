package com.BankingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// use here lombok remove boiler plate code
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    private Double amount;

}
