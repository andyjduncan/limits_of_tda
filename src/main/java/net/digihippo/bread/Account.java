package net.digihippo.bread;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private int id;

    private OutboundEvents events;

    private int balance = 0;
    private final Map<Integer, Integer> orders = new HashMap<Integer, Integer>();

    Account(int id, OutboundEvents events) {
        this.id = id;
        this.events = events;
    }

    public void deposit(int creditAmount) {
        balance += creditAmount;
          events.newAccountBalance(id, balance);
    }

    public void addOrder(int orderId, int amount) {
        orders.put(orderId, amount);
    }

    public void cancelOrder(int orderId, int PRICE_OF_BREAD) {
        Integer cancelledQuantity = orders.remove(orderId);
        if (cancelledQuantity == null)
        {
            events.orderNotFound(id, orderId);
            return;
        }

        deposit(cancelledQuantity * PRICE_OF_BREAD);
        events.orderCancelled(id, orderId);
    }

    public void placeOrder(int orderId, int amount, int cost) {
        if ( balance >= cost) {
			addOrder(orderId, amount);
			deposit(-cost);
			events.orderPlaced(id, amount);
		} else {
			events.orderRejected(id);
		}
    }
}
