package com.atm.config;

import com.atm.service.AccountService;

public class ApplicationBean {

    private static ApplicationBean instance;

    public static ApplicationBean getInstance() {
        if (instance == null) {
            instance = new ApplicationBean();
        }
        return instance;
    }

    protected ApplicationBean() {

        System.out.println("Application bean initialized !");

    }

    private volatile AccountService accountService;

    public AccountService getAccountService() {

        if (accountService == null) {
            synchronized (this) {
                if (accountService == null) {
                    accountService = new AccountService(getInstance());
                }
            }
        }
        return accountService;

    }

}
