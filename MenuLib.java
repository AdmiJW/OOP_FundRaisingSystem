
import source.classes.*;
import source.abstracts.*;
import source.enums.ApplyStatus;
import source.enums.Category;

import java.util.Scanner;
import source.FundRaiserSystem;

public class MenuLib {

    public static void MainMenu(){
        String[] page1 = {"Login", "Register", "Exit"};
        
        while (true) {
            int choice = Util.printChoiceMenu("~~~ Fund Raising System ~~~", "", page1);
            switch(choice) {
                case 1:
                    LoginMenu();
                    break;
                case 2:
                    RegisterMenu();
                    break;
                case 3:
                    Util.loadingAnimation("Thank you! Shutting down...");
                    System.exit(0);
            }
        }
    }

    public static void LoginMenu(){
        Util.printMenu("Log in");

        Scanner input = new Scanner(System.in);
        String username, password;

        //Input login info
        System.out.print("Enter Username: ");
        username = input.nextLine();
        System.out.print("Enter password: ");
        password = input.nextLine();
        
        User user = FundRaiserSystem.getUserByName(username);
        Admin admin = FundRaiserSystem.getAdminByName(username);

        //Check user type (if exists)
        if (user != null) {
            if (user.comparePassword(password)) {
                Util.loadingAnimation("User logging in");
                UserMenu(user);
            } else {
                System.out.println("Error: Incorrect password.");
                Util.pressEnterToContinue();
            }
        }
        else if (admin != null) {
            if (admin.comparePassword(password)){
                Util.loadingAnimation("Admin logging in");
                AdminMenu(admin);
            } else {
                System.out.println("Error: Incorrect password.");
                Util.pressEnterToContinue();
            }
        }
        else {
            Util.loadingAnimation("Searching database...");
            System.out.println("Error: Account Not Found");
            Util.pressEnterToContinue();
        }
    }

    public static void RegisterMenu(){
        Scanner input = new Scanner(System.in);
        Util.printMenu("Register user");

        System.out.print("Enter Your Username: ");
        String new_Name = input.nextLine();
        System.out.print("Enter Your IC: ");
        String new_IC = input.nextLine();
        System.out.print("Enter Your Phone Number: ");
        String new_Phone = input.nextLine();
        System.out.print("Enter Your Email: ");
        String new_Email = input.nextLine();
        String new_Password,confirm_Password;
        
        do {
            System.out.print("Enter Your Password: ");
            new_Password = input.nextLine();
            System.out.print("Confirm Your Password: ");
            confirm_Password = input.nextLine();
            if (
                !new_Password.equals(confirm_Password) &&
                Util.printChoiceMenu("", "Those passwords didn't match. Try again?",new String[] {"Yes","No"}) == 2
            ) return;
        } while (!new_Password.equals(confirm_Password));

        User user = new User(new_Name, new_IC, new_Phone, new_Email, new_Password);
        FundRaiserSystem.users.put(user.getID(), user);
        FundRaiserSystem.saveUsers();
        Util.loadingAnimation("Registering in database");
        System.out.println("Account succcessfully registered. Please proceed to Login.");
        Util.pressEnterToContinue();
    }
    
    //User View
    public static void UserMenu(User user){
        while (true) {
            int choice = Util.printChoiceMenu("User Menu", "", 
                new String[] {
                    "Apply new application",
                    "View application status",
                    "Apply donor applicaiton",
                    "View donation log",
                    "Log out",
                }
            );

            if (choice == 1) RequestorApplication(user);
            else if (choice == 2) applicationLogReq(user);
            else if (choice == 3) DonorApplication(user);
            else if (choice == 4) donationLog(user);
            else return;
        }
    }


