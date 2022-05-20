import java.util.Arrays;
import java.util.Date;

import source.classes.User;
import source.enums.ApplyStatus;
import source.enums.Category;
import source.classes.Admin;
import source.classes.Application;
import source.classes.CategoryPool;
import source.classes.Donation;
import source.classes.EWalletPayment;
import source.classes.FPXPayment;
import source.FundRaiserSystem;
import source.abstracts.AbstractPayment;

public class Test1 {
    static int testcase = 1;

    // Test User
    static String uname = "Chee";
    static String uic = "123456011234";
    static String uphone = "012-3456789";
    static String uemail = "jamweg22@gmail.com";
    static String upassword = "ABC123";
    static String userialized = String.format("User 6\n0\n%s\n%s\n%s\n%s\n%s", uname, uic, uphone, uemail, upassword);
    static User user = new User(uname, uic, uphone, uemail, upassword);

    // Test Admin
    static String aname = "Myn";
    static String aic = "987654011234";
    static String aphone = "012-9876543";
    static String aemail = "mynmyn@gmail.com";
    static String apassword = "PSW987";
    static String aasserted = String.format("Admin 6\n0\n%s\n%s\n%s\n%s\n%s", aname, aic, aphone, aemail, apassword);
    static Admin admin = new Admin(aname, aic, aphone, aemail, apassword);

    // Test CategoryPool
    static Category cc = Category.EDUCATION;
    static double cbalance = 1234.56;
    static String casserted = String.format("Category 2\n%s\n%.2f", cc.name(), cbalance);
    static CategoryPool cat = new CategoryPool(cc, cbalance);

    // Test Application
    static String apdescription = "Help for Chee for education";
    static double apamount = 1234.56;
    static Application application = new Application(user, cat, apdescription, apamount);
    static ApplyStatus apstatus = ApplyStatus.REJECTED;
    static String apstatusdesc = "Not qualified";
    static String apasserted;

    // Test FPXPayment
    static double fpxamount = 699.99;
    static FPXPayment fpxpayment = new FPXPayment(fpxamount);
    static String fpxserialized;
    static String fpxreceipt;

    // Test EWalletPayment
    static double ewamount = 1999.99;
    static EWalletPayment ewallet = new EWalletPayment(ewamount);
    static String ewserialized;
    static String ewreceipt;

    // Test Donation
    static Donation donation = new Donation(user, cat, ewallet);
    static ApplyStatus dstatus = ApplyStatus.APPROVED_PENDING_TRANSACTION;
    static String dstatusdesc = "Approved. Wait for transaction";
    static String dserialized;
    


    // Initialization
    static {
        FundRaiserSystem.users.put( user.getID(), user );
        FundRaiserSystem.admins.put( admin.getID(), admin );
        FundRaiserSystem.categories.put( cat.getCategory(), cat );
        FundRaiserSystem.applications.put( application.getID(), application);
        FundRaiserSystem.donations.put( donation.getID(), donation);

        // Application init
        application.setStatus( apstatus, apstatusdesc, admin);
        apasserted = String.format(
            "Application 9\n%d\n%s\n%.2f\n%s\n%d\n%d\n%s\n%s\n%d",
            application.getID(),
            apdescription,
            apamount,
            cat.getCategory().name(),
            user.getID(),
            application.getDateTime(),
            apstatus.name(),
            apstatusdesc,
            admin.getID()
        );

        // FPXPayment init
        fpxpayment.setCardNumber("1357-2468-1357-2468");
        fpxreceipt = String.format(
            "<<<<< FPX Transaction Receipt >>>>>\n" +
                "Card number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            fpxpayment.getCardNumber(),
            fpxpayment.getAmount(),
            new Date(fpxpayment.getDateTime()).toString()
        );
        fpxserialized = String.format("PaymentFPX 3\n%.2f\n%s\n%d", fpxpayment.getAmount(), fpxpayment.getCardNumber(), fpxpayment.getDateTime());

        // EWalletPayment init
        ewallet.setPhoneNumber("011-2468369");
        ewreceipt = String.format(
            "<<<<< E-Wallet Transaction Receipt >>>>>\n" +
                "Phone number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            ewallet.getPhoneNumber(),
            ewallet.getAmount(),
            new Date(ewallet.getDateTime()).toString()
        );
        ewserialized = String.format("PaymentEWallet 3\n%.2f\n%s\n%d", ewallet.getAmount(), ewallet.getPhoneNumber(), ewallet.getDateTime());

        // Donation init
        donation.setStatus(dstatus, dstatusdesc, admin);
        dserialized = String.format(
            "Donation %d\n%d\n%s\n%s\n%s\n%s\n%s\n%s",
            6 + ( ewallet.serialize().chars().filter(ch -> ch == '\n').count() + 1 ),
            donation.getID(),
            user.getID(),
            cat.getCategory().name(),
            donation.getStatus().name(),
            donation.getStatusDescription(),
            donation.getStatusAdmin().getID(),
            ewallet.serialize()
        );
        dserialized = donation.serialize();
    }







