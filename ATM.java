/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineBankingApp.newpackage;

import java.util.Scanner;

/**
 *
 * @author masbahuddin
 */
public class ATM {
    
    public static void main(String []args)
    {
        Scanner kbd =  new Scanner(System.in);
        
        Bank theBank= new Bank("Discover");
        
        User addUser = theBank.addUser("Abrar", "Patel", "1234");
        
        Account newAccount = new Account("Checking", addUser, theBank);
        
        addUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
        
        User curUser;
        
        while(true)
        {
            curUser = ATM.mainMenuPrompt(theBank, kbd);
            ATM.printUserMenu(curUser,kbd);
        }
    }   
    
    public static User mainMenuPrompt(Bank theBank, Scanner kbd)
    {
        String userID;
        String pin;
        User auth;
        
        System.out.printf("\n\nWelcome to %s \n\n", theBank.getName());
        
        do
        {
            System.out.println("Enter your UserID: ");
            userID = kbd.nextLine();
            
            System.out.println("Enter your Pin: ");
            pin = kbd.nextLine();
            
            auth = theBank.userlogin(userID, pin);
            if(auth == null)
                System.out.println("Failed Login " + "Please try again");
        }
        while(auth == null);
       
        return auth;
    }
    
    public static void printUserMenu(User curUser, Scanner kbd)
    {
        curUser.printAccountSummary();
        
        int option;
        
        System.out.printf("Welcome %s, what would you like to do?", curUser.getFirstName());
        System.out.println("");
        
        do
        {
            System.out.println("Select an Option");
            System.out.println("1. Show Transaction History");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Exit");
            System.out.println();
            option = kbd.nextInt();
            
            if (option < 1 || option > 5)
                System.out.println("Invalid Entry, Please Try Again");
        }
        while(option < 1 || option > 5);
        
        switch(option)
        {
            case 1:
                ATM.showTransactions(curUser, kbd);
                break;
            
            case 2:
                ATM.depositMoney(curUser, kbd);
                break;
            
            case 3:
                ATM.withdrawMoney(curUser, kbd);
                break;
                
            case 4:
               ATM.transferMoney(curUser, kbd);
               break;
        }
        
        if(option != 5)
            ATM.printUserMenu(curUser, kbd);
    }
    
    public static void showTransactions(User user, Scanner kbd)
    {
        int acc;
        
        do
        {
            System.out.printf("Enter the Number (1-%d) of the Account\n", user.numAccounts());
            
            acc = kbd.nextInt()-1;
            
            if(acc < 0 || acc >= user.numAccounts())
                System.out.println("Invalid Account, Please Try Again");
        }
        while(acc < 0 || acc >= user.numAccounts());
        
        user.printTransActionHistory(acc);
    }
    
    public static void transferMoney(User user, Scanner kbd)
    {
        int toAcc;
        int fromAcc;
        double requestBal;
        double accBal;
        
        do
        {
            System.out.printf("Enter the Number (1-%d) of the Account\n", user.numAccounts());
            fromAcc = kbd.nextInt()-1;
            
            if(fromAcc < 0 || fromAcc >= user.numAccounts())
                System.out.println("Invalid Account, Please Try Again");
        }
        while(fromAcc < 0 || fromAcc >= user.numAccounts());
        
        accBal = user.getAccountBalance(fromAcc);
        
        do
        {
            System.out.printf("Enter the Number (1-%d) of the Account\n", user.numAccounts());
            toAcc = kbd.nextInt()-1;
            
            if(toAcc < 0 || toAcc >= user.numAccounts())
                System.out.println("Invalid Account, Please Try Again");
        }
        while(toAcc < 0 || toAcc >= user.numAccounts());
        
        do
        {
            System.out.printf("Enter the amount you want to transfer (max $%.02f: $)", accBal);
            requestBal = kbd.nextInt();
            
            if(requestBal < 0)
                System.out.println("Amount must be greater then zero");
            else if (requestBal > accBal)
                System.out.printf("Amount exceeds account balance \n" +
                        "balance of $%.02f",accBal);
        }
        while(requestBal < 0 || requestBal > accBal);
        
        user.addAccountTransaction(fromAcc, -1*requestBal, String.format
            ("Transfer to Account %s", user.getAccUID(toAcc)));
        
        user.addAccountTransaction(toAcc, requestBal, String.format
            ("Transfer to Account %s", user.getAccUID(fromAcc)));
    }
    
    public static void depositMoney(User user, Scanner kbd)
    {
        String memo;
        int toAcc;
        double requestBal;        
        do
        {
            System.out.printf("Enter the Number (1-%d) of the Account\n", user.numAccounts());
            toAcc = kbd.nextInt()-1;
            
            if(toAcc < 0 || toAcc >= user.numAccounts())
                System.out.println("Invalid Account, Please Try Again");
        }
        while(toAcc < 0 || toAcc >= user.numAccounts());
                
        do
        {
            System.out.printf("Enter the amount you want to deposit: $");
            requestBal = kbd.nextInt();
            
            if(requestBal < 0)
                System.out.println("Amount must be greater then zero");
        }
        while(requestBal < 0);
        
        kbd.nextLine();
        
        System.out.print("Enter a memo: ");
        memo = kbd.nextLine();
        
        user.addAccountTransaction(toAcc, requestBal, memo);
    }
    
    public static void withdrawMoney(User user, Scanner kbd)
    {
        String memo;
        int fromAcc;
        double requestBal;
        double accBal;
        
        do
        {
            System.out.printf("Enter the Number (1-%d) of the Account\n", user.numAccounts());
            fromAcc = kbd.nextInt()-1;
            
            if(fromAcc < 0 || fromAcc >= user.numAccounts())
                System.out.println("Invalid Account, Please Try Again");
        }
        while(fromAcc < 0 || fromAcc >= user.numAccounts());
        
        accBal = user.getAccountBalance(fromAcc);
        
        do
        {
            System.out.printf("Enter the amount you want to withdraw (max $%.02f: $)", accBal);
            requestBal = kbd.nextInt();
            
            if(requestBal < 0)
                System.out.println("Amount must be greater then zero");
            else if (requestBal > accBal)
                System.out.printf("Amount exceeds account balance \n" +
                        "balance of $%.02f",accBal);
        }
        while(requestBal < 0 || requestBal > accBal);
        
        kbd.nextLine();
        
        System.out.print("Enter a memo");
        memo = kbd.nextLine();
        
        user.addAccountTransaction(fromAcc, -1*accBal, memo);
    }
    
}
