
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
        categories.addApplication(application);
        FundRaiserSystem.saveApplications();
        Util.loadingAnimation("Saving Application in database...");
        System.out.println("Application successfully submitted!");
        Util.pressEnterToContinue();
    }

    
    //  View application status 
    public static void applicationLogReq(User user) {
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
        categories.addDonation(donation);
        FundRaiserSystem.saveDonations();

        Util.loadingAnimation("Saving Donation in database...");
        System.out.println("Donation successfully submitted!");
        Util.pressEnterToContinue();
    }


    //Admin View
    public static void AdminMenu(Admin admin){
        while(true){
            int choice = Util.printChoiceMenu("Admin View", "", new String[]{"Application list","Donation list","Exit"});
            if (choice == 1 ) approvalListApplication(admin);
            else if (choice == 2) approvalListDonation(admin);
            else return;
        }
    }


    public static void approvalListApplication(Admin admin) {
        CategoryPool categories;
        Category[] cats = Category.values();
        String[] catsName = new String[cats.length+1];
        while(true){
        for (int i = 0; i < cats.length; ++i) {
            catsName[i] = String.format("%-15s %-15.2f<!> [%d]",
             cats[i].toString(), 
             FundRaiserSystem.categories.get(cats[i]).getBalance(),
             FundRaiserSystem.categories.get(cats[i]).getNumberOfPendingApplication());
            }
            catsName[cats.length] = "Quit";
        String formatted = String.format("%-19s%-15s%-8s","Category","Capital(RM)","Pending");
        int choice = Util.printChoiceMenuNClearScreen("-----Application list-----", formatted, catsName) -1 ;
        if (choice == cats.length) return;
        categories = FundRaiserSystem.categories.get(cats[choice]);
        Application.printListHeader();
        for ( int i = 0; i <categories.getApplicationList().size(); i++) {
            categories.getApplicationList().get(i).printAsAList(i+1);
        }
        
        System.out.printf("\n%s%d%s\n","Enter ",categories.getApplicationList().size()+1," to QUIT");
        System.out.println("Enter an index to view the application details: ");
        choice = Util.getInputOfRange(1, categories.getApplicationList().size()+1) -1;
        
        if (choice == categories.getApplicationList().size() ) return;
        
        Util.clearScreen();
        ApplicationDetailControl(admin, categories.getApplicationList().get(choice), categories);
        }
    }


    public static void approvalListDonation(Admin admin){
        Category[] cats = Category.values();
        String[] catNames = new String[cats.length + 1];
        
        while(true) {
            for (int i = 0; i < cats.length; ++i)
                catNames[i] = String.format("%-15s %-15.2f<!> [%d]", 
                cats[i].toString(), 
                FundRaiserSystem.categories.get(cats[i]).getBalance(),
                FundRaiserSystem.categories.get(cats[i]).getNumberOfPendingDonation());
            
            catNames[cats.length] = "Quit";
            String formatted = String.format("%-19s%-15s%-8s","Category","Capital(RM)","Pending");
            int choice = Util.printChoiceMenuNClearScreen("-----Donation list-----", formatted, catNames) - 1;

            if (choice ==  cats.length) return;
            
            System.out.println();
            Donation.printListHeader();
            CategoryPool cat = FundRaiserSystem.categories.get( cats[choice] );
            for (int i = 0; i < cat.getDonationList().size(); i++){
                cat.getDonationList().get(i).printAsAList(i+1);
            }
            System.out.printf("\n%s%d%s\n","Enter ",cat.getDonationList().size()+1," to QUIT");
            System.out.println("Enter an index to view the applicaiton details: ");
            choice = Util.getInputOfRange(1, cat.getDonationList().size()+1) -1;
            if (choice == cat.getDonationList().size() ) return;
        

            Util.clearScreen();
            donationDetailControl(admin,cat.getDonationList().get(choice));
        }
    }

    public static void donationDetailControl(Admin admin, Donation donation) {
        
        Scanner input = new Scanner(System.in);
        System.out.printf("Category %s Balance: RM%.2f\n\n", donation.getCategory().getCategory(), donation.getCategory().getBalance() );
        donation.printDetails();
        String description;
        int choice;
        if (donation.getStatus().equals(ApplyStatus.PENDING_VERIFICATION)){
            choice = Util.printChoiceMenuNClearScreen("", "Select your operation:", new String[]{"Approve","Reject","Back"});
            if (choice == 1){
                System.out.print("Enter description: ");
                description = input.nextLine();
                donation.setStatus(ApplyStatus.APPROVED_PENDING_TRANSACTION, description, admin);
                Util.pressEnterToContinue();
            }
            else if(choice == 2){
                System.out.print("Enter description: ");
                description = input.nextLine();
                donation.setStatus(ApplyStatus.REJECTED, description, admin);
                Util.pressEnterToContinue();
            }
            else return;     
        }
        
        else if (donation.getStatus().equals(ApplyStatus.APPROVED_PENDING_TRANSACTION)){
            choice = Util.printChoiceMenuNClearScreen("", "Select your operation:", new String[]{"Make transaction","Back"});
            if (choice == 2) return;
            
            System.out.print("Enter description: ");
            description = input.nextLine();
            donation.setStatus(ApplyStatus.TRANSACTION_SUCCESS, description, admin);
            donation.getCategory().setBalance(donation.getCategory().getBalance() + donation.getPayment().getAmount());
            Util.pressEnterToContinue();
        }
        
        else if (donation.getStatus().equals(ApplyStatus.REJECTED)){
            Util.pressEnterToContinue();
        }

        else {
            Util.pressEnterToContinue();
        }


        FundRaiserSystem.saveCategory();
        FundRaiserSystem.saveDonations();
        Util.pressEnterToContinue();
    }
    
    public static void ApplicationDetailControl(Admin admin, Application application, CategoryPool categories) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Category %s Balance: RM%.2f\n\n", categories.getCategory(), categories.getBalance());
        application.printDetails();
        int choice;
        String description;
        
        if (application.getStatus().equals(ApplyStatus.PENDING_VERIFICATION)){
            
            choice = Util.printChoiceMenuNClearScreen("", "Select your operation", new String[]{"Approve", "Reject", "Back"});
            if (choice == 1){
                System.out.print("Enter description: ");
                description = input.nextLine();
                application.setStatus(ApplyStatus.APPROVED_PENDING_TRANSACTION, description, admin);
                Util.pressEnterToContinue();

            }
            else if (choice == 2){
                System.out.print("Enter description: ");
                description = input.nextLine();
                application.setStatus(ApplyStatus.REJECTED, description, admin);
                Util.pressEnterToContinue();
            }
            else return;
        }
        else if (application.getStatus().equals(ApplyStatus.APPROVED_PENDING_TRANSACTION)){
            choice = Util.printChoiceMenuNClearScreen("", "Select your operation", new String[]{"Make Transaction", "Back"});
            if (choice == 2) return;
            
            if(categories.getBalance() >= application.getRequestAmount()){
                System.out.print("Enter description: ");
                description = input.nextLine();
                categories.setBalance(categories.getBalance()-application.getRequestAmount());
                application.setStatus(ApplyStatus.TRANSACTION_SUCCESS, description, admin);
                System.out.println("Successful ");
                Util.pressEnterToContinue();
            }
            else {
                System.out.println("Capital insufficient...sorry...");
                Util.loadingAnimation("Returning to previous page");
                return;
            }
            
        }
        else if (application.getStatus().equals(ApplyStatus.REJECTED)){
            Util.pressEnterToContinue();
            return ;
        }
        else {
            Util.pressEnterToContinue();
            return ;
        }
        FundRaiserSystem.saveApplications();
        FundRaiserSystem.saveCategory();
        Util.pressEnterToContinue();
        return;
    }

    
}