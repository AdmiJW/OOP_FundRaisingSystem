import java.util.Date;
import source.classes.*;
import source.enums.*;
import source.FundRaiserSystem;


public class TestData {


    //*======*/
    //* User */
    //*======*/
    public static String user1Name = "UserA";
    public static String user1IC = "123456011234";
    public static String user1Phone = "012-3456789";
    public static String user1Email = "userA@gmail.com";
    public static String user1Password = "ABC123";
    public static String user1Serialized = String.format("User 6\n0\n%s\n%s\n%s\n%s\n%s", user1Name, user1IC, user1Phone, user1Email, user1Password);
    public static User user1 = new User(user1Name, user1IC, user1Phone, user1Email, user1Password);

    public static String user2Name = "UserB";
    public static String user2IC = "111111223333";
    public static String user2Phone = "011-11111111";
    public static String user2Email = "userB@gmail.com";
    public static String user2Password = "123DEF";
    public static String user2Serialized = String.format("User 6\n1\n%s\n%s\n%s\n%s\n%s", user2Name, user2IC, user2Phone, user2Email, user2Password);
    public static User user2 = new User(user2Name, user2IC, user2Phone, user2Email, user2Password);

    static {
        FundRaiserSystem.users.put( user1.getID(), user1 );
        FundRaiserSystem.users.put( user2.getID(), user2 );
    }



    //*=======*/
    //* Admin */
    //*=======*/
    public static String admin1Name = "Admin1";
    public static String admin1IC = "111111334444";
    public static String admin1Phone = "012-22222222";
    public static String admin1Email = "admin1@gmail.com";
    public static String admin1Password = "Admin123";
    public static String admin1Serialized = String.format("Admin 6\n0\n%s\n%s\n%s\n%s\n%s", admin1Name, admin1IC, admin1Phone, admin1Email, admin1Password);
    public static Admin admin1 = new Admin(admin1Name, admin1IC, admin1Phone, admin1Email, admin1Password);

    public static String admin2Name = "Admin2";
    public static String admin2IC = "444444556666";
    public static String admin2Phone = "013-33333333";
    public static String admin2Email = "admin2@gmail.com";
    public static String admin2Password = "Admin456";
    public static String admin2Serialized = String.format("Admin 6\n1\n%s\n%s\n%s\n%s\n%s", admin2Name, admin2IC, admin2Phone, admin2Email, admin2Password);
    public static Admin admin2 = new Admin(admin2Name, admin2IC, admin2Phone, admin2Email, admin2Password);

    static {
        FundRaiserSystem.admins.put( admin1.getID(), admin1 );
        FundRaiserSystem.admins.put( admin2.getID(), admin2 );
    }



    //*===============*/
    //* CategoryPool */
    //*===============*/
    public static Category category1Category = Category.EDUCATION;
    public static double category1Balance = 1234.56;
    public static String category1Serialized = String.format("Category 2\n%s\n%.2f", category1Category.name(), category1Balance);
    public static CategoryPool category1 = new CategoryPool(category1Category, category1Balance);

    public static Category category2Category = Category.DISASTER;
    public static double category2Balance = 6666.56;
    public static String category2Serialized = String.format("Category 2\n%s\n%.2f", category2Category.name(), category2Balance);
    public static CategoryPool category2 = new CategoryPool(category2Category, category2Balance);

    static {
        FundRaiserSystem.categories.put( category1.getCategory(), category1 );
        FundRaiserSystem.categories.put( category2.getCategory(), category2 );
    }



    //*==============*/
    //* Applications */
    //*==============*/
    public static String application1Description = "Help for Chee for education";
    public static double application1Amount = 1234.56;
    public static Application application1 = new Application(user1, category1, application1Description, application1Amount);
    public static ApplyStatus application1ApplyStatus = ApplyStatus.REJECTED;
    public static String application1ApplyStatusDesc = "Not qualified";
    public static String application1Serialized;

    public static String application2Description = "Help KL Flood";
    public static double application2Amount = 2222.22;
    public static Application application2 = new Application(user2, category2, application2Description, application2Amount);
    public static ApplyStatus application2ApplyStatus = ApplyStatus.TRANSACTION_SUCCESS;
    public static String application2ApplyStatusDesc = "Successful Transfer";
    public static String application2Serialized;

    static {
        FundRaiserSystem.applications.put( application1.getID(), application1);
        FundRaiserSystem.applications.put( application2.getID(), application2);

        application1.setStatus(application1ApplyStatus, application1ApplyStatusDesc, admin1);
        application2.setStatus(application2ApplyStatus, application2ApplyStatusDesc, admin2);

        application1Serialized = String.format(
            "Application 9\n%d\n%s\n%.2f\n%s\n%d\n%d\n%s\n%s\n%d",
            application1.getID(),
            application1Description,
            application1Amount,
            category1.getCategory().name(),
            user1.getID(),
            application1.getDateTime(),
            application1ApplyStatus.name(),
            application1ApplyStatusDesc,
            admin1.getID()
        );
        application2Serialized = String.format(
            "Application 9\n%d\n%s\n%.2f\n%s\n%d\n%d\n%s\n%s\n%d",
            application2.getID(),
            application2Description,
            application2Amount,
            category2.getCategory().name(),
            user2.getID(),
            application2.getDateTime(),
            application2ApplyStatus.name(),
            application2ApplyStatusDesc,
            admin2.getID()
        );
    }


