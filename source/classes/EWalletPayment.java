package source.classes;

import java.util.Date;
import java.util.Scanner;

import source.abstracts.AbstractPayment;



public class EWalletPayment extends AbstractPayment {

    private String phoneNumber;
    

    // Ctor
    public EWalletPayment(double amount, String phoneNumber, long dateTime) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public EWalletPayment(double amount) {
        this(amount, "", new Date().getTime() );
    }


    // Getter
    public String getPhoneNumber() { return this.phoneNumber; }

    // Setter
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    // Methods
    @Override
    public boolean makePayment() {
        Scanner scan = new Scanner(System.in);

        Util.printMenu(" ~~~ E-Wallet Payment Gateway ~~~ ");
        System.out.printf("The payment amount is: RM%.2f\n\n", this.amount);
        System.out.print("Please enter your phone number: ");
        this.setPhoneNumber( scan.nextLine() );
        System.out.print("Please enter PIN: ");
        scan.nextLine();

        Util.loadingAnimation("Payment processing. Please wait...");
        System.out.println("Payment successful! Your E-wallet payment has been verified!");
        System.out.println();
        Util.pressEnterToContinue();

        return true;
    }

    @Override
    public String getReceipt() {
        return String.format(
            "<<<<< E-Wallet Transaction Receipt >>>>>\n" +
                "Phone number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            phoneNumber,
            amount,
            new Date(dateTime).toString()
        );
    }


    @Override
    public String serialize() {
        return String.format("PaymentEWallet 3\n%.2f\n%s\n%d", amount, phoneNumber, dateTime);
    }


    // Static methods
    // Expect to receive the data in String array without the "PaymentEWallet 3" 
    public static EWalletPayment deserialize(String[] args) {
        return new EWalletPayment( Double.parseDouble(args[0]), args[1], Long.parseLong(args[2]) );
    }
}
