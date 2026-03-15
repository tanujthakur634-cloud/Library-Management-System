package PaymentGateway;

import model.User;

import java.util.Random;

public class Wallet implements PaymentGateway {
    @Override
    public boolean processPayment(int amount, int userID) throws InterruptedException {
        System.out.println("Processing...");
        Thread.sleep(1000);
        return true;
    }

    @Override
    public void paymentReceipt(int amount, int userID, User user) {
        Random random = new Random();
        int transactionNumber = random.nextInt(100000000, 1000000000);
        System.out.println("\n\u001B[32m" + "==========================================");
        System.out.println("            PAYMENT SUCCESSFUL            ");
        System.out.println("==========================================" + "\u001B[0m");
        System.out.printf(" Transaction ID : LIBM-%d%d\n", user.getUserID(), System.currentTimeMillis() % 1000);
        System.out.printf(" Amount Paid    : ₹%.2f\n", user.getFine_due());
        System.out.print(" Status         : CLEAR\n");
        System.out.println("------------------------------------------");
    }

}
