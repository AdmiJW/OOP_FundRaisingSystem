import java.util.Arrays;

import source.classes.*;
import source.abstracts.AbstractPayment;

import source.classes.Util;

public class Test1 {
    static int testcase = 1;


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
        String data1 = TestData.user1.serialize();
        Util.assertTrue(data1.equals(TestData.user1Serialized), "test_UserClass() - Problem in serialize() of User", testcase++);
        String data2 = TestData.user2.serialize();
        Util.assertTrue(data2.equals(TestData.user2Serialized), "test_UserClass() - Problem in serialize() of User", testcase++);


        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        User reconstructed1 = User.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        User reconstructed2 = User.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.user1Serialized), "test_UserClass() - Problem in deserialize() of User", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.user2Serialized), "test_UserClass() - Problem in deserialize() of User", testcase++);
        System.out.println();
    }



    private static void test_AdminClass() {
        String data1 = TestData.admin1.serialize();
        String data2 = TestData.admin2.serialize();
        Util.assertTrue(data1.equals(TestData.admin1Serialized), "test_AdminClass() - Problem in serialize() of Admin", testcase++);
        Util.assertTrue(data2.equals(TestData.admin2Serialized), "test_AdminClass() - Problem in serialize() of Admin", testcase++);

        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Admin reconstructed1 = Admin.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Admin reconstructed2 = Admin.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.admin1Serialized), "test_AdminClass() - Problem in deserialize() of Admin", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.admin2Serialized), "test_AdminClass() - Problem in deserialize() of Admin", testcase++);
        System.out.println();
    }



    private static void test_CategoryPoolClass() {
        String data1 = TestData.category1.serialize();
        String data2 = TestData.category2.serialize();
        Util.assertTrue(data1.equals(TestData.category1Serialized), "test_CategoryPoolClass() - Problem in serialize() of CategoryPool", testcase++);
        Util.assertTrue(data2.equals(TestData.category2Serialized), "test_CategoryPoolClass() - Problem in serialize() of CategoryPool", testcase++);

        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        CategoryPool reconstructed1 = CategoryPool.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        CategoryPool reconstructed2 = CategoryPool.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.category1Serialized), "test_CategoryPoolClass() - Problem in deserialize() of CategoryPool", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.category2Serialized), "test_CategoryPoolClass() - Problem in deserialize() of CategoryPool", testcase++);
        System.out.println();
    }


    private static void test_ApplicationClass() {
        String data1 = TestData.application1.serialize();
        String data2 = TestData.application2.serialize();
        Util.assertTrue(data1.equals(TestData.application1Serialized), "test_ApplicationClass() - Problem in serialize() of Application", testcase++);
        Util.assertTrue(data2.equals(TestData.application2Serialized), "test_ApplicationClass() - Problem in serialize() of Application", testcase++);

        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Application reconstructed1 = Application.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Application reconstructed2 = Application.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.application1Serialized), "test_ApplicationClass() - Problem in deserialize() of Application", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.application2Serialized), "test_ApplicationClass() - Problem in deserialize() of Application", testcase++);
        System.out.println();
    }


    private static void test_FPXPaymentClass() {
        String data1 = TestData.fpx1.serialize();
        String data2 = TestData.fpx2.serialize();
        Util.assertTrue(data1.equals(TestData.fpx1Serialized), "test_FPXPaymentClass() - Problem in serialize() of FPXPayment", testcase++);
        Util.assertTrue(data2.equals(TestData.fpx2Serialized), "test_FPXPaymentClass() - Problem in serialize() of FPXPayment", testcase++);

        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        AbstractPayment reconstructed1 = FPXPayment.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        AbstractPayment reconstructed2 = FPXPayment.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.fpx1Serialized), "test_FPXPaymentClass() - Problem in deserialize() of FPXPayment", testcase++);
        Util.assertTrue(reconstructed1.getReceipt().equals(TestData.fpx1Receipt), "test_FPXPaymentClass() - Problem in getReceipt() of FPXPayment", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.fpx2Serialized), "test_FPXPaymentClass() - Problem in deserialize() of FPXPayment", testcase++);
        Util.assertTrue(reconstructed2.getReceipt().equals(TestData.fpx2Receipt), "test_FPXPaymentClass() - Problem in getReceipt() of FPXPayment", testcase++);
        System.out.println();
    }


    private static void test_EwalletPaymentClass() {
        String data1 = TestData.eWallet1.serialize();
        String data2 = TestData.eWallet2.serialize();
        Util.assertTrue(data1.equals(TestData.eWallet1Serialized), "test_EwalletPaymentClass() - Problem in serialize() of EWalletPayment", testcase++);
        Util.assertTrue(data2.equals(TestData.eWallet2Serialized), "test_EwalletPaymentClass() - Problem in serialize() of EWalletPayment", testcase++);

        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        AbstractPayment reconstructed1 = EWalletPayment.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        AbstractPayment reconstructed2 = EWalletPayment.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.eWallet1Serialized), "test_EwalletPaymentClass() - Problem in deserialize() of EWalletPayment", testcase++);
        Util.assertTrue(reconstructed1.getReceipt().equals(TestData.eWallet1Receipt), "test_EwalletPaymentClass() - Problem in getReceipt() of EWalletPayment", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.eWallet2Serialized), "test_EwalletPaymentClass() - Problem in deserialize() of EWalletPayment", testcase++);
        Util.assertTrue(reconstructed2.getReceipt().equals(TestData.eWallet2Receipt), "test_EwalletPaymentClass() - Problem in getReceipt() of EWalletPayment", testcase++);
        System.out.println();
    }


    private static void test_DonationClass() {
        String data1 = TestData.donation1.serialize();
        String data2 = TestData.donation2.serialize();
        Util.assertTrue(data1.equals(TestData.donation1Serialized), "test_DonationClass() - Problem in serialize() of Donation", testcase++);
        Util.assertTrue(data2.equals(TestData.donation2Serialized), "test_DonationClass() - Problem in serialize() of Donation", testcase++);

        String[] rawargs = data1.split("\n");
        String[] args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Donation reconstructed1 = Donation.deserialize(args);
        rawargs = data2.split("\n");
        args = Arrays.copyOfRange(rawargs, 1, rawargs.length);
        Donation reconstructed2 = Donation.deserialize(args);
        
        Util.assertTrue(reconstructed1.serialize().equals(TestData.donation1Serialized), "test_DonationClass() - Problem in deserialize() of Donation", testcase++);
        Util.assertTrue(reconstructed2.serialize().equals(TestData.donation2Serialized), "test_DonationClass() - Problem in deserialize() of Donation", testcase++);
        System.out.println();
    }
}
