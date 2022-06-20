
import source.classes.*;
import java.util.Scanner;
import source.FundRaiserSystem;

public class MenuLib {

    public static void MainMenu(){
        String[] page1 = {"Login", "Register", "Exit"};
        
        while(true){
            int choice = Util.printChoiceMenu("~~~ Fund Raising System ~~~", "", page1);
            switch(choice){
                case 1:
                    LoginMenu();
                    break;
                case 2:
                    RegisterMenu();
                    break;
                case 3:
                    System.out.println("Thank you");
                    System.exit(0);
            }
        }
    }

    public static void LoginMenu(){
        //Display header
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

        //Check user type
        if (user != null){
            if(user.comparePassword(password)){
                //UserMenu

                Util.loadingAnimation("User logging in");
                UserMenu();
            }
        }
        else if (admin != null){
            if(admin.comparePassword(password)){
                //AdminMenu

                Util.loadingAnimation("Admin logging in");
                AdminMenu();
            }
        }
        else{
            Util.loadingAnimation("Searching database...");
            System.out.println("Error: Account Not Found");
            Util.pressEnterToContinue();
            return ;
        }
    }


    public static void RegisterMenu(){
        Util.printMenu("Register user");
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Your Username: ");
        String new_Name = input.nextLine();
        System.out.print("Enter Your IC: ");
        String new_IC = input.nextLine();
        System.out.print("Enter Your Phone Number: ");
        String new_Phone = input.nextLine();
        System.out.print("Enter Your Email: ");
        String new_Email = input.nextLine();
        String new_Password,confirm_Password;
        do{
            System.out.print("Enter Your Password: ");
            new_Password = input.nextLine();
            System.out.print("Confirm Your Password: ");
            confirm_Password = input.nextLine();
            if(!new_Password.equals(confirm_Password)){ 
                if (Util.printChoiceMenu("", "Those passwords didn't match. Try again?",new String[] {"Yes","No"}) == 2){
                    return;
                }
            }
        }while(!new_Password.equals(confirm_Password));

        User user = new User(new_Name, new_IC, new_Phone, new_Email, new_Password);
        FundRaiserSystem.users.put(user.getID(), user);
        FundRaiserSystem.saveUsers();
        Util.loadingAnimation("Registering in database");
        System.out.println("Account succcessfully registered");
        Util.pressEnterToContinue();
        MainMenu();
    }
    
    //User View
    public static void UserMenu(){
        while(true){
            int choice = Util.printChoiceMenu("User Menu", "", 
            new String[] {"Apply new application",
                            "View application status",
                            "Apply donor applicaiton",
                            "View donation log",
                            "Log out",});
            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    return;
            }
        }
    }
    //  Apply new application
    public static void RequestorApplication(){
        Util.printMenu("Requestor Application");
        Util.pressEnterToContinue();
        return;
    }
    //  View application status 
    //  View donation log
    public static void ApplicationLog(){
        Util.pressEnterToContinue();
        return;
    }
    //  Apply donor applicaition    
    public static void DonorApplication(){
        Util.pressEnterToContinue();
        return;
    }


    //Admin View
    public static void AdminMenu(){
        Util.pressEnterToContinue();
        return;
    }

    public static void displayRequestor(int requestorIndex){
        Util.pressEnterToContinue();
        return;
    }

}