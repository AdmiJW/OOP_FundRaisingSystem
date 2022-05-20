package source.abstracts;

import source.interfaces.IPayable;
import source.interfaces.ISerializable;


public abstract class AbstractPayment implements IPayable, ISerializable {
    protected double amount;
    protected long dateTime;


    // Getters
    public double getAmount() { return this.amount; }
    public long getDateTime() { return this.dateTime; }

    // Setters
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
