package PaymentGateway;

import model.User;

public class Wallet implements PaymentGateway {
    @Override
    public void processPayment(int amount, int userID) throws InterruptedException {
        String CYAN = "\u001B[36m";
        System.out.print(CYAN + "Processing");
        Thread.sleep(1000);
    }

    @Override
    public void paymentReceipt(int amount, int userID, User user) {
        System.out.println("\n\u001B[32m" + "==========================================");
        System.out.println("            PAYMENT SUCCESSFUL            ");
        System.out.println("==========================================" + "\u001B[0m");
        System.out.printf(" Transaction ID : LIBM-%d%d\n", user.getUserID(), System.currentTimeMillis() % 1000);
        System.out.printf(" Amount Paid    : ₹%d\n", user.getFine_due());
        System.out.print(" Status         : CLEAR\n");
        System.out.println("------------------------------------------");
    }

}
