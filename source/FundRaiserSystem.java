package source;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import source.enums.Category;
import source.interfaces.ISerializable;
import source.classes.Admin;
import source.classes.User;
import source.classes.Donation;
import source.classes.CategoryPool;
import source.classes.Application;


public class FundRaiserSystem {

    final static String ADMIN_PATH = "admins.dat";
    final static String USER_PATH = "users.dat";
    final static String DONATION_PATH = "donations.dat";
    final static String CATEGORY_PATH = "categories.dat";
    final static String APPLICATION_PATH = "applications.dat";


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

    public static void saveAdmins() { save(ADMIN_PATH, admins ); }
    public static void saveUsers() { save(USER_PATH, users ); }
    public static void saveDonations() { save(DONATION_PATH, donations ); }
    public static void saveCategory() { save(CATEGORY_PATH, categories ); }
    public static void saveApplications() { save(APPLICATION_PATH, applications ); }



    //* Loading are done with separate methods */
    public static void loadAdmins() {
        try (Scanner scan = new Scanner( new File(ADMIN_PATH ) ) ) {
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




    // The function to read N lines from the File, as used by the load functions
    private static String[] readNLines(Scanner file, int n) {
        String[] args = new String[n];
        for (int i = 0; i < n; ++i) args[i] = file.nextLine();
        return args;
    }

    
    // Intiializes the system -> Load saved data
    public static void initSystem() {
        // TODO: Init default initial values
        // TODO: Attempt loading
            // TODO: Load static variables like nextID first
            // TODO: Load data
    }
}
