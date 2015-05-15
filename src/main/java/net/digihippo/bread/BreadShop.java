package net.digihippo.bread;

public class BreadShop {
    private static int PRICE_OF_BREAD = 12;

    private final OutboundEvents events;
    final AccountRepository accountRepository;

    private int outstandingQuantity = 0;

    public BreadShop(OutboundEvents events) {
        this.events = events;
        accountRepository = new AccountRepository(events);
    }

    public void createAccount(int id) {
        Account newAccount = new Account(id, events);
        accountRepository.addAccount(id, newAccount);
        events.accountCreatedSuccessfully(id);
    }

    public void deposit(int accountId, int creditAmount) {
        accountRepository.deposit(accountId, creditAmount);
    }

    public void placeOrder(int accountId, int orderId, int amount) {
        outstandingQuantity += amount;
        accountRepository.placeOrder(accountId, orderId, amount, PRICE_OF_BREAD);
    }

    public void cancelOrder(int accountId, int orderId) {
        accountRepository.cancelOrder(accountId, orderId, PRICE_OF_BREAD);
    }

    public void placeWholesaleOrder() {
        events.placeWholesaleOrder(outstandingQuantity);
    }

    public void onWholesaleOrder(int quantity) {
        throw new UnsupportedOperationException("Implement me in Objective B");
    }
}
