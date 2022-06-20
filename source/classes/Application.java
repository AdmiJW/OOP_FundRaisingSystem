package source.classes;

import java.util.Date;
import java.text.SimpleDateFormat;
import source.enums.Category;
import source.FundRaiserSystem;
import source.enums.ApplyStatus;
import source.interfaces.ISerializable;
import source.interfaces.IStatus;

public class Application implements ISerializable, IStatus {
    public static int nextID = 0;

    private int id;
    private String description;
    private double requestAmount;
    private CategoryPool category;
    private User requestor;
    private long dateTime;

    private ApplyStatus status;
    private String statusDescription;
    private Admin statusAdmin;



    // Ctor
    // Constructor with ID defined
    public Application(int id, long dateTime, User requestor, CategoryPool category, String description, double requestAmount) {
        this.id = id;
        this.requestor = requestor;
        this.category = category;
        this.description = description;
        this.requestAmount = requestAmount;
        this.dateTime = dateTime;

        status = ApplyStatus.PENDING_VERIFICATION;
        statusAdmin = null;
        statusDescription = "";
    }

    // Constructor without ID and date defined - Assign next available ID and current time
    public Application(User requestor, CategoryPool category, String description, double requestAmount) {
        this(nextID++, new Date().getTime(), requestor, category, description, requestAmount);
    }



    // Getters
    public int getID() { return this.id; }
    public String getDescription() { return this.description; }
    public double getRequestAmount() { return this.requestAmount; }
    public CategoryPool getCategory() { return this.category; }
    public User getRequestor() { return this.requestor; }
    public long getDateTime() { return this.dateTime; }

    public ApplyStatus getStatus() { return this.status; }
    public String getStatusDescription() { return this.statusDescription; }
    public Admin getStatusAdmin() { return this.statusAdmin; }



    // Setters
    public void setID(int id) {
        this.id = id;
    }
    public void setRequestor(User requestor) {
        this.requestor = requestor;
    }
    public void setCategory(CategoryPool category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }
    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public void setStatus(ApplyStatus status, String description, Admin admin) {
        this.status = status;
        this.statusDescription = description;
        this.statusAdmin = admin;
    }


    // Methods
    public static void printListHeader() {
        System.out.printf("%-6s%-15s%-30s%s\n", "Index", "Date", "Requestor", "Status");
    }

    public void printAsAList(int index) {
        System.out.printf("%-6d%-15s%-30s%s\n", index, new SimpleDateFormat("yyyy/MM/dd").format(new Date(dateTime)), this.requestor.getName(), this.status );
    }

    public void printDetails() {
        Util.printMenu("~~~ Application #" + this.id + " ~~~");
        System.out.printf("%20s: %s\n", "Description", description);
        System.out.printf("%20s: RM%.2f\n", "Requested Amount", requestAmount);
        System.out.printf("%20s: %s\n", "Category", category);
        System.out.printf("%20s: %s\n", "Requestor", requestor.getName() );
        System.out.printf("%20s: %s\n", "Date", new SimpleDateFormat("yyyy/MM/dd").format(new Date(dateTime)) );
        System.out.printf("%20s: %s\n", "Status", status );
        System.out.printf("%20s: %s\n", "Status Description", statusDescription );
        System.out.printf("%20s: %s\n", "Handling Admin", statusAdmin.getName() );
    }


    @Override
    public String serialize() {
        // HEADER, ID, Description, requestAmount, catagory, requestor, dateTime, status, statusDesc, Admin
        return String.format(
            "Application 9\n%d\n%s\n%.2f\n%s\n%d\n%d\n%s\n%s\n%d",
            id,
            description,
            requestAmount,
            category.getCategory().name(),
            requestor.getID(),
            dateTime,
            status.name(),
            statusDescription,
            statusAdmin.getID()
        );
    }


    // Static methods
    // Expect to receive the data in String array without the "Application 9" 
    // ID, Description, requestAmount, catagory, requestor, dateTime, status, statusDesc, Admin
    public static Application deserialize(String[] args) {
        User requestor = FundRaiserSystem.users.get( Integer.parseInt(args[4] ));
        CategoryPool category = FundRaiserSystem.categories.get( Category.valueOf(args[3] ) );
        Admin admin = args[8].equals("")? null: FundRaiserSystem.admins.get( Integer.parseInt(args[8]));

        Application application = new Application(
            Integer.parseInt(args[0]), 
            Long.parseLong(args[5]), 
            requestor,
            category,
            args[1], 
            Double.parseDouble(args[2])
        );
        application.setStatus( ApplyStatus.valueOf(args[6]), args[7], admin);
        return application;
    }
}
