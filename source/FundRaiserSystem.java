package source;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
    public static void saveAdmins() {
        BufferedWriter output = null;
        try {
            File file = new File( ADMIN_PATH );
            output = new BufferedWriter( new FileWriter(file) );
            for ( Admin a: admins.values() ) output.write( a.serialize() );
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save admin.");
            e.printStackTrace();
        } finally {
            try { output.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }


    public static void saveUsers() {
        BufferedWriter output = null;
        try {
            File file = new File( USER_PATH );
            output = new BufferedWriter( new FileWriter(file) );
            for ( Admin a: admins.values() ) output.write( a.serialize() );
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save users.");
            e.printStackTrace();
        } finally {
            try { output.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }


    public static void saveDonations() {
        BufferedWriter output = null;
        try {
            File file = new File( DONATION_PATH );
            output = new BufferedWriter( new FileWriter(file) );
            for ( Admin a: admins.values() ) output.write( a.serialize() );
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save donations.");
            e.printStackTrace();
        } finally {
            try { output.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }


    public static void saveCategory() {
        BufferedWriter output = null;
        try {
            File file = new File( CATEGORY_PATH );
            output = new BufferedWriter( new FileWriter(file) );
            for ( Admin a: admins.values() ) output.write( a.serialize() );
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save categories.");
            e.printStackTrace();
        } finally {
            try { output.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }


    public static void saveApplications() {
        BufferedWriter output = null;
        try {
            File file = new File( APPLICATION_PATH );
            output = new BufferedWriter( new FileWriter(file) );
            for ( Admin a: admins.values() ) output.write( a.serialize() );
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to save applications.");
            e.printStackTrace();
        } finally {
            try { output.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }


    public static void loadAdmins() {
        Scanner scan = null;
        try {
            scan = new Scanner( new File(ADMIN_PATH) );

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
        } finally { scan.close(); }
    }


    public static void loadUsers() {
        Scanner scan = null;
        try {
            scan = new Scanner( new File(USER_PATH) );

            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                User u = User.deserialize( readNLines(scan, n) );
                users.put( u.getID(), u );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load users.");
            e.printStackTrace();
        } finally { scan.close(); }
    }


    public static void loadDonations() {
        Scanner scan = null;
        try {
            scan = new Scanner( new File(DONATION_PATH) );

            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                Donation d = Donation.deserialize( readNLines(scan, n) );
                donations.put( d.getID(), d );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load donations.");
            e.printStackTrace();
        } finally { scan.close(); }
    }


    public static void loadCategories() {
        Scanner scan = null;
        try {
            scan = new Scanner( new File(CATEGORY_PATH) );

            while (scan.hasNextLine()) {
                String[] type = scan.nextLine().split(" ");
                if (type.length != 2) return;
                int n = Integer.parseInt(type[1]);
                CategoryPool c = CategoryPool.deserialize( readNLines(scan, n) );
                categories.put( c.getCategory(), c );
            }
        } catch (IOException e) {
            System.err.println("*** FATAL ERROR ***\nFailed to load category pools.");
            e.printStackTrace();
        } finally { scan.close(); }
    }



    public static void loadApplications() {
        Scanner scan = null;
        try {
            scan = new Scanner( new File(CATEGORY_PATH) );

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
        } finally { scan.close(); }
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
