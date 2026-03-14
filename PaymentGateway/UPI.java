package PaymentGateway;

import java.util.Random;

public class UPI implements paymentGateway{

    @Override
    public boolean processPayment(int amount, int userID) throws InterruptedException {
        System.out.println("Processing...");
        Thread.sleep(1000);
        System.out.println("Payment Successful");
        return true;
    }

    @Override
    public void paymentReceipt(int amount, int userID) {
        Random random = new Random();
        int transactionNumber = random.nextInt(100000000,1000000000);
        System.out.println("Payment Amount : ₹"+amount);
        System.out.println("Transaction Number : "+transactionNumber);
    }
}
