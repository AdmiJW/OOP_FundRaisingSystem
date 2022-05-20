package source.interfaces;


/*
 * Any class that implements this interface, must contain the following fields:
 * 
 * - ID
 * - Name
 * - IC
 * - Email
 * - Phone
 * 
 * and able to retrieve the details as a string.
*/
public interface IPersonalDetails {
    public int getID();
    public void setID(int id);
    public String getName();
    public void setName(String name);
    public String getIC();
    public void setIC(String ic);
    public String getEmail();
    public void setEmail(String email);
    public String getPhone();
    public void setPhone(String phone);

    public String getPersonalDetails();
}
