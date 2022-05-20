package source.classes;

import java.util.List;

import source.enums.Category;
import source.interfaces.ISerializable;

import java.util.ArrayList;

public class CategoryPool implements ISerializable {
    private Category category;
    private double balance;
    private List<Donation> donations;
    private List<Application> pendingApplications;
    private List<Application> verifiedApplications;
    private List<Application> successApplications;
    private List<Application> rejectedApplications;


    // Ctor
    public CategoryPool(Category category, double balance) {
        this.category = category;
        this.balance = balance;

        donations = new ArrayList<>();
        pendingApplications = new ArrayList<>();
        verifiedApplications = new ArrayList<>();
        successApplications = new ArrayList<>();
        rejectedApplications = new ArrayList<>();
    }


    // Getters
    public Category getCategory() { return this.category; }
    public double getBalance() { return this.balance; }
    public List<Donation> getDonationList() { return this.donations; }
    public List<Application> getPendingApplicationList() { return this.pendingApplications; }
    public List<Application> getVerifiedApplicationList() { return this.verifiedApplications; }
    public List<Application> getSuccessApplicationList() { return this.successApplications; }
    public List<Application> getRejectedApplicationList() { return this.rejectedApplications; }
    

    // Setters
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Methods
    public void addDonation(Donation d) {
        this.donations.add(d);
        this.balance += d.getPayment().getAmount();
    }

    public void addNewApplication(Application a) {
        this.pendingApplications.add(a);
    }

    public void rejectApplication(Application a) {
        if (this.pendingApplications.remove(a) )
            this.rejectedApplications.add(a);
        // TODO: If not exists?
    }

    public void verifyApplication(Application a) {
        if (this.pendingApplications.remove(a) )
            this.verifiedApplications.add(a);
        // TODO: If not exists?
    }

    public void finalizeApplication(Application a) {
        if (this.verifiedApplications.remove(a) )
            this.successApplications.add(a);
        // TODO: If not exists?
    }


    @Override
    public String serialize() {
        return String.format(
            "Category 2\n%s\n%.2f",
            category.name(), balance
        );
    }


    // Static methods
    // Expect to receive the data in String array without the "Category 2" 
    public static CategoryPool deserialize(String[] args) {
        return new CategoryPool( Category.valueOf(args[0] ), Double.parseDouble(args[1]) );
    }
}
