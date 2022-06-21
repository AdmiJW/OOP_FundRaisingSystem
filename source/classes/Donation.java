package source.classes;

import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

import source.FundRaiserSystem;
import source.abstracts.AbstractPayment;
import source.enums.ApplyStatus;
import source.enums.Category;
import source.interfaces.ISerializable;
import source.interfaces.IStatus;

public class Donation implements ISerializable, IStatus {
    public static int nextID = 0;

    private int id;
    private User donor;
    private CategoryPool donationCategory;
    private AbstractPayment payment;

    private ApplyStatus status;
    private Admin statusAdmin;
    private String statusDescription;


    // Ctor
    // Constructor with ID defined
    public Donation(int id, User donor, CategoryPool category, AbstractPayment payment) {
        this.id = id;
        this.donor = donor;
        this.donationCategory = category;
        this.payment = payment;

        status = ApplyStatus.PENDING_VERIFICATION;
        statusAdmin = null;
        statusDescription = "";
    }

    // Constructor without ID defined - Assign next available ID
    public Donation(User donor, CategoryPool category, AbstractPayment payment) {
        this(nextID++, donor, category, payment);
    }


    // Getters
    public int getID() { return this.id; }
    public User getDonor() { return this.donor; }
    public CategoryPool getCategory() { return this.donationCategory; }
    public AbstractPayment getPayment() { return this.payment; }

    public ApplyStatus getStatus() { return this.status; }
    public Admin getStatusAdmin() { return this.statusAdmin; }
    public String getStatusDescription() { return this.statusDescription; }

    

    // Setters
    public void setID(int id) {
        this.id = id;
    }
    public void setDonor(User user) {
        this.donor = user;
    }
    public void setCategory(CategoryPool category) {
        this.donationCategory = category;
    }
    public void setPayment(AbstractPayment payment) {
        this.payment = payment;
    }

    @Override
    public void setStatus(ApplyStatus status, String description, Admin admin) {
        this.status = status;
        this.statusDescription = description;
        this.statusAdmin = admin;
    }



    // Methods
    public static void printListHeader() {
        System.out.printf("%-6s%-15s%-30s%s\n", "Index", "Date", "Donor", "Status");
    }

    public void printAsAList(int index) {
        System.out.printf("%-6d%-15s%-30s%s\n", index, new SimpleDateFormat("yyyy/MM/dd").format(new Date(payment.getDateTime())), this.donor.getName(), this.status );
    }

    public void printDetails() {
        Util.printMenu("~~~ Donation #" + this.id + " ~~~");
        System.out.printf("%20s: RM%.2f\n", "Donation Amount", payment.getAmount());
        System.out.printf("%20s: %s\n", "Category", donationCategory);
        System.out.printf("%20s: %s\n", "Donor", donor.getName() );
        System.out.printf("%20s: %s\n", "Date", new SimpleDateFormat("yyyy/MM/dd").format(new Date(payment.getDateTime())) );
        System.out.printf("%20s: %s\n", "Status", status );
        System.out.printf("%20s: %s\n", "Status Description", statusDescription );
        System.out.printf("%20s: %s\n", "Handling Admin", statusAdmin.getName() );
    }


    @Override
    public String serialize() {
        // Count number of lines that payment string taken
        String paymentSerialized = payment.serialize();
        long lines = paymentSerialized.chars().filter(ch -> ch == '\n').count() + 1;

        // HEADER, ID, donor, category, status, description, admin, payment...N,
        return String.format(
            "Donation %d\n%d\n%s\n%s\n%s\n%s\n%s\n%s",
            6 + lines,
            id,
            donor.getID(),
            donationCategory.getCategory().name(),
            status.name(),
            statusDescription,
            statusAdmin == null? "": Integer.toString( statusAdmin.getID() ),
            paymentSerialized
        );
    }


    // Static methods
    // Expect to receive the data in String array without the "Donation N" 
    // ID, donor, category, status, description, admin, payment...N,
    public static Donation deserialize(String[] args) {
        User donor = FundRaiserSystem.users.get( Integer.parseInt(args[1] ));
        CategoryPool category = FundRaiserSystem.categories.get( Category.valueOf(args[2] ) );
        Admin admin = args[5].equals("")? null: FundRaiserSystem.admins.get( Integer.parseInt(args[5]));

        // Polymorphism deserializing
        AbstractPayment payment = null;
        String[] paymentArgs = Arrays.copyOfRange(args, 7, args.length);
        if (args[6].startsWith("PaymentEWallet")) 
            payment = EWalletPayment.deserialize( paymentArgs );
        else
            payment = FPXPayment.deserialize( paymentArgs );

        Donation donation = new Donation( Integer.parseInt(args[0]), donor, category, payment);
        donation.setStatus( ApplyStatus.valueOf(args[3]), args[4], admin);
        return donation;
    }

}
