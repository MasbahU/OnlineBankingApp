package OnlineBankingApp;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author masbahuddin
 */
public class Login {
    public void run() {
    Scanner scan = new Scanner (new File());
    Scanner keyboard = new Scanner (System.in);
    String user = scan.nextLine();
    String pass = scan.nextLine(); 
    
    String inpUser = keyboard.nextLine();
    String inpPass = keyboard.nextLine();
    
    int count = 0
    
    System.out.println("Please enter you Login and Password");
    
    while(count < 3)
    {
        if (inpUser.equals(user) && inpPass.equals(pass)) {
            System.out.print("Login Successful");
        } else {
            System.out.print("Please Try Again");
        }
    }
    
    System.out.println("Exceeded the attempt limit, please call support");
}
    
}
