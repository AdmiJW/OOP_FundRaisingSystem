package source.abstracts;

import source.interfaces.IPersonalDetails;
import source.interfaces.ISerializable;

public abstract class AbstractUser implements IPersonalDetails, ISerializable {
    protected int id;
    protected String name;
    protected String ic;
    protected String phone;
    protected String email;

    protected String password;


    // Ctors
    public AbstractUser(int id, String name, String ic, String phone, String email, String password) {
        setID(id);
        setName(name);
        setIC(ic);
        setPhone(phone);
        setEmail(email);

        this.password = password;
    }

    // Getters
    @Override
    public int getID() { return this.id; }
    @Override
    public String getName() { return this.name; }
    @Override
    public String getIC() { return this.ic; }
    @Override
    public String getPhone() { return this.phone; }
    @Override
    public String getEmail() { return this.email; }

    // Setters
    @Override
    public void setID(int id) {
        this.id = id;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setIC(String ic) {
        this.ic = ic;
    }
    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    // Methods
    @Override
    public String getPersonalDetails() {
        return String.format(
            "ID: %d\nName: %s\nIC: %s\nPhone: %s\nEmail: %s",
            id, name, ic, phone, email
        );
    }

    @Override
    public abstract String serialize();

    public boolean comparePassword(String toCompare) {
        return this.password.compareTo(toCompare) == 0;
    }
}
