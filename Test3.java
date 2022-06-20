
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import source.FundRaiserSystem;
import source.classes.Admin;
import source.classes.Application;
import source.classes.Donation;
import source.classes.User;
import source.classes.Util;



public class Test3 {
    static int testcase = 1;
    final static String sep = System.lineSeparator();


    public static void main(String[] args) {
        test_saveUsers();
        test_saveAdmins();
        test_saveCategory();
        test_saveApplications();
        test_saveDonation();
        System.out.println();
        test_loadUsers();
        test_loadAdmins();
        test_loadCategory();
        test_loadApplications();
        test_loadDonations();
        System.out.println();
        test_loadSaveStaticVars();
    }
    


    private static void test_saveUsers() {
        String expected = String.format("%s%s%s%s", TestData.user1Serialized, sep, TestData.user2Serialized, sep);
        FundRaiserSystem.saveUsers();

        try {
            String text = Files.readString(Paths.get( FundRaiserSystem.USER_PATH ));
            Util.assertTrue( text.equals(expected), "test_saveUsers() - Does not match expected save data", testcase++);
        } catch (IOException e) { e.printStackTrace(); }
    }



    private static void test_saveAdmins() {
        String expected = String.format("%s%s%s%s", TestData.admin1Serialized, sep, TestData.admin2Serialized, sep);
        FundRaiserSystem.saveAdmins();

        try {
            String text = Files.readString(Paths.get( FundRaiserSystem.ADMIN_PATH ));
            Util.assertTrue( text.equals(expected), "test_saveAdmins() - Does not match expected save data", testcase++);
        } catch (IOException e) { e.printStackTrace(); }
    }



    private static void test_saveCategory() {
        String expected = String.format("%s%s%s%s", TestData.category1Serialized, sep, TestData.category2Serialized, sep);
        FundRaiserSystem.saveCategory();

        try {
            String text = Files.readString(Paths.get( FundRaiserSystem.CATEGORY_PATH ));
            Util.assertTrue( text.equals(expected), "test_saveCategory() - Does not match expected save data", testcase++);
        } catch (IOException e) { e.printStackTrace(); }
    }



    private static void test_saveApplications() {
        String expected = String.format("%s%s%s%s", TestData.application1Serialized, sep, TestData.application2Serialized, sep);
        FundRaiserSystem.saveApplications();

        try {
            String text = Files.readString(Paths.get( FundRaiserSystem.APPLICATION_PATH ));
            Util.assertTrue( text.equals(expected), "test_saveApplications() - Does not match expected save data", testcase++);
        } catch (IOException e) { e.printStackTrace(); }
    }



    private static void test_saveDonation() {
        String expected = String.format("%s%s%s%s", TestData.donation1Serialized, sep, TestData.donation2Serialized, sep);
        FundRaiserSystem.saveDonations();

        try {
            String text = Files.readString(Paths.get( FundRaiserSystem.DONATION_PATH ));
            Util.assertTrue( text.equals(expected), "test_saveDonation() - Does not match expected save data", testcase++);
        } catch (IOException e) { e.printStackTrace(); }
    }



    private static void test_loadUsers() {
        FundRaiserSystem.loadUsers();

        Util.assertTrue( FundRaiserSystem.users.get(0).serialize().equals( TestData.user1Serialized ), 
            "test_loadUsers() - Loaded User1 does not match expected", 
            testcase++
        );
        Util.assertTrue( FundRaiserSystem.users.get(1).serialize().equals( TestData.user2Serialized ), 
            "test_loadUsers() - Loaded User2 does not match expected", 
            testcase++
        );
    }



    private static void test_loadAdmins() {
        FundRaiserSystem.loadAdmins();

        Util.assertTrue( FundRaiserSystem.admins.get(0).serialize().equals( TestData.admin1Serialized ), 
            "test_loadUsers() - Loaded Admin1 does not match expected", 
            testcase++
        );
        Util.assertTrue( FundRaiserSystem.admins.get(1).serialize().equals( TestData.admin2Serialized ), 
            "test_loadUsers() - Loaded Admin2 does not match expected", 
            testcase++
        );
    }



    private static void test_loadCategory() {
        FundRaiserSystem.loadCategories();

        Util.assertTrue( FundRaiserSystem.categories.get(TestData.category1Category).serialize().equals( TestData.category1Serialized ), 
            "test_loadCategory() - Loaded Category1 does not match expected", 
            testcase++
        );
        Util.assertTrue( FundRaiserSystem.categories.get(TestData.category2Category).serialize().equals( TestData.category2Serialized ), 
            "test_loadCategory() - Loaded Category2 does not match expected", 
            testcase++
        );
    }



    private static void test_loadApplications() {
        FundRaiserSystem.loadApplications();

        Util.assertTrue( FundRaiserSystem.applications.get(0).serialize().equals( TestData.application1Serialized ), 
            "test_loadApplications() - Loaded Application1 does not match expected", 
            testcase++
        );
        Util.assertTrue( FundRaiserSystem.applications.get(1).serialize().equals( TestData.application2Serialized ), 
            "test_loadApplications() - Loaded Application2 does not match expected", 
            testcase++
        );
    }



    private static void test_loadDonations() {
        FundRaiserSystem.loadDonations();

        Util.assertTrue( FundRaiserSystem.donations.get(0).serialize().equals( TestData.donation1Serialized ), 
            "test_loadDonations() - Loaded Donation1 does not match expected", 
            testcase++
        );
        Util.assertTrue( FundRaiserSystem.donations.get(1).serialize().equals( TestData.donation2Serialized ), 
            "test_loadDonations() - Loaded Donation2 does not match expected", 
            testcase++
        );
    }

    private static void test_loadSaveStaticVars() {
        int oriAdminID = Admin.nextID;
        int oriApplicationID = Application.nextID;
        int oriDonationID = Donation.nextID;
        int oriUserID = User.nextID;
        int testAdminID = 10;
        int testApplicationID = 20;
        int testDonationID = 30;
        int testUserID = 40;

        Admin.nextID = testAdminID;
        Application.nextID = testApplicationID;
        Donation.nextID = testDonationID;
        User.nextID = testUserID;
        
        FundRaiserSystem.saveStaticVars();
        // Set the nextID values to anything other than test value, to see if the loadStaticVars() is working
        Admin.nextID = 1000;
        Application.nextID = 2000;
        Donation.nextID = 3000;
        User.nextID = 4000;
        FundRaiserSystem.loadStaticVars();

        Util.assertTrue( Admin.nextID == testAdminID, 
            "test_loadSaveStaticVars() - Admin's Next ID does not match test value", 
            testcase++
        );
        Util.assertTrue( Application.nextID == testApplicationID, 
            "test_loadSaveStaticVars() - Application's Next ID does not match test value", 
            testcase++
        );
        Util.assertTrue( Donation.nextID == testDonationID, 
            "test_loadSaveStaticVars() - Donation's Next ID does not match test value", 
            testcase++
        );
        Util.assertTrue( User.nextID == testUserID, 
            "test_loadSaveStaticVars() - User's Next ID does not match test value", 
            testcase++
        );

        Admin.nextID = oriAdminID;
        Application.nextID = oriApplicationID;
        Donation.nextID = oriDonationID;
        User.nextID = oriUserID;
    }
}
