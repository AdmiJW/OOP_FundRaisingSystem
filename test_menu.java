
import source.classes.*;
import java.util.Scanner;
import source.FundRaiserSystem;

public class test_menu {

    public static void menu(){
        String[] page1 = {"Login", "Register"};
        int choice = Util.printChoiceMenu("Fund Raising System", "", page1);
        switch(choice){
            case 1:
            Login();
               break;
            case 2:
                Register();
        }

    }
    

    public static void Login(){
        FundRaiserSystem.loadUsers();
        FundRaiserSystem.loadAdmins();
        boolean pass1 = false, pass2 = false;
        Scanner input = new Scanner(System.in);
        String username, password;
        do{
        System.out.print("Enter Username: ");
        username = input.next();
        System.out.print("Enter password: ");
        password = input.next();

        for (int i=0; i<FundRaiserSystem.users.size() ; i++){
        if (FundRaiserSystem.users.get(i).getName().equals(username) && FundRaiserSystem.users.get(i).comparePassword(password)){
            //print user menu
            System.out.print("go to user page");
            pass1 = true;
            break;
        }
        else{
            //Try again username & password;
            pass1 = false;
            
        }
    }
        for (int j=0; j<FundRaiserSystem.admins.size() ; j++){
        if (FundRaiserSystem.admins.get(j).getName().equals(username) && FundRaiserSystem.admins.get(j).comparePassword(password)) { 
            // print admin menu
            System.out.print("Go to admin page");
            pass2 = true;
            break;
        }
        else{
            pass2 = false;
        }
    }
    
    if(pass1 == false && pass2 == false){
        
        System.out.println("Sorry, Your username or password is wrong");
        Util.pressEnterToContinue();
        Util.clearScreen();
    }
        
    }while(pass1 == false && pass2 == false);
    }


public static void Register(){
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
    menu();


}

public static void main(String[] args){
    
    menu();
}
}



