package com.booleanuk.core;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String accountName;
    private final int clearingNumber;
    private final int accountNumber;
    private double balance;
    protected double minLimit;
    private List<BankStatement> statements;

    public Account(String accountName, int clearingNumber, int accountNumber, double startingBalance) {
        this.accountName = accountName;
        this.clearingNumber = clearingNumber;
        this.accountNumber = accountNumber;
        this.balance = startingBalance;
        this.statements = new ArrayList<>();
    }

    public boolean changeBalance(double amount, DateFormat date) {
        double newBalance = this.balance + amount;
        String type;
        if (newBalance < this.minLimit) {
            System.out.print("Can't withdraw that much money from that account!\n");
            return false;
        }
        if (this.balance > newBalance) {
            type = "debit";
        } else {
            type = "credit";
        }
        this.balance = newBalance;
        addNewBankStatement(date, amount, type);
        return true;
    }

    public double getMinLimit() {
        return this.minLimit;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public int getClearingNumber() {
        return this.clearingNumber;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<BankStatement> getStatements() {
        return this.statements;
    }

    private void addNewBankStatement(DateFormat date, double amount, String type) {
        this.statements.add(new BankStatement(date, type, amount, this.balance));
    }
}
