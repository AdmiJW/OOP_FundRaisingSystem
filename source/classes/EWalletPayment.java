package source.classes;

import java.util.Date;

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
        // TODO: Enter Phone number (and set it)
        // TODO: Enter Pin number

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
        return String.format("PaymentEWallet 3\n%.2f\n%s\n%d\n", amount, phoneNumber, dateTime);
    }


    // Static methods
    // Expect to receive the data in String array without the "PaymentEWallet 3" 
    public static EWalletPayment deserialize(String[] args) {
        return new EWalletPayment( Double.parseDouble(args[0]), args[1], Long.parseLong(args[2]) );
    }
}
