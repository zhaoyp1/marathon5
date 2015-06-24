package com.asiainfo.baas.marathon.baseType.Example1;

import com.asiainfo.baas.marathon.baseType.*;

public class Account {

    public Bank _bank;
    public BankCustomer _bankCustomer;
    private int accountType;
    private Money currentBalance;
    private int lastTransactionDate;
    private TimePeriod validFor;
    private Duration billingFrequency;

}