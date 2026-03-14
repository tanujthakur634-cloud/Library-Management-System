package PaymentGateway;

public interface paymentGateway {
    boolean processPayment(int amount , int userID) throws InterruptedException;
    void paymentReceipt(int amount,int userID);
}
