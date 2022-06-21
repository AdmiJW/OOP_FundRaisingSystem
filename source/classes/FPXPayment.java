package source.classes;

import java.util.Date;
import java.util.Scanner;

import source.abstracts.AbstractPayment;



public class FPXPayment extends AbstractPayment {

    private String cardNumber;
    

    // Ctor
    public FPXPayment(double amount, String cardNumber, long dateTime) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public FPXPayment(double amount) {
        this(amount, "", new Date().getTime() );
    }


    // Getter
    public String getCardNumber() { return this.cardNumber; }

    // Setter
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    


    // Methods
    @Override
    public boolean makePayment() {
        Scanner scan = new Scanner(System.in);

        Util.printMenu(" ~~~ E-Wallet Payment Gateway ~~~ ");
        System.out.printf("The payment amount is: RM%.2f\n\n", this.amount);
        System.out.print("Please enter your credit/debit card number: ");
        this.setCardNumber( scan.nextLine() );
        System.out.print("Please enter CVV: ");
        scan.nextLine();

        Util.loadingAnimation("Payment processing. Please wait...");
        System.out.println("Payment successful! Your FPX Transfer has been verified!");
        System.out.println();
        Util.pressEnterToContinue();

        return true;
    }

    @Override
    public String getReceipt() {
        return String.format(
            "<<<<< FPX Transaction Receipt >>>>>\n" +
                "Card number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            cardNumber,
            amount,
            new Date(dateTime).toString()
        );
    }


    @Override
    public String serialize() {
        return String.format("PaymentFPX 3\n%.2f\n%s\n%d", amount, cardNumber, dateTime);
    }


    // Static methods
    // Expect to receive the data in String array without the "PaymentFPX 3" 
    public static FPXPayment deserialize(String[] args) {
        return new FPXPayment( Double.parseDouble(args[0]), args[1], Long.parseLong(args[2]) );
    }
}
