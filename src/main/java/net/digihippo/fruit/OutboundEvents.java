package net.digihippo.fruit;

public interface OutboundEvents {
    void accountCreatedSuccessfully(int accountId);

    void newAccountBalance(int accountId, int newBalanceAmount);

    void accountNotFound(int accountId);

    void orderPlaced(int accountId, int amount);

    void orderRejected(int accountId);
}