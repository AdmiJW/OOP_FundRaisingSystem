package source.classes;

import java.util.List;

import source.abstracts.AbstractUser;

import java.util.ArrayList;

public class User extends AbstractUser {
    public static int nextID = 0;

    private List<Donation> donations;
    private List<Application> applications;


    // Ctor
    // Constructor with ID defined
    public User(int id, String name, String ic, String phone, String email, String password) {
        super(id, name, ic, phone, email, password);
        donations = new ArrayList<>();
        applications = new ArrayList<>();
        nextID++;
    }

    // Constructor without ID defined - Assign next available ID
    public User(String name, String ic, String phone, String email, String password) {
        this(nextID++, name, ic, phone, email, password);
    }

    // Getters
    public List<Donation> getDonations() { return this.donations; }
    public List<Application> getApplications() { return this.applications; }
    

    // Methods
    @Override
    public String getPersonalDetails() {
        return String.format( 
            "[User]\n%s\nNumber of donations: %d\nNumber of applications: %d", 
            super.getPersonalDetails(),
            this.donations.size(),
            this.applications.size()
        );
    }

    @Override
    public String serialize() {
        return String.format(
            "User 6\n%d\n%s\n%s\n%s\n%s\n%s",
            id, name, ic, phone, email, password
        );
    }


    // Static methods
    // Expect to receive the data in String array without the "User 6" 
    public static User deserialize(String[] args) {
        return new User( Integer.parseInt(args[0]), args[1], args[2], args[3], args[4], args[5] );
    }

}