    //*==============*/
    //* FPXPayment   */
    //*==============*/
    public static double fpx1Amount = 699.99;
    public static FPXPayment fpx1 = new FPXPayment(fpx1Amount);
    public static String fpx1Serialized;
    public static String fpx1Receipt;

    public static double fpx2Amount = 777.77;
    public static FPXPayment fpx2 = new FPXPayment(fpx2Amount);
    public static String fpx2Serialized;
    public static String fpx2Receipt;

    static {
        fpx1.setCardNumber("1111-1111-1111-1111");
        fpx1Receipt = String.format(
            "<<<<< FPX Transaction Receipt >>>>>\n" +
                "Card number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            fpx1.getCardNumber(),
            fpx1.getAmount(),
            new Date(fpx1.getDateTime()).toString()
        );
        fpx1Serialized = String.format("PaymentFPX 3\n%.2f\n%s\n%d", fpx1.getAmount(), fpx1.getCardNumber(), fpx1.getDateTime());

        fpx2.setCardNumber("2222-2222-2222");
        fpx2Receipt = String.format(
            "<<<<< FPX Transaction Receipt >>>>>\n" +
                "Card number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            fpx2.getCardNumber(),
            fpx2.getAmount(),
            new Date(fpx2.getDateTime()).toString()
        );
        fpx2Serialized = String.format("PaymentFPX 3\n%.2f\n%s\n%d", fpx2.getAmount(), fpx2.getCardNumber(), fpx2.getDateTime());
    }




    //*===================*/
    //* E-Wallet Payment */
    //*==================*/
    public static double eWallet1Amount = 1111.99;
    public static EWalletPayment eWallet1 = new EWalletPayment(eWallet1Amount);
    public static String eWallet1Serialized;
    public static String eWallet1Receipt;

    public static double eWallet2Amount = 2222.99;
    public static EWalletPayment eWallet2 = new EWalletPayment(eWallet2Amount);
    public static String eWallet2Serialized;
    public static String eWallet2Receipt;

    static {
        eWallet1.setPhoneNumber("011-11111111");
        eWallet1Receipt = String.format(
            "<<<<< E-Wallet Transaction Receipt >>>>>\n" +
                "Phone number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            eWallet1.getPhoneNumber(),
            eWallet1.getAmount(),
            new Date(eWallet1.getDateTime()).toString()
        );
        eWallet1Serialized = String.format("PaymentEWallet 3\n%.2f\n%s\n%d", eWallet1.getAmount(), eWallet1.getPhoneNumber(), eWallet1.getDateTime());

        eWallet2.setPhoneNumber("011-22222222");
        eWallet2Receipt = String.format(
            "<<<<< E-Wallet Transaction Receipt >>>>>\n" +
                "Phone number: %s\n" + 
                "Amount: RM%.2f\n" +
                "Time: %s",
            eWallet2.getPhoneNumber(),
            eWallet2.getAmount(),
            new Date(eWallet2.getDateTime()).toString()
        );
        eWallet2Serialized = String.format("PaymentEWallet 3\n%.2f\n%s\n%d", eWallet2.getAmount(), eWallet2.getPhoneNumber(), eWallet2.getDateTime());
    }



    //*===========*/
    //* Donation */
    //*==========*/
    public static Donation donation1 = new Donation(user1, category1, fpx1);
    public static ApplyStatus donation1Status = ApplyStatus.APPROVED_PENDING_TRANSACTION;
    public static String donation1StatusDesc = "Approved. Wait for transaction";
    public static String donation1Serialized;

    public static Donation donation2 = new Donation(user2, category2, eWallet1);
    public static ApplyStatus donation2Status = ApplyStatus.REJECTED;
    public static String donation2StatusDesc = "Invalid credentials";
    public static String donation2Serialized;
    
    static {
        FundRaiserSystem.donations.put( donation1.getID(), donation1);
        FundRaiserSystem.donations.put( donation2.getID(), donation2);

        donation1.setStatus(donation1Status, donation1StatusDesc, admin1);
        donation1Serialized = String.format(
            "Donation %d\n%d\n%s\n%s\n%s\n%s\n%s\n%s",
            6 + ( fpx1.serialize().chars().filter(ch -> ch == '\n').count() + 1 ),
            donation1.getID(),
            user1.getID(),
            category1.getCategory().name(),
            donation1.getStatus().name(),
            donation1.getStatusDescription(),
            donation1.getStatusAdmin().getID(),
            fpx1.serialize()
        );

        donation2.setStatus(donation2Status, donation2StatusDesc, admin2);
        donation2Serialized = String.format(
            "Donation %d\n%d\n%s\n%s\n%s\n%s\n%s\n%s",
            6 + ( eWallet1.serialize().chars().filter(ch -> ch == '\n').count() + 1 ),
            donation2.getID(),
            user2.getID(),
            category2.getCategory().name(),
            donation2.getStatus().name(),
            donation2.getStatusDescription(),
            donation2.getStatusAdmin().getID(),
            eWallet1.serialize()
        );
    }
}
