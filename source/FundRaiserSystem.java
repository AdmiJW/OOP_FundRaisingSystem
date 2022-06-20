package source;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import source.enums.Category;
import source.interfaces.ISerializable;
import source.classes.Admin;
import source.classes.User;
import source.classes.Donation;
import source.classes.CategoryPool;
import source.classes.Application;


public class FundRaiserSystem {

    final public static String ADMIN_PATH = "data/admins.dat";
    final public static String USER_PATH = "data/users.dat";
    final public static String DONATION_PATH = "data/donations.dat";
    final public static String CATEGORY_PATH = "data/categories.dat";
    final public static String APPLICATION_PATH = "data/applications.dat";
    final public static String STATICVAR_PATH = "data/static.dat";

    public static Map<Integer, Admin> admins;
    public static Map<Integer, User> users;
    public static Map<Integer, Donation> donations;
    public static Map<Category, CategoryPool> categories;
    public static Map<Integer, Application> applications;
    
    // Static initializer
    static {
        admins = new HashMap<>();
        users = new HashMap<>();
        donations = new HashMap<>();
        categories = new HashMap<>();
        applications = new HashMap<>();

        initSystem();
    }


    //* ======================
    //* Save and load files
    //* ======================
    
    //* The general save method that will be called by the other variants, such as saveAdmins */
    @SuppressWarnings("rawtypes")
    private static void save(String path, Map toSave) {
        try (PrintWriter pw = new PrintWriter( path )) {
            for (Object o: toSave.values() ) pw.println( ((ISerializable)o).serialize() );
        } 
        catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save admin.");
            e.printStackTrace();
        }
    }

    //* The save method for he classes's static nextID field. */
    public static void saveStaticVars() {
        try (PrintWriter pw = new PrintWriter( STATICVAR_PATH )) {
            pw.println(Admin.nextID);
            pw.println(Application.nextID);
            pw.println(Donation.nextID);
            pw.println(User.nextID);
        } 
        catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save static variables.");
            e.printStackTrace();
        }
    }

    public static void saveAdmins() { save(ADMIN_PATH, admins ); }
    public static void saveUsers() { save(USER_PATH, users ); }
    public static void saveDonations() { save(DONATION_PATH, donations ); }
    public static void saveCategory() { save(CATEGORY_PATH, categories ); }
    public static void saveApplications() { save(APPLICATION_PATH, applications ); }


    //* Loading are done with separate methods */
    //! Warning: Loading will clear out the existing data in memory! */
    public static void loadAdmins() {
        try (Scanner scan = new Scanner( new File(ADMIN_PATH ) ) ) {
            admins.clear();
            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                Admin a = Admin.deserialize( readNLines(scan, n) );
                admins.put( a.getID(), a );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load admins.");
            e.printStackTrace();
        }
    }

    public static void loadUsers() {
        try (Scanner scan = new Scanner( new File(USER_PATH ) ) ) {
            users.clear();
            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                User a = User.deserialize( readNLines(scan, n) );
                users.put( a.getID(), a );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load users.");
            e.printStackTrace();
        }
    }

    public static void loadDonations() {
        try (Scanner scan = new Scanner( new File(DONATION_PATH ) ) ) {
            donations.clear();
            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                Donation a = Donation.deserialize( readNLines(scan, n) );
                donations.put( a.getID(), a );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load donations.");
            e.printStackTrace();
        }
    }

    public static void loadCategories() {
        try (Scanner scan = new Scanner( new File(CATEGORY_PATH ) ) ) {
            categories.clear();
            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                CategoryPool a = CategoryPool.deserialize( readNLines(scan, n) );
                categories.put( a.getCategory(), a );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load categories.");
            e.printStackTrace();
        }
    }

    public static void loadApplications() {
        try (Scanner scan = new Scanner( new File(APPLICATION_PATH ) ) ) {
            applications.clear();
            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                Application a = Application.deserialize( readNLines(scan, n) );
                applications.put( a.getID(), a );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load applications.");
            e.printStackTrace();
        }
    }

    public static void loadStaticVars() {
        try (Scanner scan = new Scanner( new File(STATICVAR_PATH ) ) ) {
            Admin.nextID = Integer.parseInt( scan.nextLine() );
            Application.nextID = Integer.parseInt( scan.nextLine() );
            Donation.nextID = Integer.parseInt( scan.nextLine() );
            User.nextID = Integer.parseInt( scan.nextLine() );
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load static vars.");
            e.printStackTrace();
        }
    }


    // The function to read N lines from the File, as used by the load functions
    private static String[] readNLines(Scanner file, int n) {
        String[] args = new String[n];
        for (int i = 0; i < n; ++i) args[i] = file.nextLine();
        return args;
    }

    
    // Intiializes the system -> Load saved data
    public static void initSystem() {
        // Add an default admin to the system
        admins.put(-1, new Admin(-1, "admin", "010203-04-0506", "011-12345678", "def@gmail.com", "1234"));

        //* Create the 'data' directory automatically if not exists */
        File f = new File("data");
        if (!f.exists()) f.mkdir();
        
        // Load from the file if exists
        if ( new File(STATICVAR_PATH).exists() ) loadStaticVars();
        if ( new File(ADMIN_PATH).exists() ) loadAdmins();
        if ( new File(USER_PATH).exists() ) loadUsers();
        if ( new File(CATEGORY_PATH).exists() ) loadCategories();
        if ( new File(APPLICATION_PATH).exists() ) loadApplications();
        if ( new File(DONATION_PATH).exists() ) loadDonations();
    }

}