    public static void main(String[] args) {
        test_UserClass();
        test_AdminClass();
        test_CategoryPoolClass();
        test_ApplicationClass();
        test_FPXPaymentClass();
        test_EwalletPaymentClass();
        test_DonationClass();
    }






    private static void test_UserClass() {
        String data = user.serialize();
        assertTrue(data.equals(userialized), "test_UserClass() - Problem in serialize() of User");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        User reconstructed = User.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(userialized), "test_UserClass() - Problem in deserialize() of User");
        System.out.println();
    }


    private static void test_AdminClass() {
        String data = admin.serialize();
        assertTrue(data.equals(aasserted), "test_AdminClass() - Problem in serialize() of Admin");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Admin reconstructed = Admin.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(aasserted), "test_AdminClass() - Problem in deserialize() of Admin");
        System.out.println();
    }



    private static void test_CategoryPoolClass() {
        String data = cat.serialize();
        assertTrue(data.equals(casserted), "test_CategoryPoolClass() - Problem in serialize() of CategoryPool");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        CategoryPool reconstructed = CategoryPool.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(casserted), "test_CategoryPoolClass() - Problem in deserialize() of CategoryPool");
        System.out.println();
    }


    private static void test_ApplicationClass() {
        String data = application.serialize();
        assertTrue(data.equals(apasserted), "test_ApplicationClass() - Problem in serialize() of Application");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Application reconstructed = Application.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(apasserted), "test_ApplicationClass() - Problem in deserialize() of Application");
        System.out.println();
    }


    private static void test_FPXPaymentClass() {
        String data = fpxpayment.serialize();
        assertTrue(data.equals(fpxserialized), "test_FPXPaymentClass() - Problem in serialize() of FPXPayment");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        AbstractPayment reconstructed = FPXPayment.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(fpxserialized), "test_FPXPaymentClass() - Problem in deserialize() of FPXPayment");
        assertTrue(reconstructed.getReceipt().equals(fpxreceipt), "test_FPXPaymentClass() - Problem in getReceipt() of FPXPayment");
        System.out.println();
    }


    private static void test_EwalletPaymentClass() {
        String data = ewallet.serialize();
        assertTrue(data.equals(ewserialized), "test_EwalletPaymentClass() - Problem in serialize() of EWalletPayment");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        AbstractPayment reconstructed = EWalletPayment.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(ewserialized), "test_EwalletPaymentClass() - Problem in deserialize() of EWalletPayment");
        assertTrue(reconstructed.getReceipt().equals(ewreceipt), "test_EwalletPaymentClass() - Problem in getReceipt() of EWalletPayment");
        System.out.println();
    }


    private static void test_DonationClass() {
        String data = donation.serialize();
        assertTrue(data.equals(dserialized), "test_DonationClass() - Problem in serialize() of Donation");

        String[] rawargs = data.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Donation reconstructed = Donation.deserialize(args);
        
        assertTrue(reconstructed.serialize().equals(dserialized), "test_DonationClass() - Problem in deserialize() of Donation");
        System.out.println();
    }




    private static void assertTrue(boolean condition, String message) {
        if (!condition) System.out.printf("(X) Test case #%d FAILED: %s\n", testcase++, message);
        else System.out.printf("(OK) Test case #%d PASSED.\n", testcase++);
    }
}
