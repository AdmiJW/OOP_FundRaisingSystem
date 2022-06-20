
import source.classes.*;
import java.util.Scanner;
import source.FundRaiserSystem;

public class MenuLib {

    public static void MainMenu(){
        String[] page1 = {"Login", "Register", "Exit"};
        int choice = Util.printChoiceMenu("~~~ Fund Raising System ~~~", "", page1);
        while(true){
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
        //Load file
        FundRaiserSystem.loadUsers();
        FundRaiserSystem.loadAdmins();

        Scanner input = new Scanner(System.in);
        String username, password;

        System.out.print("Enter Username: ");
        username = input.nextLine();
        System.out.print("Enter password: ");
        password = input.nextLine();

        for (int i=0; i<FundRaiserSystem.users.size() ; i++){
            if (FundRaiserSystem.users.get(i).getName().equals(username) && FundRaiserSystem.users.get(i).comparePassword(password)){
                //UserMenu
                System.out.print("go to user page");
                break;
            }
            else{
                //return MainMenu
                
            }
        }
        for (int j=0; j<FundRaiserSystem.admins.size() ; j++){
            if (FundRaiserSystem.admins.get(j).getName().equals(username) && FundRaiserSystem.admins.get(j).comparePassword(password)) { 
                // print admin menu
                System.out.print("Go to admin page");
                break;
            }
            else{
                
            }
        }
        
            
        
    }


    public static void RegisterMenu(){
        Util.printMenu("Register for ");
        FundRaiserSystem.loadUsers();
        boolean pass1 = false;
        Scanner input = new Scanner(System.in);
        String new_Name, new_IC, new_Phone, new_Email, new_Password , confirm_Password;
        System.out.print("Enter your Username: ");
        new_Name = input.next();
        System.out.print("Enter your IC: ");
        new_IC = input.next();
        System.out.print("Enter your phone num: ");
        new_Phone = input.next();
        System.out.print("Enter your email: ");
        new_Email = input.next();
        do{
        System.out.print("Enter Your Password: ");
        new_Password = input.next();
        System.out.print("Confirm your Password: ");
        confirm_Password = input.next();
        
        if(new_Password.equals(confirm_Password)){
            User user = new User(new_Name, new_IC, new_Phone, new_Email, new_Password);
            FundRaiserSystem.users.put( user.getID(), user );
            pass1 = true;
        }
        else{
            System.out.println("Different password! Please Try again");
            pass1 = false;
        }

        }while(pass1 == false);

        FundRaiserSystem.saveUsers();
        MainMenu();


    }
    
    //User View
    public static void UserMenu(){

    }

    public static void RequestorApplication(){
        
    }

    public static void ApplicationLog(){

    }

    public static void DonorApplication(){

    }

    //Admin View
    public static void AdminMenu(){

    }

    public static void displayRequestor(int requestorIndex){

    }

}