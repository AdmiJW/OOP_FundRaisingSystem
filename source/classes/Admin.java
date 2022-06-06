package source.classes;

import source.abstracts.AbstractUser;

public class Admin extends AbstractUser {
    public static int nextID = 0;


    // Ctor
    // Constructor with ID defined
    public Admin(int id, String name, String ic, String phone, String email, String password) {
        super(id, name, ic, phone, email, password);
    }

    // Constructor without ID defined - Assign next available ID
    public Admin(String name, String ic, String phone, String email, String password) {
        this(nextID++, name, ic, phone, email, password);
    }
    

    // Methods
    @Override
    public String getPersonalDetails() {
        return String.format( "[Admin]\n%s", super.getPersonalDetails() );
    }

    @Override
    public String serialize() {
        return String.format(
            "Admin 6\n%d\n%s\n%s\n%s\n%s\n%s\n",
            id, name, ic, phone, email, password
        );
    }


    // Static methods
    // Expect to receive the data in String array without the "Admin 6" 
    public static Admin deserialize(String[] args) {
        return new Admin( Integer.parseInt(args[0]), args[1], args[2], args[3], args[4], args[5] );
    }

}
