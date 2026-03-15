package PaymentGateway;

import model.User;

public interface PaymentGateway {
    void processPayment(int amount, int userID) throws InterruptedException;

    void paymentReceipt(int amount, int userID, User user);
}
