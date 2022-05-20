package source.interfaces;


/*
 * An object that implements IPayable shall allow 
 * payment to be made and receipt to be generated.
*/
public interface IPayable {
    public boolean makePayment();
    public String getReceipt();
}