    //  Apply new application
    public static void RequestorApplication(User user){
        Scanner input = new Scanner(System.in);
        double requestAmount;
        String description;
        CategoryPool categories;
        Category[] cats = Category.values();
        String[] catNames = new String[cats.length];

        // Get list of names of the available categories
        for (int i = 0; i < cats.length; ++i) catNames[i] = cats[i].toString();

        Util.printMenu(" ~~~ Requestor Application ~~~ ");
        int choice = Util.printChoiceMenu("Requestor Application", "Select category", catNames);
        System.out.println(user.getPersonalDetails());
        System.out.println("Category: " + cats[choice-1]);
        System.out.print("Enter request Amount: RM");
        requestAmount = input.nextDouble();
        input.nextLine();
        System.out.print("Enter Description:");
        description = input.nextLine();

        categories = FundRaiserSystem.categories.get(cats[choice-1]);
        Application application = new Application(user, categories, description, requestAmount);
        FundRaiserSystem.applications.put(application.getID(), application);
        user.getApplications().add(application);
        FundRaiserSystem.saveApplications();
        Util.loadingAnimation("Saving Application in database...");
        System.out.println("Application successfully submitted!");
        Util.pressEnterToContinue();
    }

    
    //  View application status 
    public static void applicationLogReq(User user){
        Util.clearScreen();
        Util.printMenu(" ~~~ Application Log Requestor ~~~ ");
        
        Application.printListHeader();
        for ( int i = 0; i <user.getApplications().size(); i++) {
            user.getApplications().get(i).printAsAList(i+1);
        }
        
        System.out.printf("\n%s%d%s\n","Enter ",user.getApplications().size()+1," to QUIT");
        System.out.println("Enter an index to view the applicaiton details: ");
        int choice = Util.getInputOfRange(1, user.getApplications().size()+1) -1;

        if (choice == user.getApplications().size() ) return;

        Util.clearScreen();
        user.getApplications().get(choice).printDetails();
        System.out.println();
        
        Util.pressEnterToContinue();
    }


    //  View donation log
    public static void donationLog(User user){
        Util.clearScreen();
        Util.printMenu(" ~~~ Donation Log Donor ~~~ ");
        
        Donation.printListHeader();
        for ( int i = 0; i <user.getDonations().size(); i++) {
            user.getDonations().get(i).printAsAList(i+1);
        }
        System.out.printf("\n%s%d%s\n","Enter ",user.getDonations().size()+1," to QUIT");
        System.out.println("Enter an index to view the donation details: ");
        int choice = Util.getInputOfRange(1, user.getDonations().size()+1)-1;

        if (choice == user.getDonations().size() ) return;

        Util.clearScreen();
        user.getDonations().get(choice).printDetails();
        System.out.println();
        
        Util.pressEnterToContinue();
    }
    
    
    //  Apply donor applicaition    
    public static void DonorApplication(User user){
        Scanner input = new Scanner(System.in);
        CategoryPool categories;
        Category[] cats = Category.values();
        String[] catNames = new String[cats.length];

        // Get list of names of the available categories
        for (int i = 0; i < cats.length; ++i) catNames[i] = cats[i].toString();

        Util.printMenu(" ~~~ Donor Application ~~~ ");
        int choice = Util.printChoiceMenu("Donor Application", "Select category", catNames);
        System.out.println(user.getPersonalDetails());
        System.out.println("Category: " + cats[choice]);
        categories = FundRaiserSystem.categories.get(cats[choice]);
        System.out.println();
        
        AbstractPayment payment;
        double donationAmount =0;

        choice = Util.printChoiceMenu("", "Select payment type", new String[] {"FPXPayment","EWalletPayment"});
        System.out.print("Enter amount to be donated: RM");
        donationAmount= input.nextDouble();
        input.nextLine();
        if (choice == 1) payment = new FPXPayment(donationAmount);
        else payment = new EWalletPayment(donationAmount);
        
        payment.makePayment();

        Donation donation = new Donation(user, categories, payment);
        
        //Compulsory saving
        FundRaiserSystem.donations.put(donation.getID(), donation);
        user.getDonations().add(donation);
        FundRaiserSystem.saveDonations();

        Util.loadingAnimation("Saving Donation in database...");
        System.out.println("Donation successfully submitted!");
        Util.pressEnterToContinue();
    }


    //Admin View
    public static void AdminMenu(Admin admin){
        Util.printMenu("Admin view");

        ApplyStatus[] status = ApplyStatus.values();
        String[] statusName = new String(ApplyStatus.length);
        for (int i = 0; i < ApplyStatus.length; ++i) statusName[i] = status[i].toString();

        FundRaiserSystem.applications.get(requestorIndex).printDetails();
         
        Util.pressEnterToContinue();
        return;
    }

}