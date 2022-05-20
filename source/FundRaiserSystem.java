package source;

import java.util.Map;
import java.util.HashMap;
import source.enums.Category;
import source.classes.Admin;
import source.classes.User;
import source.classes.Donation;
import source.classes.CategoryPool;
import source.classes.Application;


public class FundRaiserSystem {

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

    
    // Intiializes the system -> Load saved data
    public static void initSystem() {
        // TODO: Init default initial values
        // TODO: Attempt loading
            // TODO: Load static variables like nextID first
            // TODO: Load data
    }
}
