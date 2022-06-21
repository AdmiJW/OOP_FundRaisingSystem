package source.classes;

import java.util.List;

import source.enums.Category;
import source.enums.ApplyStatus;
import source.interfaces.ISerializable;

import java.util.ArrayList;

public class CategoryPool implements ISerializable {
    private Category category;
    private double balance;
    private List<Donation> donations;
    private List<Application> applications;


    // Ctor
    public CategoryPool(Category category, double balance) {
        this.category = category;
        this.balance = balance;

        donations = new ArrayList<>();
        applications = new ArrayList<>();
    }


    // Getters
    public Category  getCategory() { return this.category; }
    public double getBalance() { return this.balance; }
    public List<Donation> getDonationList() { return this.donations; }
    public List<Application> getApplicationList() { return this.applications; }

    public int getNumberOfPendingDonation() {
        return (int)(donations.stream().filter(
            (d)-> d.getStatus() == ApplyStatus.PENDING_VERIFICATION || d.getStatus() == ApplyStatus.APPROVED_PENDING_TRANSACTION
        ).count());
    }

    public int getNumberOfPendingApplication() {
        return (int)(applications.stream().filter(
            (a)-> a.getStatus() == ApplyStatus.PENDING_VERIFICATION || a.getStatus() == ApplyStatus.APPROVED_PENDING_TRANSACTION
        ).count());
    }
    

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
    }

    public void addApplication(Application a) {
        this.applications.add(a);
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
