package source.classes;

import java.util.Date;

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
        // TODO: Enter card number (and set it)
        // TODO: Enter OTP

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
