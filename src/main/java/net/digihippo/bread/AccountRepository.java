package net.digihippo.bread;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private final Map<Integer, Account> accounts = new HashMap<Integer, Account>();

    private OutboundEvents events;

    public AccountRepository(OutboundEvents events) {

        this.events = events;
    }

    void addAccount(int id, Account newAccount) {
        accounts.put(id, newAccount);
    }

    public void deposit(int accountId, int creditAmount) {
        Account account = accounts.get(accountId);
        if (account != null) {
            account.deposit(creditAmount);
        } else {
            events.accountNotFound(accountId);
        }
    }

    public void placeOrder(int accountId, int orderId, int amount, int PRICE_OF_BREAD) {

        Account account = accounts.get(accountId);
        if (account != null) {
            int cost = amount * PRICE_OF_BREAD;
            account.placeOrder(orderId, amount, cost);
        } else {
            events.accountNotFound(accountId);
        }
    }

    public void cancelOrder(int accountId, int orderId, int PRICE_OF_BREAD) {
        Account account = accounts.get(accountId);
        if (account == null)
        {
            events.accountNotFound(accountId);
            return;
        }

        account.cancelOrder(orderId, PRICE_OF_BREAD);


    }
}